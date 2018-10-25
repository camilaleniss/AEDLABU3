package controller;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.jfoenix.controls.JFXProgressBar;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import threads.LoadingThread;

public class LoadingController {
	
	@FXML
    private JFXProgressBar progress;

    @FXML
    private Label lblLoading;
    
    public void init() {
   
       	LoadingThread task = new LoadingThread();
       	
       	task.setOnSucceeded(suceededEvent -> {
       		try {
    			FXMLLoader loader = new FXMLLoader();
    			loader.setLocation(getClass().getResource("/view/main.fxml"));
    			Parent root = loader.load();
    			Scene scene = new Scene(root);
    			Stage stage = (Stage) lblLoading.getScene().getWindow();
    			stage.setScene(scene);
    			stage.centerOnScreen();
    			MainView contr = loader.getController();
    			contr.init(null, task.getFiba());
    			stage.show();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
       	});
       	
       	ExecutorService executorService = Executors.newFixedThreadPool(1);
       	executorService.execute(task);
       	executorService.shutdown();
    }

}
