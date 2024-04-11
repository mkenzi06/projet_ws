package projet.web.foot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import projet.web.foot.*;
import projet.web.foot.Modele.*;
/**
 * Cette classe représente un service web pour gérer les opérations liées aux équipes et aux joueurs.
 * Elle utilise JAX-WS pour la publication des opérations en tant que services web.
 */
@WebService(targetNamespace="http://www.projet.web.foot",serviceName = "FootWeb",portName = "FootService")

public class FootWebService {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/servicewebapi";
	private static final String USER = "root";
	private static final String PASS = "";
	 /**
     * Méthode pour ajouter un joueur à une équipe.
     * @param player Le joueur à ajouter.
     * @return Un message indiquant si l'ajout du joueur a réussi ou non.
     */
	@WebMethod(operationName = "addPlayer")
	public String addPlayer(@WebParam(name = "player")Player player) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; // Utilisé pour récupérer l'ID de l'équipe
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String checkTeamSql = "SELECT team_id FROM teams WHERE team_name = ?";
			stmt = conn.prepareStatement(checkTeamSql);
			stmt.setString(1, player.getTeamName());
			rs = stmt.executeQuery();

			if (rs.next()) {
				int teamId = rs.getInt("team_id");

				String insertSql = "INSERT INTO players (player_name, team_id, player_poste) VALUES (?, ?, ?)";
				stmt = conn.prepareStatement(insertSql);

				stmt.setString(1, player.getPrenom());
				stmt.setInt(2, teamId); 
				stmt.setString(3, player.getPoste());

				int affectedRows = stmt.executeUpdate();
				return affectedRows > 0 ? "Player ajoute" : "l'ajout du player a echouer";
			} else {
				return "equipe non existante ";
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
	/**
     * Méthode pour ajouter une équipe.
     * @param team L'équipe à ajouter.
     * @return un message de reussite ou d'echoue
     **/
	@WebMethod(operationName = "addTeam")
	public String addTeam(@WebParam(name = "team") Team team) {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null; 

	    try {
	    
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection(DB_URL, USER, PASS);

	        
	        String checkTeamSql = "SELECT * FROM teams WHERE team_name = ?";
	        stmt = conn.prepareStatement(checkTeamSql);
	        stmt.setString(1, team.getName());
	        rs = stmt.executeQuery();


	        if (rs.next()) {
	            return "Team existante deja !!";
	        } else {
	           
	            String insertSql = "INSERT INTO teams (team_name, coach_name, ligue) VALUES (?, ?, ?)";
	            stmt = conn.prepareStatement(insertSql);
	            stmt.setString(1, team.getName());
	            stmt.setString(2, team.getCoachName());
	            stmt.setString(3, team.getLeague());

	            // Exécution de la requête SQL pour ajouter l'équipe
	            int affectedRows = stmt.executeUpdate();
	            return affectedRows > 0 ? "equipe ajoute avec succees" : "Equipe pas ajouter";
	        }
	    } catch (SQLException se) {
	        
	        se.printStackTrace();
	        return "SQL Error: " + se.getMessage();
	    } catch (Exception e) {
	       
	        e.printStackTrace();
	        return "Error: " + e.getMessage();
	    } finally {
	        
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
	/**
     * Méthode pour supprimer une equipe.
     * @param team L'équipe à supprimer.
     * @return un message de reussite ou d'echoue
     **/
	@WebMethod(operationName = "deleteTeam")
	public String deleteTeam(@WebParam(name = "deleteteam") Team team) {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null; 

	    try {
	    
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection(DB_URL, USER, PASS);

	            String insertSql = "delete from teams where team_name = ?";
	            stmt = conn.prepareStatement(insertSql);
	            stmt.setString(1, team.getName());
	            

	            // Exécution de la requête SQL pour ajouter l'équipe
	            int affectedRows = stmt.executeUpdate();
	            return affectedRows > 0 ? "equipe supprimer avec succees" : "Equipe pas supprimer (probleme)";
	        
	    } catch (SQLException se) {
	        
	        se.printStackTrace();
	        return "SQL Error: " + se.getMessage();
	    } catch (Exception e) {
	       
	        e.printStackTrace();
	        return "Error: " + e.getMessage();
	    } finally {
	        
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
