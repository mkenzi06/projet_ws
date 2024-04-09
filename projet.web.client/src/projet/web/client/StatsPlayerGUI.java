package projet.web.client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jettison.json.JSONArray;
import org.json.JSONObject;

public class StatsPlayerGUI extends JFrame implements ActionListener{
	private JTable table;
    private JLabel imageLabel;

    public StatsPlayerGUI() {
        setTitle("Statistiques des Joueurs");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panneau pour la recherche
        JPanel searchPanel = new JPanel();
        JTextField searchTextField = new JTextField(20);
        JButton searchButton = new JButton("Rechercher");
        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        // Configuration du JTable et du JLabel pour les images
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        imageLabel = new JLabel();
        add(imageLabel, BorderLayout.EAST);

        // Action du bouton de recherche
        searchButton.addActionListener(e -> fetchPlayersData(searchTextField.getText()));

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    String imageUrl = (String) table.getValueAt(row, 5); // Assurez-vous que l'index de la colonne est correct
                    setImage(imageUrl);
                }
            }
        });
    }
    
    private void fetchPlayersData(String playerName) {
        // Remplacez BASE_URL par votre URL de base réelle
        String baseUrl = "http://localhost:8080/projet.web.foot/api/players/stats";

        try {
            // Création du client WebClient
            WebClient client = WebClient.create(baseUrl);
            client = client.accept(MediaType.APPLICATION_JSON); // Assurez-vous de configurer le type de contenu attendu
            client.path(playerName);
            // Envoi de la requête GET et récupération de la réponse
            String response = client.get(String.class);
            System.out.println(response);
            // Traitement de la réponse JSON
            JSONArray players = new JSONArray(response);
            String[] columnNames = {"Nom", "Âge", "Matchs joués", "Buts", "Passes", "Photo"};
            Object[][] data = new Object[players.length()][6];

            for (int i = 0; i < players.length(); i++) {
                Object playerObject = players.get(i);
                if (playerObject instanceof org.codehaus.jettison.json.JSONObject) {
                    org.codehaus.jettison.json.JSONObject player = (org.codehaus.jettison.json.JSONObject) playerObject;
                    // Traitement de l'objet JSON comme avant
                    data[i][0] = player.getString("Nom");
                    data[i][1] = player.getString("Âge");
                    data[i][2] = player.getString("Nombre de matchs joués");
                    data[i][3] = player.getString("Nombre de buts");
                    data[i][4] = player.getString("Nombre de passe d");
                    data[i][5] = player.getString("Photo");
                } else if (playerObject instanceof String) {
                    // Si l'élément est une chaîne JSON, essayez de le convertir en objet JSON
                    try {
                        org.codehaus.jettison.json.JSONObject player = new org.codehaus.jettison.json.JSONObject((String) playerObject);
                        // Traitement de l'objet JSON comme avant
                        data[i][0] = player.getString("Nom");
                        data[i][1] = player.getString("Âge");
                        data[i][2] = player.getString("Nombre de matchs joués");
                        data[i][3] = player.getString("Nombre de buts");
                        data[i][4] = player.getString("Nombre de passe d");
                        data[i][5] = player.getString("Photo");
                    } catch (org.codehaus.jettison.json.JSONException e) {
                        // Gérer les erreurs de conversion en objet JSON
                        System.err.println("Erreur lors de la conversion de la chaîne JSON en objet JSON : " + e.getMessage());
                    }
                } else {
                    // Gérer le cas où l'élément n'est ni un objet JSON ni une chaîne JSON
                    System.err.println("Élément non valide à l'index " + i + " : " + playerObject.toString());
                }
            }

            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            table.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void setImage(String imageUrl) {
        try {
            if (imageUrl != null && !imageUrl.isEmpty()) {
                URL url = new URL(imageUrl);
                ImageIcon imageIcon = new ImageIcon(ImageIO.read(url));
                imageLabel.setIcon(imageIcon);
            } else {
                // Gérer le cas où l'URL de l'image est vide ou null
                // Peut-être afficher un message d'erreur ou laisser l'imageLabel vide
                System.err.println("L'URL de l'image est vide ou null.");
            }
        } catch (Exception ex) {
        	JOptionPane.showMessageDialog(this, "une erreur avec la photo");
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StatsPlayerGUI().setVisible(true);
        });
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}