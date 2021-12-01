package com.company.chiffres;
import java.util.*;

public class getPlate {
    private static int nbPlaques = 28;
    private int[] plateOne = {1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10};
    private int[] plateTwo = {25, 25, 75, 75};
    private int[] plateThree = {75, 75, 100, 100};
    private static int[] plaques = new int[nbPlaques];



    public static int[] getPlaques(Scanner sc) {

        // Randomly select a list of indexes (can only be chosen once)
        List<Integer> chosenIdx = new ArrayList<Integer>();
        int nbChosen = 0;
        int nbTirage = 3;
        while (nbChosen < nbTirage) {
            int p = sc.nextInt(nbPlaques);
            if (!chosenIdx.contains(p)) {
                chosenIdx.add(p);
                nbChosen++;
            }
        }

        // Compute the returned list with the values
        int[] tirage = new int[nbTirage];
        for (int i = 0; i< nbTirage; i++) {
            tirage[i] = plaques[chosenIdx.get(i)];
        }
        return tirage;
    }
}
