package com.company;
import com.company.chiffres.*;
import com.company.lettres.*;

import java.util.Arrays;
import java.util.Scanner;

public class menu {

    static void displayMenu() {
        System.out.println("""
                1 : Démarrer le jeu
                2 : Règles
                3 : Héloïse send nudes pls
                4 : Quitter le jeu
                """);

    }

     static void chooseOption() {
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

     static void secondChooseOption() {
        Scanner sc = new Scanner(System.in);
        subMenu();

        switch (sc.nextInt()) {
            case 1 -> {
                sc.nextLine();
                System.out.println("Type de jeu choisi : solo");
                System.out.print("Votre nom : ");
                String playerName = sc.nextLine();
                for (int i = 0; i < 10; i++){

                }
            }
            case 2 -> {
                sc.nextLine();
                System.out.println("Type de jeu choisi : duo");
                System.out.print("Nom joueur 1 : ");
                String player1Name = sc.nextLine();

                System.out.print("Nom joueur 2 : ");
                String player2name = sc.nextLine();
                boolean turn = true;
                for (int i = 0; i < 10; i++){
                    if (turn){
                        System.out.print("Joueur " + player1Name + " quelle lettre voulez vous? ");
                        String answer = sc.nextLine();
                        dictionnaire.getLetters(answer);
                        turn = false;
                    }
                    else{
                        System.out.print("Joueur " + player2name + " quelle lettre voulez vous? ");
                        String answer = sc.nextLine();
                        dictionnaire.getLetters(answer);
                        turn = true;
                    }
                }
                System.out.print("Jouisseur " + player1Name + " insérez le mot que vous avez trouvé : ");
                String answerP1 = sc.nextLine();
                System.out.print("Jouisseur " + player1Name + " insérez le mot que vous avez trouvé : ");
                String answerP2 = sc.nextLine();
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
