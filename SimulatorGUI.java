package racingsim;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class SimulatorGUI extends Application {
    private HBox mainGUI;
    private Simulator sim;
    private Canvas trackImage;
    private GraphicsContext gc;
    private double lastX, lastY;

    public void start(Stage primaryStage) throws InterruptedException {
        Scene s = makeGUI();
        primaryStage.setScene(s);
        primaryStage.show();
    }

    private Scene makeGUI() throws InterruptedException {
        lastX = 1;
        lastY = 1;
        mainGUI = new HBox(); //the main GUI
        VBox leftHalf, rightHalf; //the halves of the GUI
        leftHalf = new VBox(); //leftHalf holds the buttons and the image representing the race
        rightHalf = new VBox(); //rightHalf holds the script of the race as it occurs
        mainGUI.getChildren().addAll(leftHalf, rightHalf); //adding our halves to the main GUI
        HBox controls = new HBox(); //Button controls



        trackImage = new Canvas(1000, 500); //Canvas for creating the image representing the race
        //We need to modularly set the size of the canvas based on size of the track
        //drawCarsOnTrack();

        leftHalf.getChildren().add(trackImage); //add the trackImage to the left half
        leftHalf.getChildren().add(controls); //add the controls to the left half underneath the image

        Button start, chooseCar, chooseTrack;
        start = new Button("Start Race");
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startSimulation();
            }
        });
        chooseCar = new Button("Select your car");
        chooseCar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showMenuCarSelection();
            }
        });
        chooseTrack = new Button("Choose your track");
        chooseTrack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showMenuTrackSelection();
            }
        });
        sim = new Simulator();
        Thread.sleep(100);
        sim.setTrack(sim.getTrackList().get(0));
        drawTrack();

        controls.getChildren().addAll(start, chooseCar, chooseTrack);

        return new Scene(mainGUI);
    }

    private void drawTrack(){
        for(int i = 0; i < 4; i++) {
            drawSegment();
        }
    }

    /*
    private void drawCarsOnTrack(){
        gc = trackImage.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        gc.fillPolygon(new double[]{10, 40, 10, 40},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                new double[]{210, 210, 240, 240}, 4);
    }*/

    public void drawSegment() {
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


        //gc.strokeLine(40, 10, 10, 40);
        int adj = 10;
        gc.strokeLine(lastX * adj, lastY * adj, newX * adj, newY * adj);
        //gc.strokeLine(lastX, lastY, newX, newY);
        lastX = newX;
        lastY = newY;

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

    }
}
