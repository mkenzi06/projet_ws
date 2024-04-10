package projet.web.foot.Modele;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * La classe Team représente une equipe de football.
 * Elle contient des informations telles que l'identifiant du joueur, son NOM, son coach et sa ligue.
 */
@XmlRootElement
public class Team {
	private int id;
	private String name;
	private String league;
	private String coachName;
//	private ArrayList<Player> players;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}


	public String getCoachName() {
		return coachName;
	}

	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}

}
