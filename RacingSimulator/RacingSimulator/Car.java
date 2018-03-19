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
    public double calculateTopSpeedInCurrentConditions(){
      return (driveWheels+tireGrip+engineMaxRPM+enginePeakTorque+currentEngineRPM+currentEngineTorque+
              weight+downforceFromAero+engineTemp+damage+frontSuspensionStiffness+rearSuspensionStiffness);
      
    } 
    public int getDriveWheels(){
        return driveWheels;
    }
    public int getTireGrip(){
        return tireGrip;
    }
    public int getEngineMaxRPM(){
        return engineMaxRPM;
    }
    public int getEnginePeakTorque(){
        return enginePeakTorque;
    }
    public int getCurrentEngineRPM(){
        return currentEngineRPM;
    }
    public int getCurrentEngineTorque(){
        return currentEngineTorque;
    }
    public int getWeight(){
        return weight;
    }
    public int getDownforceFromAero(){
        return downforceFromAero;
    }
    public int getEngineTemp(){
        return engineTemp;
    }
    public int getDamage(){
        return damage;
    }
    public int getFrontSuspensionStiffness(){
        return frontSuspensionStiffness;
    }
    public int getRearSuspensionStiffness(){
        return rearSuspensionStiffness;
    }
    public Driver getDriver(){
        return driver;
    }

    public String getMakeAndModel(){
        return makeAndModel;
    }
    public void setDriveWheels(int dw){
        driveWheels = dw;
    }
    public void setTireGrip(int tg){
        tireGrip = tg;
    }
    public void setEngineMaxRPM(int emRPM){
        engineMaxRPM = emRPM;
    }
    public void setEnginePeakTorque(int ept){
        enginePeakTorque = ept;
    }
    public void setCurrentEngineRPM(int ceRPM){
        currentEngineRPM = ceRPM;
    }
    public void setCurrentEngineTorque(int cet){
        currentEngineTorque = cet;
    }
    public void setWeight(int w){
        weight = w;
    }
    public void setDownforceFromAero(int dfa){
        downforceFromAero = dfa;
    }
    public void setEngineTemp(int et){
        engineTemp = et;
    }
    public void setDamage(int d){
        damage = d;
    }
    public void setFrontSuspensionStiffness(int fss){
        frontSuspensionStiffness = fss;
    }
    public void setRearSuspensionStiffness(int rss){
        rearSuspensionStiffness = rss;
    }
    public void setDriver(Driver d){
        driver = d;
    }
    public void setMakeAndModel(String mam){
        makeAndModel = mam;
    }
}
