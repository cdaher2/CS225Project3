package RacingSimulator;


import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class SimulatorGUI extends Application {
    private Simulator sim; //Simulator instance. We interact with the rest of the classes through simulator
    private Canvas trackImage; //Canvas for creating a visual representation of our racetrack
    private double lastX, lastY; //variables for keeping track of the last coordinates we had when we draw
    private Path p;
    private ArrayList<MoveTo> stops;
    private GraphicsContext gc;
    private PathTransition pt;
    private Timeline t;
    private AnimationTimer timer;
    private HBox mainGUI;
    private VBox leftHalf, rightHalf;
    private Pane pane;
    private int index;

    public void start(Stage primaryStage) throws InterruptedException {
        Scene s = makeGUI();
        primaryStage.setScene(s);
        primaryStage.show();
    }

    private Scene makeGUI() throws InterruptedException {
        stops = new ArrayList<MoveTo>();
        lastX = 1; //initializing lastX. It needs to be a non-zero number to properly appear on the canvas later.
        lastY = 1; //initializing lastY. It needs to be a non-zero number to properly appear on the canvas later.
        mainGUI = new HBox(); //initializing our mainGUI HBox to occupy our scene
        //VBox leftHalf, rightHalf; //the halves of the GUI
        leftHalf = new VBox(); //leftHalf holds the buttons and the image representing the race
        rightHalf = new VBox(); //rightHalf holds the script of the race as it occurs
        StackPane sp = new StackPane();
        leftHalf.getChildren().add(sp);

        mainGUI.getChildren().addAll(leftHalf, rightHalf); //adding our halves to the main GUI
        HBox controls = new HBox(); //Button controls
        pane = new Pane();
        trackImage = new Canvas(1000, 500); //Canvas for creating the image representing the race
        //We need to modularly set the size of the canvas based on size of the track

        sp.getChildren().add(trackImage); //add the trackImage to the left half
        sp.getChildren().add(pane);
        leftHalf.getChildren().add(controls); //add the controls to the left half underneath the image

        Button start, chooseCar, chooseTrack; //Button declarations
        start = new Button("Start Race"); //start button initialization
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startSimulation();
            }
        });
        chooseCar = new Button("Select your car"); //chooseCar button initialization
        chooseCar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showMenuCarSelection();
            }
        });
        chooseTrack = new Button("Choose your track"); //chooseTrack button initialization
        chooseTrack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showMenuTrackSelection();
            }
        });

        sim = new Simulator(); //initialization of our simulator

        controls.getChildren().addAll(start, chooseCar, chooseTrack);

        return new Scene(mainGUI);
    }

    private void drawTrack(){
        index = 0;
        p = new Path();
        for(int i = 0; i < sim.getTrack().getNumberOfSegments(); i++) {
            drawSegment();
        }
        drawCarsOnTrack();
    }

    private void drawCarsOnTrack(){
        Rectangle car = new Rectangle(lastX, lastY, 5, 5);
        car.setArcHeight(10);
        car.setArcHeight(10);
        car.setFill(Color.ORANGE);

        pane.getChildren().add(car);

        pt = new PathTransition();
        pt.setPath(p);
        pt.setDuration(Duration.millis(10000));
        pt.setNode(car);

        //System.out.println(p.toString());
    }

    private void drawSegment() {
        createStop();
        gc = trackImage.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);

        Segment s = sim.getTrack().getNextSegment();
        gc.setLineWidth(s.getWidth());
        double angle = s.getAngle();
        double length = s.getLength();
        double newX, newY;
        //draw segment from gc using LastX, LastY as starting draw point and their modified form as not.
        //lastX += sim.getTrack().getSegment(segNum).getLength();
        //lastY += sim.getTrack().getSegment(segNum).getLength();


        //if statement to determine the direction of the angle to modify the direction we will draw in for each coordinate
        newX = lastX + length * Math.cos(angle);
        newY = lastY + length * Math.sin(angle);

        p.getElements().add(stops.get(index));
        p.getElements().add(new LineTo(newX , newY));
        //gc.strokeLine(40, 10, 10, 40);
        gc.strokeLine(lastX, lastY, newX, newY);
        //gc.strokeLine(lastX, lastY, newX, newY);
        lastX = newX;
        lastY = newY;
        index++;

    }

    private void createStop(){
        stops.add(new MoveTo(lastX, lastY));
    }

    private void showMenuTrackSelection(){
        ScrollPane sc = new ScrollPane();
        Scene s = new Scene(sc);
        Stage temp = new Stage();
        temp.setScene(s);
        temp.show();
    }

    private void showMenuCarSelection(){
        ScrollPane sc = new ScrollPane();
        Scene s = new Scene(sc);
        Stage temp = new Stage();
        temp.setScene(s);
        temp.show();
    }

    private void startSimulation(){
        sim.setTrack(sim.getTrackList().get(0)); //setting the track. This is only for making sure the program runs when I draw the track
        drawTrack();
        pt.play();
        //timer.start();
        //t.play();
    }
}
