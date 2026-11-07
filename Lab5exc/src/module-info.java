module Lab5exc {
	requires javafx.controls;
	exports application;
	opens application to javafx.graphics;
}
