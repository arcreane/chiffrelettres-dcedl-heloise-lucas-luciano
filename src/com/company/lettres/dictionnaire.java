package com.company.lettres;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class dictionnaire {
    static String[] arr;
    private static final String[] consonne = {"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "z"};
    private static final String[] voyelle = {"a", "e", "i", "o", "u", "y"};
    public static ArrayList<String> chosenLetters = new ArrayList<>();
    static int count = 0;
    public static List<String> wordsFound = new ArrayList<>();

    public static ArrayList<String> getLetters(String letter,int i) {

        if (letter.equals("voyelle") || letter.equals("v") || letter.equals("V")) {
            chosenLetters.add(voyelle[rdnGen(voyelle.length)]);
            System.out.println(chosenLetters);
            count++;
        } else if (letter.equals("consonne") || letter.equals("c") || letter.equals("C")) {
            chosenLetters.add(consonne[rdnGen(consonne.length)]);
            System.out.println(chosenLetters);
            count++;
        } else if (count == i) {
            return chosenLetters;
        }
        return null;
    }
    public static void getRDMletters(){
        for (int i = 0; i < 5; i++){
            if (rdnGen(100) <= 50){
                chosenLetters.add(consonne[rdnGen(consonne.length)]);
            }
            else {
                chosenLetters.add(voyelle[rdnGen(voyelle.length)]);
            }
        }
        System.out.println("L'ordinateur à choisi 5 types de lettres au hazard.\n" +
                "Les lettres disponibles sont : " + chosenLetters);
    }

    private static int rdnGen(int z) {
        int min = 0;
        return (int) Math.floor(Math.random() * (z - min) + min);
    }

    public static String[] convertFileToArr() {

        try {
            List<String> lines = Files.readAllLines(Path.of("/Users/luciano/Desktop/coursAlgoAV/chiffrelettres-dcedl-heloise-lucas-luciano/src/liste_francais.txt"), StandardCharsets.ISO_8859_1);
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
    public static String longestWord(List<String> dicoWords){
        String wordRef = "";


        for (String mot : dicoWords){
            boolean wordIsValid = true;
            ArrayList<String> cloneLetters = new ArrayList<>();
            for (int k = 0; k < chosenLetters.size(); k++){
                cloneLetters.add(chosenLetters.get(k));
            }
            String[] wordSplit = mot.split("");
            for (int i = 0; i < mot.length(); i++) {
                if (wordIsValid) {
                    for (int j = 0; j < cloneLetters.size(); j++) {
                        if (Objects.equals(wordSplit[i], cloneLetters.get(j))) {
                            wordIsValid = true;
                            cloneLetters.remove(j);
                            break;
                        } else {
                            wordIsValid = false;
                        }
                    }
                }
            }
            if(wordIsValid && mot.length() <= 10 && mot.length() > wordRef.length()){
                wordRef = mot;
            }
        }
        return wordRef;
    }
}
