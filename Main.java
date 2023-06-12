/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mydsass;

import java.util.Scanner;

/**
 *
 * @author jiash
 */
public class Main {
    public static void main(String[] args){
        
        // Initialization of class
        Scanner sc = new Scanner(System.in);
        int selection;
                do {
            System.out.println("Welcome to the world of Three Kingdoms: The Battle of Red Cliff");
            System.out.println("You're Sun Quan. Let's see the Wu Kingdom Strategy System");
            System.out.println("");
            System.out.println("1. Basic Features ");
            System.out.println("2. Extra Features ");
            System.out.println("-1. Exit the System");
            System.out.print("\nPlease select: ");
            selection = sc.nextInt();
            switch (selection) {
                case 1 -> basicFeatures();
                case 2 -> extraFeatures();
                case -1 -> System.out.println("""
                                              Thank you for visiting Wu Kingdom Strategy System.
                                              We hope to see you again.""");
                default -> System.out.println("Invalid input. Please try again.\n");
            }
        } while (selection != -1);
    }
        
    public static void basicFeatures(){
        WuKingdomHierarchy kingdom = new WuKingdomHierarchy();
        SoldierArrangement soldiers = new SoldierArrangement();
        BorrowingArrow arrow = new BorrowingArrow();
        EncryptedText text = new EncryptedText();
        RedCliffonFire fire = new RedCliffonFire();
        EngagingCaoCao EngCC = new EngagingCaoCao();
        
        Scanner choice1 = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Wu Kingdom's Hierarchy");
            System.out.println("2. Soldier's Arrangement");
            System.out.println("3. Borrowing Arrows with Straw Boats");
            System.out.println("4. Enemy Fortress Attack Simulation");
            System.out.println("5. Food Harvesting");
            System.out.println("6. Encrypted Text");
            System.out.println("7. Red Cliff on Fire");
            System.out.println("8. Engaging Cao Cao at Hua Rong Road");
            System.out.println("-1 Exit the Basic Features");
            System.out.print("\nPlease select: ");
            String selection = choice1.nextLine();
            System.out.println("\n----------------------------------------------------------------------------------------\n");
            
            if (selection.equals("1")) {
                kingdom.kingdomHierarchySelection();
            } else if (selection.equals("2")) {
                soldiers.soldierArrangementSelection();
            } else if (selection.equals("3")) {
                arrow.borrowingArrowSelection();
            } else if (selection.equals("4")) {
                EnemyFortress.main(new String[]{});
            } else if (selection.equals("5")) {
                FoodHarvesting.main(new String[]{});
            } else if (selection.equals("6")) {
                text.EncryptedTextSelection();
            } else if (selection.equals("7")) {
                fire.redCliffonFireSelection();
            } else if (selection.equals("8")) {
                EngCC.EngagingCaoCaoSelection();
            } else if (selection.equals("-1")) {
                break;
            } else {
                System.out.println("Invalid input. Please try again.\n");
            }
        }  
    }
    
    public static void extraFeatures(){
        SecuredTextEncryption securetext = new SecuredTextEncryption();
            
        Scanner choice2 = new Scanner(System.in);
            
        while (true){
            System.out.println("\n1. Text Converter with More Secured Encryption");
            System.out.println("2. Enemy Fortress Attack Stimulation 1");
            System.out.println("-1 Exit the Extra Features");
            System.out.print("\nPlease select: ");
            String selection = choice2.nextLine();
            System.out.println("\n----------------------------------------------------------------------------------------\n");
            
            if (selection.equals("1")) {
                        securetext.SecureTextEncryptionSelection();
            } else if (selection.equals("-1")) {
                        break;
            } else{
                System.out.println("Invalid input. Please try again.\n");   
            }
        }
    }
}
