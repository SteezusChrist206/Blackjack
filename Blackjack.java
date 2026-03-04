package Blackjack;


import java.util.Random;
import java.util.Scanner;

import java.util.*;
import static java.lang.Thread.sleep;

public class Blackjack {

    public static int kontostand = 500;

    public static void runde() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bestimme deinen Einsatz und starte eine Runde: ");
        int Einsatz = scanner.nextInt();
        boolean gueltigerEinsatz = false;
        while(!gueltigerEinsatz) {
            if (Einsatz > kontostand || Einsatz < 0) {
                System.out.println("Ungültiger Einsatz ");
                System.out.println("Bestimme deinen Einsatz und starte eine Runde: ");
                Einsatz = scanner.nextInt();
            } else {
                gueltigerEinsatz = true;
            }
        }
        System.out.println("Karten werden gedealt... bitte warten");
        sleep(3000);

        ArrayList<Integer> spielerkarten = new ArrayList<>();
        ArrayList<Integer> dealerkarten = new ArrayList<>();
        spielerkarten.add(randomkarte());
        spielerkarten.add(randomkarte());
        dealerkarten.add(randomkarte());
        dealerkarten.add(randomkarte());

        int spielerkartentotal = spielerkarten.get(0) + spielerkarten.get(1);
        int dealerkartentotal = dealerkarten.get(0) + dealerkarten.get(1);

        while (spielerkarten.get(0) + spielerkarten.get(1) == 22 || dealerkarten.get(0) + dealerkarten.get(1) ==22){
            if (spielerkartentotal==22) {
                spielerkarten.set(0, randomkarte());
                spielerkarten.set(1, randomkarte());
            }
            if (dealerkartentotal ==22){
                dealerkarten.set(0,randomkarte());
                dealerkarten.set(0,randomkarte());
            }

        }

        System.out.println("Deine ersten zwei Karten sind: " + spielerkarten.get(0) + ", " + spielerkarten.get(1));
        System.out.println("Dealers erste Karten sind: " + dealerkarten.get(0) + ", " + dealerkarten.get(1));

        if (spielerkartentotal == 21 && dealerkartentotal != 21){
            System.out.println("Du hast einen Blackjack. Glückwunsch!");
            kontostand += Einsatz;
            System.out.println("Dein Kontostand ist jetzt " + kontostand);
        }
        else if (dealerkartentotal == 21 && spielerkartentotal != 21) {
            System.out.println("Dealer hat einen Blackjack. Schade, du hast verloren!");
            kontostand -= Einsatz;
            System.out.println("Dein Kontostand ist jetzt: " + kontostand);
        }
        else {
            String weiter;
            for (int round = 2; round < 100; round++) {
                System.out.println("möchtest du noch eine Karte? (j/n)");
                weiter = scanner.next();
                if (weiter.equals("j")){
                    spielerkarten.add(randomkarte());
                    System.out.println("deine Karte ist: " + spielerkarten.get(round));
                    spielerkartentotal += spielerkarten.get(round);

                    if (dealerkartentotal<17) {
                        dealerkarten.add(randomkarte());
                        System.out.println("Dealer Karte ist: " + dealerkarten.get(round));
                        dealerkartentotal += dealerkarten.get(round);
                    }
                    if (spielerkartentotal==21){
                        System.out.println("Blackjack, du gewinnst!!!");
                        kontostand += Einsatz;
                        System.out.println("Dein Kontostand beträgt: " + kontostand);
                        break;
                    }
                    else if(dealerkartentotal==21){
                        System.out.println("Dealer hat Blackjack. Du verlierst!!!");
                        kontostand -= Einsatz;
                        System.out.println("Dein Kontostand ist: " + kontostand);
                        break;
                    }
                    else if (spielerkartentotal>21) {
                        System.out.println("Du hast 21 überschritten. Du verlierst!!!");
                        kontostand-= Einsatz;
                        System.out.println("Dein Kontostand beträgt: " + kontostand);
                        break;
                    } if (dealerkartentotal>21){
                        System.out.println("Du gewinnst!!!");
                        kontostand += Einsatz;
                        System.out.println("Dein Kontostand beträgt: " + kontostand);
                        break;
                    }

                }
                else {
                    if (dealerkartentotal<17){
                        for (int i = dealerkartentotal; i<17;) {
                            dealerkarten.add(randomkarte());
                            System.out.println("Dealer Karte ist: " + dealerkarten.get(round));
                            dealerkartentotal += dealerkarten.get(round);
                            i += dealerkarten.get(round);
                            if (dealerkartentotal>21){
                                System.out.println("Du gewinnst!!!");
                                kontostand+= Einsatz;
                                System.out.println("Dein Kontostand beträgt: " + kontostand);
                            }
                        }
                    }
                    System.out.println("Deine Endsumme ist: " + spielerkartentotal);
                    System.out.println("Dealers Endsumme ist: " + dealerkartentotal);
                    if (spielerkartentotal>dealerkartentotal) {
                        System.out.println("Du gewinnst!!");
                        kontostand += Einsatz;
                        System.out.println("Dein Kontostand beträgt: " + kontostand);
                        break;
                    }
                    else if (spielerkartentotal==dealerkartentotal){
                        System.out.println("Gleichstand, versuch es nochmal. Du erhälst dein Einsatz zurück");
                        System.out.println("Dein Kontostand begrägt: " + kontostand);
                        break;
                    } else{
                        System.out.println("Du verlierst!!!");
                        kontostand -= Einsatz;
                        System.out.println("Dein Kontostand beträgt: " + kontostand);
                        break;
                    }
                }
            }
        }
    }

    public static int randomkarte(){
        Random rand = new Random();
        int[][] kartendeck = {{2,3,4,5,6,7,8,9,10,10,10,10,11},
                {2,3,4,5,6,7,8,9,10,10,10,10,11},
                {2,3,4,5,6,7,8,9,10,10,10,10,11},
                {2,3,4,5,6,7,8,9,10,10,10,10,11}};
        int randomrow = rand.nextInt(kartendeck.length);
        int randomcol = rand.nextInt(kartendeck[randomrow].length);
        return kartendeck[randomrow][randomcol];
    }
}
