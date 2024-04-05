package projet.web.foot.Modele;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.cxf.jaxrs.json.basic.JsonObject;
import org.codehaus.jettison.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.net.HttpURLConnection;
import java.net.*;

public class ExternalApiCalls {
	 private static final String API_KEY = "49a6fe0f3e05e8663918b09f83edf5bf14953828f142f7abc6ea9a83ca47d877";
	    private static final String BASE_URL = "https://apiv3.apifootball.com/";

	    public String getTeamsLiga() throws JSONException {
	        StringBuffer responseContent = new StringBuffer();
	        StringBuffer responseteam = new StringBuffer();
	        try {
	            URL url = new URL(BASE_URL + "?action=get_teams&league_id=" + 302 + "&APIkey=" + API_KEY);
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

	            // Configuration de la requête
	            connection.setRequestMethod("GET");
	            connection.setConnectTimeout(5000);
	            connection.setReadTimeout(5000);

	            // Création d'un BufferedReader pour lire la réponse
	            int status = connection.getResponseCode();
	            BufferedReader reader;
	            if (status > 299) {
	                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
	                String line;
	                while ((line = reader.readLine()) != null) {
	                    responseContent.append(line);
	                }
	                reader.close();
	            } else {
	                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	                String line;
	                while ((line = reader.readLine()) != null) {
	                    responseContent.append(line);
	                }
	                reader.close();
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	        JSONArray j = new JSONArray(responseContent.toString());
	        for (int i = 0; i < j.length(); i++) {
	        	org.json.JSONObject je = j.getJSONObject(i);
				responseteam.append(je.getString("team_name")+"\n");
			}
	        return responseteam.toString();
	    }
	    public String getPlayersData(String playerName) throws JSONException {
	        StringBuffer responseContent = new StringBuffer();
	        StringBuffer responsePlayers = new StringBuffer();
	        try {
	            // Construire l'URL avec le nom du joueur
	            String encodedPlayerName = java.net.URLEncoder.encode(playerName, "UTF-8");
	            URL url = new URL(BASE_URL + "?action=get_players&player_name=" + encodedPlayerName + "&APIkey=" + API_KEY);
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

	            // Configuration de la requête
	            connection.setRequestMethod("GET");
	            connection.setConnectTimeout(5000);
	            connection.setReadTimeout(5000);

	            // Création d'un BufferedReader pour lire la réponse
	            int status = connection.getResponseCode();
	            BufferedReader reader;
	            if (status > 299) {
	                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
	                String line;
	                while ((line = reader.readLine()) != null) {
	                    responseContent.append(line);
	                }
	                reader.close();
	            } else {
	                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	                String line;
	                while ((line = reader.readLine()) != null) {
	                    responseContent.append(line);
	                }
	                reader.close();
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }

	        JSONArray playersArray = new JSONArray(responseContent.toString());
	        for (int i = 0; i < playersArray.length(); i++) {
	            org.json.JSONObject playerObject = playersArray.getJSONObject(i);
	            // Ajouter les données pertinentes du joueur à la chaîne de réponse
	            responsePlayers.append("Nom: " + playerObject.getString("player_name") + "\n");
	            responsePlayers.append("Âge: " + playerObject.getString("player_age") + "\n");
	            responsePlayers.append("Nombre de matchs joués: " + playerObject.getString("player_match_played") + "\n");
	            responsePlayers.append("Nombre de buts : "+playerObject.getString("player_goals")+"\n");
	            // Ajouter d'autres données pertinentes au besoin
	            responsePlayers.append("\n");
	        }

	        return responsePlayers.toString();
	    }
}
