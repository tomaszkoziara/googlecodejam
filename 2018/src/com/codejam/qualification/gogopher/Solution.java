package com.codejam.qualification.gogopher;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * Passes both test cases
 *
 */

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int testCases = Integer.parseInt(in.nextLine());

        for (int i = 1; i <= testCases; ++i) {
            int minimumSquares = in.nextInt();

            resolve(in, minimumSquares);

        }

    }

    // i rows, j columns
    private static void resolve(Scanner in, int minimumSquares) {

        int[] upperLine = new int[2000];
        int[] middleLine = new int[2000];
        int[] lowerLine = new int[2000];

        Position currentPosition = new Position(2,2);

        while (true) {
            System.out.println(currentPosition.i + " " + currentPosition.j);

            int i = in.nextInt();
            int j = in.nextInt();

            if (i == -1 && j == -1) {
                System.exit(-1);
            } else if (i == 0 && j == 0) {
                return;
            }

            if (i == 1) {
                upperLine[j - 1] = 1;
            } else if (i == 2) {
                middleLine[j - 1] = 1;
            } else {
                lowerLine[j - 1] = 1;
            }

            while (upperLine[currentPosition.j - 2] == 1 && middleLine[currentPosition.j - 2] == 1 && lowerLine[currentPosition.j - 2] == 1) {
                currentPosition.j = currentPosition.j + 1;
            }
        }

    }

    private static class Position {

        public int i;
        public int j;

        public Position(int i, int j){
            this.i = i;
            this.j = j;
        }

    }

}

