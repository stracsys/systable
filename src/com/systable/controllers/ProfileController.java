package com.systable.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.systable.app.Main;
import com.systable.entities.User;
import com.systable.exceptions.UMSException;
import com.systable.metier.AdminMetier;
import com.systable.session.UserSession;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ProfileController implements Initializable {

	@FXML
	private BorderPane profileWindow;

	@FXML
	private Button minimizeB;

	@FXML
	private TextField idTF;

	@FXML
	private TextField loginTF;

	@FXML
	private TextField firstNameTF;

	@FXML
	private TextField lastNameTF;

	@FXML
	private TextField phoneCodeTF;

	@FXML
	private TextField phoneNumberTF;

	@FXML
	private TextField emailTF;

	@FXML
	private TextField addressTF;

	@FXML
	private PasswordField confirmPasswordPF;

	@FXML
	private PasswordField passwordPF;

	@FXML
	private Button updateUserB;

	@FXML
	private Button resetUserB;

	@FXML
	private Button closeB;

	private Stage stage;

	public static ProfileController INSTANCE = null;
	public static boolean isOpen;

	public ProfileController() {
	}

	public static ProfileController getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ProfileController();
		}
		return INSTANCE;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		resetField(null);
	}

	private boolean checkField() {

		boolean bool;

		bool = ServiceController.checkStringTF(loginTF.getText(), "login");
		if (!bool)
			return false;

		bool = ServiceController.checkStringTF(passwordPF.getText(), "password");
		if (!bool)
			return false;

		bool = ServiceController.checkStringTF(confirmPasswordPF.getText(), "confirm password");
		if (!bool)
			return false;

		bool = ServiceController.checkStringTF(firstNameTF.getText(), "first name");
		if (!bool)
			return false;

		bool = ServiceController.checkStringTF(lastNameTF.getText(), "last name");
		if (!bool)
			return false;

		bool = ServiceController.checkIntTF(phoneCodeTF.getText(), "phone code");
		if (!bool)
			return false;

		bool = ServiceController.checkIntTF(phoneNumberTF.getText(), "phone number");
		if (!bool)
			return false;

		bool = ServiceController.checkEmailTF(emailTF.getText(), "email");
		if (!bool)
			return false;

		bool = ServiceController.checkStringTF(addressTF.getText(), "address");
		if (!bool)
			return false;

		return true;

	}

	@FXML
	void closeB(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(Main.TITLE);
		alert.setHeaderText("Close window?");

		if (alert.showAndWait().get() == ButtonType.OK) {
			ProfileController.isOpen = false;
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.close();
		}
	}

	@FXML
	void minimizeWindow(ActionEvent event) {
		ServiceController.minimizeWindow(event);
	}

	@FXML
	void resetField(ActionEvent event) {
		User user = UserSession.getUser();

		idTF.setText(String.valueOf(user.getId()));
		loginTF.setText(user.getLogin());
		passwordPF.setText(null);
		confirmPasswordPF.setText(null);
		firstNameTF.setText(user.getFirstName());
		lastNameTF.setText(user.getLastName());
		phoneCodeTF.setText(String.valueOf(user.getPhoneCode()));
		phoneNumberTF.setText(String.valueOf(user.getPhoneNumber()));
		emailTF.setText(user.getEmail());
		addressTF.setText(user.getAddress());
	}

	@FXML
	void updateUser(ActionEvent event) throws UMSException {
		int idVal = Integer.parseInt(idTF.getText());
		String loginVal = loginTF.getText();
		String confirmPasswordVal = confirmPasswordPF.getText();
		String firstNameVal = firstNameTF.getText();
		String lastNameVal = lastNameTF.getText();
		String emailVal = emailTF.getText();
		String addressVal = addressTF.getText();

		boolean check = checkField();
		if (!check)
			return;

		int status = 0;

		User newUser = new User(idVal, loginVal, confirmPasswordVal, firstNameVal, lastNameVal,
				Integer.parseInt(phoneCodeTF.getText()), Integer.parseInt(phoneNumberTF.getText()), emailVal,
				addressVal, UserSession.getUser().getProfile());

		status = AdminMetier.updateUser(newUser);

		if (status < 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle(Main.TITLE);
			alert.setHeaderText("Echec lors de la modification de l'utilisateur!!");
			alert.showAndWait();
			return;
		}

		UserSession.setUser(newUser);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(Main.TITLE);
		alert.setHeaderText("Utilisateur modifie avec succes!");
		alert.showAndWait();
	}

}
