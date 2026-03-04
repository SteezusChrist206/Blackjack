package Blackjack;


import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main {
    static void main() throws InterruptedException {

        boolean rundeläuft = true;

        System.out.println("Willkommen!!!");
        System.out.println("Dein Startkapital ist: " + Blackjack.kontostand);

        while(rundeläuft){
            Blackjack.runde();
            Scanner input = new Scanner(System.in);
            System.out.println("Möchtest du noch eine Runde spielen? (j/n)");
            String keepgoing = input.next();
            if(keepgoing.equals("n")){
                rundeläuft = false;
            }
            if (Blackjack.kontostand<=0){
                System.out.println("Du hast kein Geld mehr!!!");
                rundeläuft = false;
            }

        }





    }
}
