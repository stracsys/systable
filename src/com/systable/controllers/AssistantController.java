package com.systable.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.systable.app.Main;
import com.systable.entities.Classe;
import com.systable.entities.User;
import com.systable.entities.jointures.ClasseUser;
import com.systable.enumeration.Profile;
import com.systable.exceptions.UMSException;
import com.systable.metier.AssistantMetier;
import com.systable.session.UserSession;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class AssistantController implements Initializable {

	@FXML
	private TextField nameClasseAddTF;

	@FXML
	private RadioButton updateClasseRB;

	@FXML
	private RadioButton addClasseRB;

	@FXML
	private Button classeActionB;

	@FXML
	private ToggleGroup classeG;

	@FXML
	private TableColumn<User, String> firstNameChiefCol;

	@FXML
	private TableColumn<User, String> firstNameTeacherCol;

	@FXML
	private TableColumn<ClasseUser, String> firstNameChiefCol2;

	@FXML
	private TableColumn<ClasseUser, String> firstNameChiefCol3;

	@FXML
	private TableColumn<User, String> lastNameChiefCol;

	@FXML
	private TableColumn<User, String> lastNameTeacherCol;

	@FXML
	private TableColumn<ClasseUser, String> lastNameChiefCol2;

	@FXML
	private TableColumn<ClasseUser, String> lastNameChiefCol3;

	@FXML
	private TableColumn<User, String> emailChiefCol;

	@FXML
	private TableColumn<User, String> emailTeacherCol;

	@FXML
	private TableColumn<ClasseUser, String> emailChiefCol2;

	@FXML
	private TableColumn<ClasseUser, String> emailChiefCol3;

	@FXML
	private TableColumn<ClasseUser, String> nameClasseCol2;

	@FXML
	private TableColumn<ClasseUser, String> nameClasseCol3;

	@FXML
	private TableView<User> tableChiefs;

	@FXML
	private TableView<User> tableTeachers;

	@FXML
	private TableView<ClasseUser> tableClassesChiefs;

	@FXML
	private TableView<ClasseUser> tableClassesChiefs3;

	private FXMLLoader loader;
	private Stage stage;
	private Parent root;

	private ObservableList<User> chiefs;
	private ObservableList<User> teachers;
	private ObservableList<ClasseUser> classesUsers;

	public static AssistantController INSTANCE = null;

	public static AssistantController getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new AssistantController();
		}
		return INSTANCE;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			initData();
		} catch (UMSException e) {
			e.printStackTrace();
		}
//		initComboBox();
		initTable();
	}

	private void initData() throws UMSException {
		chiefs = AssistantMetier.listUsers("profile", Profile.CHIEF.toString());
		teachers = AssistantMetier.listUsers("profile", Profile.TEACHER.toString());
		classesUsers = AssistantMetier.listClasseUsers();
//			updateNbUserL();
	}

	private void initTable() {

		firstNameChiefCol.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
		lastNameChiefCol.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
		emailChiefCol.setCellValueFactory(new PropertyValueFactory<User, String>("email"));

		firstNameTeacherCol.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
		lastNameTeacherCol.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
		emailTeacherCol.setCellValueFactory(new PropertyValueFactory<User, String>("email"));

		nameClasseCol2.setCellValueFactory(new PropertyValueFactory<ClasseUser, String>("nameClasse"));
		firstNameChiefCol2.setCellValueFactory(new PropertyValueFactory<ClasseUser, String>("firstNameChief"));
		lastNameChiefCol2.setCellValueFactory(new PropertyValueFactory<ClasseUser, String>("lastNameChief"));
		emailChiefCol2.setCellValueFactory(new PropertyValueFactory<ClasseUser, String>("emailChief"));

		nameClasseCol3.setCellValueFactory(new PropertyValueFactory<ClasseUser, String>("nameClasse"));
		firstNameChiefCol3.setCellValueFactory(new PropertyValueFactory<ClasseUser, String>("firstNameChief"));
		lastNameChiefCol3.setCellValueFactory(new PropertyValueFactory<ClasseUser, String>("lastNameChief"));
		emailChiefCol3.setCellValueFactory(new PropertyValueFactory<ClasseUser, String>("emailChief"));

//		lastNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
//		phoneCodeCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("phoneCode"));
//		phoneNumberCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("phoneNumber"));
//		emailCol.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
//		addressCol.setCellValueFactory(new PropertyValueFactory<User, String>("address"));
//		profileCol.setCellValueFactory(new PropertyValueFactory<User, String>("profile"));

		refreshTable();
	}

	private void refreshTable() {
		tableChiefs.setItems(chiefs);
		tableTeachers.setItems(teachers);
		tableClassesChiefs.setItems(classesUsers);
		tableClassesChiefs3.setItems(classesUsers);
	}

