package projet.web.foot.ressource;

import java.net.URI;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import projet.web.foot.Modele.Player;
import projet.web.foot.Modele.Team;
import projet.web.foot.service.PlayerService;
import projet.web.foot.service.TeamService;

@Path("/players")
public class PlayerRessource {

//    PlayerService service = new PlayerService();
    TeamService teamService = new TeamService();
    PlayerService service = new PlayerService(teamService);
    @Context
    UriInfo uriInfo;

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response addPlayer(Player player) {
        // Ajouter le joueur avec l'identifiant d'équipe spécifié
        Player addedPlayer = service.addPlayer(player);
        if (addedPlayer == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        // Construire l'URI avec l'ID du joueur ajouté
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(addedPlayer.getId())).build();
        // Retourner la réponse avec l'URI de la ressource créée et les données du joueur ajouté
        return Response.created(uri).entity(addedPlayer).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response deletePlayer(@PathParam("id") int id) {
        if (!service.deletePlayer(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getPlayer(@PathParam("id") int id) {
        Player player = service.getPlayer(id);
        if (player == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Link link = Link.fromUri(uriInfo.getRequestUri()).rel("self").type("application/xml").build();
        return Response.ok().entity(player).links(link).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Player> getAllPlayers() {
        return service.getAllPlayers();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response updatePlayer(@PathParam("id") int id, Player player) {
        if (service.updatePlayer(id, player)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
//    @GET
//    @Path("/{id}/team")
//    @Produces(MediaType.APPLICATION_XML)
//    public Response getTeamOfPlayer(@PathParam("id") int id) {
//        Player player = service.getPlayer(id);
//        if (player == null) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//
//        int team = player.getEquipe(); // Récupérer l'équipe du joueur
//        if (team == 0) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//
//        Link link = Link.fromUri(uriInfo.getRequestUri()).rel("self").type("application/xml").build();
//        return Response.ok().entity(team).links(link).build();
//    }

}
