package projet.web.foot.ressource;

import java.net.URI;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.json.JSONException;

import projet.web.foot.Modele.Team;
import projet.web.foot.Modele.Player;
import projet.web.foot.service.TeamService;
import projet.web.foot.Modele.*;
@Path("/teams")
public class TeamRessource {
	TeamService service = new TeamService();
	ExternalApiCalls externe = new ExternalApiCalls();
    @Context
    UriInfo uriInfo;
    
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response addTeam(Team team) {
        Team addedTeam = service.addTeam(team);
        if (addedTeam == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(addedTeam.getId())).build();
        return Response.created(uri).entity(addedTeam).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response deleteTeam(@PathParam("id") int id) {
        if (!service.deleteTeam(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getTeam(@PathParam("id") int id) {
        Team team = service.getTeam(id);
        if (team == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Link link = Link.fromUri(uriInfo.getRequestUri()).rel("self").type("application/xml").build();
        return Response.ok().entity(team).links(link).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Team> getAllTeams() {
        return service.getAllTeams();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response updateTeam(@PathParam("id") int id, Team team) {
        if (service.updateTeam(id, team)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/{id}/players")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response addPlayerToTeam(@PathParam("id") int id, Player player) {
        if (service.addPlayerToTeam(id, player)) {
            URI uri = uriInfo.getAbsolutePathBuilder().path("players").build(); // Construire l'URI pour le joueur ajouté
            return Response.created(uri).entity(player).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{id}/players")
    @Produces(MediaType.APPLICATION_XML)
    public Response getPlayersOfTeam(@PathParam("id") int id) {
        List<Player> players = service.getPlayersOfTeam(id);
        if (players.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(players).build();
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
    @Path("/liga/players/{playerName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayersData(@PathParam("playerName") String playerName) throws JSONException {
        String playersJson = externe.getPlayersData(playerName);
        if (playersJson != null && !playersJson.isEmpty()) {
            return Response.ok(playersJson).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Impossible de récupérer les données des joueurs").build();
        }
    }
}