//	@FXML
//	void tabChanged(ActionEvent event) {
//		System.out.println("TAB");
//	}

	private void addClasse() throws UMSException {
		String nameClasseVal = nameClasseAddTF.getText();

		if (!ServiceController.checkStringTF(nameClasseVal, "nom"))
			return;

		Classe classe = new Classe(nameClasseVal);

		int status = AssistantMetier.addClasse(classe);
		if (status < 0) {

			String errorMsg = null;
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle(Main.TITLE);
			alert.setHeaderText("Add classe!!");

			if (status == -1)
				errorMsg = ("Oops. Cette classe existe deja.");
			if (status == -2)
				errorMsg = ("Oops. Ce nom existe deja.");
			if (status == -3)
				errorMsg = ("Oops. Ce chef de classe est déja associe.");

			alert.setContentText(errorMsg);
			alert.showAndWait();
			return;
		}

		ClasseUser classeUser = new ClasseUser(classe);
		classesUsers.add(classeUser);

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(Main.TITLE);
		alert.setHeaderText("Classe ajoutee avec succes!");
		alert.showAndWait();

		nameClasseAddTF.setText(null);
	}

	private void updateClasse() throws UMSException {
		ClasseUser classeChief = new ClasseUser();
		try {
			classeChief = tableClassesChiefs.getSelectionModel().getSelectedItem();
		} catch (NullPointerException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(Main.TITLE);
			alert.setHeaderText("Update classe!");
			alert.setContentText("Aucune classe selectionee.");
			alert.showAndWait();
			return;
		}

		User chief = new User();
		try {
			chief = tableChiefs.getSelectionModel().getSelectedItem();
		} catch (NullPointerException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(Main.TITLE);
			alert.setHeaderText("Update classe!");
			alert.setContentText("Aucun chef de classe selectionee.");
			alert.showAndWait();
			return;
		}

		Classe classe = classeChief.getClasse();

		classe.setIdChief(chief.getId());
		int selectedIndex = tableClassesChiefs.getSelectionModel().getSelectedIndex();

		int status = AssistantMetier.updateClasse(classe, selectedIndex);

		if (status < 0) {
			if (status == 1) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle(Main.TITLE);
				alert.setHeaderText("Update classe!");
				alert.setContentText("Ce chef a deja une classe.");
				alert.showAndWait();
			}
			return;
		}

		classeChief = new ClasseUser(classe, chief);
		classesUsers.remove(selectedIndex);
		classesUsers.add(selectedIndex, classeChief);

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(Main.TITLE);
		alert.setHeaderText("Classe modifiee avec succes!");
		alert.showAndWait();
	}

	@FXML
	void updateClasseActionB(ActionEvent event) {
		if (addClasseRB.isSelected())
			classeActionB.setText("Add");
		else
			classeActionB.setText("Update");
	}

	@FXML
	void addOrUpdateClasse(ActionEvent event) throws UMSException {
		if (classeActionB.getText().equals("Add"))
			addClasse();
		else
			updateClasse();
	}

	@FXML
	void minimizeWindow(ActionEvent event) {
		ServiceController.minimizeWindow(event);
	}

	@FXML
	void logOut(ActionEvent event) throws IOException {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(Main.TITLE);
		alert.setHeaderText("Deconnexion");
		alert.setContentText("Vous deconnecter?");

		if (alert.showAndWait().get() == ButtonType.OK) {
			UserSession.logOut();
			String rootFxmlFile = "Home";
			loader = new FXMLLoader(getClass().getResource(Main.FXML_PATH + rootFxmlFile + ".fxml"));
			root = loader.load();

			stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle(Main.TITLE);
			stage.setResizable(false);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

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
			stage.show();

			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.close();
		}
	}

	@FXML
	void showProfile(ActionEvent event) {
		try {
			ProfileController profileController = ProfileController.getInstance();

			if (ProfileController.isOpen) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle(Main.TITLE);
				alert.setHeaderText("Ouverture!!");
				alert.setContentText("La fenetre de profile est ouverte.");
				alert.showAndWait();
			} else {
				ProfileController.isOpen = true;
				String rootFxmlFile = "Profile";
				loader = new FXMLLoader(getClass().getResource(Main.FXML_PATH + rootFxmlFile + ".fxml"));
				loader.setController(profileController);
				root = loader.load();

				stage = new Stage();
				stage.setScene(new Scene(root));
				stage.setTitle(Main.TITLE);
				stage.setResizable(false);
				stage.initStyle(StageStyle.UNDECORATED);
				stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

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
				stage.show();
			}
		} catch (

		Exception e2) {
			e2.printStackTrace();
		}
	}

}
