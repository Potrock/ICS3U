package Blais_Robots;

import robocode.*;
import java.awt.Color;
import java.awt.Graphics2D;
import robocode.util.Utils;


/**
 * Opera Bot, by Patrick B
 * Compile: javac -cp C:\Users\Suzan\Documents\GitHub\ICS3U\Robocode\robocode\libs\robocode.jar Blais_V2.java
 *
 * Uses randomized movement (RNG based) to make it harder to hit, while being a Simple Robot.
 * Incorporates Predictive Targetting, hopefully without getting disabled by missing every shot.
 *
 * My strategy: Get the highest amount of points through Survival. When in practice matches against other classmates, I had less damage but significantly more survival points.
 * I've messed around with other types of shooters, tweaking values, bullet powers, but
 * increasing the bullet power could harm my robots ability to survive, since the shooter is not perfect.
 *
 */
public class Blais_V2 extends Robot {
double previousEnergy = 100;
int dist = 50;   // distance to move when we're hit
int bulletPower;
double gunTurnAmmount = 10;   // Initialize gunTurn to 10

/**
 * Runs on startup, one time commands to be run
 */
public void run() {
        // Sets colors
        setBodyColor(Color.black);
        setGunColor(Color.black);
        setRadarColor(Color.black);
        setScanColor(Color.black);
        setBulletColor(Color.black);

        //THIS WHILE LOOP WILL RUN FOREVER!
        while (true) {

                /**
                 * The RNG system works like this:
                 * rng1, rng2 are generated as a random number between 0 and 1
                 * If rng1 is between for every interval of 0.25, a certain set of movements is randomly generated depending on what the rng is.
                 * The movement is generated by multiplying the rng (1 and 2 are both used) by a multiple of 50 over 200.
                 * The movement is further randomized by using Math.max, picking the highest of the RNG1 combination, or the RNG2 combination.
                 * The multiple of 50 is changed to make more differences in distance/turns.
                 */

                double rng1 = Math.random();
                double rng2 = Math.random();

                if (rng1 <= 0.25 && rng1 > 0) { //The RNG based movement system.
                        System.out.println(rng1 + " , " + rng2); //for test purposes
                        turnLeft(Math.max(rng1 * 500, rng2 * 500));
                        ahead(Math.max(rng1 * 500, rng2 * 400));
                } else if (rng1 <=0.5 && rng1 > 0.25) {
                        turnRight(Math.max(rng1 * 400, rng2 * 500));
                        back(Math.max(rng1 * 500, rng2 * 500));
                } else if (rng1 <= 0.75 && rng1 > 0.5) {
                        turnLeft(Math.max(rng1 * 500, rng2 * 200));
                        back(Math.max(rng1 * 300, rng2 * 250));
                } else {
                        turnRight(Math.max(rng1 * 450, rng2 * 350));
                        ahead(Math.max(rng1 * 250, rng2 * 250));
                }
                turnGunRight(360); //Sweeps the scanner
        }
}

/**
 * onScannedRobot:  Fire!
 */
public void onScannedRobot(ScannedRobotEvent e) {

        double changeInEnergy = previousEnergy-e.getEnergy();
        if (changeInEnergy > 0 && changeInEnergy <=3) {
                System.out.println("I've been shot at");
                turnLeft(90);
                ahead(100);
        }
        if (e.getVelocity() > 0) { //Checks if the robot is moving, if it is, conserve energy and shoot with power 1.
                System.out.println("Found a target, checking if intendedTarget");
                double bulletPower = 1;
        }


        if (getOthers() == 4) {
                //KILL PATEL_MISTER
                String intendedTarget = "sample.SittingDuck";
                System.out.println("Initializing operation kill " + intendedTarget);
                if (e.getName().equals(intendedTarget)) {
                        if (e.getVelocity() > 0) { //Checks if the robot is moving, if it is, conserve energy and shoot with power 1.
                                System.out.println("They're moving, shooting at a lower power for safety reasons.");
                                bulletPower = 1;
                                //Gets the sum of your heading, and the bearing of the spotted robot (e).
                                double headOnBearing = Math.toRadians(getHeading()) + Math.toRadians(e.getBearing());

                                //Gets the sum of headOnBearing + the inversed sine of the velocity divided by the bullet speed.
                                //Then multiplies it by the sin of the heading(in Radians), afterwards, subtracts headOnBearing from it.
                                double linearBearing = headOnBearing + Math.asin(e.getVelocity() / Rules.getBulletSpeed(bulletPower) * Math.sin(Math.toRadians(e.getHeading()) - headOnBearing));

                                //Takes into account the current gun heading, and subtracts it from the linear bearing that we previously acquired ^^
                                turnGunRight(Math.toDegrees(Utils.normalRelativeAngle(linearBearing - Math.toRadians(getGunHeading()))));


                                fire(bulletPower);
                                System.out.println("Fired at the intendedTarget");
                                scan(); //Restart the scanned robot event
                        } else { //If this is running, the opponents velocity is not greater than zero (equal to zero). Shoot at it with lots of power.
                                bulletPower = 3; //Sets us to be using max power.
                                System.out.println("I shot them hard!");
                                fire(bulletPower); //They aren't moving! Hit them hard (Power is 3, in this case.)
                                System.out.println("Fired at the intendedTarget");
                                scan(); //Restart the scanned robot event
                        }

                }

        } else {
                if (e.getVelocity() > 0) { //Checks if the robot is moving, if it is, conserve energy and shoot with power 1.
                        System.out.println("They're moving, shooting at a lower power for safety reasons.");
                        bulletPower = 1;
                        //Gets the sum of your heading, and the bearing of the spotted robot (e).
                        double headOnBearing = Math.toRadians(getHeading()) + Math.toRadians(e.getBearing());

                        //Gets the sum of headOnBearing + the inversed sine of the velocity divided by the bullet speed.
                        //Then multiplies it by the sin of the heading(in Radians), afterwards, subtracts headOnBearing from it.
                        double linearBearing = headOnBearing + Math.asin(e.getVelocity() / Rules.getBulletSpeed(bulletPower) * Math.sin(Math.toRadians(e.getHeading()) - headOnBearing));

                        //Takes into account the current gun heading, and subtracts it from the linear bearing that we previously acquired ^^
                        turnGunRight(Math.toDegrees(Utils.normalRelativeAngle(linearBearing - Math.toRadians(getGunHeading()))));


                        fire(bulletPower);
                        scan(); //Restart the scanned robot event
                } else { //If this is running, the opponents velocity is not greater than zero (equal to zero). Shoot at it with lots of power.
                        bulletPower = 3; //Sets us to be using max power.
                        System.out.println("I shot them hard!");
                        fire(bulletPower); //They aren't moving! Hit them hard (Power is 3, in this case.)
                        scan(); //Restart the scanned robot event
                }
        }
}
public void onHitByBullet(HitByBulletEvent e) {   //When hit by bullet
        turnRight(Utils.normalRelativeAngleDegrees(90 - (getHeading() - e.getHeading()))); //Faces target

        ahead(dist);
        dist *= -1; //Multiplies 50 * -1, and sets it as the new dist variable.
        scan();
}

public void onHitWall(HitWallEvent e) {   //Attempting to prevent myself from getting stuck on walls.
        back(400);
        turnLeft(180);
        ahead(100);
        turnLeft(90);
        ahead(100);
        scan();
}


public void onHitRobot(HitRobotEvent e) {   //Aims at the robot, and shoots at max power.
        double turnGunAmt = Utils.normalRelativeAngleDegrees(e.getBearing() + getHeading() - getGunHeading()); //Figures out how much to turn gun by taking into account the current gun heading.

        turnGunRight(turnGunAmt);
        fire(3);
}
}
