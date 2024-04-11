package projet.web.client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.cxf.jaxrs.client.Client;
import org.apache.cxf.jaxrs.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import projet.web.foot.Modele.Player;
import projet.web.foot.Modele.Team;
/**
 * Cette classe représente l'interface graphique pour afficher les joueurs d'une équipe sélectionnée.
 * Elle étend la classe JFrame et implémente l'interface ActionListener pour gérer les événements des boutons.
 * L'interface comprend un menu déroulant pour sélectionner une équipe parmi celles disponibles,
 * ainsi qu'un bouton pour afficher les joueurs de l'équipe sélectionnée dans un tableau.
 * Lorsque l'utilisateur clique sur le bouton "Afficher joueurs", une requête est envoyée à l'API pour récupérer les joueurs de l'équipe sélectionnée.
 * Les données des joueurs sont récupérées au format JSON et affichées dans un tableau.
 * En cas d'erreur lors de la récupération des données ou de l'affichage des joueurs, un message d'erreur est affiché.
 */
public class PlayersOfTeamGUI extends JFrame implements ActionListener {
	private JComboBox<String> teamComboBox;
	private static final String BASE_URL = "http://localhost:8080/projet.web.foot/api/teams";
    private JTable playersTable;
    private JButton showPlayersButton;

    public PlayersOfTeamGUI() {
        setTitle("Liste des joueurs par équipe");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        teamComboBox = new JComboBox<>();
        panel.add(teamComboBox, BorderLayout.NORTH);

        showPlayersButton = new JButton("Afficher joueurs");
        panel.add(showPlayersButton, BorderLayout.SOUTH);

        // JTable pour afficher les joueurs
        playersTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(playersTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);

        // Ajouter un action lisetener au bouton "Afficher joueurs"
        showPlayersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayPlayers();
            }
        });
        loadTeams();
    }
    private void displayPlayers() {
        String selectedTeam = (String) teamComboBox.getSelectedItem();
        if (selectedTeam != null) {
            String playersJson = getPlayersOfTeam(selectedTeam);
            if (playersJson != null) {
                try {
                    ObjectMapper mapper = new ObjectMapper();//permet de serialiser ou deserialiser des donnees json
                    JsonNode rootNode = mapper.readTree(playersJson);//permet de mapper le playersJson (qui est en Json) en un objet pour faciliter la recuperation des donnees
                    JsonNode playersNode = rootNode.get("player");//on recupere l'objet Json player
                    
                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("Nom");
                    model.addColumn("Poste");

                    for (JsonNode playerNode : playersNode) {
                        String prenom = playerNode.get("prenom").asText(); //on recupere chaque nom du noeud player
                        String poste = playerNode.get("poste").asText();
                        model.addRow(new Object[]{prenom, poste});
                    }

                    playersTable.setModel(model);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Impossible de récupérer les joueurs de l'équipe sélectionnée.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Méthode pour récupérer les joueurs d'une equipe depuis l'API
    private String getPlayersOfTeam(String teamName) {
        String apiUrl = "http://localhost:8080/projet.web.foot/api/teams/" + teamName + "/player"; // Remplacez cette URL par votre URL réelle
        WebClient client = WebClient.create(apiUrl)
                .accept(MediaType.APPLICATION_JSON);
        String players = client.get(String.class);
//        System.out.println(players);
        return players;
    }
    
   
 
    private void loadTeams() {
        List<Team> teams = getAllTeamsFromServer();
        for (Team team : teams) {
            teamComboBox.addItem(team.getName());
        }
    }
    private List<Team> getAllTeamsFromServer() {
        // Utilisation de WebClient ou toute autre méthode appropriée pour appeler le service REST
        WebClient client = WebClient.create(BASE_URL);
        List<Team> teams = client.get(new GenericType<List<Team>>() {});
        return teams;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
