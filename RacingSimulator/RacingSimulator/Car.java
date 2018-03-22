package RacingSimulator;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

/** @authors-
 * Ben Florek
 * Devin Troy
 * Christian Daher
 *
 * Car is the basic unit of the simulation, the main focus which has a bunch of different attributes
 * whichever cars attributes best fits the algorithm is the car which will win the race. Takes input from
 * a .car file and sets attributes accordingly
 */

public class Car {

    private int tireGrip;
    private int weight;
    private int downforceFromAero;
    private double carTopSpeed;
    private Driver driver;
    private String makeAndModel;
    private File dataFile;

    public Car(File f){
        dataFile = f;
        readDataFromFile();
    }

    /**
     * readDataFromFile, takes in variables from a .car file and sets the attributes for the car instance
     *   uses a scanner and reads in 12 lines of ints
    */
    private void readDataFromFile(){
        try{
            Scanner scan = new Scanner(dataFile);
            setMakeAndModel(scan.nextLine());
            setCarTopSpeed((double)scan.nextInt());
            setTireGrip(scan.nextInt());
            setWeight(scan.nextInt());
            setDownforceFromAero(scan.nextInt());
            setDriver(new Driver(scan.nextInt()));
            scan.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
    /** uses an algorithm to calculate the top speed of the car
    * given the current conditions the car is in
    * the most important part of the sim, basically determines which car will win
    * @return - double containing top speed
   */
    private double calculateTopSpeedInCurrentConditions(Segment s){
        double topSpeed;
        if (s.getAngle() != 0) {
            double normalForce = (this.getWeight() * 9.8) + this.getDownforceFromAero();
            double maxFrictionForce = ((this.getTireGrip() + s.getGrip()) / 2) * normalForce;
            topSpeed = Math.sqrt(((maxFrictionForce * s.getLength()) / this.getWeight()));
            if (topSpeed > this.getCarTopSpeed()){
                topSpeed = this.getCarTopSpeed();
            }
        }
        else {
            topSpeed = this.getCarTopSpeed();
        }
        return topSpeed;
    }

    /**
     * Applies the driver's skill level to the speed calculation
     * This is the method that should be used to determine the car's final speed
     * @param s
     * @return
     */
    public double getSegmentSpeed(Segment s){
        double speed = calculateTopSpeedInCurrentConditions(s);
        speed = speed * (driver.successLevel(s) / 100);
        return speed;
    }

    // @return int tireGrip attribute
    public int getTireGrip(){
        return tireGrip;
    }
    // @return int weight attribute
    public int getWeight(){
        return weight;
    }
    // @return int downforceFromAero attribute
    public int getDownforceFromAero(){
        return downforceFromAero;
    }
    // @return instance of Driver class
    public Driver getDriver(){
        return driver;
    }
    // @return String makeAndModel attribute
    public String getMakeAndModel(){
        return makeAndModel;
    }
    // sets tireGrip attribute
    public void setTireGrip(int tg){
        tireGrip = tg;
    }
    // sets weight attribute
    public void setWeight(int w){
        weight = w;
    }
    // sets downforceFromAero attribute
    public void setDownforceFromAero(int dfa){
        downforceFromAero = dfa;
    }
    // sets driver attribute using an instance of Driver class
    public void setDriver(Driver d){
        driver = d;
    }
    // sets makeAndModel attribute
    public void setMakeAndModel(String mam){
        makeAndModel = mam;
    }

    public double getCarTopSpeed() {
        return carTopSpeed;
    }

    public void setCarTopSpeed(double carTopSpeed) {
        this.carTopSpeed = carTopSpeed;
    }

    public String toString(){
        return makeAndModel;
    }

}