package com.systable.controllers;

import java.io.IOException;

import com.systable.app.Main;
import com.systable.entities.User;
import com.systable.enumeration.Profile;
import com.systable.exceptions.UMSException;
import com.systable.metier.AdminMetier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminController {

	@FXML
	private BorderPane adminWindow;

	@FXML
	private RadioButton addBR;

	@FXML
	private ToggleGroup addUpdateTG;

	@FXML
	private RadioButton updateBR;

	@FXML
	private Button minimizeB;

	@FXML
	private Button profileB;

	@FXML
	private TextField idTF;

	@FXML
	private TextField loginTF;

	@FXML
	private PasswordField passwordPF;

	@FXML
	private PasswordField confirmPasswordPF;

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
	private ComboBox<Profile> profileCB;

	@FXML
	private Button clearB;

	@FXML
	private Button addUpdateB;

	@FXML
	private Button deleteB;

	@FXML
	private Button logOutB;

	@FXML
	private ComboBox<Profile> searchProfileCB;

	@FXML
	private TextField searchAttributeTF;

	@FXML
	private ComboBox<String> searchAttributeCB;

	@FXML
	private Button searchAttributeB;

	@FXML
	private TableView<User> table;

	@FXML
	private TableColumn<User, Integer> idCol;

	@FXML
	private TableColumn<User, String> firstNameCol;

	@FXML
	private TableColumn<User, String> lastNameCol;

	@FXML
	private TableColumn<User, String> loginCol;

	@FXML
	private TableColumn<User, Integer> phoneCodeCol;

	@FXML
	private TableColumn<User, Integer> phoneNumberCol;

	@FXML
	private TableColumn<User, String> emailCol;

	@FXML
	private TableColumn<User, String> addressCol;

	@FXML
	private TableColumn<User, String> profileCol;

	private ObservableList<User> users;

	private User user;

	private FXMLLoader loader;
	private Stage stage;
	private Scene scene;
	private Parent root;

	public void shareData(User user) {
		this.user = user;
		init();
	}

	private void init() {

		users = AdminMetier.listUsers();

		initComboBox();
		initTable();
	}

	private void initTable() {

		idCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
		loginCol.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
		firstNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
		lastNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
		phoneCodeCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("phoneCode"));
		phoneNumberCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("phoneNumber"));
		emailCol.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
		addressCol.setCellValueFactory(new PropertyValueFactory<User, String>("address"));
		profileCol.setCellValueFactory(new PropertyValueFactory<User, String>("profile"));

		refreshTable();
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

		bool = ServiceController.checkProfileCB(profileCB.getValue(), "profile");
		if (!bool)
			return false;

		return true;

	}

	private void initComboBox() {
		profileCB.getItems().addAll(Profile.values());

		searchProfileCB.getItems().addAll(Profile.values());

		ObservableList<String> attributes = FXCollections.observableArrayList();
		attributes.add("First name");
		attributes.add("Last name");
		searchAttributeCB.setItems(attributes);
	}

	private boolean add() throws UMSException {
		String loginVal = loginTF.getText();
		String passwordVal = passwordPF.getText();
		String firstNameVal = firstNameTF.getText();
		String lastNameVal = lastNameTF.getText();
		String emailVal = emailTF.getText();
		String addressVal = addressTF.getText();

		boolean check = checkField();
		if (!check)
			return false;

		int status = 0;

		User newUser = new User(loginVal, passwordVal, firstNameVal, lastNameVal,
				Integer.parseInt(phoneCodeTF.getText()), Integer.parseInt(phoneNumberTF.getText()), emailVal,
				addressVal, profileCB.getValue());

		status = AdminMetier.addUser(newUser);

		if (status < 0)
			return false;

		if (profileCB.getValue() == searchProfileCB.getValue() || searchProfileCB.getValue() == null) {
			users.add(newUser);
		}

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(Main.TITLE);
		alert.setHeaderText("Utilisateur ajoute avec succes!");
		alert.showAndWait();

		clearField(null);

		return true;
	}

	private boolean update() throws UMSException {
		int idVal = Integer.parseInt(idTF.getText());
		String loginVal = loginTF.getText();
		String confirmPasswordVal = confirmPasswordPF.getText();
		String firstNameVal = firstNameTF.getText();
		String lastNameVal = lastNameTF.getText();
		String emailVal = emailTF.getText();
		String addressVal = addressTF.getText();

		boolean check = checkField();
		if (!check)
			return false;

		int status = 0;

		User newUser = new User(idVal, loginVal, confirmPasswordVal, firstNameVal, lastNameVal,
				Integer.parseInt(phoneCodeTF.getText()), Integer.parseInt(phoneNumberTF.getText()), emailVal,
				addressVal, profileCB.getValue().toString());

		int selectedIndex = table.getSelectionModel().getSelectedIndex();
		status = AdminMetier.updateUser(newUser, selectedIndex);

		if (status < 0)
			return false;

		users.remove(selectedIndex);
		users.add(selectedIndex, newUser);

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(Main.TITLE);
		alert.setHeaderText("Utilisateur modifie avec succes!");
		alert.showAndWait();

		clearField(null);

		return true;

	}

	public void refreshTable() {
		table.setItems(users);
	}

	@FXML
	void minimizeWindow(ActionEvent event) {
		ServiceController.minimizeWindow(adminWindow);
	}

	@FXML
	void addOrUpdate(ActionEvent event) throws UMSException {
		if (addUpdateB.getText().equals("Add")) {
			add();
		} else {
			update();
		}
	}

	@FXML
	void clearField(ActionEvent event) {
		idTF.setText(null);
		loginTF.setText(null);
		passwordPF.setText(null);
		confirmPasswordPF.setText(null);
		firstNameTF.setText(null);
		lastNameTF.setText(null);
		phoneCodeTF.setText(null);
		phoneNumberTF.setText(null);
		emailTF.setText(null);
		addressTF.setText(null);
		profileCB.setValue(null);
	}

	@FXML
	void delete(ActionEvent event) {
		try {

			User user = table.getSelectionModel().getSelectedItem();

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle(Main.TITLE);
			alert.setHeaderText("Supprimer cet utilisateur ?");

			if (alert.showAndWait().get() == ButtonType.OK) {
				int status = AdminMetier.deleteUser(user);

				if (status > 0) {
					users.remove(user);

					Alert alert2 = new Alert(AlertType.INFORMATION);
					alert2.setTitle(Main.TITLE);
					alert2.setHeaderText("Utilisateur supprime avec success!");
					alert2.showAndWait();
				}

			}
		} catch (UMSException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle(Main.TITLE);
			alert.setHeaderText("Delete user!!");
			alert.setContentText("Aucun utilisateur selectionnee.");
			alert.showAndWait();
		}
	}

	@FXML
	void logOut(ActionEvent event) throws IOException {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(Main.TITLE);
		alert.setHeaderText("Deconnexion");
		alert.setContentText("Vous deconnecter?");

		if (alert.showAndWait().get() == ButtonType.OK) {
			String rootFxmlFile = "Home";
			loader = new FXMLLoader(getClass().getResource(Main.FXML_PATH + rootFxmlFile + ".fxml"));
			root = loader.load();

			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}

	@FXML
	void searchByAttribute(ActionEvent event) throws UMSException {
		AdminMetier.listUsersByAttributes(searchAttributeCB.getValue(), searchAttributeTF.getText());
	}

	@FXML
	void searchByProfile(ActionEvent event) throws UMSException {
		AdminMetier.listUsersByProfile(searchProfileCB.getValue());
	}

	@FXML
	void showProfile(ActionEvent event) {
		try {
			String rootFxmlFile = "Profile";
			loader = new FXMLLoader(getClass().getResource(Main.FXML_PATH + rootFxmlFile + ".fxml"));
			root = loader.load();

			ProfileController profileController = loader.getController();
			profileController.shareData(user);

			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	@FXML
	void updateAddUpdateB(ActionEvent event) throws UMSException {
		if (addBR.isSelected())
			addUpdateB.setText("Add");
		else
			addUpdateB.setText("Update");
	}

	@FXML
	void updateField(MouseEvent event) {
		try {
			User user = table.getSelectionModel().getSelectedItem();
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
			profileCB.setValue(user.getProfile());

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
