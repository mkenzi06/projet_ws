package projet.web.client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;

import projet.web.foot.Modele.Team;
/**
 * Cette classe représente une interface graphique permettant de mettre à jour les informations d'une équipe.
 * Elle étend la classe JFrame et implémente l'interface ActionListener pour gérer les événements des boutons.
 * L'interface comprend un champ de sélection pour choisir une équipe, un champ de texte pour saisir le nom du nouveau coach,
 * et un bouton pour effectuer la mise à jour.
 * Lorsque l'utilisateur clique sur le bouton "Mettre à jour", une requête est envoyée à l'API pour mettre à jour le coach de l'équipe sélectionnée.
 * Si la mise à jour réussit, un message de confirmation est affiché. Sinon, un message d'erreur est affiché.
 */
public class TeamUpdateGUI extends JFrame implements ActionListener{
	private JComboBox<String> teamComboBox;
    private JTextField coachNameTextField;
    private JButton updateButton;
    private JLabel coachname;

    private static final String SERVER_URL = "http://localhost:8080/projet.web.foot/api/teams";

    public TeamUpdateGUI() {
        setTitle("Mise à jour d'équipe");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(this);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new GridLayout(4, 1));

        teamComboBox = new JComboBox<>();
        coachNameTextField = new JTextField();
        updateButton = new JButton("Mettre à jour");
        coachname = new JLabel("Nom du nouveau coach");
        
        contentPane.add(teamComboBox);
        contentPane.add(coachname);
        contentPane.add(coachNameTextField);
        contentPane.add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                String selectedTeamName = (String) teamComboBox.getSelectedItem();
                String newCoachName = coachNameTextField.getText();
                
                if (selectedTeamName != null && !selectedTeamName.isEmpty() && newCoachName != null && !newCoachName.isEmpty()) {
                    try {
                    	WebClient client = WebClient.create(SERVER_URL).type(MediaType.APPLICATION_XML);
            	    	
            	    	client.path(selectedTeamName);
            	    	Team t = new Team();
            	    	t.setCoachName(newCoachName);
            	    	 Response response = client.put(t);

            	         // Vérification du code de réponse
            	         if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            	             System.out.println("Mise à jour réussie.");
            	           
            	         } else {
            	             System.out.println("Échec de la mise à jour. Code de réponse : " + response.getStatus());
            	            
            	         }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erreur lors de la communication avec le serveur: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner une équipe et saisir le nom du nouveau coach.","erreur",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(contentPane);
        setVisible(true);
        loadTeams();
    }
    private void loadTeams() {
        List<Team> teams = getAllTeamsFromServer();
        for (Team team : teams) {
            teamComboBox.addItem(team.getName());
        }
    }
    private List<Team> getAllTeamsFromServer() {
    	//methode utiliser un peu partout qui recupere les equipes (teams) depuis le serveur on faisant une requete
        WebClient client = WebClient.create(SERVER_URL);
        List<Team> teams = client.get(new GenericType<List<Team>>() {});
        return teams;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TeamUpdateGUI();
            }
        });
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
