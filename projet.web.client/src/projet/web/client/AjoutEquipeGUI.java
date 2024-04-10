package projet.web.client;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/**
 * Cette classe représente l'interface graphique pour ajouter une équipe dans l'application Foot API Client.
 * Elle herite la classe JFrame et implémente l'interface ActionListener pour gérer les événements des boutons.
 * L'interface comprend des champs de texte pour saisir le nom de l'équipe, le nom de l'entraîneur et le nom de la ligue,
 * ainsi qu'un bouton pour ajouter l'équipe.
 * Lorsque l'utilisateur clique sur le bouton "Add Team", les données saisies sont récupérées,
 * puis une requête est envoyée à l'API pour ajouter l'équipe.
 * Si l'ajout est réussi, un message de succès est affiché avec l'identifiant de l'équipe ajoutée.
 * Sinon, un message d'erreur est affiché.
 */
public class AjoutEquipeGUI extends JFrame implements ActionListener{
	private JTextField teamNameField;
    private JTextField coachNameField;
    private JTextField ligueNameField;
    private JButton addButton;

    public AjoutEquipeGUI() {
        setTitle("Foot API Client");
        setSize(300, 200);
        setLocationRelativeTo(this);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 3));

        JLabel teamNameLabel = new JLabel("Team Name:");
        add(teamNameLabel);

        teamNameField = new JTextField();
        add(teamNameField);

        JLabel coachNameLabel = new JLabel("Coach Name:");
        add(coachNameLabel);

        coachNameField = new JTextField();
        add(coachNameField);
        JLabel ligueNameLabel = new JLabel("Ligue Name:");
        add(ligueNameLabel);

        ligueNameField = new JTextField();
        add(ligueNameField);

        addButton = new JButton("Add Team");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTeam();
                coachNameField.setText("");
                teamNameField.setText("");
                coachNameField.setText("");
            }
        });
        add(addButton);
    }
    private void addTeam() {
        String teamName = teamNameField.getText();
        String coachName = coachNameField.getText();
        String ligue = ligueNameField.getText();
        // Call the API method to add team
        FootApiClient.addTeam(teamName, coachName,ligue);
     
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AjoutEquipeGUI().setVisible(true);
            }
        });

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
