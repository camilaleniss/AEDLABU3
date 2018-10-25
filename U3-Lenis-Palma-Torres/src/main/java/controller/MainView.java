package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.FIBA;
import model.Player;

public class MainView implements Initializable{

	public static final int[] CATEGORIES = new int[] { Player.NAME, Player.AGE, Player.TEAM, Player.PPG, Player.RPG,
			Player.APG, Player.SPG, Player.BPG };
	public static final int[] TYPES = new int[] { FIBA.LESS, FIBA.LESS_EQUAL, FIBA.EQUAL, FIBA.BIGGER_EQUAL,
			FIBA.BIGGER };

	@FXML
	private JFXButton butInsert;

	@FXML
	private JFXButton butModify;

	@FXML
	private JFXButton butImport;

	@FXML
	private JFXButton butDelete;

	@FXML
	private JFXTextField boxValue;

	@FXML
	private JFXComboBox<String> boxCategory;

	@FXML
	private JFXComboBox<String> boxType;

	@FXML
	private JFXButton butSearch;

	@FXML
	private JFXButton butNew;

	@FXML
	private JFXCheckBox chkBalanced;

	@FXML
	private JFXTextField txtName;

	@FXML
	private JFXTextField txtAge;

	@FXML
	private JFXTextField txtTeam;

	@FXML
	private JFXTextField txtPpg;

	@FXML
	private JFXTextField txtRpg;

	@FXML
	private JFXTextField txtApg;

	@FXML
	private JFXTextField txtSpg;

	@FXML
	private JFXTextField txtBpg;

	private FIBA fiba;

	private Player player;

	@FXML
	void catChange(ActionEvent event) {
		int sel = boxCategory.getSelectionModel().getSelectedIndex();
		int cat = CATEGORIES[sel == -1 ? 0 : sel];
		boolean disabled = true;
		if (cat == Player.RPG || cat == Player.SPG)
			disabled = false;
		chkBalanced.setDisable(disabled);
	}

	@FXML
	void delete(ActionEvent event) {
		fiba.deletePlayer(player);
		JOptionPane.showMessageDialog(null, player.getName() + " was successfully deleted");
		player = null;
		updatePlayer();
	}

	@FXML
	void importCSV(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(new ExtensionFilter("CSV", "*.csv"));
		File file = fc.showOpenDialog((Stage) butImport.getScene().getWindow());
		if (file != null) {
			fiba.createPlayers(file);
			JOptionPane.showMessageDialog(null, "The players were successfully imported");
		}
	}

	@FXML
	void insert(ActionEvent event) {
		if (txtName.getText().equals("") || txtTeam.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "You must enter valid values", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				String name = txtName.getText();
				int age = (int) Double.parseDouble(txtAge.getText());
				String team = txtTeam.getText();
				double ppg = Double.parseDouble(txtPpg.getText());
				double rpg = Double.parseDouble(txtRpg.getText());
				double apg = Double.parseDouble(txtApg.getText());
				double spg = Double.parseDouble(txtSpg.getText());
				double bpg = Double.parseDouble(txtBpg.getText());
				fiba.addPlayer(name, age, team, ppg, rpg, apg, spg, bpg);
				JOptionPane.showMessageDialog(null, name + " was successfully inserted");
				updatePlayer();
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "You must enter valid values", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@FXML
	void modify(ActionEvent event) {
		if (txtName.getText().equals("") || txtTeam.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "You must enter valid values", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				String name = txtName.getText();
				int age = (int) Double.parseDouble(txtAge.getText());
				String team = txtTeam.getText();
				double ppg = Double.parseDouble(txtPpg.getText());
				double rpg = Double.parseDouble(txtRpg.getText());
				double apg = Double.parseDouble(txtApg.getText());
				double spg = Double.parseDouble(txtSpg.getText());
				double bpg = Double.parseDouble(txtBpg.getText());
				fiba.modifyPlayer(player, name, age, team, ppg, rpg, apg, spg, bpg);
				JOptionPane.showMessageDialog(null, name + " was successfully modified");
				updatePlayer();
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "You must enter valid values", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@FXML
	void newPlayer(ActionEvent event) {
		player = null;
		updatePlayer();
	}

	@FXML
	void search(ActionEvent event) {

		int cat = CATEGORIES[boxCategory.getSelectionModel().getSelectedIndex()];
		int typ = TYPES[boxType.getSelectionModel().getSelectedIndex()];

		if (boxValue.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "You must enter a value", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			if (cat == Player.NAME || cat == Player.TEAM) {
				ArrayList<Player> players = fiba.search(cat, 0, typ, true, boxValue.getText());
				if (players.size() == 0) {
					JOptionPane.showMessageDialog(null, "No player meets the requirements");
				} else {
					openSearch(players);
				}
			} else {
				try {
					double value = Double.parseDouble(boxValue.getText());
					long t1 = System.currentTimeMillis();
					ArrayList<Player> players = fiba.search(cat, value, typ, chkBalanced.isSelected(), "");
					JOptionPane.showMessageDialog(null,
							"The search took " + (System.currentTimeMillis() - t1) + " milliseconds");
					if (players.size() == 0) {
						JOptionPane.showMessageDialog(null, "No player meets the requirements");
					} else {
						openSearch(players);
					}

				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "You must enter a number", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> listCat = FXCollections.observableArrayList("Name (linear)", "Age (linear)",
				"Team (linear)", "Points per game (linear)", "Rebounds per game (RB)", "Assists per game (RB)",
				"Steals per game (AVL)", "Blocks per game (AVL)");

		ObservableList<String> listType = FXCollections.observableArrayList("<", "<=", "=", ">=", ">");

		boxCategory.setItems(listCat);
		boxCategory.getSelectionModel().select(0);

		boxType.setItems(listType);
		boxType.getSelectionModel().select(2);

		chkBalanced.setSelected(true);
		chkBalanced.setDisable(true);
	}

	public void init(Player player, FIBA fiba) {
		if(fiba == null)
			this.fiba = new FIBA();
		else 
			this.fiba = fiba;
		this.player = player;
		updatePlayer();
	}

	private void updatePlayer() {
		if (player == null) {
			butInsert.setDisable(false);
			butModify.setDisable(true);
			butDelete.setDisable(true);
			butNew.setDisable(true);
			txtName.setText("");
			txtAge.setText("");
			txtTeam.setText("");
			txtPpg.setText("");
			txtRpg.setText("");
			txtApg.setText("");
			txtSpg.setText("");
			txtBpg.setText("");

		} else {
			butInsert.setDisable(true);
			butModify.setDisable(false);
			butDelete.setDisable(false);
			butNew.setDisable(false);
			txtName.setText(player.getName());
			txtAge.setText("" + player.getAge());
			txtTeam.setText("" + player.getTeam());
			txtPpg.setText("" + player.getPpg());
			txtRpg.setText("" + player.getRpg());
			txtApg.setText("" + player.getApg());
			txtSpg.setText("" + player.getSpg());
			txtBpg.setText("" + player.getBpg());
		}
	}

	private void openSearch(ArrayList<Player> players) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/SearchController.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = (Stage) butSearch.getScene().getWindow();
			stage.setScene(scene);
			SearchController contr = loader.getController();
			contr.init(players, fiba);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
