package projet.web.client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
/**
 * Cette classe représente l'interface graphique pour afficher les équipes de football d'une ligue européenne sélectionnée.
 * Elle herite la classe JFrame et implémente l'interface ActionListener pour gérer les événements des boutons.
 * L'interface comprend un menu déroulant pour sélectionner une ligue européenne parmi plusieurs options (Liga, Bundesliga, Ligue1, PremierLeague, SerieA),
 * ainsi qu'un bouton pour récupérer les équipes de la ligue sélectionnée et les afficher dans un tableau.
 * Lorsque l'utilisateur clique sur le bouton , une requête est envoyée à l'API pour récupérer les équipes de la ligue sélectionnée (l'api qui recupere les donneee depuis l'api externe).
 * Les équipes sont affichées dans un tableau avec une colonne pour le nom de l'équipe.
 * Les données sont récupérées depuis le serveur à l'aide de WebClient et sont traitées sous forme de JSON.
 * En cas d'erreur lors de la récupération ou du traitement des données JSON, une trace de la pile est affichée.
 */
public class ChampionnatEuropeGUI extends JFrame implements ActionListener{
    private JComboBox<String> leagueComboBox;
    private JButton fetchButton;
    private JTable teamTable;
    private DefaultTableModel tableModel;

    private final String BASE_URL = "http://localhost:8080/projet.web.foot/api/teams/";
    
    public ChampionnatEuropeGUI() {
        setTitle("Football Teams");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(this);
        leagueComboBox = new JComboBox<>(new String[]{"Liga", "Bundesliga", "Ligue1", "PremierLeague", "SerieA"});
        fetchButton = new JButton("Afficher les equipes");
        teamTable = new JTable();
        tableModel = new DefaultTableModel();
        teamTable.setModel(tableModel);

        tableModel.addColumn("Team Name");

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Choisi un championnat: "));
        topPanel.add(leagueComboBox);
        topPanel.add(fetchButton);

        JScrollPane scrollPane = new JScrollPane(teamTable);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedLeague = (String) leagueComboBox.getSelectedItem();
                fetchTeams(selectedLeague);
            }
        });
    }

    private void fetchTeams(String selectedLeague) {
        String url = BASE_URL + selectedLeague.toLowerCase();
        
        // Utilisation de WebClient pour récupérer les données JSON
        String jsonResponse = WebClient.create(url).get(String.class);
        
        // Effacer les lignes existantes de la table
        tableModel.setRowCount(0);
        
        try {
            // Convertir la réponse JSON en tableau JSON
            JSONArray jsonArray = new JSONArray(jsonResponse);
            
            // Parcourir le tableau JSON et ajouter chaque élément à la table
            for (int i = 0; i < jsonArray.length(); i++) {
                String teamName = jsonArray.getString(i);
                tableModel.addRow(new Object[]{teamName});
            }
        } catch (JSONException e) {
            e.printStackTrace();
            // Gérer les erreurs de parsing JSON
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChampionnatEuropeGUI().setVisible(true);
            }
        });
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
