/**
 * Copyright (c) 2001-2016 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://robocode.sourceforge.net/license/epl-v10.html
 */
package Blais_Robots;

import static robocode.util.Utils.*;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import java.awt.*;
import java.lang.*;
import robocode.*;


/**
 * Fire - a sample robot by Mathew Nelson, and maintained.
 * <p/>
 * Sits still. Spins gun around. Moves when hit.
 *
 * @author Mathew A. Nelson (original)
 * @author Flemming N. Larsen (contributor)
 */
public class Blais_V2 extends Robot {
	int dist = 50; // distance to move when we're hit

	/**
	 * run:  Fire's main run function
	 */
	public void run() {
		// Set colors
		setBodyColor(Color.orange);
		setGunColor(Color.orange);
		setRadarColor(Color.red);
		setScanColor(Color.red);
		setBulletColor(Color.red);

		// Spin the gun around slowly... forever
		while (true) {
			//Left movement randomized
			double rng1 = Math.random();
			double rng2 = Math.random();

			if (rng1 <= 0.5) {
				turnLeft(Math.max(rng1 * 500, rng2 * 500));
				ahead(Math.max(rng1 * 500, rng2 * 400));
			} else {
				turnRight(Math.max(rng1 * 400, rng2 * 500));
				back(Math.max(rng1 * 500, rng2 * 500));
			}
			turnGunRight(5);
		}
	}

	/**
	 * onScannedRobot:  Fire!
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// If the other robot is close by, and we have plenty of life,
		// fire hard!
		double bulletPower = 3;
    double headOnBearing = Math.toRadians(getHeading()) + Math.toRadians(e.getBearing());
    double linearBearing = headOnBearing + Math.asin(e.getVelocity() / Rules.getBulletSpeed(bulletPower) * Math.sin(Math.toRadians(e.getHeading()) - headOnBearing));
    turnGunRight(Math.toDegrees(normalRelativeAngle(linearBearing - Math.toRadians(getGunHeading()))));
    fire(bulletPower);
}
		// Call scan again, before we turn the gun
	/**
	 * onHitByBullet:  Turn perpendicular to the bullet, and move a bit.
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		turnRight(normalRelativeAngleDegrees(90 - (getHeading() - e.getHeading())));

		ahead(dist);
		dist *= -1;
		scan();
	}

	/**
	 * onHitRobot:  Aim at it.  Fire Hard!
	 */
	public void onHitRobot(HitRobotEvent e) {
		double turnGunAmt = normalRelativeAngleDegrees(e.getBearing() + getHeading() - getGunHeading());

		turnGunRight(turnGunAmt);
		fire(3);
	}
}
