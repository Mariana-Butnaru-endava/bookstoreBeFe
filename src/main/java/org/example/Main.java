package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello, World!");
        String[] colours = new String[]{"Red", "Green", "Blue", "Yellow", "Pink", "Orange"};
        List<String> coloursList = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i <= 10; i++) {
            coloursList.add(colours[rand.nextInt(colours.length)]);
        }
        coloursList.forEach(System.out::println);
        Map<String, Integer> duplicatesMap = mapDuplicates(coloursList);
        System.out.println("Duplicates:");
        duplicatesMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

        for (int i = 100; i < 1000; i++) {
            if (isArmstrong(i))
                System.out.printf("%d is Armstrong number \n", i);
        }

        Integer[] apiCodes = new Integer[]{101, 202, 303, 404, 502, 110, 115, 204, 200, 303, 401, 502, 505};
        Map<String, Integer> duplicateRanges = extractDuplicateRanges(apiCodes);
        System.out.println("Duplicate Ranges:");
        duplicateRanges.entrySet().stream().forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

    }

    public static Map<String, Integer> mapDuplicates(List<String> list) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : list) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        return map;
    }

    public static boolean isArmstrong(Integer nr) {
        String nrStr = nr.toString();
        int sum = 0;
        for (char c : nrStr.toCharArray()) {
            int digit = Character.getNumericValue(c);
            sum += Math.pow(digit, 3);
        }
        return sum == nr;
    }

    public static Map<String, Integer> extractDuplicateRanges(Integer[] ranges) {
        Map<String, Integer> map = new HashMap<>();
        String codeStr = "";
        for (int r : ranges) {
            codeStr = String.valueOf(r);
            map.put(codeStr.charAt(0) + "xx", map.getOrDefault(codeStr.charAt(0) + "xx", 0) + 1);
        }
        return map;
    }
}