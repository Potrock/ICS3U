/**
	THE PLAN:
	USING THE OUTLINE OF "WALLS"
		Keep the parts that allow the robot to get right to a wall,
		but implement getEnergy() from Simple Robot to detect when someone is
		shooting at me, and move off of the walls to avoid the shot.
		Also, use Math.random with a min/max to randomize movement at times to avoid trig trackers
 */


// Classpath: C:\Users\pwbl\Documents\GitHub\ICS3U\Robocode\robocode\robots\Blais_Robots\Blais_v1.java


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
		fire(2);
		if (peek) {
    scan();
	}
    double changeInEnergy = previousEnergy-e.getEnergy();
    if (changeInEnergy > 0 && changeInEnergy <=3) {
      boolean spotted = true;
      //THEY JUST SHOT AT ME DODGE WITH A (kinda)RANDOMLY GENERATED MOVEMENT
      //This nice little piece of code will generate a random number between 0.1 and 2
      randomNum = 0.1 + (int)(Math.random() * 2);

      while (spotted) {
        randomNum = 0.1 + (int)(Math.random() * 2);
        if (randomNum > 0.1 && randomNum <= 0.3) { //Take our random number, and turn it into a (kinda)random movement!
          turnLeft(90);
          ahead(100);
          turnLeft(90);
          ahead(100);
          turnLeft(90);
          ahead(100);
          spotted = false;
          scan();
        } else if (randomNum > 0.3 && randomNum <= 0.5) {
          back(100);
          ahead(100);
          scan();
          spotted = false;
        } else if (randomNum > 0.5 && randomNum <= 0.8) {
          turnRight(90);
          back(100);
          turnRight(90);
          back(100);
          turnRight(90);
          back(100);
          scan();
          spotted = false;
        } else if (randomNum > 0.8 && randomNum <= 1) {
          ahead(100);
          back(200);
          scan();
          spotted = false;
        } else if (randomNum > 1 && randomNum <= 1.4) {
          ahead(100);
          back(200);
          scan();
          spotted = false;
        } else if (randomNum > 1.4 && randomNum <= 1.7) {
          back(300);
          turnRight(90);
          back(100);
          turnRight(90);
          back(300);
          scan();
          spotted = false;
        } else if (randomNum > 1.7 && randomNum <= 2) {
          turnLeft(90);
          ahead(50);
          turnRight(90);
          ahead(50);
          scan();
          spotted = false;
        } else {
          turnLeft(90);
          ahead(100);
          turnLeft(90);
          ahead(100);
          scan();
          spotted = false;
        }
      }
      turnGunRight(360); //Sweeps scanner?
    }
  }
}
