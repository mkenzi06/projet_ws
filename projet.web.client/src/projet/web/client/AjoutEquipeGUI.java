package projet.web.client;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
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
            }
        });
        add(addButton);
    }
    private void addTeam() {
        String teamName = teamNameField.getText();
        String coachName = coachNameField.getText();
        String ligue = ligueNameField.getText();
        // Call the API method to add team
        Integer teamId = FootApiClient.addTeam(teamName, coachName,ligue);
        if (teamId != null) {
            JOptionPane.showMessageDialog(this, "Team added successfully with ID: " + teamId, "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add team.", "Error", JOptionPane.ERROR_MESSAGE);
        }
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
