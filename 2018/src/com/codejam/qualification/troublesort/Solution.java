package com.codejam.qualification.troublesort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * Passes the first test case
 *
 */

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int t = Integer.parseInt(in.nextLine());

        for (int i = 1; i <= t; ++i) {
            int integerArraySize = in.nextInt();
            int[] integerArray = new int[integerArraySize];

            for (int j = 0; j < integerArraySize; j++) {
                integerArray[j] = in.nextInt();
            }

            String solution = resolve(integerArraySize, integerArray);

            System.out.println("Case #" + i + ": " + solution);
        }

    }

    private static String resolve(int integerArraySize, int[] integerArray) {

        boolean finished = false;
        while(!finished) {
            finished = true;

            for (int i = 0; i < integerArray.length - 2; i++) {
                if (integerArray[i] > integerArray[i+2]) {
                    int tmp = integerArray[i];
                    integerArray[i] = integerArray[i+2];
                    integerArray[i+2] = tmp;
                    finished = false;
                }
            }
        }

        for (int i = 0; i < integerArray.length - 1; i++) {
            if (integerArray[i] > integerArray[i+1]) {
                return "" + i;
            }
        }

        return "OK";
    }

}

