module projet_java_sql {
	requires java.sql;
	requires java.desktop;
	requires javafx.controls;
	requires javafx.base;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires javafx.media;
	requires javafx.swing;
	requires javafx.web;
	
	exports fx to javafx.graphics;
}