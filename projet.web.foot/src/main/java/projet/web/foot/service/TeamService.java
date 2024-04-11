package projet.web.foot.service;

import antlr.collections.List;
import projet.web.foot.Modele.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
/**
 * Service pour la gestion des équipes dans la base de données.
 * Ce service fournit des méthodes pour ajouter, supprimer, mettre à jour et récupérer des équipes dans la base de données.
 * Il permet également de récupérer les joueurs d'une équipe.
 */
public class TeamService {
  
    //cette methode a ete implemente aussi avec l'autre frameworks JAX-WS
    public Team addTeamToDatabase(Team t) throws ClassNotFoundException {
    	DatabaseManager c = new DatabaseManager();
        try {

            // Préparation de la requête sql pour ajouter une equipe
            Connection connection = c.connectBd();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO teams (team_name, coach_name, ligue ) VALUES (?, ?, ?)");
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

    public boolean deleteTeamFromDatabase(int id) throws ClassNotFoundException {
    	DatabaseManager c = new DatabaseManager();
        try {
            // Préparation de la requête SQL pour supprimer une equipe de la bd
            Connection connection = c.connectBd();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM teams WHERE team_id = ?");
            statement.setInt(1, id);

            // Exécution de la requête SQL
            int rowsAffected = statement.executeUpdate();

      
            if (rowsAffected > 0) {
 
                return true;
            }
            return false; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Une erreur s'est produite lors de la suppression 
        }
    }


    public ArrayList<Team> getAllTeamsFromDatabase() throws ClassNotFoundException {
    	DatabaseManager c = new DatabaseManager();
        ArrayList<Team> players = new ArrayList<Team>();
        try {
            
            Connection connection = c.connectBd();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM teams");

           
            ResultSet resultSet = statement.executeQuery();


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
            
        }
        return players;
    }
    private boolean teamExistsInDatabase(int teamId) throws ClassNotFoundException {
    	DatabaseManager c = new DatabaseManager();
        try {
          
            Connection connection = c.connectBd();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM teams WHERE team_id = ?");
            statement.setInt(1, teamId);

            
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
    }
    public boolean updateTeamCoachInDatabase(int id, Team updatedteam) throws ClassNotFoundException {
    	DatabaseManager c = new DatabaseManager();
        try {
            // Vérifier si l'equioe existe dans la bd
            if (!teamExistsInDatabase(id)) {
                return false; // l'equipe n'existe pas dans la BD
            }
            
            // Préparation de la requête SQL pour mettre à jour le coach d'une equipe
            Connection connection = c.connectBd();
            PreparedStatement statement = connection.prepareStatement("UPDATE teams SET coach_name = ? WHERE team_id = ?");
            statement.setString(1, updatedteam.getCoachName());
            statement.setInt(2, id);

            // Exécution de la requête SQL
            int rowsAffected = statement.executeUpdate();

            // Vérifier si la maj a bien ete faite
            if (rowsAffected > 0) {
                return true;
            }
            return false; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
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
         }
         return players;
    }

}
