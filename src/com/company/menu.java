package com.company;
import com.company.chiffres.*;
import com.company.lettres.*;
import com.sun.source.tree.Scope;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class menu {

    static int scoreP1 = 0;
    static int scoreP2 = 0;
    static int scoreSolo = 0;
    static int scoreOrdi = 0;

    static void displayMenu() {
        System.out.println("""
                1 : Démarrer le jeu
                2 : Règles
                3 : Héloïse send nudes pls
                4 : Quitter le jeu
                """);

    }

     static void chooseOption() throws IOException {
        Scanner sc = new Scanner(System.in);
        displayMenu();
         switch (sc.nextInt()) {
            case 1 -> secondChooseOption();
            case 2 -> System.out.println("ma bite");
            case 3 -> System.out.println("60% trop forte");
            case 4 -> System.exit(0);
        }
    }

    static void subMenu() {
        System.out.println("Choisissez votre mode de jeu :");
        System.out.println("1 : mode de jeu à 1 joueur (contre l'ordinateur)");
        System.out.println("2 : mode de jeu 2 joueurs/joueuses (car tu as des ami(e)s)");
    }

     static void secondChooseOption() throws IOException {
        Scanner sc = new Scanner(System.in);
        subMenu();

        switch (sc.nextInt()) {
            case 1 -> {
                sc.nextLine();
                System.out.println("Type de jeu choisi : solo");
                System.out.print("Votre nom : ");
                String playerName = sc.nextLine();
                for (int i = 0; i < 5; i++){
                    System.out.print("Quel type de lettre voulez vous? (consonne[c]/voyelle[v]) : ");
                    String answer = sc.nextLine();
                    dictionnaire.getLetters(answer, i);
                }
                dictionnaire.getRDMletters();
                dictionnaire.convertFileToArr();
                System.out.print("Insérez le mot que vous avez trouvé : ");
                String answerP = sc.nextLine();
                dictionnaire.findWordInDico(dictionnaire.convertFileToArr(), dictionnaire.chosenLetters);
                System.out.println("L'ordinateur à choisi le mot " + dictionnaire.longestWord(dictionnaire.wordsFound) + ".");
                String longestWord = dictionnaire.longestWord(dictionnaire.wordsFound);
                if(dictionnaire.verifWord1(answerP)){
                    if (answerP.length() == longestWord.length()){
                        System.out.println("Les mots ont la même taille, aucun joueur ne gagne de points.");
                    }
                    else if (answerP.length() < longestWord.length()){
                        scoreOrdi += longestWord.length();
                        System.out.println("Le mot de l'ordinateur est plus long il gagne " + longestWord.length() + " points. Ses points sont désormais de : " + scoreOrdi + ".");
                    }
                    else {
                        scoreSolo += answerP.length();
                        System.out.println("Votre mot est plus long vous gagnez " + answerP.length() + " points. Vos points sont désormais de : " + scoreSolo + ".");
                    }
                }
                else {
                    scoreOrdi += longestWord.length();
                    System.out.println("Votre mot n'est pas valide, l'ordinateur gagne " + longestWord.length() + " points.");
                }
                chooseOption();

            }
            case 2 -> {
                sc.nextLine();
                System.out.println("Type de jeu choisi : duo");
                System.out.print("Nom joueur 1 : ");
                String player1Name = sc.nextLine();

                System.out.print("Nom joueur 2 : ");
                String player2Name = sc.nextLine();
                boolean turn = true;
                for (int i = 0; i < 10; i++){
                    if (turn){
                        System.out.print("Joueur " + player1Name + " quel type de lettre voulez vous? (consonne[c]/voyelle[v]) : ");
                        String answer = sc.nextLine();
                        dictionnaire.getLetters(answer, i);
                        turn = false;
                    }
                    else{
                        System.out.print("Joueur " + player2Name + " quel type de lettre voulez vous? (consonne[c]/voyelle[v]) : ");
                        String answer = sc.nextLine();
                        dictionnaire.getLetters(answer, i);
                        turn = true;
                    }
                }
                dictionnaire.convertFileToArr();
                System.out.print("Joueur " + player1Name + " insérez le mot que vous avez trouvé : ");
                String answerP1 = sc.nextLine();
                System.out.print("Joueur " + player2Name + " insérez le mot que vous avez trouvé : ");
                String answerP2 = sc.nextLine();

                dictionnaire.findWordInDico(dictionnaire.convertFileToArr(), dictionnaire.chosenLetters);
                if (dictionnaire.verifWord1(answerP1) && dictionnaire.verifWord2(answerP2)){
                    if (answerP1.length() > answerP2.length()){
                        scoreP1 += answerP1.length();
                        System.out.println("Le joueur " + player1Name + " gagne la manche, il gagne " + answerP1.length() + " points. Ses points sont désormais de : " + scoreP1 + ".");
                    }
                    else if (answerP1.length() == answerP2.length()){
                        System.out.println("Les mots ont la même taille, aucun joueur ne gagne de points.");
                    }
                    else {
                        scoreP2 += answerP2.length();
                        System.out.println("Le joueur " + player2Name + " gagne la manche, il gagne " + answerP2.length() + " points. Ses points sont désormais de : " + scoreP2 + ".");
                    }
                    System.out.println("Le mot le plus long trouvé dans le dictionnaire est : " + dictionnaire.longestWord(dictionnaire.wordsFound) + ".");
                }
                else if(dictionnaire.verifWord1(answerP1) && !dictionnaire.verifWord2(answerP2)){
                    scoreP1 += answerP1.length();
                    System.out.println("Le joueur " + player1Name + " gagne la manche, ses points sont désormais de : " + scoreP1 +".");
                    System.out.println("Le mot le plus long trouvé dans le dictionnaire est : " + dictionnaire.longestWord(dictionnaire.wordsFound) + ".");
                }
                else if(!dictionnaire.verifWord1(answerP1) && dictionnaire.verifWord2(answerP2)){
                    scoreP2 += answerP2.length();
                    System.out.println("Le joueur " + player2Name + " gagne la manche, ses points sont désormais de : " + scoreP2 + ".");
                    System.out.println("Le mot le plus long trouvé dans le dictionnaire est : " + dictionnaire.longestWord(dictionnaire.wordsFound) + ".");
                }
                else {
                    System.out.println("Les deux mots ne sont pas valides, aucun des joueurs ne marque de points.");
                    System.out.println("Le mot le plus long trouvé dans le dictionnaire est : " + dictionnaire.longestWord(dictionnaire.wordsFound) + ".");
                }
                chooseOption();
            }
        }
    }
    private static void displayTitle() {
        System.out.println("  +-------------------------+");
        System.out.println("  |   Le Compte est bon !   |");
        System.out.println("  +-------------------------+");
    }
//    static String getPlayerName(){
//        Scanner sc = new Scanner(System.in);
//        return sc.nextLine();
//    }
}
