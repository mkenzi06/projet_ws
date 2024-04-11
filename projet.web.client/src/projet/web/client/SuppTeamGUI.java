package projet.web.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.ws.rs.core.GenericType;

import org.apache.cxf.jaxrs.client.WebClient;

import projet.web.foot.Modele.Team;
/**
 * Cette classe représente une interface graphique permettant de supprimer une équipe.
 * Elle étend la classe JFrame et implémente l'interface ActionListener pour gérer les événements des boutons.
 * L'interface comprend un champ de texte pour saisir l'identifiant de l'équipe à supprimer et un bouton pour effectuer la suppression.
 * Lorsque l'utilisateur clique sur le bouton "Supprimer équipe", une requête est envoyée à l'API pour supprimer l'équipe avec l'identifiant spécifié.
 * Si la suppression réussit, un message de confirmation est affiché. Sinon, un message d'erreur est affiché.
 */
public class SuppTeamGUI extends JFrame implements ActionListener{
    private JFrame frame;
//    private JTextField teamIdField;
    private JComboBox<String> jcbb;
    private JButton deleteButton;
    private static final String SERVER_URL = "http://localhost:8080/projet.web.foot/api/teams"; // Mettez votre URL du serveur ici

    public SuppTeamGUI() {
        frame = new JFrame("Delete Team");
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(this);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

//        teamIdField = new JTextField();
        jcbb = new JComboBox<String>();
        jcbb.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, jcbb.getPreferredSize().height));
        frame.add(jcbb);

        deleteButton = new JButton("Delete Team");
        frame.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	FootApiClient.deleteTeam(jcbb.getSelectedItem().toString());
            	frame.dispose();
            	//quand c'etait avec jax-rs seulement
//                String teamId = teamIdField.getText();
//                boolean success = FootApiClient.deleteTeamByName(jcbb.getSelectedItem().toString());
//                if (success) {
//                    JOptionPane.showMessageDialog(frame, "Team deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
//                } else {
//                    JOptionPane.showMessageDialog(frame, "Failed to delete team.", "Error", JOptionPane.ERROR_MESSAGE);
//                }
            }
        });
        loadTeams();
        frame.setVisible(true);
    }
    private void loadTeams() {
        List<Team> teams = getAllTeamsFromServer();
        for (Team team : teams) {
            jcbb.addItem(team.getName());
        }
    }
    private List<Team> getAllTeamsFromServer() {
        // Utilisation de WebClient pour recuperer les teams depuis notre serveur (sans passer par la bd directement)
        WebClient client = WebClient.create(SERVER_URL);
        List<Team> teams = client.get(new GenericType<List<Team>>() {});
        return teams;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SuppTeamGUI();
            }
        });
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
