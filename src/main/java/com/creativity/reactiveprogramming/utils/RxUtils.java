package com.creativity.reactiveprogramming.utils;

import com.creativity.reactiveprogramming.models.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RxUtils {

    public static List<Integer> positiveNumbers(Integer n) {
        List<Integer> positiveNumbers = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            positiveNumbers.add(i);
        }
        return positiveNumbers;
    }

    public static List<Shape> shapes(Integer n) {
        List<Shape> shapes = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            shapes.add(new Shape(randomShape(), randomColor()));
        }
        return shapes;
    }

    public static List<Integer> primeNumbers(Integer n) {
        List<Integer> primerNumbers = new ArrayList<>(n);
        boolean totalPrimeCollect = false;
        int i = 1;
        int totalNumbers = 0;
        while (!totalPrimeCollect) {
            if (isPrime(i)) {
                primerNumbers.add(i);
                totalNumbers++;
            }
            i++;
            if (totalNumbers == n) {
                totalPrimeCollect = true;
            }
        }
        return primerNumbers;
    }

    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static String randomColor() {
        Random random = new Random();
        String[] colors = {"blue", "green", "red", "yellow", "pink"};
        return colors[random.nextInt(getRandomNumberInRange(1, 5))];
    }

    public static String randomShape() {
        Random random = new Random();
        String[] shape = {"square", "triangle", "circle", "pentagon", "hexagon", "start"};
        return shape[random.nextInt(getRandomNumberInRange(1, 6))];
    }

    public static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max debe ser mayor que min");
        }
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public static void sleeps(long s) throws InterruptedException {
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
