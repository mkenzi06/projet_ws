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
/**
 * Cette classe représente une interface graphique permettant de rechercher et d'afficher les statistiques des joueurs.
 * Elle herite la classe JFrame et implémente l'interface ActionListener pour gérer les événements des boutons.
 * L'interface comprend un champ de recherche pour saisir le nom du joueur et un bouton pour effectuer la recherche.
 * Les statistiques des joueurs sont récupérées à partir de l'API REST et affichées dans un tableau.
 * L'utilisateur peut cliquer sur une ligne du tableau pour afficher la photo du joueur correspondant à cette ligne.
 * La photo du joueur est affichée dans un JLabel à droite du tableau.
 * Lorsque l'utilisateur clique sur le bouton "Rechercher", une requête est envoyée à l'API pour récupérer les statistiques du joueur spécifié.
 * Les statistiques sont affichées dans un tableau avec les colonnes suivantes : Nom, Âge, Matchs joués, Buts, Passes, Photo.
 * En cas d'erreur lors de la récupération des données ou de l'affichage des statistiques, un message d'erreur est affiché.
 */
public class StatsPlayerGUI extends JFrame implements ActionListener{
	private JTable table;
    private JLabel imageLabel;

    public StatsPlayerGUI() {
        setTitle("Statistiques des Joueurs");
        setSize(800, 600);
        setLocationRelativeTo(this);
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

        searchButton.addActionListener(e -> fetchPlayersData(searchTextField.getText()));

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    String imageUrl = (String) table.getValueAt(row, 5); 
                    setImage(imageUrl);
                }
            }
        });
    }
    
    private void fetchPlayersData(String playerName) {
        String baseUrl = "http://localhost:8080/projet.web.foot/api/players/stats";

        try {
            // Création du client WebClient
            WebClient client = WebClient.create(baseUrl);
            client = client.accept(MediaType.APPLICATION_JSON); 
            client.path(playerName);
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
                    // Traitement de l'objet JSON comme dans l'exemple du serveur avec l'api externe
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
    //methode pour setIcon sur un jlabel
    private void setImage(String imageUrl) {
        try {
            if (imageUrl != null && !imageUrl.isEmpty()) {
                URL url = new URL(imageUrl);
                ImageIcon imageIcon = new ImageIcon(ImageIO.read(url));
                imageLabel.setIcon(imageIcon);
            } else {
            	JOptionPane.showMessageDialog(this, "une erreur avec la photo");
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
