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

/**
 * Service pour la gestion des joueurs dans la base de données. Ce service
 * fournit des méthodes pour ajouter, supprimer, mettre à jour et récupérer des
 * joueurs dans la base de données. Il permet également de récupérer l'équipe
 * d'un joueur et de mettre à jour l'équipe d'un joueur.
 */
public class PlayerService {

	java.sql.Statement s;
	ResultSet r;

	public boolean teamExistsInDatabase(int teamId) throws ClassNotFoundException {
		DatabaseManager c = new DatabaseManager();
		try {

			PreparedStatement statement = c.connectBd().prepareStatement("SELECT * FROM teams WHERE team_id = ?");
			statement.setInt(1, teamId);

			ResultSet resultSet = statement.executeQuery();

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
		return -1;
	}
//si lajout se faire avec JAX-rs
	public Player addPlayerToDatabase(Player player) throws ClassNotFoundException {
		DatabaseManager c = new DatabaseManager();
		try {
			// Vérifier si l'équipe existe dans la bds et récupérer son ID
			int teamId = getTeamIdFromName(player.getTeamName());
			if (teamId == -1) {
				return null;
			}

			Connection connection = c.connectBd();
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO players (player_name, team_id, player_poste) VALUES (?, ?, ?)");

			statement.setString(1, player.getPrenom());
			statement.setInt(2, teamId); // Utiliser l'ID de l'équipe
			statement.setString(3, player.getPoste());

			// Exécution de la requête SQL
			statement.executeUpdate();

			return player;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

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
		return -1; 
	}

	public boolean deletePlayerFromDatabase(int id) throws ClassNotFoundException {
		DatabaseManager c = new DatabaseManager();
		try {
			Connection connection = c.connectBd();
			PreparedStatement statement = connection.prepareStatement("DELETE FROM players WHERE player_id = ?");
			statement.setInt(1, id);

			int rowsAffected = statement.executeUpdate();
			if (rowsAffected > 0) {

				return true; // Joueur supprimé avec succès
			}
			return false; // Aucun joueur trouvé avec cet ID
		} catch (SQLException e) {
			e.printStackTrace();
			return false; 
		}
	}

	public Player getPlayerFromDatabase(int id) throws ClassNotFoundException {
		DatabaseManager c = new DatabaseManager();
		try {
			Connection connection = c.connectBd();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM players WHERE id = ?");
			statement.setInt(1, id);

			// Exécution de la requête SQL
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
			
				Player player = new Player();
				player.setId(resultSet.getInt("player_id"));
				player.setPrenom(resultSet.getString("player_name"));
				player.setEquipe(resultSet.getInt("team_id"));
				player.setPoste("player_poste");

				return player; // Retourne le joueur trouve
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int getPlayerIdFromName(String playerName) throws ClassNotFoundException {
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
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public ArrayList<Player> getAllPlayersFromDatabase() throws ClassNotFoundException {
		DatabaseManager c = new DatabaseManager();
		ArrayList<Player> players = new ArrayList<Player>();
		try {
			
			Connection connection = c.connectBd();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM players");

			ResultSet resultSet = statement.executeQuery();

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
		}
		return players;
	}

	private boolean playerExistsInDatabase(int playerId) throws ClassNotFoundException {
		DatabaseManager c = new DatabaseManager();
		try {

			Connection connection = c.connectBd();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM players WHERE player_id = ?");
			statement.setInt(1, playerId);

			ResultSet resultSet = statement.executeQuery();

			return resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updatePlayerInDatabase(int id, Player updatedPlayer) throws ClassNotFoundException {
		DatabaseManager c = new DatabaseManager();
		try {
			if (!playerExistsInDatabase(id)) {
				return false; // Le joueur n'existe pas dans la base de données
			}

			Connection connection = c.connectBd();
			PreparedStatement statement = connection
					.prepareStatement("UPDATE players SET player_name = ?, team_id = ? WHERE id = ?");
			statement.setString(1, updatedPlayer.getPrenom());
			statement.setInt(2, updatedPlayer.getId());
			statement.setInt(3, id);

			int rowsAffected = statement.executeUpdate();

			if (rowsAffected > 0) {
				return true; 
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
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
		return -1;
	}

	public boolean updatePlayerTeamInDatabase(int id, int newTeamId) throws ClassNotFoundException {
		DatabaseManager c = new DatabaseManager();
		try {

			if (!playerExistsInDatabase(id)) {
				return false; // Le joueur n'existe pas dans la base de données
			}

			Connection connection = c.connectBd();
			PreparedStatement statement = connection
					.prepareStatement("UPDATE players SET team_id = ? WHERE player_id = ?");
			statement.setInt(1, newTeamId);
			statement.setInt(2, id);

			int rowsAffected = statement.executeUpdate();

			if (rowsAffected > 0) {
				return true; // Joueur mis à jour avec succès
			}
			return false; 
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updatePlayerTeamByName(String playerName, String teamName) throws ClassNotFoundException {
		int teamId = getTeamIdByName(teamName);
		int playerId = getPlayerIdByName(playerName);
		if (teamId == -1) {
			System.out.println("Équipe non trouvée.");
			return false; // L'équipe n'a pas été trouvée dans la base de données
		}

		// Utilisation de la méthode précédente pour mettre à jour l'équipe du joueu
		return updatePlayerTeamInDatabase(playerId, teamId);
	}

	public Team getTeamOfPlayerFromDatabase(int playerId) throws ClassNotFoundException {
		DatabaseManager c = new DatabaseManager();
		try {
			Connection connection = c.connectBd();
			PreparedStatement statement = connection.prepareStatement(
					"SELECT * FROM teams WHERE team_id = (SELECT team_id FROM players WHERE player_id = ?)");
			statement.setInt(1, playerId);

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

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
			return null; 
		}
	}

}
