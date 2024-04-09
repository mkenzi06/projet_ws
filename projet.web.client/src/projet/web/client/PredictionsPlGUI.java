package projet.web.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
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

import org.apache.cxf.jaxrs.client.WebClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PredictionsPlGUI extends JFrame implements ActionListener{
    private JTextField fromDateTextField;
    private JTextField toDateTextField;
    private JTable predictionTable;
    private JButton searchButton;

    public PredictionsPlGUI() {
        setTitle("Predictions Client");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("From Date:"));
        fromDateTextField = new JTextField(10);
        inputPanel.add(fromDateTextField);
        inputPanel.add(new JLabel("To Date:"));
        toDateTextField = new JTextField(10);
        inputPanel.add(toDateTextField);

        searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					performSearch();
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        inputPanel.add(searchButton);

        mainPanel.add(inputPanel);

        predictionTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(predictionTable);
        mainPanel.add(scrollPane);

        add(mainPanel);
    }

    private void performSearch() throws JSONException {
        // Vérification de la validité des dates
        String fromDate = fromDateTextField.getText();
        String toDate = toDateTextField.getText();
        if (fromDate.isEmpty() || toDate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez saisir les dates de début et de fin.");
            return;
        }
        if (fromDate.compareTo(toDate) > 0) {
            JOptionPane.showMessageDialog(this, "La date de début doit être inférieure ou égale à la date de fin.");
            return;
        }

        // Appel à l'API pour récupérer les prédictions
        WebClient client = WebClient.create("http://localhost:8080/projet.web.foot/api/teams/predictions/");
        client.query("from", fromDate);
        client.query("to", toDate);
        client.accept(MediaType.APPLICATION_JSON);
        String predictionsJson = client.get(String.class);

     // Convertir la chaîne JSON en tableau JSON
     JSONArray predictionsArray = new JSONArray(predictionsJson);

     // Affichage des résultats dans la JTable
     DefaultTableModel model = new DefaultTableModel(new Object[]{"Match", "Probabilité HW", "Probabilité D", "Probabilité AW"}, 0);
     for (int i = 0; i < predictionsArray.length(); i++) {
         JSONObject prediction = predictionsArray.getJSONObject(i);
         Object[] row = new Object[4];
         row[0] = prediction.getString("match_hometeam_name") + " vs " + prediction.getString("match_awayteam_name");
         row[1] = prediction.getString("prob_HW");
         row[2] = prediction.getString("prob_D");
         row[3] = prediction.getString("prob_AW");
         model.addRow(row);
     }
     predictionTable.setModel(model);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PredictionsPlGUI client = new PredictionsPlGUI();
                client.setVisible(true);
            }
        });
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
