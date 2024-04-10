package projet.web.client;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.swing.JOptionPane;
import javax.ws.rs.*;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.*;
import javax.xml.namespace.QName;

import org.apache.cxf.jaxrs.client.*;

import foot.web.projet.client.FootWeb;
import projet.web.foot.FootWebService;
import projet.web.foot.Modele.Player;
import projet.web.foot.Modele.Team;
/**
 * Cette classe fournit des méthodes statiques pour interagir avec l'API REST du service Web Football.
 * Elle comprend des méthodes pour ajouter, supprimer et mettre à jour des équipes et des joueurs.
 * Les méthodes sont conçues pour effectuer des opérations HTTP appropriées (POST, DELETE, PUT) en fonction de l'action souhaitée.
 * Les URLs de base des ressources de l'API sont définies en tant que constantes.
 * Les méthodes utilisent la bibliothèque JAX-RS Client pour effectuer des appels REST aux services Web.
 * Les données sont échangées au format XML.
 * En cas de succès ou d'échec des opérations, des messages appropriés sont affichés dans la console.
 */
public class FootApiClient {
	 private static final String BASE_URL = "http://localhost:8080/projet.web.foot/api/teams";
	 private static final String BASE_URL2 = "http://localhost:8080/projet.web.foot/api/players/name";
	 private static final String BASE_URL1 = "http://localhost:8080/projet.web.foot/api/players";
	 private static final QName SERVICE_NAME = new QName("http://www.projet.web.foot", "FootWeb");
	private static foot.web.projet.client.Team team = new foot.web.projet.client.Team();
	private static foot.web.projet.client.Player p = new foot.web.projet.client.Player();
	 public static void addTeam(String teamName, String coachName, String league) {
		    // Créer l'URL du service web en fonction de l'emplacement du WSDL
			URL wsdlURL = FootWeb.WSDL_LOCATION;

			// Créer le service web et obtenir le port
			FootWeb service = new FootWeb(wsdlURL, SERVICE_NAME);
			foot.web.projet.client.FootWebService port = service.getFootService();		
//			team = null;
			team.setName(teamName);
			team.setCoachName(coachName);
			team.setLeague(league);

			// Appeler la méthode addTeam sur le port du service web
			String result = port.addTeam(team);

			// Afficher le résultat de l'ajout de l'équipe
			System.out.println(result);
			JOptionPane.showMessageDialog(null, result);
		}
	 public static void addPlayer(String playerName, String teamName, String Poste) {
		    // Créer l'URL du service web en fonction de l'emplacement du WSDL
			URL wsdlURL = FootWeb.WSDL_LOCATION;

			// Créer le service web et obtenir le port
			FootWeb service = new FootWeb(wsdlURL, SERVICE_NAME);
			foot.web.projet.client.FootWebService port = service.getFootService();		
//			team = null;
			p.setPrenom(playerName);
			p.setTeamName(teamName);
			p.setPoste(Poste);;

			// Appeler la méthode addTeam sur le port du service web
			String result = port.addPlayer(p);

			// Afficher le résultat de l'ajout de l'équipe
			System.out.println(result);
			JOptionPane.showMessageDialog(null, result);
		}
	 
	 //cette fonction etait pour la version du projet avec JAX-RS seulement
//	 public static Integer addTeam(String teamName, String coachName, String league) {
//		    System.out.print("Adding team " + teamName + "... ");
//		    WebClient c = WebClient.create(BASE_URL).type(MediaType.APPLICATION_XML);
//		    Team team = new Team();
//		    team.setCoachName(coachName);
//		    team.setName(teamName);
//		    team.setLeague(league);
//		    Response r = c.post(team);
//		    if (r.getStatus() == 400) {
//		        System.out.println("Oops!");
//		        return null;
//		    }
//		    String locationHeader = r.getHeaderString("Location");
//		    if (locationHeader == null) {
//		        System.out.println("Error: Location header not found in response.");
//		        return null;
//		    }
//		    System.out.println("OK.");
//		    // Extracting the ID from the location header
//		    String idString = locationHeader.substring(locationHeader.lastIndexOf('/') + 1);
//		    return Integer.parseInt(idString);
//		}
	 //c'est l'ancienne methode avec JAX-RS
	    public static boolean deleteTeamByName(String teamName) {
	        WebClient client = WebClient.create(BASE_URL);
	        client.path(teamName); // Ajout du nom de l'équipe à l'URL

	        client.delete(); // Envoi de la requête DELETE
	        
	        // Vérification de la réponse
	        int status = client.getResponse().getStatus();
	        return status == 200; // Si la suppression a réussi, retourne true
	    }
	    
	    public static boolean deletePlayerByName(String playerName) {
	    	WebClient c = WebClient.create(BASE_URL2);
	    	c.path(playerName);
	    	c.delete();
	    	int status = c.getResponse().getStatus();
	    	return status == 200;
	    }
		 public static void deleteTeam(String teamName) {
			    // Créer l'URL du service web en fonction de l'emplacement du WSDL
				URL wsdlURL = FootWeb.WSDL_LOCATION;

				// Créer le service web et obtenir le port
				FootWeb service = new FootWeb(wsdlURL, SERVICE_NAME);
				foot.web.projet.client.FootWebService port = service.getFootService();		

				team.setName(teamName);
				

				// Appeler la méthode addTeam sur le port du service web
				String result = port.deleteTeam(team);

				// Afficher le résultat de l'ajout de l'équipe
				System.out.println(result);
				JOptionPane.showMessageDialog(null, result);
			}
//	    public static boolean addPlayer(String namePlayer,String Poste, String teamName ) {
//	    	WebClient client = WebClient.create(BASE_URL1).type(MediaType.APPLICATION_XML);
////	    	c.pos
//	    	Player p = new Player();
//	    	p.setPoste(Poste);
//	    	p.setPrenom(namePlayer);
//	    	p.setTeamName(teamName);
//	    	Response r =  client.post(p);
//	        if (r.getStatus() == Response.Status.CREATED.getStatusCode()) {
//	            System.out.println("Le joueur a été ajouté avec succès.");
//	            return true;
//	        } else {
//	            System.out.println("Une erreur s'est produite lors de l'ajout du joueur.");
//	            return false;
//	        }
//	    }
	    public static boolean updateTeamOfPlayer(String namePlayer, String teamName) throws UnsupportedEncodingException{
	    	// Encoder le nom du joueur pour l'inclure dans l'URL
	    	String baseUrl = "http://localhost:8080/projet.web.foot/api/players";
//	        String encodedPlayerName = URLEncoder.encode(namePlayer, StandardCharsets.UTF_8.toString());

	        // Construire l'URL finale avec le nom du joueur
//	        String updateUrl = String.format("%s/%s/team", baseUrl, encodedPlayerName);
	    	WebClient client = WebClient.create(baseUrl).type(MediaType.APPLICATION_XML);
	    	client.path(namePlayer);
	    	client.path(teamName);
	    	
	    	 Response response = client.put(namePlayer);

	         // Vérification du code de réponse
	         if (response.getStatus() == Response.Status.OK.getStatusCode()) {
//	             System.out.println("Mise à jour réussie.");
	        	 JOptionPane.showMessageDialog(null,"Mise à jour réussie.");
	             return true;
	         } else {
	        	 JOptionPane.showMessageDialog(null,"Joueur inexistant !!");
//	             System.out.println("Échec de la mise à jour. Code de réponse : " + response.getStatus());''
	             return false;
	         }
	    }

}
