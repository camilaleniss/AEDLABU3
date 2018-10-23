package model;

import java.io.*;

public class Player {
	
	public static final int NAME = 2;
	public static final int AGE = 3;
	public static final int TEAM = 1;
	public static final int PPG = 6;
	public static final int RPG = 12;
	public static final int APG = 13;
	public static final int SPG = 14;
	public static final int BPG = 15;
				
	private File location;
	private String name;
	private int age;
	private String team;
	private double ppg; //points per game
	private double rpg; //rebounds per game
	private double apg; //assists per game
	private double spg; //steals per game
	private double bpg; //blocks per game;
	
	public Player(File location, String name, int age, String team, double ppg, double rpg, double apg, double spg,
			double bpg) {
		super();
		this.location = location;
		this.name = name;
		this.age = age;
		this.team = team;
		this.ppg = ppg;
		this.rpg = rpg;
		this.apg = apg;
		this.spg = spg;
		this.bpg = bpg;
	}

	public File getLocation() {
		return location;
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
	
	public void modifyFile() {
		PrintWriter writer;
		try {
			writer = new PrintWriter(location, "UTF-8");
			StringBuilder sb = new StringBuilder();
			String[] info = new String[20];
			for (int i = 0; i < info.length; i++) {
				info[i] = "";
			}
			info[NAME]  = name;
			info[AGE]  = ""+age;
			info[TEAM]  = team;
			info[PPG]  = ""+ppg;
			info[RPG]  = ""+rpg;
			info[APG]  = ""+apg;
			info[SPG]  = ""+spg;
			info[BPG]  = ""+bpg;

			for (int i = 0; i < info.length; i++) {
				sb.append(info[i]+",");
			}
			writer.println(sb.toString());
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public String toString() {
		return name+" "+"/ "+team+"/ "+age+"/ "+" / "+ppg+"/ "+rpg+"/ "+
				apg+"/ "+spg+"/ "+bpg+"/";
	}
	

}
