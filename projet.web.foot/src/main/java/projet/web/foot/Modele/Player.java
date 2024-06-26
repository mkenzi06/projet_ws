package projet.web.foot.Modele;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * La classe Player représente un joueur de football.
 * Elle contient des informations telles que l'identifiant du joueur, son prénom, son poste, le nom de son équipe et l'identifiant de son équipe.
 */
@XmlRootElement
public class Player {
	private int id;
	private String prenom;
	private String Poste;
	private String teamName;
	private int equipe;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPoste() {
		return Poste;
	}

	public void setPoste(String poste) {
		Poste = poste;
	}

	public int getEquipe() {
		return equipe;
	}

	public void setEquipe(int equipe) {
		this.equipe = equipe;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
}
