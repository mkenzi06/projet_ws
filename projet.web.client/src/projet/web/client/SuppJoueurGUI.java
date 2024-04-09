package projet.web.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SuppJoueurGUI extends JFrame implements ActionListener {
	private JFrame frame;
    private JTextField teamIdField;
    private JButton deleteButton;

    public SuppJoueurGUI() {
        frame = new JFrame("Delete Team");
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(this);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        teamIdField = new JTextField();
//        teamIdField.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, teamIdField.getPreferredSize().height));
        frame.add(teamIdField);

        deleteButton = new JButton("Delete player");
        frame.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String teamId = teamIdField.getText();
                boolean success = FootApiClient.deletePlayerByName(teamId);
                if (success) {
                    JOptionPane.showMessageDialog(frame, "player deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    teamIdField.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to delete player.", "Error", JOptionPane.ERROR_MESSAGE);
                    teamIdField.setText("");
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SuppJoueurGUI();
            }
        });
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
