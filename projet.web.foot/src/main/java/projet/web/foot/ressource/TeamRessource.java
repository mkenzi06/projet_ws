package projet.web.foot.ressource;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.json.JSONArray;
import org.json.JSONException;

import projet.web.foot.Modele.Team;
import projet.web.foot.Modele.Player;
import projet.web.foot.service.PlayerService;
import projet.web.foot.service.TeamService;
import projet.web.foot.Modele.*;
@Path("/teams")
public class TeamRessource {
	TeamService service = new TeamService();
	PlayerService s = new PlayerService(service);
	ExternalApiCalls externe = new ExternalApiCalls();
    @Context
    UriInfo uriInfo;
  //remarque le post pour l'equipe a seulement ete implementer avant de savoir qu'on devait utilises JAX_WS
    /**
     * Endpoint pour ajouter une nouvelle équipe.
     * Prend en charge les requêtes POST avec les données de l'équipe au format XML dans le corps de la requête.
     * Renvoie une réponse avec un code de statut approprié et les données de l'équipe ajoutée au format XML.
     *
     * @param team Les données de la nouvelle équipe.
     * @return Une réponse HTTP indiquant le résultat de l'opération.
     *         - Si l'équipe est ajoutée avec succès, renvoie une réponse avec un code de statut 201 Created et les données de l'équipe ajoutée.
     *         - Si l'ajout de l'équipe échoue, renvoie une réponse avec un code de statut 400 Bad Request.
     * @throws ClassNotFoundException Si une erreur se produit lors de la gestion de la base de données.
     */
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response addTeam(Team team) throws ClassNotFoundException {
        Team addedTeam = service.addTeamToDatabase(team);
        if (addedTeam == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(addedTeam.getId())).build();
        return Response.created(uri).entity(addedTeam).build();
    }
    /**
     * Endpoint pour supprimer une équipe par son nom.
     * Prend en charge les requêtes DELETE avec le nom de l'équipe dans l'URL.
     * Renvoie une réponse avec un code de statut approprié indiquant le résultat de l'opération.
     *
     * @param teamName Le nom de l'équipe à supprimer.
     * @return Une réponse HTTP indiquant le résultat de l'opération.
     *         - Si l'équipe est supprimée avec succès, renvoie une réponse avec un code de statut 200 OK.
     *         - Si l'équipe n'est pas trouvée, renvoie une réponse avec un code de statut 404 Not Found.
     * @throws ClassNotFoundException Si une erreur se produit lors de la gestion de la base de données.
     */
    @DELETE
    @Path("/{teamName}")
    @Produces(MediaType.APPLICATION_XML)
    public Response deleteTeamByName(@PathParam("teamName") String teamName) throws ClassNotFoundException {
        int id =s.getTeamIdByName(teamName); // Recherche de l'ID de l'équipe par son nom
        if (id == -1) {
            // Si aucune equipe existe avec ce nom renvoyer un statut 404
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (!service.deleteTeamFromDatabase(id)) {
            // Si la suppression échoue pour une autre raison
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        
        // Si la suppression réussit
        return Response.ok().build();
    }

    /**
     * Endpoint pour récupérer toutes les équipes.
     * Prend en charge les requêtes GET.
     * Renvoie une liste de toutes les équipes au format XML.
     *
     * @return Une liste de toutes les équipes.
     * @throws ClassNotFoundException Si une erreur se produit lors de la gestion de la base de données.
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Team> getAllTeams() throws ClassNotFoundException {
        return service.getAllTeamsFromDatabase();
    }
    /**
     * Endpoint pour mettre à jour l'entraîneur d'une équipe.
     * Prend en charge les requêtes PUT avec le nom de l'équipe dans l'URL et les nouvelles données de l'équipe au format XML dans le corps de la requête.
     * Renvoie une réponse avec un code de statut approprié indiquant le résultat de l'opération.
     *
     * @param teamName    Le nom de l'équipe à mettre à jour.
     * @param updatedTeam Les nouvelles données de l'équipe.
     * @return Une réponse HTTP indiquant le résultat de l'opération.
     *         - Si la mise à jour est réussie, renvoie une réponse avec un code de statut 200 OK.
     *         - Si l'équipe ou le coach n'est pas trouvée, renvoie une réponse avec un code de statut 404 Not Found.
     * @throws ClassNotFoundException Si une erreur se produit lors de la gestion de la base de données.
     */
    @PUT
    @Path("/{teamName}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response updateTeam(@PathParam("teamName") String teamName, Team updatedTeam) throws ClassNotFoundException {
        // Récupérer l'ID de l'équipe en fonction de son nom
        int teamId = s.getTeamIdByName(teamName);

        
        if (teamId != -1) {
 
            if (service.updateTeamCoachInDatabase(teamId, updatedTeam)) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la mise à jour de l'équipe").build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Équipe ou coach non trouvés").build();
        }
    }
    /**
     * Endpoint pour récupérer les joueurs d'une équipe.
     * Prend en charge les requêtes GET avec le nom de l'équipe dans l'URL.
     * Renvoie une liste des joueurs de l'équipe au format JSON.
     *
     * @param team_name Le nom de l'équipe.
     * @return Une liste des joueurs de l'équipe au format JSON.
     * @throws ClassNotFoundException Si une erreur se produit lors de la gestion de la base de données.
     */
    @GET
    @Path("/{team_name}/player")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Player> getPlayersOfTeam(@PathParam("team_name") String team_name) throws ClassNotFoundException {
        
    	int id = s.getTeamIdByName(team_name);
        return service.getPlayersOfTeam(id);
    
    }
    /**
     * Endpoint pour récupérer les équipes de la Liga en utilisant l'appel a une API Externe.
     * Prend en charge les requêtes GET.
     * Renvoie une liste des équipes de la Liga au format JSON.
     *
     * @return Une liste des équipes de la Liga au format JSON.
     * @throws JSONException Si une erreur se produit lors de la gestion des données JSON.
     */
    @GET
    @Path("/liga")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLaLigaTeams() throws JSONException {
    	String teamsJson = externe.getTeamsLiga();
        if (teamsJson != null && !teamsJson.isEmpty()) {
            return Response.ok(teamsJson).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Impossible de récupérer les données des équipes de la Liga").build();
        }
    }
    @GET
    @Path("/bundesliga")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLaLigaBunde() throws JSONException {
    	String teamsJson = externe.getTeamsBundes();
        if (teamsJson != null && !teamsJson.isEmpty()) {
            return Response.ok(teamsJson).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Impossible de récupérer les données des équipes de la Liga").build();
        }
    }
    @GET
    @Path("/seriea")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getserieaTeams() throws JSONException {
    	String teamsJson = externe.getTeamsSeria();
        if (teamsJson != null && !teamsJson.isEmpty()) {
            return Response.ok(teamsJson).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Impossible de récupérer les données des équipes de la Liga").build();
        }
    }
    @GET
    @Path("/ligue1")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLaLigueTeams() throws JSONException {
    	String teamsJson = externe.getTeamsLigue1();
        if (teamsJson != null && !teamsJson.isEmpty()) {
            return Response.ok(teamsJson).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Impossible de récupérer les données des équipes de la Liga").build();
        }
    }
    @GET
    @Path("/premierleague")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlTeams() throws JSONException {
    	String teamsJson = externe.getTeamsPL();
        if (teamsJson != null && !teamsJson.isEmpty()) {
            return Response.ok(teamsJson).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Impossible de récupérer les données des équipes de la Liga").build();
        }
    }
    @GET
    @Path("/predictions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPredictionsPL(@QueryParam("from") String fromDate, @QueryParam("to") String toDate) throws JSONException {
        String predictionsJson = externe.getPredictions(fromDate, toDate);
        if (predictionsJson != null && !predictionsJson.isEmpty()) {
            return Response.ok(predictionsJson).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Impossible de récupérer les prédictions").build();
        }
    }
    
}
