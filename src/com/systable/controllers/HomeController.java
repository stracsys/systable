package com.systable.controllers;

import java.io.IOException;

import com.systable.app.Main;
import com.systable.entities.User;
import com.systable.exceptions.UMSException;
import com.systable.jdbc.UserDAO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HomeController {

	@FXML
	private BorderPane homeWindow;

	@FXML
	private TextField loginTF;

	@FXML
	private PasswordField passwordTF;

	@FXML
	private Button minimizeB;
	
	@FXML
	private Button signInB;

	@FXML
	private Button exitB;

	private FXMLLoader loader;
	private Stage stage;
	private Scene scene;
	private Parent root;

	private User user;

	@FXML
	void exitBtn(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(Main.TITLE);
		alert.initStyle(StageStyle.UNDECORATED);
		alert.setHeaderText("Quitter l'application?");

		if (alert.showAndWait().get() == ButtonType.OK) {
			stage = (Stage) homeWindow.getScene().getWindow();
			stage.close();
		}
	}

	@FXML
    void minimizeWindow(ActionEvent event) {
		ServiceController.minimizeWindow(homeWindow);
    }
	
	@FXML
	void signIn(ActionEvent event) throws UMSException, IOException {
		int status = UserDAO.signIn(loginTF.getText(), passwordTF.getText());

		if (status > 0) {
			user = UserDAO.getUserByLogin(loginTF.getText());

			String rootFxmlFile;

			switch (user.getProfile()) {
			case ADMIN:

				rootFxmlFile = "Admin";
				loader = new FXMLLoader(getClass().getResource(Main.FXML_PATH + rootFxmlFile + ".fxml"));
				root = loader.load();

				AdminController adminController = loader.getController();
				adminController.shareData(user);
				break;

			default:
				break;
			}

			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle(Main.TITLE);
			alert.initStyle(StageStyle.UNDECORATED);
			alert.setHeaderText("Connexion echouee!!");
			alert.setContentText("Login ou mot de passe incorrect.");
			alert.showAndWait();
		}
	}

}
