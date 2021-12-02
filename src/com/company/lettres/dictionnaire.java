package com.company.lettres;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Array;
import java.util.*;

public class dictionnaire {
    static String[] arr;
    private static final String[] consonne = {"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "z"};
    private static final String[] voyelle = {"a", "e", "i", "o", "u", "y"};
    public static ArrayList<String> chosenLetters = new ArrayList<>();
    static int count = 0;
    public static List<String> wordsFound = new ArrayList<>();

    public static ArrayList<String> getLetters(String letter) {

        if (letter.equals("voyelle") || letter.equals("v")) {
            chosenLetters.add(voyelle[rdnGen(voyelle.length)]);
            System.out.println(chosenLetters);
            count++;
        } else if (letter.equals("consonne") || letter.equals("c")) {
            chosenLetters.add(consonne[rdnGen(consonne.length)]);
            System.out.println(chosenLetters);
            count++;
        } else if (count == 10) {
            return chosenLetters;
        }
        return null;
    }

    private static int rdnGen(int z) {
        int min = 0;
        return (int) Math.floor(Math.random() * (z - min) + min);
    }

    public static String[] convertFileToArr() {

        try {
            List<String> lines = Files.readAllLines(Paths.get("/Users/lucasgascoin/Desktop/DCEDL/chiffrelettres-dcedl-heloise-lucas-luciano/src/liste_francais.txt"), StandardCharsets.ISO_8859_1);
            arr = lines.toArray(new String[lines.size()]);
            return arr;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }

    public static boolean verifWord1(String p1word) {
        boolean wordValidP1 = true;
        ArrayList<String> cloneLetters = new ArrayList<>();
        for (int i = 0; i < chosenLetters.size(); i++){
            cloneLetters.add(chosenLetters.get(i));
        }

        String[] tabP1 = p1word.split("");

        for (int i = 0; i < tabP1.length; i++) {
            if (wordValidP1) {
                for (int j = 0; j < cloneLetters.size(); j++) {
                    if (Objects.equals(tabP1[i], cloneLetters.get(j))) {
                        wordValidP1 = true;
                        cloneLetters.remove(j);
                        break;
                    } else {
                        wordValidP1 = false;
                    }
                }
            } else {
                return false;
            }
        }
        List<String> listDico = Arrays.asList(arr);
        if(listDico.contains(p1word)){
            if (wordValidP1 == false) {
                System.out.println("in if n-word false P1");

                return false;
            }
            System.out.println("in if n-word true P1");

            return true;
        }
        System.out.println("or if n-word false P1");

        return false;
    }

    public static boolean verifWord2(String p2word) {
        boolean wordValidP2 = true;
        ArrayList<String> cloneLetters = new ArrayList<>();
        for (int i = 0; i < chosenLetters.size(); i++){
            cloneLetters.add(chosenLetters.get(i));
        }
        String[] tabP1 = p2word.split("");

        for (int i = 0; i < tabP1.length; i++) {
            if (wordValidP2) {
                for (int j = 0; j < cloneLetters.size(); j++) {
                    if (Objects.equals(tabP1[i], cloneLetters.get(j))) {
                        wordValidP2 = true;
                        cloneLetters.remove(j);
                        break;
                    } else {
                        wordValidP2 = false;
                    }
                }
            } else {
                return false;
            }
        }
        List<String> listDico = Arrays.asList(arr);
        if(listDico.contains(p2word)){
            if (wordValidP2 == false) {
                System.out.println("in if n-word false P2");
                return false;
            }
            System.out.println("in if n-word true P2");

            return true;
        }
        System.out.println("or if n-word false P2");

        return false;
    }

    public static void findWordInDico(String[] dico, List<String> letters){
        String lettersCombined = String.join("", letters);


        for (int i = 0; i < dico.length; i++) {
            TreeMap<Character, Integer> freq = new TreeMap<Character, Integer>();

            for (int j = 0; j < dico[i].length(); j++){
                freq.put(dico[i].charAt(j), 0);
            }

            for (int j = 0; j < lettersCombined.length(); j++) {
                if (freq.containsKey(lettersCombined.charAt(j)))
                    freq.put(lettersCombined.charAt(j), freq.get(lettersCombined.charAt(j)) + 1);

            }

            boolean match = true;

            for (int count : freq.values()) {
                if (count <= 0) {
                    match = false;
                    break;
                }
            }
            if (match)
                wordsFound.add(dico[i]);
                //System.out.println("'" + dico[i] + "' was found");
        }
    }
    public static String longestWord(List<String> tss){
        String wordRef = "";
        ArrayList<String> cloneLetters = new ArrayList<>();


        for (String mot : tss) {
            cloneLetters.clear();
            for (int i = 0; i < chosenLetters.size(); i++){
                cloneLetters.add(chosenLetters.get(i));
            }
            String[] wordSplit = mot.split("");
            for (int i = 0; i < mot.length(); i++){
                System.out.println(cloneLetters);
                for (int j = 0; j < cloneLetters.size(); j++) {
                    System.out.println(cloneLetters);
                    if (Objects.equals(wordSplit[i], cloneLetters.get(j))) {
                        System.out.println(cloneLetters);
                        cloneLetters.remove(i);
                        if (mot.length() <= 10 && mot.length() > wordRef.length()) {
                            wordRef = mot;
                        }
                    }
                }
            }
        }
        return wordRef;
    }
}
