package com.systable.app;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Main extends Application {

	private Parent root;
	private Scene scene;

	public static final String TITLE = "SYSTABLE";
	public static final String FXML_PATH = "/assets/fxml/";

	@Override
	public void start(Stage primaryStage) {
		try {
			String rootFxmlFile = "Home";
			root = FXMLLoader.load(getClass().getResource(Main.FXML_PATH + rootFxmlFile + ".fxml"));

			scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle(Main.TITLE);
			primaryStage.setResizable(false);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent arg0) {

					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle(Main.TITLE);
					alert.setHeaderText("Fermeture non autorisee!!");
					alert.setContentText("Utilisez le bouton correspondant.");
					alert.showAndWait();

					arg0.consume();
				}
			});
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
