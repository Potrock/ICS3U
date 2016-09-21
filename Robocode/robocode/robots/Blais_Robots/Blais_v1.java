/**
	THE PLAN:
	USING THE OUTLINE OF "WALLS"
		Keep the parts that allow the robot to get right to a wall,
		but implement getEnergy() from Simple Robot to detect when someone is
		shooting at me, and move off of the walls to avoid the shot.
		Also, use Math.random with a min/max to randomize movement at times to avoid trig trackers
 */

 // TEST FOR GIT SYNCING

 //SECOND TEST FOR GIT SYNCING
package Blais_Robots;


import robocode.HitRobotEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;
//import robocode.*;

import java.awt.*;

/**
  Potbot: a robot by Patrick Blais
*/

public class Blais_v1 extends Robot {
  double previousEnergy = 100;
  int movementDirection = 1;
  int gunDirection = 1;
  double randomNum;
	boolean peek; // Don't turn if someone is there
	double moveAmount;

	/**
	 * run: Move around the walls
	 */
	public void run() {
		// Set colors
		setBodyColor(Color.black);
		setGunColor(Color.black);
		setRadarColor(Color.black);
		setBulletColor(Color.black);
		setScanColor(Color.black);

		// Initialize moveAmount to the maximum possible for this battlefield.
		moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		// Initialize peek to false
		peek = false;

		// turnLeft to face a wall.
		// getHeading() % 90 means the remainder of
		// getHeading() divided by 90.
		turnLeft(getHeading() % 90);
		ahead(moveAmount);
		// Turn the gun to turn right 90 degrees.
		peek = true;
		turnGunRight(90);
		turnRight(90);

		while (true) {
			// Look before we turn when ahead() completes.
			peek = true;
			// Move up the wall
			ahead(moveAmount);
			// Don't look now
			peek = false;
			// Turn to the next wall
			turnRight(90);
		}
	}

	/**
	 * onHitRobot:  Move away a bit.
	 */
	public void onHitRobot(HitRobotEvent e) {
		// If he's in front of us, set back up a bit.
		if (e.getBearing() > -90 && e.getBearing() < 90) {
			back(100);
		} // else he's in back of us, so set ahead a bit.
		else {
			ahead(100);
		}
	}

	/**
	 * onScannedRobot:  Fire!
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// fire(2);
		// // Note that scan is called automatically when the robot is moving.
		// // By calling it manually here, we make sure we generate another scan event if there's a robot on the next
		// // wall, so that we do not start moving up it until it's gone.
		// if (peek) {
		// 	scan();
    boolean spotted = true;
    double changeInEnergy = previousEnergy-e.getEnergy();
    if (changeInEnergy > 0 && changeInEnergy <=3) {
      //THEY JUST SHOT AT ME DODGE WITH A RANDOMLY GENERATED MOVEMENT
      //This nice little piece of code will generate a random number between 0.1 and 2
      randomNum = 0.1 + (int)(Math.random() * 2);

      while (spotted) {
        if (randomNum > 0.1 && randomNum =< 0.3) {
          //ADD RANDOM #1
        } else if (randomNum > 0.3 && randomNum =< 0.5) {
          //ADD RANDOM #2
        } else if () {
          //ADD RANDOM #3
        } else if () {
          //ADD RANDOM #4
        } else if () {
          //ADD RANDOM #5
        } else {
          //ADD RANDOM #5
        }
      }
    }

    double previousEnergy = e.getEnergy(); //Sets previousEnergy to be the post-shot energy
	}

  public void dodgeScript () {

	}
}
