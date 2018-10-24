package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.FIBA;
import model.Player;

public class MainView implements Initializable {

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

	private FIBA fiba;

	@FXML
	void delete(ActionEvent event) {

	}

	@FXML
	void importCSV(ActionEvent event) {

	}

	@FXML
	void insert(ActionEvent event) {

	}

	@FXML
	void modify(ActionEvent event) {

	}
	
	@FXML
    void newPlayer(ActionEvent event) {

    }

	@FXML
	void search(ActionEvent event) {

		int[] categories = new int[] { Player.NAME, Player.AGE, Player.TEAM, Player.PPG, Player.RPG, Player.APG,
				Player.SPG, Player.BPG };
		int[] types = new int[] { FIBA.LESS, FIBA.LESS_EQUAL, FIBA.EQUAL, FIBA.BIGGER_EQUAL, FIBA.BIGGER };

		int cat = categories[boxCategory.getSelectionModel().getSelectedIndex()];
		int typ = types[boxType.getSelectionModel().getSelectedIndex()];

		if (boxValue.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "You must enter a value", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			if (cat == Player.NAME || cat == Player.TEAM) {

			} else {
				try {
					double value = Double.parseDouble(boxValue.getText());
					ArrayList<Player> players = fiba.search(cat, value, typ, true, "");
					JOptionPane.showMessageDialog(null, "oli");
					JOptionPane.showMessageDialog(null, "oli2");
					openSearch(players);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "You must enter a number", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fiba = new FIBA();
		init();

	}

	public void init() {
		ObservableList<String> listCat = FXCollections.observableArrayList("Name (linear)", "Age (linear)",
				"Team (linear)", "Points per game (linear)", "Rebounds per game (efficient)",
				"Assists per game (efficient)", "Steals per game (efficient)", "Blocks per game (efficient)");

		ObservableList<String> listType = FXCollections.observableArrayList("<", "<=", "=", ">=", ">");

		boxCategory.setItems(listCat);
		boxCategory.getSelectionModel().select(0);

		boxType.setItems(listType);
		boxType.getSelectionModel().select(2);
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
			contr.init(players);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
