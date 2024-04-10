package projet.web.client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
/**
 * Cette classe représente le menu principal de l'application Service Web Football.
 * Elle étend la classe JFrame et implémente l'interface ActionListener pour gérer les événements des boutons.
 * Le menu principal affiche différents boutons pour accéder aux fonctionnalités de l'application.
 * Chaque bouton est associé à une action spécifique qui ouvre une nouvelle fenêtre pour effectuer cette fonctionnalité.
 * Les fonctionnalités comprennent l'ajout, la suppression et la mise à jour d'équipes et de joueurs et quelques interactions avec une API externe,
 * l'affichage des équipes et des joueurs, la visualisation des statistiques des joueurs,
 * et la visualisation des prédictions des matchs de la Premier League.
 */
public class MenuPrincipal extends JFrame implements ActionListener{
	public MenuPrincipal() {
        setTitle("Menu Principal - Service Web Football");
        setSize(650, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2));

        // Boutons pour les différentes fonctionnalités
        JButton btnAddTeam = new JButton("Ajouter une equipe");
        JButton btnDeleteplayer= new JButton("Supprimer un joueur");
        JButton btnViewTeams = new JButton("Voir toutes les equipes (Serveur + API Externe)");
        JButton btnUpdateTeam = new JButton("Mettre à jour une equipe");
        JButton btnGetPlayers = new JButton("Afficher les joueurs d'une equipe");
        JButton btnSuppTeam = new JButton("Supprimer une equipe");
        JButton btnaddplayer = new JButton("Ajouter un joueur");
        JButton btnupdateplayer = new JButton("Changer d'equipe a un joueur");
        JButton btnStatPlayer = new JButton("Voir les stats de joueurs(Serveur + API Externe");
        JButton btnPredPlayer = new JButton("Voir les predicitions des matchs de PL");
        // Ajoutez plus de boutons pour les autres fonctionnalités comme nécessaire

        // Ajout des boutons au JFrame
        add(btnAddTeam);
        add(btnDeleteplayer);
        add(btnViewTeams);
        add(btnUpdateTeam);
        add(btnGetPlayers);
        add(btnSuppTeam);
        add(btnaddplayer);
        add(btnupdateplayer);
        add(btnStatPlayer);
        add(btnPredPlayer);
        // Continuez à ajouter les autres boutons

        // Associer les actions aux boutons
        btnAddTeam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AjoutEquipeGUI().setVisible(true);
            }
        });
        btnSuppTeam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SuppTeamGUI();
            }
        });
        btnDeleteplayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SuppJoueurGUI();
            }
        });
 
        btnaddplayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AjoutJoueurGUI().setVisible(true);
            }
        });
        btnupdateplayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateEquipeJoueur().setVisible(true);
            }
        });
        btnStatPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StatsPlayerGUI().setVisible(true);
            }
        });
        btnGetPlayers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PlayersOfTeamGUI().setVisible(true);
            }
        });
        btnViewTeams.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChampionnatEuropeGUI().setVisible(true);
            }
        });
        btnUpdateTeam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TeamUpdateGUI().setVisible(true);
            }
        });
        btnPredPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PredictionsPlGUI().setVisible(true);
            }
        });

        // Répétez pour les autres boutons avec leurs frames respectives

    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
