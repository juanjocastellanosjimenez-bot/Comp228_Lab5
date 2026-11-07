package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;


public class Main extends Application {
	@Override
	public void start(Stage stage) {

	
// 1. Top HBox holds the application title and subtitle
	Label titleLabel = new Label("College Student Services");
	titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white;");
	Label subtitleLabel = new Label("Student Registration");
	subtitleLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
		 
	HBox header = new HBox(20, titleLabel, subtitleLabel);
	header.setPadding(new Insets(15));
	header.setAlignment(Pos.CENTER_LEFT);
	header.setStyle("-fx-background-color: #1F3A93;");

		
		
//2. Left Vbox stacks navigation buttons 

	 Label menuTittle =new Label("MENU");
	 menuTittle.setStyle("-fx-font-weight:bold;");
	 Button dashboardButton = new Button("Dashboard");
	 Button registrationButton = new Button("Registration");
	 Button coursesButton =new Button("Courses");
	 Button helpButton = new Button("Help");
	 
	 VBox navigation = new VBox(12,
			 menuTittle, dashboardButton, registrationButton,
			 coursesButton, helpButton);
	 navigation.setPadding(new Insets(15));  //Spacing...
	 navigation.setPrefWidth(160);
	 navigation.setStyle("-fx-backgorund-color: #4A90D9;");
	 
	// 3. GridPane holds the four form fields (add(node, column, row))
	TextField idField = new TextField();
	idField.setPromptText("e.g. 300123456");
	TextField nameField = new TextField();
	nameField.setPromptText("Full name");
	TextField emailField = new TextField();
	emailField.setPromptText("name@example.com");
	 
	ComboBox<String> programBox = new ComboBox<>();
	programBox.getItems().addAll(
	"Software Engineering - AI","Computer Programming","Business Administration","Game Development");
	programBox.setPromptText("Select a program");
	programBox.setPrefWidth(250);
	 
	idField.setPrefWidth(250);
	nameField.setPrefWidth(250);
	emailField.setPrefWidth(250);
	 
	GridPane form = new GridPane();
	form.setHgap(12);
	form.setVgap(14);
	form.setPadding(new Insets(10));
	form.add(new Label("Student ID:"), 0, 0);
	form.add(idField, 1, 0);
	form.add(new Label("Full Name:"), 0, 1);
	form.add(nameField, 1, 1);
	form.add(new Label("Email:"), 0, 2);
	form.add(emailField, 1, 2);
	form.add(new Label("Program:"), 0, 3);
	form.add(programBox, 1, 3);
	
	// 4. HBox holds Register and Clear buttons, then the center VBox
	// stacks: form title, GridPane, HBox buttons, status message
	Button registerButton = new Button("Register");
	Button clearButton = new Button("Clear");
	 
	HBox actions = new HBox(10, registerButton, clearButton);
	actions.setAlignment(Pos.CENTER_LEFT);
	actions.setPadding(new Insets(10));
	 
	Label formTitle = new Label("Student Registration Form");
	formTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
	Label statusLabel = new Label("");
	 
	VBox center = new VBox(15, formTitle, form, actions, statusLabel);
	center.setPadding(new Insets(20));
	
	// 6. Register button: validate required fields
	registerButton.setOnAction(event -> {
	String id = idField.getText().trim();
	String name = nameField.getText().trim();
	String email = emailField.getText().trim();
	String program = programBox.getValue();
	 
	if (id.isEmpty() || name.isEmpty() || email.isEmpty() || program == null) {
	statusLabel.setStyle("-fx-text-fill: red;");
	statusLabel.setText("Error: please fill in all fields.");
	} else if (!email.contains("@")) {
	statusLabel.setStyle("-fx-text-fill: red;");
	statusLabel.setText("Error: enter a valid email address.");
	} else {
	statusLabel.setStyle("-fx-text-fill: green;");
	statusLabel.setText("Registration accepted for " + name);
	}
	});
			 
	//5.BORDERPANE
	 BorderPane root = new BorderPane();
	 root.setTop(header);
	 root.setLeft(navigation);
	 root.setCenter(center);
	 
	 Scene scene = new Scene(root, 800, 500);
	 stage.setTitle("Lab 5");
	 stage.setScene(scene);
	 stage.show();
	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

