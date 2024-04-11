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
/**
 * La classe PlayerRessource fournit des endpoints RESTful pour la gestion des joueurs de football.
 * Elle permet d'ajouter, de supprimer, de mettre à jour et de récupérer des informations sur les joueurs et a un appel a une api externe.
 */
@Path("/players")
public class PlayerRessource {
	TeamService teamService = new TeamService();
	PlayerService service = new PlayerService(teamService);
	ExternalApiCalls externe = new ExternalApiCalls();
	@Context
	UriInfo uriInfo;
	//remarque le post pour le player a seulement ete implementer avant de savoir qu'on devait utilises JAX_WS
	/**
     * Endpoint pour ajouter un joueur.
     * Prend en charge les requêtes POST avec un contenu XML.
     * Renvoie une réponse avec un code de statut approprié et les données du joueur ajouté.
     *
     * @param player Le joueur à ajouter.
     * @return Une réponse HTTP indiquant le résultat de l'opération.
     * @throws ClassNotFoundException Si une erreur se produit lors de la gestion de la base de données.
     */
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

		URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(addedPlayer.getId())).build();

		return Response.created(uri).entity(addedPlayer).build();
	}
	/**
     * Endpoint pour supprimer un joueur par son nom.
     * Prend en charge les requêtes DELETE avec le nom du joueur comme paramètre dans l'URL.
     * Renvoie une réponse avec un code de statut approprié.
     *
     * @param playerName Le nom du joueur à supprimer.
     * @return Une réponse HTTP indiquant le résultat de l'opération.
     * @throws ClassNotFoundException Si une erreur se produit lors de la gestion de la base de données.
     */

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
    /**
     * Endpoint pour récupérer les données d'un joueur à partir de son id.
     * Prend en charge les requêtes GET avec l'id du joueur comme paramètre dans l'URL.
     * Renvoie une réponse avec un code de statut approprié et les données du joueur.
     *
     * @param id du player dont on souhaite récupérer les données.
     * @return Une réponse HTTP contenant les données du joueur au format XML.
     */
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
    /**
     * Endpoint pour récupérer les données des joueurs
     
     * @return Une réponse HTTP contenant les données des joueurs au format XML.
     
     */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public ArrayList<Player> getAllPlayers() throws ClassNotFoundException {
		return service.getAllPlayersFromDatabase();
	}
	/**
	 * Endpoint pour mettre à jour les informations d'un joueur.
	 * Prend en charge les requêtes PUT avec l'ID du joueur dans l'URL et les données du joueur au format XML dans le corps de la requête.
	 * Renvoie une réponse avec un code de statut approprié indiquant le résultat de l'opération.
	 *
	 * @param id     L'identifiant du joueur à mettre à jour.
	 * @param player Les données mises à jour du joueur.
	 * @return Une réponse HTTP indiquant le résultat de l'opération.
	 *         - Si la mise à jour est réussie, renvoie une réponse avec un code de statut 200 OK.
	 *         - Si le joueur avec l'ID spécifié n'est pas trouvé, renvoie une réponse avec un code de statut 404 Not Found.
	 */
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
	/**
	 * Endpoint pour récupérer l'équipe d'un joueur.
	 * Prend en charge les requêtes GET avec l'ID du joueur dans l'URL.
	 * Renvoie une réponse avec un code de statut approprié et les informations sur l'équipe du joueur au format XML.
	 *
	 * @param id L'identifiant du joueur.
	 * @return Une réponse HTTP indiquant le résultat de l'opération.
	 *         - Si l'équipe du joueur est trouvée, renvoie une réponse avec un code de statut 200 OK et les informations sur l'équipe du joueur.
	 *         - Si l'équipe du joueur n'est pas trouvée, renvoie une réponse avec un code de statut 404 Not Found.
	 */
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
	/**
	 * Endpoint pour mettre à jour l'équipe d'un joueur.
	 * Prend en charge les requêtes PUT avec le nom du joueur et le nom de l'équipe dans l'URL et les données au format XML dans le corps de la requête.
	 * Renvoie une réponse avec un code de statut approprié indiquant le résultat de l'opération.
	 *
	 * @param playerId  Le nom du joueur dont on souhaite mettre à jour l'équipe.
	 * @param teamName  Le nom de la nouvelle équipe du joueur.
	 * @return Une réponse HTTP indiquant le résultat de l'opération.
	 *         - Si la mise à jour est réussie, renvoie une réponse avec un code de statut 200 OK.
	 *         - Si l'ID du joueur ou le nom de l'équipe n'est pas trouvé, renvoie une réponse avec un code de statut 404 Not Found.
	 * @throws ClassNotFoundException Si une erreur se produit lors de la gestion de la base de données.
	 */
	@PUT
	@Path("/{playerName}/{teamName}")
	@Consumes(MediaType.APPLICATION_XML)
	public Response updatePlayerTeam(@PathParam("playerName") String playerId, @PathParam("teamName") String teamName)
			throws ClassNotFoundException {
		
		boolean isUpdated = service.updatePlayerTeamByName(playerId, teamName);

		if (isUpdated) {
			return Response.ok().build(); // Retourne 200 OK si la mise à jour est réussie
		} else {
			return Response.status(Response.Status.NOT_FOUND).build(); // Retourne 404 Not Found si l'ID du joueur ou le
																		// nom de l'équipe n'est pas trouvé
		}
	}
	/**
	 * Endpoint pour récupérer les statistiques d'un joueur à partir de son nom.
	 * Prend en charge les requêtes GET avec le nom du joueur dans l'URL en faisant appel a l'api externe defini dans la classe externalApiCalls.
	 * Renvoie une réponse avec un code de statut approprié et les statistiques du joueur au format JSON.
	 *
	 * @param playerName Le nom du joueur dont on souhaite récupérer les statistiques.
	 * @return Une réponse HTTP indiquant le résultat de l'opération.
	 *         - Si les statistiques du joueur sont trouvées, renvoie une réponse avec un code de statut 200 OK et les statistiques du joueur au format JSON.
	 *         - Si les statistiques du joueur ne sont pas trouvées, renvoie une réponse avec un code de statut 400 Bad Request.
	 */
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
