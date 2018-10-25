package model;

import java.util.*;
import java.io.*;

public class FIBA {

//	public static final String LOCRPGBTS = "db" + File.separator + "trees" + File.separator + "rpgbts.txt";
//	public static final String LOCRPGRBT = "db" + File.separator + "trees" + File.separator + "rpgrbt.txt";
//	public static final String LOCAPGRBT = "db" + File.separator + "trees" + File.separator + "apgrbt.txt";
//	public static final String LOCSPGBTS = "db" + File.separator + "trees" + File.separator + "spgbts.txt";
//	public static final String LOCSPGAVL = "db" + File.separator + "trees" + File.separator + "spgavl.txt";
//	public static final String LOCBPGAVL = "db" + File.separator + "trees" + File.separator + "bpgavl.txt";

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
			File location = new File("db" + File.separator + "players" + File.separator + maxNum + ".txt");
			try {
				writer = new PrintWriter(location, "UTF-8");
				String name = names.get((int) (Math.random() * names.size())).toUpperCase();
				String surname = surnames.get((int) (Math.random() * surnames.size())).toUpperCase();
				writer.println(randomInfo(name + " " + surname));
				maxNum++;
				updateMaxNum();
				writer.close();
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private String randomInfo(String name) {
		StringBuilder sb = new StringBuilder();
		String[] info = new String[20];
		for (int i = 0; i < info.length; i++) {
			info[i] = "";
		}
		info[Player.NAME] = name;
		info[Player.AGE] = "" + (int) (18 + Math.random() * 16);
		info[Player.TEAM] = "" + (char) (Math.random() * 26 + 'A') + (char) (Math.random() * 26 + 'A')
				+ (char) (Math.random() * 26 + 'A');
		info[Player.PPG] = "" + String.format(Locale.US,"%.1f", (Math.random() * 15 + 4));
		info[Player.RPG] = "" + String.format(Locale.US,"%.1f", (Math.random() * 15 + 1));
		info[Player.APG] = "" + String.format(Locale.US,"%.1f", (Math.random() * 15 + 1));
		info[Player.SPG] = "" + String.format(Locale.US,"%.1f", (Math.random() * 4));
		info[Player.BPG] = "" + String.format(Locale.US,"%.1f", (Math.random() * 3));

		for (int i = 0; i < info.length; i++) {
			sb.append(info[i] + ",");
		}
		return sb.toString();
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

	public ArrayList<Player> search(int item, double key, int criteria, boolean efficient, String keyStr) {
		ArrayList<Player> players = new ArrayList<Player>();
		if (item == Player.NAME || item == Player.TEAM || item == Player.AGE || item == Player.PPG) {
			if (keyStr.equals("")) {
				keyStr = item == Player.AGE ? "" + (int) key : "" + key;
			}
			players = searchLinear(item, criteria, keyStr);
		} else {
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
		}
		return players;
	}

	private ArrayList<Player> searchLinear(int item, int criteria, String keyStr) {
		ArrayList<Player> players = new ArrayList<>();

		try {
			for (int i = 1; i < maxNum; i++) {
				File player = new File("db" + File.separator + "players" + File.separator + i + ".txt");
				if (player.exists()) {
					BufferedReader br = new BufferedReader(new FileReader(player));
					String line = br.readLine();
					String[] info = line.split(",");
					boolean satisfies = false;
					switch (criteria) {
					case LESS:
						try {
							satisfies = Double.parseDouble(info[item]) < Double.parseDouble(keyStr);
						} catch (NumberFormatException e) {
							satisfies = info[item].compareToIgnoreCase(keyStr) < 0;
						}
						break;
					case LESS_EQUAL:
						try {
							satisfies = Double.parseDouble(info[item]) <= Double.parseDouble(keyStr);
						} catch (NumberFormatException e) {
							satisfies = info[item].compareToIgnoreCase(keyStr) <= 0;
						}

						break;
					case EQUAL:
						try {
							satisfies = Double.parseDouble(info[item]) == Double.parseDouble(keyStr);
						} catch (NumberFormatException e) {
							satisfies = info[item].compareToIgnoreCase(keyStr) == 0;
						}

						break;
					case BIGGER_EQUAL:
						try {
							satisfies = Double.parseDouble(info[item]) >= Double.parseDouble(keyStr);
						} catch (NumberFormatException e) {
							satisfies = info[item].compareToIgnoreCase(keyStr) >= 0;
						}

						break;
					case BIGGER:
						try {
							satisfies = Double.parseDouble(info[item]) > Double.parseDouble(keyStr);
						} catch (NumberFormatException e) {
							satisfies = info[item].compareToIgnoreCase(keyStr) > 0;
						}

						break;
					}
					if (satisfies)
						players.add(createPlayer(player.toString()));
					br.close();
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			String line = br.readLine();
			if (players.toString()
					.equals(new File(getClass().getResource("/info/nba_season_data.csv").getFile()).toString())) {
				line = br.readLine();
				int i = 0;
				while (i < 16000 && line != null && !line.equals("")) {
					PrintWriter writer = new PrintWriter(
							"db" + File.separator + "players" + File.separator + maxNum + ".txt", "UTF-8");
					writer.println(line);
					maxNum++;
					updateMaxNum();
					line = br.readLine();
					writer.close();
					i++;
				}
			} else {
				while(line != null && !line.equals("")) {
					PrintWriter writer = new PrintWriter(
							"db" + File.separator + "players" + File.separator + maxNum + ".txt", "UTF-8");
					writer.println(randomInfo(line.split(",")[0]));
					maxNum++;
					updateMaxNum();
					line = br.readLine();
					writer.close();
				}
				createTrees();
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

	public void deletePlayer(Player player) {
		String value = player.getLocation().toString();
		// Delete from BinaryTrees
		rpgBST.delete(player.getRpg(), value);
		spgBST.delete(player.getSpg(), value);
		// Delete from RBTrees
		rpgRBT.delete(player.getRpg(), value);
		apgRBT.delete(player.getApg(), value);
		// Delete from AVLTrees
		spgAVL.delete(player.getSpg(), value);
		bpgAVL.delete(player.getBpg(), value);
		// IT CAN ALSO DELTE THE TXT FILE.
		player.getLocation().delete();
	}

	public void modifyPlayer(Player player, String name, int age, String team, double ppg, double rpg, double apg,
			double spg, double bpg) {
//		if (player.getName().equals(name))
		player.setName(name);
//		if (player.getAge() == age)
		player.setAge(age);
//		if (player.getTeam().equals(team))
		player.setTeam(team);
//		if (player.getPpg() == ppg)
		player.setPpg(ppg);
//		if (player.getRpg() == rpg)
		player.setRpg(rpg);
//		if (player.getSpg() == spg)
		player.setSpg(spg);
//		if (player.getBpg() == bpg)
		player.setBpg(bpg);

		player.modifyFile();
		deletePlayer(player);
		insertPlayer(player);
	}

	private void insertPlayer(Player player) {
		player.modifyFile();
		String value = player.getLocation().toString();
		// Insert in BinaryTrees
		rpgBST.insert(player.getRpg(), value);
		spgBST.insert(player.getSpg(), value);
		// Insert in RBTrees
		rpgRBT.insert(player.getRpg(), value);
		apgRBT.insert(player.getApg(), value);
		// Insert in AVLTrees
		spgAVL.insert(player.getSpg(), value);
		bpgAVL.insert(player.getBpg(), value);
	}

	public void addPlayer(String name, int age, String team, double ppg, double rpg, double apg, double spg,
			double bpg) {
		File file = new File("db" + File.separator + "players" + File.separator + maxNum + ".txt");
		maxNum++;
		updateMaxNum();
		Player player = new Player(file, name, age, team, ppg, rpg, apg, spg, bpg);
		insertPlayer(player);
	}

	/*
	 * public void saveTrees() {
	 * 
	 * }
	 * 
	 * public void saveTree(String path) { File file = new File(path); if
	 * (file.exists()) file.delete(); else ObjectOutputStream oos = new
	 * ObjectOutputStream( new FileOutputStream( file ) ); oos.writeObject(obj);
	 * oos.close();
	 * 
	 * }
	 * 
	 * public void importTres() {
	 * 
	 * }
	 */
}
