package RacingSimulator;

import java.io.File;

public class Car {

    int driveWheels;
    int tireGrip;
    int engineMaxRPM;
    int enginePeakTorque;
    int currentEngineRPM;
    int currentEngineTorque;
    int weight;
    int downforceFromAero;
    int engineTemp;
    int damage;
    int frontSuspensionStiffness;
    int rearSuspensionStiffness;
    Driver driver;
    String makeAndModel;
    File image, dataFile;
    double[][] coordinates;


    public Car(String f){
        dataFile = new File(f);
        readDataFromFile();
    }


    private void readDataFromFile(){

    }

    public double calculateTopSpeedInCurrentConditions(){
        return 0;
    }

    public void findBestLine(){

    }

}
