package com.codejam.practice.bathroomstalls;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;

/**
 *
 * Solves all 3 datasets
 *
 */

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int t = in.nextInt();

        for (int i = 1; i <= t; ++i) {
            BigInteger stalls = in.nextBigInteger();
            BigInteger people = in.nextBigInteger();

            String solution = resolve(stalls, people);

            System.out.println("Case #" + i + ": " + solution);
        }

    }

    private static String resolve(BigInteger stalls, BigInteger peopleTotal) {

        TreeSet<BigInteger> cases = new TreeSet<>();
        HashMap<BigInteger, BigInteger> caseInstances = new HashMap<>();

        cases.add(stalls);
        caseInstances.put(stalls, BigInteger.ONE);

        BigInteger peopleIn = BigInteger.ZERO;
        while (true) {
            BigInteger biggestInterval = cases.pollLast();
            BigDecimal halfInterval = new BigDecimal(biggestInterval).subtract(BigDecimal.ONE).divide(new BigDecimal(2));
            BigInteger leftInterval = halfInterval.setScale(0, RoundingMode.FLOOR).toBigInteger();
            BigInteger rightInterval = halfInterval.setScale(0, RoundingMode.CEILING).toBigInteger();

            cases.add(leftInterval);
            cases.add(rightInterval);

            BigInteger leftIntervalPreviousInstancesNumber = caseInstances.get(leftInterval) != null ? caseInstances.get(leftInterval) : BigInteger.ZERO;
            caseInstances.put(leftInterval, leftIntervalPreviousInstancesNumber.add(caseInstances.get(biggestInterval)));

            BigInteger rightIntervalPreviousInstancesNumber = caseInstances.get(rightInterval) != null ? caseInstances.get(rightInterval) : BigInteger.ZERO;
            caseInstances.put(rightInterval, rightIntervalPreviousInstancesNumber.add(caseInstances.get(biggestInterval)));

            peopleIn = peopleIn.add(caseInstances.get(biggestInterval));
            if (peopleIn.compareTo(peopleTotal) == 1 || peopleIn.compareTo(peopleTotal) == 0) {
                return rightInterval + " " + leftInterval;
            }
        }

    }

}

