

import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        // Initialization of objects
        WuKingdomHierarchy kingdomHierarchy = new WuKingdomHierarchy();
        SoldierArrangement generalsArrangement = new SoldierArrangement();
        BorrowArrow arrow = new BorrowArrow();
        

        System.out.println("*****Welcome to the Wu Kingdom War Strategy System!*****\n");
        Scanner select = new Scanner(System.in);
        while (true) {
          
            System.out.println(" Choose option(Main)");
           
            System.out.println(" 1 Forming Wu Kingdom’s Hierarchy");
            System.out.println(" 2 Soldier's Arrangement");
            System.out.println(" 3 Borrowing Arrows with Straw Boats");
            
            System.out.println(" -1 Exit");
           

            System.out.print("\n Please select: ");
            String selection = select.nextLine();
            System.out.println("\n--------------------------------------------------------\n");
            if (selection.equals("1")) {
                kingdomHierarchy.wkhSelection();
            } else if (selection.equals("2")) {
               generalsArrangement.soldierArrangementSelection();
            } else if (selection.equals("3")) {
                arrow.borrowingArrowSelection();
            } else if (selection.equals("-1")) {
                break;
            } else {
                System.out.println("Wrong selection. Try again.\n");
            }
        }
        System.out.println("\nExit from visiting Wu Kingdom War Strategy System\n\n");

    }

}
