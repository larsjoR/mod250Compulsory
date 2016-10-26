package no.hib.mod250.anthrax.client;

import no.hib.mod250.anthrax.util.AuctionServiceUtility;

import java.util.Scanner;

/**
 * Created by royne on 25.10.2016.
 */
public class AnthraxAuctionClient {
    private static AuctionServiceUtility serviceUtility;

    public static void main(String[] argv) {
        serviceUtility = new AuctionServiceUtility();
        showMenu();
    }

    public static void showMenu() {
        String menu = "" +
                "Welcome to Anthrax Marketplace SOAPY services client application\n" +
                "-----------------------------------------------------------------\n" +
                "1. Show active auctions \n" +
                "2. Place a bid \n" +
                "9. Exit \n" +
                "\n" +
                "Your choice: ";

        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (choice != 9) {
            System.out.println(menu);

            while (!sc.hasNextInt()) {
                sc.next();
            }

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n");
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
                    System.out.format("%-12s%-25s%-25s%-30s%-15s%22s\n", "Id:",  "Name:", "Category:", "Seller:", "Current bid:", "End date:          ");
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
                    serviceUtility.getProducts().forEach(System.out::println);
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------\n");

                    break;

                case 2:
                    System.out.println("You chose 2");
                    break;
                case 9:
                    System.out.println("Welcome back next time, for more exciting auctions!");
                    return;

                default:
                    break;
            }


        }


    }
}


