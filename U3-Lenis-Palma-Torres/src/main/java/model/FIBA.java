package model;

import java.util.*;
import java.io.*;

public class FIBA {

	public static final int NUMBER_OF_PLAYERS = 0;

	public static final int LESS = 0;
	public static final int LESS_EQUAL = 1;
	public static final int EQUAL = 2;
	public static final int BIGGER_EQUAL = 3;
	public static final int BIGGER = 4;

	private List<String> names;
	private List<String> surnames;
	private int maxNum; // Number of players loaded
	private BinaryTree<Double, String> rpgBST; 
	private RBTree<Double, String> rpgRBT;
	private RBTree<Double, String> apgRBT;
	private BinaryTree<Double, String> spgBST;
	private AVLTree<Double, String> spgAVL;
	private AVLTree<Double, String> bpgAVL;

	public FIBA() {
		names = new ArrayList<>();
		surnames = new ArrayList<>();
		rpgBST = new BinaryTree<>();
		rpgRBT = new RBTree<>();
		apgRBT = new RBTree<>();
		spgBST = new BinaryTree<>();
		spgAVL = new AVLTree<>();
		bpgAVL = new AVLTree<>();
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

			createTrees();

		} catch (IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}

		loadNames();
		loadSurnames();
		createRandomPlayers();

	}

	private void createTrees() {
		for (int i = 1; i < maxNum; i++) {
			File player = new File("db" + File.separator + "players" + File.separator + i + ".txt");
			if (player.exists()) {
				try {
					BufferedReader br = new BufferedReader(new FileReader(player));
					String line = br.readLine();

					String[] info = line.split(",");

					rpgBST.insert(Double.parseDouble(info[Player.RPG]), player.toString());
					rpgRBT.insert(Double.parseDouble(info[Player.RPG]), player.toString());

					apgRBT.insert(Double.parseDouble(info[Player.APG]), player.toString());

					spgBST.insert(Double.parseDouble(info[Player.SPG]), player.toString());
					spgAVL.insert(Double.parseDouble(info[Player.SPG]), player.toString());

					bpgAVL.insert(Double.parseDouble(info[Player.BPG]), player.toString());

					br.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

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
		while (maxNum <= NUMBER_OF_PLAYERS) {
			PrintWriter writer;
			try {
				writer = new PrintWriter("db" + File.separator + "players" + File.separator + maxNum + ".txt", "UTF-8");
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

	public ArrayList<Player> search(int item, double key, int criteria, boolean efficient) {
		ArrayList<Player> players = new ArrayList<Player>();
		IBinaryTree<Double, String> tree;
		switch (item) {
		case Player.RPG:
			tree = efficient ? rpgRBT : rpgBST;
			break;
		case Player.APG:
			tree = apgRBT;
			break;
		case Player.SPG:
			tree = efficient ? spgAVL : spgBST;
			break;
		case Player.BPG:
			tree = bpgAVL;
			break;
		default:
			tree = efficient ? rpgRBT : rpgBST;
			break;
		}

		ArrayList<String> locations = new ArrayList<String>();

		switch (criteria) {
		case LESS:
			locations = tree.searchLowerTo(key);
			break;
		case LESS_EQUAL:
			locations = tree.searchLowerOrEqualTo(key);
			break;
		case EQUAL:
			locations = tree.searchEqualTo(key);
			break;
		case BIGGER_EQUAL:
			locations = tree.searchBiggerOrEqualThan(key);
			break;
		case BIGGER:
			locations = tree.searchBiggerThan(key);
			break;
		default:
			locations = tree.searchEqualTo(key);
			break;
		}
		
		for (int i = 0; i < locations.size(); i++) {
			players.add(createPlayer(locations.get(i)));
		}

		return players;
	}

	public Player createPlayer(String location) {
		File file = new File(location);
		Player p = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			br.close();
			String[] info = line.split(",");
			p = new Player(file, info[Player.NAME], Integer.parseInt(info[Player.AGE]), info[Player.TEAM],
					Double.parseDouble(info[Player.PPG]), Double.parseDouble(info[Player.RPG]),
					Double.parseDouble(info[Player.APG]), Double.parseDouble(info[Player.SPG]),
					Double.parseDouble(info[Player.BPG]));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return p;
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
	
	public void deletePlayer (Player player) throws FileNotFoundException {
		File file = player.getLocation();
		if (file.exists() && file.isFile()) {
			
		}else {
			throw new FileNotFoundException();
		}
	}
	
	
	

	public static void main(String[] args) {
		new FIBA();
	}

}
