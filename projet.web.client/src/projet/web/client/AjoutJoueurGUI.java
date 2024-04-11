package projet.web.client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.core.GenericType;

import org.apache.cxf.jaxrs.client.WebClient;

import projet.web.foot.Modele.Team;
/**
 * Cette classe représente l'interface graphique pour ajouter un joueur dans l'application Foot API Client.
 * Elle herite la classe JFrame et implémente l'interface ActionListener pour gérer les événements des boutons.
 * L'interface comprend des champs de texte pour saisir le nom du joueur, ainsi qu'un menu déroulant pour sélectionner
 * le poste du joueur et une liste déroulante pour choisir l'équipe (existante dans la bd) et recuperer depuis l'api à laquelle le joueur appartient.
 * Lorsque l'utilisateur clique sur le bouton "Ajouter", les données saisies sont récupérées,
 * puis une requête est envoyée à l'API pour ajouter le joueur à l'équipe sélectionnée.
 * Les équipes sont chargées depuis le serveur au démarrage de l'interface.
 * Les postes des joueurs sont prédéfinis et affichés dans le menu déroulant.
 */
public class AjoutJoueurGUI extends JFrame implements ActionListener {
	 private JLabel nameLabel, positionLabel, teamLabel;
	    private JTextField nameField, positionField;
	    private JComboBox<String> teamComboBox,positioncbb;
	    private JButton addButton;
	    private static final String BASE_URL = "http://localhost:8080/projet.web.foot/api/teams";

	    public AjoutJoueurGUI() {
	        setTitle("Ajouter un joueur");
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        setLayout(new BorderLayout());

	        JPanel inputPanel = new JPanel();
	        inputPanel.setLayout(new GridLayout(4, 2, 5, 5));
	        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

	        nameLabel = new JLabel("Nom:");
	        inputPanel.add(nameLabel);
	        nameField = new JTextField();
	        inputPanel.add(nameField);

	        positionLabel = new JLabel("Poste:");
	        inputPanel.add(positionLabel);
//	        positionField = new JTextField();
//	        inputPanel.add(positionField);
	        positioncbb = new JComboBox<>();
	        inputPanel.add(positioncbb);

	        teamLabel = new JLabel("Équipe:");
	        inputPanel.add(teamLabel);
	        teamComboBox = new JComboBox<>();
	        inputPanel.add(teamComboBox);

	        addButton = new JButton("Ajouter");
	        inputPanel.add(addButton);

	        addButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	if(!nameField.getText().isEmpty()) {
	                FootApiClient.addPlayer(nameField.getText(), teamComboBox.getSelectedItem().toString(), positioncbb.getSelectedItem().toString());
	                nameField.setText("");
	            	}else {
	            		JOptionPane.showMessageDialog(null, "Erreur");
	            	}
	            }
	        });

	        add(inputPanel, BorderLayout.CENTER);

	        setSize(300, 200);
	        setLocationRelativeTo(null);
	        setVisible(true);

	        // Charger les équipes existantes depuis le serveur
	        loadTeams();
	        poste();
	    }

	    private void loadTeams() {
	        List<Team> teams = getAllTeamsFromServer();
	        for (Team team : teams) {
	            teamComboBox.addItem(team.getName());
	        }
	    }
	    private List<Team> getAllTeamsFromServer() {
	        // Utilisation de WebClient pour appeller les equipes depuis notre service
	        WebClient client = WebClient.create(BASE_URL);
	        List<Team> teams = client.get(new GenericType<List<Team>>() {});
	        return teams;
	    }
	    private void poste() {
	        
	        List<String> teams = new ArrayList<>();
	        teams.add("Attaquant");
	        teams.add("Milieu");
	        teams.add("Defenseur");
	        teams.add("Gardien");

	        for (String team : teams) {
	            positioncbb.addItem(team);
	        }
	    }

	    

	    private void clearFields() {
	        nameField.setText("");
	        positionField.setText("");
	        teamComboBox.setSelectedIndex(0);
	    }



		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
}
