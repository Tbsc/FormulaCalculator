package tbsc.factorial;

import java.math.BigInteger;

/**
 * @author tbsc on 28/01/2018
 */
public class Main {

    public static void main(String[] args) {
        // Make sure user has given enough arguments
        if (args.length != 2) {
            System.err.println("Usage: ./factorial maxX maxY");
            System.exit(1);
        }

        // Make sure the maximum X is an actual number
        int maxX;
        try {
            maxX = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("Error: Given max X isn't a number.");
            System.exit(2);
            return; // to make IDE understand maxX has to be initialized
        }

        // Make sure the maximum Y is an actual number
        int maxY;
        try {
            maxY = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("Error: Given max Y isn't a number.");
            System.exit(2);
            return; // to make IDE understand maxY has to be initialized
        }

        // Create the 2D table for the calculations
        String[][] table = new String[maxX + 1][maxY + 1];

//        table[0][0] = "hello";
//        table[0][1] = "there";
//        table[1][0] = "world";
//        table[2][0] = "test";
//        table[0][2] = "trying";
//        table[1][1] = "crying";
//        table[1][2] = "why";
//        table[2][1] = "you";
//        table[2][2] = "are";
//
//        printTable(table);

//        System.out.println("x=0, y=1 - " + calculate(0, 1));
//        System.out.println("x=0, y=2 - " + calculate(0, 2));
//        System.out.println("x=0, y=3 - " + calculate(0, 3));
//        System.out.println("x=1, y=1 - " + calculate(1, 1));
//        System.out.println("x=1, y=2 - " + calculate(1, 2));
//        System.out.println("x=1, y=3 - " + calculate(1, 3));
//        System.out.println("x=2, y=1 - " + calculate(2, 1));
//        System.out.println("x=2, y=2 - " + calculate(2, 2));
//        System.out.println("x=2, y=3 - " + calculate(2, 3));
//        System.out.println("x=3, y=1 - " + calculate(3, 1));
//        System.out.println("x=3, y=2 - " + calculate(3, 2));
//        System.out.println("x=3, y=3 - " + calculate(3, 3));

        // Pre-initialize all cells in the table with an empty string
        for (int y = 0; y < table.length; ++y) {
            for (int x = 0; x < table[y].length; ++x) {
                table[y][x] = "";
            }
        }

        table[0][0] = "";

        // Pre-fill the left column with the Y values
        for (int y = 0; y < maxY; ++y) {
            table[y + 1][0] = "y=" + String.valueOf(y + 1);
        }

        // Pre-fill the top row with the X values
        for (int x = 0; x < maxX; ++x) {
            table[0][x + 1] = "x=" + String.valueOf(x);
        }

        // X values are from 0 (+ 1) to maxX inclusive
        for (int x = 1; x < maxX + 1; ++x) {
            // Y values are from 1 (+ 1) to maxX inclusive
            for (int y = 1; y < maxY + 1; ++y) {
                table[y][x] = calculate(x - 1, y).toString(100);
            }
        }

        printTable(table);
    }

    public static void printTable(String[][] table) {
        // Find out what the maximum number of columns is in any row
        int maxColumns = 0;
        for (int i = 0; i < table.length; i++) {
            maxColumns = Math.max(table[i].length, maxColumns);
        }

        // Find the maximum length of a string in each column
        int[] lengths = new int[maxColumns];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                lengths[j] = Math.max(table[i][j].length(), lengths[j]);
            }
        }

        // Generate a format string for each column
        String[] formats = new String[lengths.length];
        for (int i = 0; i < lengths.length; i++) {
            formats[i] = "%1$" + lengths[i] + "s"
                    + (i + 1 == lengths.length ? "\n" : " | ");
        }

        // Print 'em out
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.printf(formats[j], table[i][j]);
            }
        }
    }

    /**
     * Calculate the formula this whole program is built for.
     *
     *          (x + y - 1)!
     * Formula: ------------
     *           x! (y-1)!
     *
     * @param x First num
     * @param y Second num
     * @return The calculation of the formula
     */
    public static BigInteger calculate(int x, int y) {
        BigInteger mone = factorial(x + y - 1);
        BigInteger xFactorial = factorial(x);
        BigInteger mehaneRight = factorial(y - 1);
        BigInteger mehane = xFactorial.multiply(mehaneRight);
        return mone.divide(mehane);
    }

    /**
     * Calculate factorial of n.
     * @param n The number to calculate the factorial of.
     * @return Factorial of the number n.
     */
    public static BigInteger factorial(int n) {
        int i = n;
        BigInteger total = BigInteger.ONE;
        while (i != 0) {
            total = total.multiply(BigInteger.valueOf(i));
            i--;
        }
        return total;
    }

}
