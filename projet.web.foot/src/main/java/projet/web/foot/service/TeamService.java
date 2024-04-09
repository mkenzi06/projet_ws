package projet.web.foot.service;

import antlr.collections.List;
import projet.web.foot.Modele.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TeamService {
    private static Map<Integer, Team> TEAM_DATA = new HashMap<Integer,Team>();
    private static int teamCount = 0;

    private int getNewId() {
        return ++teamCount;
    }
    
//    public void updateTeamPlayersAfterPlayerRemoval(int playerId) {
//        // Parcourir toutes les équipes pour mettre à jour la liste des joueurs
//        for (Team team : TEAM_DATA.values()) {
//            // Vérifier si le joueur supprimé appartient à cette équipe
//            Iterator<Player> iterator = team.getPlayers().iterator();
//            while (iterator.hasNext()) {
//                Player player = iterator.next();
//                if (player.getId() == playerId) {
//                    // Supprimer le joueur de la liste des joueurs de l'équipe
//                    iterator.remove();
//                }
//            }
//        }
//    }
    
    public Team addTeamToDatabase(Team t) throws ClassNotFoundException {
    	DatabaseManager c = new DatabaseManager();
        try {

            // Préparation de la requête SQL pour ajouter un joueur
            Connection connection = c.connectBd();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO teams (team_name, coach_name, ligue ) VALUES (?, ?, ?)");
//            statement.setInt(1, player.getId());
            statement.setString(1, t.getName());
            statement.setString(2, t.getCoachName());
            statement.setString(3, t.getLeague());

            // Exécution de la requête SQL
            statement.executeUpdate();

            // Retourne le joueur lui-même
            return t;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
//    public Team addTeam(Team team) {
//        int id = getNewId();
//        team.setId(id);
//        TEAM_DATA.put(id, team);
//        return team;
//    }
    public boolean deleteTeamFromDatabase(int id) throws ClassNotFoundException {
    	DatabaseManager c = new DatabaseManager();
        try {
            // Préparation de la requête SQL pour supprimer le joueur de la base de données
            Connection connection = c.connectBd();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM teams WHERE team_id = ?");
            statement.setInt(1, id);

            // Exécution de la requête SQL
            int rowsAffected = statement.executeUpdate();

            // Vérifie si une ligne a été affectée (c'est-à-dire si le joueur a été supprimé)
            if (rowsAffected > 0) {
                // Mettre à jour la liste des joueurs de chaque équipe dans TeamService
//                teamService.updateTeamPlayersAfterPlayerRemoval(id);
                return true; // Joueur supprimé avec succès
            }
            return false; // Aucun joueur trouvé avec cet ID
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Une erreur s'est produite lors de la suppression du joueur
        }
    }


    public ArrayList<Team> getAllTeamsFromDatabase() throws ClassNotFoundException {
    	DatabaseManager c = new DatabaseManager();
        ArrayList<Team> players = new ArrayList<Team>();
        try {
            // Préparation de la requête SQL pour récupérer tous les joueurs de la base de données
            Connection connection = c.connectBd();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM teams");

            // Exécution de la requête SQL
            ResultSet resultSet = statement.executeQuery();

            // Parcours des résultats et ajout des joueurs à la liste
            while (resultSet.next()) {
            	Team player = new Team();
                player.setId(resultSet.getInt("team_id"));
                
                player.setCoachName(resultSet.getString("coach_name"));
                player.setLeague(resultSet.getString("ligue"));
                player.setName(resultSet.getString("team_name"));
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception selon les besoins
        }
        return players;
    }
    private boolean teamExistsInDatabase(int teamId) throws ClassNotFoundException {
    	DatabaseManager c = new DatabaseManager();
        try {
            // Préparation de la requête SQL pour vérifier l'existence du joueur
            Connection connection = c.connectBd();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM teams WHERE team_id = ?");
            statement.setInt(1, teamId);

            // Exécution de la requête SQL
            ResultSet resultSet = statement.executeQuery();

            // Si une ligne est retournée, cela signifie que le joueur existe
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Une erreur s'est produite lors de la vérification de l'existence du joueur
        }
    }
    public boolean updateTeamCoachInDatabase(int id, Team updatedteam) throws ClassNotFoundException {
    	DatabaseManager c = new DatabaseManager();
        try {
            // Vérifier si le joueur existe dans la base de données
            if (!teamExistsInDatabase(id)) {
                return false; // Le joueur n'existe pas dans la base de données
            }
            
            // Préparation de la requête SQL pour mettre à jour le joueur
            Connection connection = c.connectBd();
            PreparedStatement statement = connection.prepareStatement("UPDATE teams SET coach_name = ? WHERE team_id = ?");
            statement.setString(1, updatedteam.getCoachName());
            statement.setInt(2, id);

            // Exécution de la requête SQL
            int rowsAffected = statement.executeUpdate();

            // Vérifier si le joueur a été mis à jour avec succès
            if (rowsAffected > 0) {
                return true; // Joueur mis à jour avec succès
            }
            return false; // Aucun joueur trouvé avec cet ID
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Une erreur s'est produite lors de la mise à jour du joueur
        }
    }


    public ArrayList<Player> getPlayersOfTeam(int id) throws ClassNotFoundException{
    	 ArrayList<Player> players = new ArrayList<Player>();
         String sql = "SELECT * FROM players WHERE team_id = ?";
         DatabaseManager c = new DatabaseManager();
         try {
        	 Connection connection = c.connectBd();
             PreparedStatement statement = connection.prepareStatement(sql);
             statement.setInt(1, id);
             ResultSet resultSet = statement.executeQuery();
             while (resultSet.next()) {
             	Player player = new Player();
             	player.setId(resultSet.getInt("player_id"));
                player.setPrenom(resultSet.getString("player_name"));
                player.setEquipe(resultSet.getInt("team_id"));
                player.setPoste(resultSet.getString("player_poste"));
                players.add(player);
             }
             
         } catch (SQLException e) {
             e.printStackTrace();
            // Une erreur s'est produite lors de la mise à jour du joueur
         }
         return players;
    }

}
