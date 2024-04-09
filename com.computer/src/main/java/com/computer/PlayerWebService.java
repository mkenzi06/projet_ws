package com.computer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.computer.*;

@WebService(targetNamespace="http://www.com.computer")

public class PlayerWebService {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/servicewebapi";
	private static final String USER = "root";
	private static final String PASS = "";
	@WebMethod(operationName = "addPlayer")
	public String addPlayer(@WebParam(name = "player")Player player) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; // Utilisé pour récupérer l'ID de l'équipe
		try {
			// Étape 1 : Enregistrement du driver JDBC et ouverture de la connexion
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Étape 2 : Vérifier si l'équipe existe
			String checkTeamSql = "SELECT team_id FROM teams WHERE team_name = ?";
			stmt = conn.prepareStatement(checkTeamSql);
			stmt.setString(1, player.getTeamName());
			rs = stmt.executeQuery();

			// Étape 3 : Si l'équipe existe, récupérer son ID et insérer le joueur
			if (rs.next()) {
				int teamId = rs.getInt("team_id");

				// Préparer la requête d'insertion du joueur
				String insertSql = "INSERT INTO players (player_name, team_id, player_poste) VALUES (?, ?, ?)";
				stmt = conn.prepareStatement(insertSql);

				stmt.setString(1, player.getName());
				stmt.setInt(2, teamId); // Utiliser l'ID de l'équipe récupéré
				stmt.setString(3, player.getPosition());

				int affectedRows = stmt.executeUpdate();
				return affectedRows > 0 ? "Player added successfully" : "Failed to add player";
			} else {
				return "Team does not exist";
			}

		} catch (SQLException se) {
			// Gérer les erreurs JDBC
			se.printStackTrace();
			return "SQL Error: " + se.getMessage();
		} catch (Exception e) {
			// Gérer les autres erreurs
			e.printStackTrace();
			return "Error: " + e.getMessage();
		} finally {
			// Nettoyer l'environnement
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

}
