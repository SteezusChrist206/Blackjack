package Blackjack;


import java.util.Scanner;


public class Main {
    static void main() throws InterruptedException {

        boolean rundeleauft = true;

        System.out.println("Willkommen!!!");
        System.out.println("Dein Startkapital ist: " + Blackjack.kontostand);

        while(rundeleauft){
            Blackjack.runde();
            Scanner input = new Scanner(System.in);
            System.out.println("Möchtest du noch eine Runde spielen? (j/n)");
            String keepgoing = input.next();
            if(keepgoing.equals("n")){
                rundeleauft = false;
            }
            if (Blackjack.kontostand<=0){
                System.out.println("Du hast kein Geld mehr!!!");
                rundeleauft = false;
            }

        }
    }
}
