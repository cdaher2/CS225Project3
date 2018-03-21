package RacingSimulator;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Car {

    private int driveWheels;
    private int tireGrip;
    private int engineMaxRPM;
    private int enginePeakTorque;
    private int currentEngineRPM;
    private int currentEngineTorque;
    private int weight;
    private int downforceFromAero;
    private int engineTemp;
    private int damage;
    private int frontSuspensionStiffness;
    private int rearSuspensionStiffness;
    private Driver driver;
    private String makeAndModel;
    private File image, dataFile;
    private double[][] coordinates;

    public Car(){
        dataFile = new File("file.txt");
        readDataFromFile();
    }
    
    /*readDataFromFile, takes in variables from a .txt file and sets the attributes for the car instance
    uses a scanner and reads in 12 lines of ints
    */
    private void readDataFromFile(){
      try{
        Scanner scan = new Scanner(dataFile);
        while(scan.hasNextLine()){
          setDriveWheels(scan.nextInt());
          setTireGrip(scan.nextInt());
          setEngineMaxRPM(scan.nextInt());
          setEnginePeakTorque(scan.nextInt());
          setCurrentEngineRPM(scan.nextInt());
          setCurrentEngineTorque(scan.nextInt());
          setWeight(scan.nextInt());
          setDownforceFromAero(scan.nextInt());
          setEngineTemp(scan.nextInt());
          setDamage(scan.nextInt());
          setFrontSuspensionStiffness(scan.nextInt());
          setRearSuspensionStiffness(scan.nextInt());
        }
        scan.close();
      }
      catch(FileNotFoundException e){
        e.printStackTrace();}
    }
    // uses an algorithm to calculate the top speed of the car
    // given the current conditions the car is in
    // the most important part of the sim, basically determines which car will win
    //@return - double containing top speed
    public double calculateTopSpeedInCurrentConditions(){
      return (driveWheels+tireGrip+engineMaxRPM+enginePeakTorque+currentEngineRPM+currentEngineTorque+
              weight+downforceFromAero+engineTemp+damage+frontSuspensionStiffness+rearSuspensionStiffness);
      
    } 
    // @return int driveWheels attribute
    public int getDriveWheels(){
        return driveWheels;
    }
    // @return int tireGrip attribute    
    public int getTireGrip(){
        return tireGrip;
    }    
    // @return int engineMaxRPM attribute
    public int getEngineMaxRPM(){
        return engineMaxRPM;
    }
    // @return int enginePeakTorque attribute
    public int getEnginePeakTorque(){
        return enginePeakTorque;
    }
    // @return int currentEngineRPM attribute
    public int getCurrentEngineRPM(){
        return currentEngineRPM;
    }
    // @return int currentEngineTorque attribute
    public int getCurrentEngineTorque(){
        return currentEngineTorque;
    }
    // @return int weight attribute
    public int getWeight(){
        return weight;
    }
    // @return int downforceFromAero attribute
    public int getDownforceFromAero(){
        return downforceFromAero;
    }
    // @return int engineTemp attribute
    public int getEngineTemp(){
        return engineTemp;
    }
    // @return int damage attribute
    public int getDamage(){
        return damage;
    }
    // @return int frontSuspensionStiffness attribute
    public int getFrontSuspensionStiffness(){
        return frontSuspensionStiffness;
    }
    // @return int rearSuspensionStiffness attribute
    public int getRearSuspensionStiffness(){
        return rearSuspensionStiffness;
    }
    // @return instance of Driver class
    public Driver getDriver(){
        return driver;
    }
    // @return String makeAndModel attribute
    public String getMakeAndModel(){
        return makeAndModel;
    }
    // sets driveWheels attribute
    public void setDriveWheels(int dw){
        driveWheels = dw;
    }
    // sets tireGrip attribute
    public void setTireGrip(int tg){
        tireGrip = tg;
    }
    // sets engineMaxRPM attribute
    public void setEngineMaxRPM(int emRPM){
        engineMaxRPM = emRPM;
    }
    // sets enginePeakTorque attribute
    public void setEnginePeakTorque(int ept){
        enginePeakTorque = ept;
    }
    // sets currentEngineRPM attribute
    public void setCurrentEngineRPM(int ceRPM){
        currentEngineRPM = ceRPM;
    }
    // sets curentEngineTorque attribute
    public void setCurrentEngineTorque(int cet){
        currentEngineTorque = cet;
    }
    // sets weight attribute
    public void setWeight(int w){
        weight = w;
    }
    // sets downforceFromAero attribute
    public void setDownforceFromAero(int dfa){
        downforceFromAero = dfa;
    }
    // sets engineTemp attribute
    public void setEngineTemp(int et){
        engineTemp = et;
    }
    // sets damage attribute
    public void setDamage(int d){
        damage = d;
    }
    // sets frontSuspensionStiffness attribute
    public void setFrontSuspensionStiffness(int fss){
        frontSuspensionStiffness = fss;
    }
    // sets rearSuspensionStiffness attribute
    public void setRearSuspensionStiffness(int rss){
        rearSuspensionStiffness = rss;
    }
    // sets driver attribute using an instance of Driver class
    public void setDriver(Driver d){
        driver = d;
    }
    // sets makeAndModel attribute
    public void setMakeAndModel(String mam){
        makeAndModel = mam;
    }
}
