package projet.web.client;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.ws.rs.core.GenericType;

import org.apache.cxf.jaxrs.client.WebClient;

import projet.web.foot.Modele.Team;
/**
 * Cette classe représente une interface graphique permettant de mettre à jour l'équipe d'un joueur en saisissant son nom.
 * Elle étend la classe JFrame et implémente l'interface ActionListener pour gérer les événements des boutons.
 * L'interface comprend un champ de texte pour saisir le nom du joueur, un menu déroulant pour sélectionner la nouvelle équipe du joueur,
 * et un bouton pour effectuer la mise à jour.
 * Lorsque l'utilisateur clique sur le bouton "Mettre à jour", une requête est envoyée à l'API pour mettre à jour l'équipe du joueur.
 * Si la mise à jour réussit, aucune confirmation n'est affichée, sinon une exception est levée.
 */
public class UpdateEquipeJoueur extends JFrame implements ActionListener{
	 private JTextField playerNameField; 
	    private JComboBox<String> teamComboBox;
	    private JButton updateButton;
	    private static final String BASE_URL = "http://localhost:8080/projet.web.foot/api/teams"; // Remplacez avec votre URL de base

	    public UpdateEquipeJoueur() {
	        super("Mettre à jour l'équipe du joueur par nom");
	        createUI();
	        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	        setSize(400, 200);
	        setLocationRelativeTo(null); // Centre la fenêtre
	    }

	    private void createUI() {
	        setLayout(new FlowLayout());

	        playerNameField = new JTextField(20); 
	        add(new JLabel("Nom du Joueur:")); 
	        add(playerNameField);

	        teamComboBox = new JComboBox<>();
	        add(new JLabel("Sélectionner une nouvelle équipe:"));
	        add(teamComboBox);
	        
	        updateButton = new JButton("Mettre à jour");
	        add(updateButton);

	        updateButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                try {
						FootApiClient.updateTeamOfPlayer(playerNameField.getText(), teamComboBox.getSelectedItem().toString());
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            }
	        });

	        // Chargement initial des équipes dans le JComboBox en faisant appel au serveur
	       loadTeams();
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
		public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                new UpdateEquipeJoueur().setVisible(true);
	            }
	        });
	    }

}
