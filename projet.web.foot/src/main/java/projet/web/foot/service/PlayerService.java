package projet.web.foot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import antlr.collections.List;
import projet.web.foot.Modele.Player;
import projet.web.foot.Modele.Team;


public class PlayerService {
    private static Map<Integer, Player> PLAYER_DATA = new HashMap<Integer,Player>();
    static int playerCount = 0;
	private TeamService teamService;
    public PlayerService(TeamService teamService) {
        this.teamService = teamService;
    }
    public Player addPlayer(Player player) {
        int id = getNewId();
        player.setId(id);
        if (!teamService.teamExists(player.getEquipe())) {
            return null; // Équipe non trouvée, retourner null
        }
//        player.setEquipe(equipeId);// Définir l'identifiant de l'équipe pour le joueur
        
        PLAYER_DATA.put(id, player);
        // Ajouter le joueur à l'équipe dans TeamService
//        TeamService teamService = new TeamService();
        teamService.addPlayerToTeam(player.getEquipe(), player);
        return player;
    }

    public boolean deletePlayer(int id) {
        Player deletedPlayer = PLAYER_DATA.remove(id);
        if (deletedPlayer != null) {
            // Mettre à jour la liste des joueurs de chaque équipe
            teamService.updateTeamPlayersAfterPlayerRemoval(id);
            return true;
        }
        return false;
    }

    public Player getPlayer(int id) {
        return PLAYER_DATA.get(id);
    }

    public ArrayList<Player> getAllPlayers() {
        return new ArrayList<Player>(PLAYER_DATA.values());
    }

    public boolean updatePlayer(int id, Player updatedPlayer) {
        if (PLAYER_DATA.containsKey(id)) {
            updatedPlayer.setId(id);
            PLAYER_DATA.put(id, updatedPlayer);
            return true;
        }
        return false;
    }

    private static int getNewId() {
        return PLAYER_DATA.isEmpty() ? 1 : PLAYER_DATA.size() + 1;
    }
}
