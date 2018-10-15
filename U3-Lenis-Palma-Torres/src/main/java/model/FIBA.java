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
		new File("db" + File.separator + "players").mkdirs();
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
		
		loadNames();
		loadSurnames();
		createRandomPlayers();

		System.out.println("listo");
	}

	public void loadNames() {
		File names = new File(getClass().getResource("/info/names.csv").getFile());
		try {
			BufferedReader br = new BufferedReader(new FileReader(names));
			br.readLine();
			String line = br.readLine();
			int i = 0;
			while (i < 5700 && line != null && !line.equals("")) {

				String[] info = line.split(",");
				if (info[1].equals("M")) {
					this.names.add(info[0]);
				}

				line = br.readLine();
				i++;
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loadSurnames() {
		File surnames = new File(getClass().getResource("/info/surnames.csv").getFile());
		try {
			BufferedReader br = new BufferedReader(new FileReader(surnames));
			br.readLine();
			String line = br.readLine();
			int i = 0;
			while (i < 5700 && line != null && !line.equals("")) {

				String[] info = line.split(",");

				this.surnames.add(info[0]);

				line = br.readLine();
				i++;
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createRandomPlayers() {
		while (maxNum <= 250000) {
			PrintWriter writer;
			try {
				writer = new PrintWriter("db" + File.separator + "players" + File.separator + maxNum + ".txt",
						"UTF-8");
				String name = names.get((int) (Math.random() * names.size())).toUpperCase();
				String surname = surnames.get((int) (Math.random() * surnames.size())).toUpperCase();
				writer.println(name + " " + surname);
				maxNum++;
				updateMaxNum();
				writer.close();
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
			while (i < 17163 && line != null && !line.equals("")) {
				PrintWriter writer = new PrintWriter(
						"db" + File.separator + "players" + File.separator + maxNum + ".txt", "UTF-8");
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
		} finally {

		}
	}

	public static void main(String[] args) {
		new FIBA();
	}

}
