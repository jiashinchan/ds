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
        WuKingdomHierarchy kingdom = new WuKingdomHierarchy();
        SoldierArrangement soldiers = new SoldierArrangement();
        BorrowingArrow arrow = new BorrowingArrow();
        EnemyFortress attack = new EnemyFortress();
        FoodHarvesting food = new FoodHarvesting();
        EncryptedText secret = new EncryptedText();
        RedCliffonFire fire = new RedCliffonFire();
        EngagingCaoCao EngCC = new EngagingCaoCao();
        
        System.out.println("Welcome to the world of Three Kingdoms: The Battle of Red Cliff");
        System.out.println("You're Sun Quan. Let's see the Wu Kingdom Strategy System");
        Scanner choice = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Wu Kingdom's Hierarchy");
            System.out.println("2. Soldier's Arrangement");
            System.out.println("3. Borrowing Arrows with Straw Boats");
            System.out.println("4. Enemy Fortress Attack Simulation");
            System.out.println("5. Food Harvesting");
            System.out.println("6. Encrypted Text");
            System.out.println("7. Red Cliff on Fire");
            System.out.println("8. Engaging Cao Cao at Hua Rong Road");
            System.out.println("-1 Exit ");
            System.out.print("\nPlease select: ");
            String selection = choice.nextLine();
            System.out.println("\n--------------------------------------------------------\n");
             if (selection.equals("1")) {
                kingdom.kingdomHierarchySelection();
            } else if (selection.equals("2")) {
                soldiers.soldierArrangementSelection();
            } else if (selection.equals("3")) {
                arrow.borrowingArrowSelection();
            } else if (selection.equals("4")) {
                attack.enemyFortressSelection();
            } else if (selection.equals("5")) {
                food.foodHarvestingSelection(); 
            } else if (selection.equals("6")) {
                secret.EncryptedTextSelection();
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
        System.out.println("Thank you for visiting Wu Kingdom Strategy System\n");

    }


}

