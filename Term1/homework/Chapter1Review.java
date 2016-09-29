package homework;

import java.text.NumberFormat;
import java.util.Scanner;

public class Chapter1Review {

	public static void main (String[] args) {
		//Question 1
		/*
		Here we are (finally,

		at the end
		of

		Chapter 1

		 */
		System.out.print("Here we are ");
		System.out.println("(finally,\n");
		System.out.print("at the end\nof\n");
		System.out.println("\nChapter 1");

		/* Question 2 */
		System.out.println("The handsome stranger said,\n\"My name is Kim, Paul Kim.\"");

		/* Question 3
		 * a. int
		 * b. double
		 * c. Illegal, char can only have 1 character
		 * d. Illegal, double/float cannot have a letter in it
		 * e. char
		 * f. String
		 * g. String
		 * h. Illegal, int cannot have a letter in it
		 * i. char
		 * j. Illegal, char can only have 1 character
		 * k. Illegal, int cannot have a letter in it
		 * l. Illegal, int cannot have a , in it
		 */

		/* Question 4
		 * int
		 * int
		 * int
		 * long
		 */

		/* Question 5 */
		double answer1 = 8.77e-4;
		double answer2 = 299.04e0;
		double answer3 = -0.0443E6;
		double answer4 = -23e-4;
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(7);
		System.out.println(nf.format(answer1));
		System.out.println(nf.format(answer2));
		System.out.println(nf.format(answer3));
		System.out.println(nf.format(answer4));

		/* Question 6
		 * This variable is fine
		 * This variable should have a lowercase p
		 * This variable is fine
		 * This variable is fine
		 * This variable cannot have a space in it
		 * This variable is fine
		 */


		/* Question 7
		 * a. Does not requirs a cast
		 * b. requires a cast
		 * c. Does not Require a cast
		 * d. Does not Require a cast
		 * e. Requires a cast
		 * f. Does not Require a cast
		 */

		/* Question 8
		 * a. x: -0.907y: -0.003
		 * b. first is 5
		 *	  second is -77
		 */


//		double x = 0.093-1; double y = -3e-3;
//		System.out.println("x: " + x + "y: " + y);
//
//		int i = 5; int j = -77;
//		System.out.println("first is "+i+"\nsecond is "+j);

		/* Question 9 */
		//class BadNews {
		//public static main(String[] args) {
		int i = 7;
		int j = 3;
		System.out.print("Smaller is " + i);
		System.out.println("  Larger is " + j);
		//}
	//}

		/* Question 10 */

		/* Question 11 */
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter a string:");
		String answerString = scanner.next();

		System.out.println("Enter a character");
		String character = scanner.next();

		System.out.println("Enter an integer");
		int integer = scanner.nextInt();

		System.out.println("Enter a float value");
		float floatValue = scanner.nextFloat();

		System.out.println("You entered " + answerString + " as your string!");
		System.out.println("You entered " + character + " as your character!");
		System.out.println("You entered " + integer + " as your integer!");
		System.out.println("You entered " + floatValue + "as your float!");

	}

}
