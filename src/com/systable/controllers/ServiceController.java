package com.systable.controllers;

import com.systable.app.Main;
import com.systable.enumeration.Profile;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ServiceController {

	public static boolean checkStringTF(String value, String name) {

		if (value == null || value.isBlank()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(Main.TITLE);
			alert.setHeaderText("Champ requis!!");
			alert.setContentText("Le champ " + name + " est vide.");
			alert.showAndWait();

			return false;
		}

		return true;
	}

	public static boolean checkIntTF(String value, String name) {

		if (value == null || value.isBlank()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(Main.TITLE);
			alert.setHeaderText("Champ requis!!");
			alert.setContentText("Le champ " + name + " est vide.");
			alert.showAndWait();

			return false;
		}

		try {
			Integer.parseInt(value);
		} catch (NumberFormatException ne) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(Main.TITLE);
			alert.setHeaderText("Format incorrect!!");
			alert.setContentText("Le champ " + name + " doit etre un nombre.");
			alert.showAndWait();

			return false;
		}

		return true;
	}

	public static boolean checkEmailTF(String email, String name) {
		if (email == null || email.isBlank()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(Main.TITLE);
			alert.setHeaderText("Champ requis!!");
			alert.setContentText("Le champ " + name + " est vide.");
			alert.showAndWait();

			return false;
		} else if (!email.contains("@") && !email.contains(".")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(Main.TITLE);
			alert.setHeaderText("Format incorrect!!");
			alert.setContentText("Le champ " + name + " doit etre un email.\nExample: example@gmail.com");
			alert.showAndWait();
			return false;
		}

		return true;
	}

	public static <T> boolean checkComboBox(ComboBox<T> cb, String name) {

		if (cb.getValue() == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(Main.TITLE);
			alert.setHeaderText("Champ requis!!");
			alert.setContentText("Le champ " + name + " est vide.");
			alert.showAndWait();

			return false;
		}

		return true;
	}

	public static boolean checkTwoStringTF(String string1, String string2, String name1, String name2) {

		if (!string1.equals(string2)) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(Main.TITLE);
			alert.setHeaderText("Non concordance!!");
			alert.setContentText("Le champ " + name1 + " est different du champ " + name2 + ".");
			alert.showAndWait();

			return false;
		}

		return true;
	}

	public static void minimizeWindow(Pane rootWindow) {
		Stage stage = (Stage) rootWindow.getScene().getWindow();
		stage.setIconified(true);
	}
}
