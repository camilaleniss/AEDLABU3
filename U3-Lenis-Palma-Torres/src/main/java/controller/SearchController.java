package controller;

import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.FIBA;
import model.Player;

public class SearchController {

    @FXML
    private JFXButton butBack;

    @FXML
    private JFXListView<Player> listPlayers;
    
    private FIBA fiba;

    @FXML
    void back(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/main.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = (Stage) butBack.getScene().getWindow();
			stage.setScene(scene);
			MainView contr = loader.getController();
			contr.init(listPlayers.getSelectionModel().getSelectedItem(), fiba);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void init(ArrayList<Player> players, FIBA fiba){
    	this.fiba = fiba;
    	ObservableList<Player> list = FXCollections.observableArrayList(players);
    	listPlayers.setItems(list);
    	listPlayers.getSelectionModel().select(0);
    }
    
   

}
