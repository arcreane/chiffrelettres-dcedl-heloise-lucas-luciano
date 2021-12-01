package com.company.lettres;

import java.util.ArrayList;
import java.util.Objects;

public class dictionnaire {
    private static final String[] consonne = {"b","c","d","f","g","h","j","k","l","m","n","p","q","r","s","t","v","w","x","z"};
    private static final String[] voyelle = {"a","e","i","o","u","y"};
    static ArrayList<String> chosenLetters = new ArrayList<>();
    static int count = 0;

    public static ArrayList<String> getLetters (String letter){

        if (letter.equals("voyelle") || letter.equals("v")){
            chosenLetters.add(voyelle[rdnGen(voyelle.length)]);
            count++;
        }
        else if (letter.equals("consonne") || letter.equals("c")){
            chosenLetters.add(consonne[rdnGen(consonne.length)]);
            count++;
        }
        else if (count == 10){
            return chosenLetters;
        }
        return null;
    }

    private static int rdnGen(int z){
        int min = 0;
        return (int) Math.floor(Math.random()*(z-min)+min);
    }

    private static boolean verifWord1 (String p1word){
        boolean wordValidP1 = false;
        ArrayList<String> cloneLetters = chosenLetters;
        String [] tabP1 = p1word.split("");

        for (int i = 0; i < chosenLetters.size(); i++){
            for (int j = 0; j < tabP1.length; j++){
                if(Objects.equals(tabP1[j], chosenLetters.get(i))){
                    wordValidP1 = true;
                    cloneLetters.remove(i);
                break;
                }
            }
            if (wordValidP1 == true){
                return true;
            }
            else {
                wordValidP1 = false;
            }
        }
        return false;
    }

    private static boolean verifWord2 (String p2word){
        boolean wordValidP2 = false;
        String [] tabP1 = p2word.split("");

        for (int i = 0; i < chosenLetters.size(); i++){
            for (int j = 0; j < tabP1.length; j++){
                if(Objects.equals(tabP1[j], chosenLetters.get(i))){
                    wordValidP2 = true;
                    break;
                }
                else{
                    wordValidP2 = false;
                }
            }
            if (wordValidP2 == true){
                return true;
            }
        }
        return false;
    }
}
