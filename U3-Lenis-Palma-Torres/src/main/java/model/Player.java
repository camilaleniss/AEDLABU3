package model;

import java.io.*;

public class Player {
	
	private File location;
	private String name;
	private int age;
	private String team;
	private double ppg; //points per game
	private double rpg; //rebounds per game
	private double apg; //assists per game
	private double spg; //steals per game
	private double bpg; //blocks per game;
	public File getLocation() {
		return location;
	}
	public void setLocation(File location) {
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public double getPpg() {
		return ppg;
	}
	public void setPpg(double ppg) {
		this.ppg = ppg;
	}
	public double getRpg() {
		return rpg;
	}
	public void setRpg(double rpg) {
		this.rpg = rpg;
	}
	public double getApg() {
		return apg;
	}
	public void setApg(double apg) {
		this.apg = apg;
	}
	public double getSpg() {
		return spg;
	}
	public void setSpg(double spg) {
		this.spg = spg;
	}
	public double getBpg() {
		return bpg;
	}
	public void setBpg(double bpg) {
		this.bpg = bpg;
	}
	
	
	

}
