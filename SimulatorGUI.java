public class SimulatorGUI extends Application {
    private HBox mainGUI;
    private Simulator sim;

    private void drawTrack(){

    }

    public void start(Stage primaryStage){
        primaryStage.setScene(makeGUI());
        primaryStage.show();
    }

    private Scene makeGUI(){
        mainGUI = new HBox();
        VBox leftHalf, rightHalf;
        leftHalf = new VBox();
        rightHalf = new VBox();
        mainGUI.add(leftHalf, rightHalf);
        HBox controls = new HBox();
        //this is where we would add the image of the racetrack to leftHalf
        leftHalf.add(controls);
    }

    private void drawCarsOnTrack(){

    }

    private void showMenuTrackSelection(){

    }

    private void showMenuCarSelection(){

    }

    private void startSimulation(){

    }
}
