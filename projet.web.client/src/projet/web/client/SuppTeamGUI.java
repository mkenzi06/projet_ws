package projet.web.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SuppTeamGUI extends JFrame implements ActionListener{
    private JFrame frame;
    private JTextField teamIdField;
    private JButton deleteButton;

    public SuppTeamGUI() {
        frame = new JFrame("Delete Team");
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(this);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        teamIdField = new JTextField();
        teamIdField.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, teamIdField.getPreferredSize().height));
        frame.add(teamIdField);

        deleteButton = new JButton("Delete Team");
        frame.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String teamId = teamIdField.getText();
                boolean success = FootApiClient.deleteTeamByName(teamId);
                if (success) {
                    JOptionPane.showMessageDialog(frame, "Team deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to delete team.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
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
