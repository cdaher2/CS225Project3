package cars;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File; 
public class OpenNewModelessWindowExample extends Application {
	File f = new File("cars.txt");
	Car car1 = new Car(f);
    @Override
    public void start(final Stage primaryStage) {
        //creating car data button
        Button button = new Button();
        button.setText("Car data");
 
        button.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	//creating labels
            	Label firstLabel = new Label("Make and model:" + " " + car1.getMakeAndModel());
            	Label secondLabel = new Label("Drive Wheels: " + car1.getDriveWheels());
                Label thirdLabel = new Label("Tire Grip: " +car1.getTireGrip());
                Label fourthLabel = new Label("Max Engine RPM: " + car1.getEngineMaxRPM());
                Label fifthLabel = new Label("Engine peak torque: " + car1.getEnginePeakTorque());
        		Label sixLabel = new Label("Current engine RPM: " + car1.getCurrentEngineRPM());
        		Label sevenLabel = new Label("Current engine torque: " + car1.getCurrentEngineTorque());
        		Label eightLabel = new Label("Car weight: " + car1.getWeight());
        		Label nineLabel = new Label("Down force: " + car1.getDownforceFromAero());
        		Label tenLabel = new Label("Engine temp: " + car1.getEngineTemp());
        		Label elevenLabel = new Label("Damage : " + car1.getDamage());
        		Label twelveLabel = new Label("Front suspension stiffness: " + car1.getFrontSuspensionStiffness());
        		Label thirteenLabel = new Label("Rear suspension stiffness: " + car1.getRearSuspensionStiffness());
                
                //creating VBox layout, adding all labels
                VBox vbox = new VBox(firstLabel, secondLabel, thirdLabel, fourthLabel, fifthLabel, sixLabel, sevenLabel, eightLabel, 
                		nineLabel, tenLabel, elevenLabel, twelveLabel, thirteenLabel);
                vbox.setSpacing(5);
                
                StackPane secondaryLayout = new StackPane();
                //adding vbox to new window layout
                secondaryLayout.getChildren().addAll(vbox);
 
                Scene secondScene = new Scene(secondaryLayout, 300, 300);
                
                
                // New window 
                Stage newWindow = new Stage();
                newWindow.setTitle("Car data");
                newWindow.setScene(secondScene);
 
                // Set position of second window, related to primary window.
                newWindow.setX(primaryStage.getX() + 200);
                newWindow.setY(primaryStage.getY() + 100);
 
                newWindow.show();
            }
        });
 
        StackPane root = new StackPane();
        root.getChildren().add(button);
 
        Scene scene = new Scene(root, 450, 250);
 
        primaryStage.setTitle("Main Stage");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 
    public static void main(String[] args) {
    	
    	launch(args);
        
    }
 
}