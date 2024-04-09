package projet.web.foot.service;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.sql.*;
import antlr.collections.List;
import projet.web.foot.Modele.DatabaseManager;
import projet.web.foot.Modele.Player;
import projet.web.foot.Modele.Team;

public class PlayerService {

	java.sql.Statement s;
	ResultSet r;

	public boolean teamExistsInDatabase(int teamId) throws ClassNotFoundException {
		DatabaseManager c = new DatabaseManager();
		try {
			// Préparation de la requête SQL
			PreparedStatement statement = c.connectBd().prepareStatement("SELECT * FROM teams WHERE team_id = ?");
			statement.setInt(1, teamId);

			// Exécution de la requête SQL
			ResultSet resultSet = statement.executeQuery();

			// Si une ligne est retournée, cela signifie que l'équipe existe
			return resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private TeamService teamService;

	public PlayerService(TeamService teamService) {
		this.teamService = teamService;
	}

	public int getTeamIdFromName(String teamName) throws ClassNotFoundException, SQLException {
		DatabaseManager c = new DatabaseManager();
		Connection connection = c.connectBd();
		PreparedStatement statement = connection.prepareStatement("SELECT team_id FROM teams WHERE team_name = ?");
		statement.setString(1, teamName);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getInt("team_id");
		}
		return -1; // Retourner -1 si l'équipe n'est pas trouvée
	}

	public Player addPlayerToDatabase(Player player) throws ClassNotFoundException {
		DatabaseManager c = new DatabaseManager();
		try {
			// Vérifier si l'équipe existe dans la base de données et récupérer son ID
			int teamId = getTeamIdFromName(player.getTeamName());
			if (teamId == -1) {
				return null; // Équipe non trouvée dans la base de données, retourner null
			}

			// Préparation de la requête SQL pour ajouter un joueur
			Connection connection = c.connectBd();
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO players (player_name, team_id, player_poste) VALUES (?, ?, ?)");

			statement.setString(1, player.getPrenom());
			statement.setInt(2, teamId); // Utiliser l'ID de l'équipe
			statement.setString(3, player.getPoste());

			// Exécution de la requête SQL
			statement.executeUpdate();

			// Retourner le joueur lui-même
			return player;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

//    public Player addPlayerToDatabase(Player player) throws ClassNotFoundException {
//    	DatabaseManager c = new DatabaseManager();
//        try {
//            // Vérifie si l'équipe existe dans la base de données
//            if (!teamExistsInDatabase(player.getEquipe())) {
//                return null; // Équipe non trouvée dans la base de données, retourner null
//            }
//
//            // Préparation de la requête SQL pour ajouter un joueur
//            Connection connection = c.connectBd();
//            PreparedStatement statement = connection.prepareStatement("INSERT INTO players (player_name, team_id, player_poste) VALUES (?, ?, ?)");
////            statement.setInt(1, player.getId());
//            statement.setString(1, player.getPrenom());
//            statement.setInt(2, player.getEquipe());
//            statement.setString(3, player.getPoste());
//
//            // Exécution de la requête SQL
//            statement.executeUpdate();
//
//            // Retourne le joueur lui-même
//            return player;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
	public boolean deletePlayerByName(String playerName) throws ClassNotFoundException {
		int playerId = getPlayerIdByName(playerName);
		if (playerId == -1) {
			// Si aucun joueur n'est trouvé avec ce nom, renvoyer false
			return false;
		}

		return deletePlayerFromDatabase(playerId);
	}

	public int getPlayerIdByName(String playerName) throws ClassNotFoundException {
		DatabaseManager c = new DatabaseManager();
		try {
			Connection connection = c.connectBd();
			PreparedStatement statement = connection
					.prepareStatement("SELECT player_id FROM players WHERE player_name = ?");
			statement.setString(1, playerName);

			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("player_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1; // Retourne -1 si aucun joueur n'est trouvé avec ce nom
	}

	public boolean deletePlayerFromDatabase(int id) throws ClassNotFoundException {
		DatabaseManager c = new DatabaseManager();
		try {
			// Préparation de la requête SQL pour supprimer le joueur de la base de données
			Connection connection = c.connectBd();
			PreparedStatement statement = connection.prepareStatement("DELETE FROM players WHERE player_id = ?");
			statement.setInt(1, id);

			// Exécution de la requête SQL
			int rowsAffected = statement.executeUpdate();

			// Vérifie si une ligne a été affectée (c'est-à-dire si le joueur a été
			// supprimé)
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

	public Player getPlayerFromDatabase(int id) throws ClassNotFoundException {
		DatabaseManager c = new DatabaseManager();
		try {
			// Préparation de la requête SQL pour récupérer le joueur de la base de données
			Connection connection = c.connectBd();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM players WHERE id = ?");
			statement.setInt(1, id);

			// Exécution de la requête SQL
			ResultSet resultSet = statement.executeQuery();

			// Vérifie si le joueur a été trouvé dans la base de données
			if (resultSet.next()) {
				// Création d'un objet Player à partir des données récupérées de la base de
				// données
				Player player = new Player();
				player.setId(resultSet.getInt("player_id"));
				player.setPrenom(resultSet.getString("player_name"));
				player.setEquipe(resultSet.getInt("team_id"));
				player.setPoste("player_poste");

				return player; // Retourne le joueur trouvé
			}
			return null; // Aucun joueur trouvé avec cet ID
		} catch (SQLException e) {
			e.printStackTrace();
			return null; // Une erreur s'est produite lors de la récupération du joueur
		}
	}
	public int getPlayerIdFromName(String playerName) throws ClassNotFoundException {
	    DatabaseManager c = new DatabaseManager();
	    try {
	        // Préparation de la requête SQL pour récupérer l'ID du joueur par son nom
	        Connection connection = c.connectBd();
	        PreparedStatement statement = connection.prepareStatement("SELECT player_id FROM players WHERE player_name = ?");
	        statement.setString(1, playerName);

	        // Exécution de la requête SQL
	        ResultSet resultSet = statement.executeQuery();

	        // Vérification si un joueur avec le nom spécifié a été trouvé
	        if (resultSet.next()) {
	            return resultSet.getInt("player_id"); // Retourne l'ID du joueur trouvé
	        }
	        return -1; // Aucun joueur trouvé avec ce nom
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return -1; // Une erreur s'est produite lors de la récupération de l'ID
	    }
	}
	public ArrayList<Player> getAllPlayersFromDatabase() throws ClassNotFoundException {
		DatabaseManager c = new DatabaseManager();
		ArrayList<Player> players = new ArrayList<Player>();
		try {
			// Préparation de la requête SQL pour récupérer tous les joueurs de la base de
			// données
			Connection connection = c.connectBd();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM players");

			// Exécution de la requête SQL
			ResultSet resultSet = statement.executeQuery();

			// Parcours des résultats et ajout des joueurs à la liste
			while (resultSet.next()) {
				Player player = new Player();
				player.setId(resultSet.getInt("player_id"));
				player.setPrenom(resultSet.getString("player_name"));
				player.setEquipe(resultSet.getInt("team_id"));
				player.setPoste("player_poste");
				players.add(player);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Gérer l'exception selon les besoins
		}
		return players;
	}

	private boolean playerExistsInDatabase(int playerId) throws ClassNotFoundException {
		DatabaseManager c = new DatabaseManager();
		try {
			// Préparation de la requête SQL pour vérifier l'existence du joueur
			Connection connection = c.connectBd();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM players WHERE player_id = ?");
			statement.setInt(1, playerId);

			// Exécution de la requête SQL
			ResultSet resultSet = statement.executeQuery();

			// Si une ligne est retournée, cela signifie que le joueur existe
			return resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false; // Une erreur s'est produite lors de la vérification de l'existence du joueur
		}
	}

	public boolean updatePlayerInDatabase(int id, Player updatedPlayer) throws ClassNotFoundException {
		DatabaseManager c = new DatabaseManager();
		try {
			// Vérifier si le joueur existe dans la base de données
			if (!playerExistsInDatabase(id)) {
				return false; // Le joueur n'existe pas dans la base de données
			}

			// Préparation de la requête SQL pour mettre à jour le joueur
			Connection connection = c.connectBd();
			PreparedStatement statement = connection
					.prepareStatement("UPDATE players SET player_name = ?, team_id = ? WHERE id = ?");
			statement.setString(1, updatedPlayer.getPrenom());
			statement.setInt(2, updatedPlayer.getId());
			statement.setInt(3, id);

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

	public int getTeamIdByName(String teamName) throws ClassNotFoundException {
		DatabaseManager c = new DatabaseManager();
		try {
			Connection connection = c.connectBd();
			PreparedStatement statement = connection.prepareStatement("SELECT team_id FROM teams WHERE team_name = ?");
			statement.setString(1, teamName);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				return resultSet.getInt("team_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1; // Retourner -1 ou toute autre valeur indicative d'erreur si l'équipe n'est pas
					// trouvée
	}

	public boolean updatePlayerTeamInDatabase(int id, int newTeamId) throws ClassNotFoundException {
		DatabaseManager c = new DatabaseManager();
		try {
			// Vérifier si le joueur existe dans la base de données
			if (!playerExistsInDatabase(id)) {
				return false; // Le joueur n'existe pas dans la base de données
			}

			// Préparation de la requête SQL pour mettre à jour l'équipe du joueur
			Connection connection = c.connectBd();
			PreparedStatement statement = connection
					.prepareStatement("UPDATE players SET team_id = ? WHERE player_id = ?");
			statement.setInt(1, newTeamId);
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

	public boolean updatePlayerTeamByName(String playerName, String teamName) throws ClassNotFoundException {
		int teamId = getTeamIdByName(teamName);
		int playerId = getPlayerIdByName(playerName); 
		if (teamId == -1) {
			System.out.println("Équipe non trouvée.");
			return false; // L'équipe n'a pas été trouvée dans la base de données
		}

		// Utilisation de la méthode précédente pour mettre à jour l'équipe du joueur
		// avec l'ID de l'équipe
		return updatePlayerTeamInDatabase(playerId, teamId);
	}

	public Team getTeamOfPlayerFromDatabase(int playerId) throws ClassNotFoundException {
		DatabaseManager c = new DatabaseManager();
		try {
			// Préparation de la requête SQL pour récupérer l'équipe du joueur de la base de
			// données
			Connection connection = c.connectBd();
			PreparedStatement statement = connection.prepareStatement(
					"SELECT * FROM teams WHERE team_id = (SELECT team_id FROM players WHERE player_id = ?)");
			statement.setInt(1, playerId);

			// Exécution de la requête SQL
			ResultSet resultSet = statement.executeQuery();

			// Vérifie si l'équipe du joueur a été trouvée dans la base de données
			if (resultSet.next()) {
				// Création d'un objet Team à partir des données récupérées de la base de
				// données
				Team team = new Team();
				team.setId(resultSet.getInt("team_id"));
				team.setName(resultSet.getString("team_name"));
				team.setCoachName(resultSet.getString("coach_name"));
				team.setLeague(resultSet.getString("ligue"));
				return team; // Retourne l'équipe du joueur trouvée
			}
			return null; // Aucune équipe trouvée pour ce joueur
		} catch (SQLException e) {
			e.printStackTrace();
			return null; // Une erreur s'est produite lors de la récupération de l'équipe du joueur
		}
	}

}
