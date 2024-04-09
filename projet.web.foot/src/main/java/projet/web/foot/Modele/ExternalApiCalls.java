package projet.web.foot.Modele;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.ehcache.CacheManager;
import org.ehcache.core.Ehcache;
import org.ehcache.core.EhcacheManager;
import org.apache.cxf.jaxrs.json.basic.JsonObject;
import org.codehaus.jettison.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.net.HttpURLConnection;
import java.net.*;

public class ExternalApiCalls {
	 private static final String API_KEY = "c9b0830b910939bf1968d81d5a5278ff44c1c2335632a04ce50e7f22210c114c";
	    private static final String BASE_URL = "https://apiv3.apifootball.com/";
	   

	    public String getTeamsLiga() throws JSONException {
	        StringBuffer responseContent = new StringBuffer();
	        List<String> teamNames = new ArrayList<String>();
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
	        
	        JSONArray jsonArray = new JSONArray(responseContent.toString());
	        
	        for (int i = 0; i < jsonArray.length(); i++) {
	            org.json.JSONObject jsonObject = jsonArray.getJSONObject(i);
	            String teamName = jsonObject.getString("team_name");
	            teamNames.add(teamName);
	        }
	        
	        JSONArray resultArray = new JSONArray(teamNames);
	        return resultArray.toString();
	    }
	    public String getTeamsBundes() throws JSONException {
	        StringBuffer responseContent = new StringBuffer();
	        List<String> teamNames = new ArrayList<String>();
	        try {
	            URL url = new URL(BASE_URL + "?action=get_teams&league_id=" + 175 + "&APIkey=" + API_KEY);
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
	        
	        JSONArray jsonArray = new JSONArray(responseContent.toString());
	        
	        for (int i = 0; i < jsonArray.length(); i++) {
	            org.json.JSONObject jsonObject = jsonArray.getJSONObject(i);
	            String teamName = jsonObject.getString("team_name");
	            teamNames.add(teamName);
	        }
	        
	        JSONArray resultArray = new JSONArray(teamNames);
	        return resultArray.toString();
	    }
	    public String getTeamsLigue1() throws JSONException {
	        StringBuffer responseContent = new StringBuffer();
	        List<String> teamNames = new ArrayList<String>();
	        try {
	            URL url = new URL(BASE_URL + "?action=get_teams&league_id=" + 168 + "&APIkey=" + API_KEY);
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
	        
	        JSONArray jsonArray = new JSONArray(responseContent.toString());
	        
	        for (int i = 0; i < jsonArray.length(); i++) {
	            org.json.JSONObject jsonObject = jsonArray.getJSONObject(i);
	            String teamName = jsonObject.getString("team_name");
	            teamNames.add(teamName);
	        }
	        
	        JSONArray resultArray = new JSONArray(teamNames);
	        return resultArray.toString();
	    }
	    public String getTeamsPL() throws JSONException {
	        StringBuffer responseContent = new StringBuffer();
	        List<String> teamNames = new ArrayList<String>();
	        try {
	            URL url = new URL(BASE_URL + "?action=get_teams&league_id=" + 152 + "&APIkey=" + API_KEY);
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
	        
	        JSONArray jsonArray = new JSONArray(responseContent.toString());
	        
	        for (int i = 0; i < jsonArray.length(); i++) {
	            org.json.JSONObject jsonObject = jsonArray.getJSONObject(i);
	            String teamName = jsonObject.getString("team_name");
	            teamNames.add(teamName);
	        }
	        
	        JSONArray resultArray = new JSONArray(teamNames);
	        return resultArray.toString();
	    }
	    public String getTeamsSeria() throws JSONException {
	        StringBuffer responseContent = new StringBuffer();
	        List<String> teamNames = new ArrayList<String>();
	        try {
	            URL url = new URL(BASE_URL + "?action=get_teams&league_id=" + 207 + "&APIkey=" + API_KEY);
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
	        
	        JSONArray jsonArray = new JSONArray(responseContent.toString());
	        
	        for (int i = 0; i < jsonArray.length(); i++) {
	            org.json.JSONObject jsonObject = jsonArray.getJSONObject(i);
	            String teamName = jsonObject.getString("team_name");
	            teamNames.add(teamName);
	        }
	        
	        JSONArray resultArray = new JSONArray(teamNames);
	        return resultArray.toString();
	    }
		public String getPlayersData(String playerName) throws JSONException, org.codehaus.jettison.json.JSONException {
			StringBuffer responseContent = new StringBuffer();
			JSONArray responsePlayers = new JSONArray();
			
			try {
				// Construire l'URL avec le nom du joueur
//				String encodedPlayerName = URLEncoder.encode(playerName, "UTF-8");
				URL url = new URL(BASE_URL + "?action=get_players&player_name=" + playerName + "&APIkey=" + API_KEY);
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
				} else {
					reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				}
				
				String line;
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
				
				JSONArray playersArray = new JSONArray(responseContent.toString());
				for (int i = 0; i < playersArray.length(); i++) {
					org.json.JSONObject playerObject = playersArray.getJSONObject(i);
					JSONObject playerData = new JSONObject();
					// Ajouter les données pertinentes du joueur à l'objet JSON
					playerData.put("Nom", playerObject.getString("player_name"));
					playerData.put("Âge", playerObject.getString("player_age"));
					playerData.put("Nombre de matchs joués", playerObject.getString("player_match_played"));
					playerData.put("Nombre de buts", playerObject.getString("player_goals"));
					playerData.put("Nombre de passe d", playerObject.getString("player_assists"));
					playerData.put("Photo", playerObject.getString("player_image"));
					// Ajouter d'autres données pertinentes au besoin
					responsePlayers.put(playerData);
				}

			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}

			return responsePlayers.toString();
		}
		public String getPredictions(String fromDate,String toDate) {
	        StringBuffer responseContent = new StringBuffer();
	        try {
	            URL url = new URL(BASE_URL + "?action=get_predictions&from=" + fromDate + "&to=" + toDate  + "&league_id=" + 152 +"&APIkey=" + API_KEY);
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
	            connection.disconnect();
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }

	        return responseContent.toString();
	    }

}
