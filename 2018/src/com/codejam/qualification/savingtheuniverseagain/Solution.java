package com.codejam.qualification.savingtheuniverseagain;

import java.util.*;
import java.io.*;

/**
 *
 * Passes both test cases
 *
 */

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int t = Integer.parseInt(in.nextLine());

        for (int i = 1; i <= t; ++i) {
            String[] splittedLine = in.nextLine().split(" ");
            int shieldPower = Integer.parseInt(splittedLine[0]);
            String actions = splittedLine[1];

            String solution = resolve(shieldPower, actions);

            System.out.println("Case #" + i + ": " + solution);
        }

    }

    private static String resolve(int shieldPower, String actions) {

        List<Shot> shots = new ArrayList<>();
        List<Shot> buffer = new ArrayList<>();
        Shot currentShot = new Shot(1, 0);
        buffer.add(currentShot);
        for (int i = 0; i < actions.length(); i++) {
            if (actions.charAt(i) == 'S') {
                shots.addAll(buffer);
                buffer = new ArrayList<>();
                currentShot.increaseNumber();
            } else {
                currentShot = new Shot(currentShot.damage * 2, 0);
                buffer.add(currentShot);
            }
        }

        int totalDamage = 0;
        for (int i = 0; i < shots.size(); i++) {
            totalDamage += shots.get(i).getTotalDamage();
        }

        if (totalDamage <= shieldPower) {
            return "0";
        }

        int hacksNumber = 0;
        for (int i = shots.size() - 1; i >= 0; i--) {

            for (int j = shots.get(i).number; j > 0; j--) {

                if (i - 1 >= 0) {
                    shots.get(i).decreaseNumber();
                    shots.get(i - 1).increaseNumber();

                    hacksNumber++;
                    totalDamage -= shots.get(i).damage - shots.get(i - 1).damage;
                    if (totalDamage <= shieldPower) {
                        return "" + hacksNumber;
                    }
                } else {
                    return "IMPOSSIBLE";
                }

            }

        }

        return "IMPOSSIBLE";
    }

    private static class Shot {

        public int damage;
        public int number;

        public Shot(int damage, int number) {
            this.damage = damage;
            this.number = number;
        }

        public void increaseNumber() {
            this.number++;
        }

        public void decreaseNumber() {
            this.number--;
        }

        public int getTotalDamage() {
            return number * damage;
        }

    }

}

