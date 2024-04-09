package com.computer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Player")
@XmlAccessorType(XmlAccessType.FIELD)
public class Player {
	@XmlElement(name = "id")
	private int id;

	@XmlElement(name = "name")
	private String name;

	@XmlElement(name = "teamName")
	private String teamName;

	@XmlElement(name = "position")
	private String position;

	// Constructeur par défaut requis pour les opérations de marshalling et
	
	public Player() {
	}

	// Constructeur avec paramètres
	public Player(int id, String name, String teamName, String position) {
		this.id = id;
		this.name = name;
		this.teamName = teamName;
		this.position = position;
	}

	// Getters et Setters
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

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}
