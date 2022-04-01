module SysTable {
	requires javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	
	opens com.systable.app to javafx.graphics, javafx.fxml;
	opens com.systable.entities to javafx.base;
	opens com.systable.controllers to javafx.graphics, javafx.fxml;
}
