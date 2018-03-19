package RacingSimulator;

/**
 * @author Christian Daher
 *
 * A simple class used to determine how well the Car performs on a certain Segment
 */

import java.util.Random;

public class Driver {

    private int skillLevel;

    /**
     * Instantiates the Driver with a number for skillLevel
     * @param skill
     */
    public Driver(int skill){
        skillLevel = skill;
    }

    /**
     * Generates a random result output as a percentage
     * Should be used in the calculateTopSpeedInCurrentConditions method in Car to determine what percentage of that Car's
     * top speed the Driver is able to achieve
     * skillLevel acts as a "re-roll" - if the "roll" is less than a certain number and there is a "re-roll" remaining,
     * the Driver gets another chance to roll a good result
     * For numbers between 20 and 70, 70 will be returned
     * Numbers between 5 and 20 will return a 40, the idea being the Driver bumped a wall or other obstacle and was severely slowed down
     * Numbers 5 and below will return a 0, indicating that the Driver has wiped out and been disqualified from the race
     * Segments with an angle of 0 should temporarily increase the Driver's skillLevel, because straights are much easier than corners
     * @param s the current Segment that the track is on
     * @return percentage of the car's maximum speed that the Driver is able to achieve
     */
    public int successLevel(Segment s){
        int b = 0;
        if (s.getAngle() == 0){
            b = 10;
        }
        int t = skillLevel + b;
        Random r = new Random();
        int roll = r.nextInt(101);
        int result = 0;

        while (t > 0 && roll < 90){
            t--;
            if (roll < 5){
                result = 0;
            }
            else if (roll < 20){
                result = 40;
            }
            else if (roll < 70){
                result = 70;
            }
            else {
                result = roll;
            }
        }

        return result;
    }

}
