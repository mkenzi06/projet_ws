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

    @DELETE
    @Path("/{teamName}")
    @Produces(MediaType.APPLICATION_XML)
    public Response deleteTeamByName(@PathParam("teamName") String teamName) throws ClassNotFoundException {
        int id =s.getTeamIdByName(teamName); // Recherche de l'ID de l'équipe par son nom
        if (id == -1) {
            // Si aucune équipe n'est trouvée avec ce nom, renvoyer un statut 404
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (!service.deleteTeamFromDatabase(id)) {
            // Si la suppression échoue pour une autre raison
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        
        // Si la suppression réussit
        return Response.ok().build();
    }


    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Team> getAllTeams() throws ClassNotFoundException {
        return service.getAllTeamsFromDatabase();
    }
    @PUT
    @Path("/{teamName}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response updateTeam(@PathParam("teamName") String teamName, Team updatedTeam) throws ClassNotFoundException {
        // Récupérer l'ID de l'équipe en fonction de son nom
        int teamId = s.getTeamIdByName(teamName);

        // 

        // Vérifier si l'équipe existe et si le nouveau coach est valide
        if (teamId != -1) {
            // Instancier un objet Team avec le nouveau coach
//            Team updatedTeam = new Team();
            
            // Mettre à jour l'équipe dans la base de données
            if (service.updateTeamCoachInDatabase(teamId, updatedTeam)) {
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la mise à jour de l'équipe").build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Équipe ou coach non trouvés").build();
        }
    }
//    @PUT
//    @Path("/{id}")
//    @Consumes(MediaType.APPLICATION_XML)
//    @Produces(MediaType.APPLICATION_XML)
//    public Response updateTeam(@PathParam("id") int id, Team team) throws ClassNotFoundException {
//        if (service.updateTeamCoachInDatabase(id, team)) {
//            return Response.ok().build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }


    @GET
    @Path("/{team_name}/player")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Player> getPlayersOfTeam(@PathParam("team_name") String team_name) throws ClassNotFoundException {
        // Renvoyer la réponse avec le contenu XML
    	int id = s.getTeamIdByName(team_name);
        return service.getPlayersOfTeam(id);
    
    }
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
