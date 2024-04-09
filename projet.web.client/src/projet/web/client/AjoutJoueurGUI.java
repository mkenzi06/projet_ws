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
	                FootApiClient.addPlayer(nameField.getText(), positioncbb.getSelectedItem().toString(), teamComboBox.getSelectedItem().toString());
	                nameField.setText("");
	                
	            }
	        });

	        add(inputPanel, BorderLayout.CENTER);

	        setSize(300, 200);
	        setLocationRelativeTo(null);
	        setVisible(true);

	        // Charger les équipes existantes depuis la base de données
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
	        // Utilisation de WebClient ou toute autre méthode appropriée pour appeler le service REST
	        WebClient client = WebClient.create(BASE_URL);
	        List<Team> teams = client.get(new GenericType<List<Team>>() {});
	        return teams;
	    }
	    private void poste() {
	        // Ici vous pouvez implémenter la logique pour récupérer les équipes depuis la base de données
	        // Pour cet exemple, je vais juste simuler des équipes fictives
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
