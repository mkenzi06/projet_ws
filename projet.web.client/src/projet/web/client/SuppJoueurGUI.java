package projet.web.client;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Cette classe représente une interface graphique permettant de supprimer un
 * joueur. Elle étend la classe JFrame et implémente l'interface ActionListener
 * pour gérer les événements des boutons. L'interface comprend un champ de texte
 * pour saisir l'identifiant du joueur à supprimer et un bouton pour effectuer
 * la suppression. Lorsque l'utilisateur clique sur le bouton "Supprimer
 * joueur", une requête est envoyée à l'API pour supprimer le joueur avec
 * l'identifiant spécifié. Si la suppression réussit, un message de confirmation
 * est affiché. Sinon, un message d'erreur est affiché.
 */
public class SuppJoueurGUI extends JFrame implements ActionListener {
	private JFrame frame;
	private JTextField teamIdField;
	private JButton deleteButton;

	public SuppJoueurGUI() {
		frame = new JFrame("Supprimer un joueur");
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(this);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		teamIdField = new JTextField();
		teamIdField.setMaximumSize(new Dimension(Integer.MAX_VALUE, teamIdField.getPreferredSize().height));;
		frame.add(teamIdField);

		deleteButton = new JButton("Supprimer player");
		frame.add(deleteButton);

		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String teamId = teamIdField.getText();
				boolean success = FootApiClient.deletePlayerByName(teamId);
				if (success) {
					JOptionPane.showMessageDialog(frame, "player supprimee avec succees!", "Info",
							JOptionPane.INFORMATION_MESSAGE);
					teamIdField.setText("");
				} else {
					JOptionPane.showMessageDialog(frame, "Erreur lors de la suppression.", "Error",
							JOptionPane.ERROR_MESSAGE);
					teamIdField.setText("");
				}
			}
		});

		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
