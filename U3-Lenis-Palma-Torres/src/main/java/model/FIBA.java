package model;

import java.util.*;
import java.io.*;
import java.net.URL;

public class FIBA {

	private List<String> names;
	private List<String> surnames;
	private int maxNum; // Number of players loaded

	public FIBA() {
		names = new ArrayList<>();
		surnames = new ArrayList<>();
		load();
		
	}
	
	public void load() {
		new File("db").mkdirs();
		new File("db"+File.separator+"players").mkdirs();
		PrintWriter writer = null;
		BufferedReader br = null;
		File max = new File("db" + File.separator + "max.txt");

		try {
			if (max.exists()) {
				br = new BufferedReader(new FileReader(max));
				maxNum = Integer.parseInt(br.readLine());
				
			} else {
				maxNum = 1;
				updateMaxNum();
				File players = new File(getClass().getResource("/info/nba_season_data.csv").getFile());
				createPlayers(players);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		} 
		
		System.out.println("listo");
	}
	
	public void loadNames() {
		File names = new File(getClass().getResource("/info/names.csv").getFile());
	}
	
	public void loadSurnames() {
		
	}
	
	public void createRandomPlayer() {
		
	}
	
	public void updateMaxNum() {
		PrintWriter writer;
		try {
			writer = new PrintWriter("db" + File.separator + "max.txt", "UTF-8");
			writer.println(maxNum);
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void createPlayers(File players) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(players));
			br.readLine();
			String line = br.readLine();
			int i = 0;
			while(i < 17163 && line != null && !line.equals("")) {
				PrintWriter writer = new PrintWriter("db"+File.separator+"players"+File.separator+maxNum+".txt", "UTF-8");
				writer.println(line);
				maxNum++;
				updateMaxNum();
				line = br.readLine();
				writer.close();
				i++;
			}
			br.close();
			
						
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
	}

	
	public static void main(String[] args) {
		new FIBA();
	}

}
