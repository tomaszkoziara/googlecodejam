package com.codejam.qualification.cubicufo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;
import java.text.DecimalFormat;

/**
 *
 * All wrong!
 *
 */

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        in.useLocale(Locale.US);

        int t = Integer.parseInt(in.nextLine());

        for (int i = 1; i <= t; ++i) {
            double area = in.nextDouble();

            String[] solution = resolve(area);

            System.out.println("Case #" + i + ":");
            System.out.println(solution[0]);
            System.out.println(solution[1]);
            System.out.println(solution[2]);

        }

    }

    private static String[] resolve(double area) {

//        double halfArea = area / 2;
//        double radiansRotation = Math.acos(halfArea);

//        double radiansRotation = 45;
//
//        String[] solution = new String[3];
//
//        solution[0] = format((Math.cos(radiansRotation) * 0.5)) + " " + format((Math.cos(Math.toRadians(90) + radiansRotation) * 0.5)) + " 0";
//
////        double inverseRadiansRotation = Math.toRadians(360) - radiansRotation;
//        solution[1] = format((Math.sin(radiansRotation) * 0.5)) + " " + format((Math.sin(Math.toRadians(90) + radiansRotation) * 0.5)) + " 0";
//        solution[2] = "0 0 0.5";

//        double myAngle = Math.PI / 2;
//        System.out.println((double)Math.sqrt((Math.pow(Math.sin(Math.PI / 2 + myAngle), 2)) + (Math.pow(Math.cos(Math.PI / 2 + myAngle), 2))) + (double)Math.sqrt((Math.pow(Math.sin(myAngle), 2)) + (Math.pow(Math.cos(myAngle), 2))));

        double[] x = new double[3];
        double[] y = new double[3];
        double[] z = new double[3];

        double minArea = area - 1e-7;
        double maxArea = area + 1e-7;

        double currentAngle = 0;
        double nextAngle = Math.PI / 2;

        double newArea = 1;

        while (newArea < minArea || newArea > maxArea) {
//            newArea = Math.abs(Math.cos(currentAngle)) + Math.abs(Math.sin(currentAngle));
//            newArea =  Math.sqrt((Math.pow(Math.sin(Math.PI + currentAngle), 2)) + (Math.PI + Math.pow(Math.cos(currentAngle), 2))) + Math.sqrt((Math.pow(Math.sin(currentAngle), 2)) + (Math.pow(Math.cos(currentAngle), 2)));
            newArea = Math.sqrt((Math.pow(Math.sin(Math.PI / 2 + currentAngle), 2)) + (Math.pow(Math.cos(Math.PI / 2 + currentAngle), 2))) + Math.sqrt((Math.pow(Math.sin(currentAngle), 2)) + (Math.pow(Math.cos(currentAngle), 2)));

            if (newArea < minArea) {
                currentAngle += nextAngle;

            } else if (newArea > maxArea) {
                currentAngle -= nextAngle;

            } else {
                break;
            }

            nextAngle = nextAngle / 2;
        }

        x[0] = Math.cos(currentAngle)/2;
        x[1] = Math.sin(currentAngle)/2;
        x[2] = 0;

        y[0] = Math.sin(Math.PI + currentAngle)/2;
        y[1] = Math.cos(Math.PI + currentAngle)/2;
        y[2] = 0;

        String[] solution = new String[3];
        System.out.println("Angle: " + currentAngle);
        System.out.println(format(x[1]));
        System.out.println(format(y[0]));
        solution[0] = format(x[0]) + " " + format(x[1]) + " " + format(x[2]);
        solution[1] = format(y[0]) + " " + format(y[1]) + " " + format(y[2]);
        solution[2] = "0 0 0.5";

        return solution;
    }

    private static String format(double d) {
        DecimalFormat f = new DecimalFormat();
        f.setMaximumFractionDigits(16);
        f.setMinimumFractionDigits(0);

        DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
        dfs.setDecimalSeparator('.');
        f.setDecimalFormatSymbols(dfs);
        return f.format(d);
    }

}

