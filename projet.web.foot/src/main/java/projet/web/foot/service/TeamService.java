package projet.web.foot.service;

import java.util.HashMap;
import java.util.Map;

import antlr.collections.List;
import projet.web.foot.Modele.Team;
import projet.web.foot.Modele.*;
import java.util.*;

public class TeamService {
    private static Map<Integer, Team> TEAM_DATA = new HashMap<Integer,Team>();
    private static int teamCount = 0;

    private int getNewId() {
        return ++teamCount;
    }
    public void updateTeamPlayersAfterPlayerRemoval(int playerId) {
        // Parcourir toutes les équipes pour mettre à jour la liste des joueurs
        for (Team team : TEAM_DATA.values()) {
            // Vérifier si le joueur supprimé appartient à cette équipe
            Iterator<Player> iterator = team.getPlayers().iterator();
            while (iterator.hasNext()) {
                Player player = iterator.next();
                if (player.getId() == playerId) {
                    // Supprimer le joueur de la liste des joueurs de l'équipe
                    iterator.remove();
                }
            }
        }
    }
    public Team addTeam(Team team) {
        int id = getNewId();
        team.setId(id);
        TEAM_DATA.put(id, team);
        return team;
    }

    public boolean deleteTeam(int id) {
        return TEAM_DATA.remove(id) != null;
    }

    public Team getTeam(int id) {
        return TEAM_DATA.get(id);
    }

    public ArrayList<Team> getAllTeams() {
        return new ArrayList<Team>(TEAM_DATA.values());
    }

    public boolean updateTeam(int id, Team updatedTeam) {
        if (TEAM_DATA.containsKey(id)) {
            updatedTeam.setId(id);
            TEAM_DATA.put(id, updatedTeam);
            return true;
        }
        return false;
    }
    public boolean teamExists(int equipeId) {
        return TEAM_DATA.containsKey(equipeId);
    }
    public boolean addPlayerToTeam(int teamId, Player player) {
        Team team = TEAM_DATA.get(teamId);
        if (team != null) {
            ArrayList<Player> players = team.getPlayers();
            if (players == null) {
                players = new ArrayList<Player>();
                team.setPlayers(players);
            }
            player.setId(PlayerService.playerCount++);
            players.add(player);
            return true;
        }
        return false;
    }

    public ArrayList<Player> getPlayersOfTeam(int teamId) {
        Team team = TEAM_DATA.get(teamId);
        if (team != null) {
            return team.getPlayers();
        }
        return new ArrayList<Player>();
    }
}
