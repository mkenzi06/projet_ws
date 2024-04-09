package projet.web.foot.ressource;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.json.JSONException;

import projet.web.foot.Modele.ExternalApiCalls;
import projet.web.foot.Modele.Player;
import projet.web.foot.Modele.Team;
import projet.web.foot.service.PlayerService;
import projet.web.foot.service.TeamService;

@Path("/players")
public class PlayerRessource {
	TeamService teamService = new TeamService();
	PlayerService service = new PlayerService(teamService);
	ExternalApiCalls externe = new ExternalApiCalls();
	@Context
	UriInfo uriInfo;

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response addPlayer(Player player) throws ClassNotFoundException {
		// Récupérer l'ID de l'équipe à partir de son nom
		int teamId;
		try {
			teamId = service.getTeamIdFromName(player.getTeamName());
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

		// Vérifier si l'équipe existe dans la base de données
		if (teamId == -1) {
			return Response.status(Response.Status.BAD_REQUEST).entity("L'équipe spécifiée n'existe pas.").build();
		}

		// Ajouter le joueur avec l'identifiant d'équipe spécifié
		Player addedPlayer = service.addPlayerToDatabase(player);

		if (addedPlayer == null) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

		// Construire l'URI avec l'ID du joueur ajouté
		URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(addedPlayer.getId())).build();

		// Retourner la réponse avec l'URI de la ressource créée et les données du
		// joueur ajouté
		return Response.created(uri).entity(addedPlayer).build();
	}


	@DELETE
	@Path("/name/{playerName}")
	@Produces(MediaType.APPLICATION_XML)
	public Response deletePlayerByName(@PathParam("playerName") String playerName) throws ClassNotFoundException {
		int playerId = service.getPlayerIdByName(playerName);
		if (playerId == -1) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		boolean deleted = service.deletePlayerFromDatabase(playerId);
		if (!deleted) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.ok().build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getPlayer(@PathParam("id") int id) throws ClassNotFoundException {
		Player player = service.getPlayerFromDatabase(id);
		if (player == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		Link link = Link.fromUri(uriInfo.getRequestUri()).rel("self").type("application/xml").build();
		return Response.ok().entity(player).links(link).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public ArrayList<Player> getAllPlayers() throws ClassNotFoundException {
		return service.getAllPlayersFromDatabase();
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response updatePlayer(@PathParam("id") int id, Player player) throws ClassNotFoundException {
		if (service.updatePlayerInDatabase(id, player)) {
			return Response.ok().build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@GET
	@Path("/{id}/team")
	@Produces(MediaType.APPLICATION_XML)
	public Response getTeamOfPlayer(@PathParam("id") int id) throws ClassNotFoundException {
		Team t = service.getTeamOfPlayerFromDatabase(id);
		if (t == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		Link link = Link.fromUri(uriInfo.getRequestUri()).rel("self").type("application/xml").build();
		return Response.ok().entity(t).links(link).build();
	}

	@PUT
	@Path("/{playerName}/{teamName}")
	@Consumes(MediaType.APPLICATION_XML)
	public Response updatePlayerTeam(@PathParam("playerName") String playerId, @PathParam("teamName") String teamName)
			throws ClassNotFoundException {
		// Utilisation de la méthode service pour mettre à jour l'équipe du joueur
		boolean isUpdated = service.updatePlayerTeamByName(playerId, teamName);

		if (isUpdated) {
			return Response.ok().build(); // Retourne 200 OK si la mise à jour est réussie
		} else {
			return Response.status(Response.Status.NOT_FOUND).build(); // Retourne 404 Not Found si l'ID du joueur ou le
																		// nom de l'équipe n'est pas trouvé
		}
	}
	@GET
    @Path("/stats/{playerName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayersData(@PathParam("playerName") String playerName) throws JSONException, org.codehaus.jettison.json.JSONException {
        String playersJson = externe.getPlayersData(playerName);
        if (playersJson != null && !playersJson.isEmpty()) {
            return Response.ok(playersJson).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Impossible de récupérer les données des joueurs").build();
        }
    }
}
