
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author:
 */
public class BorrowArrow {

    BorrowArrow a;

    public void borrowingArrowSelection() {

        //Initialization of Borrowing Arrow
        a = new BorrowArrow();

        while (true) {
            Scanner option = new Scanner(System.in);

            System.out.println("1. Arrow Borrowing : \n");
            
            System.out.println("There are four directions(edges) of the boat: front, right, back, left. Straw men can be placed\n"
                            + "along each edge of the boat.\n\n"
                            + "Enter the number of straw men for each direction and the number\n"
                            + "of arrow waves sent by enemy.\n\n");
            
            System.out.println("2 Exit Page");
            System.out.print("\nPlease Select: ");
            String opt = option.nextLine();
            System.out.println("\n--------------------------------------------------------\n");

            switch (opt) {

                case "1":
                    System.out.println(" Borrowing Arrows with Straw Boats(Rules)\n");
                    System.out.println("->Number of straw men in one direction is always less than 100");
                    System.out.println("-> Number of arrow waves must be in decreasing order");
                    System.out.println("->Straw men for each direction cannot be used more than 3 times\n");
                    a.computeBorrowingArrow(1);
                    System.out.println("Enter to go back to \"Arrow Borrowing\" page");
                    option.nextLine();
                    break;

                //Exit interface
                case "2":
                    return;

                default:
                    System.out.println("Unrecognized selection. Please key in again.\n");
            }

        }

    }

    public void computeBorrowingArrow(int mode) {

        ArrayList<StrawMen> strawMenList = new ArrayList<>();
        ArrayList<Integer> arrowWaveList = new ArrayList<>();
        ArrayList<String> boatDirList = new ArrayList<>();
        ArrayList<Integer> arrowReceivedList = new ArrayList<>();
        int totalArrow = 0;

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Number of straw men ( less than 100 ) for each direction [-1 to exit]");
        String[] dir = {"Front", "Left", "Right", "Back"};

        for (String d : dir) {

            int input;

            while (true) {

                try {
                    System.out.print(d + ": ");
                    input = sc.nextInt();
                    sc.nextLine(); //avoid skipping nextLine() problem

                    // quit if input = -1
                    if (input == -1) {
                        return;
                    }

                    // Wrong input
                    if (input < 0 || input >= 100) {
                        System.out.println("Invalid input. Number of straw men must be [0~100)");

                        // Correct input
                    } else {
                        // new StrawMen( direction, numStrawMen, mode)
                        strawMenList.add(new StrawMen(d, input, mode));
                        break;
                    }

                } catch (InputMismatchException ex) {
                    System.out.println("Invalid input.Input must be integer.");
                    sc.nextLine(); //avoid skipping nextLine() problem
                }

            }
        }

        String inputArrow;

        while (true) {

            System.out.println("\nInput Arrows in descending order. Format\"[2000,1500,100,..]\" : ");
            
            inputArrow = sc.nextLine();

            if (!inputArrow.contains("[") || !inputArrow.contains("]")) {
                System.out.println("Invalid input. Please enter the arrow wave (integer) in the correct format \"[2000,1500,100]\"");
                continue;
            }

            try {
                String inputArrow1 = inputArrow.substring(1, inputArrow.length() - 1);
                String[] arrowArr = inputArrow1.split(",");

                if (mode == 1) {
                    // Check if the arrow wave is in descending order
                    int prevArrow = Integer.MAX_VALUE;
                    for (String arrow : arrowArr) {
                        int currentArrow = Integer.parseInt(arrow);
                        if (currentArrow > prevArrow) {
                            throw new IllegalArgumentException("Invalid input. Arrows must be in descending order.");
                        }
                        prevArrow = currentArrow;
                    }
                }

                for (String arrow : arrowArr) {
                    arrowWaveList.add(Integer.parseInt(arrow));
                }
                break;

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter arrow wave (integer) in correct format \"[2000,1500,100]\"");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        //Iterate arrowWaveList
        for (int i = 0; i < arrowWaveList.size(); i++) {

            // get direction and max no of arrow for each arrow wave
            String[] dirArrow = getMaxNumArrow(arrowWaveList.get(i), strawMenList, mode);

            // Case 1: if arrows are to less to be captured
            // Case 2: all the straw men in all direction are used up
            if (dirArrow[0] == null) {

                int countUsedUp = 0;

                //iterate all strawmen in each arrow wave to check what case is it
                for (int j = 0; j < strawMenList.size(); j++) {

                    StrawMen current = strawMenList.get(j);

                    switch (mode) {
                        case 1:
                            if (current.getCountUsed() == 3) {
                                countUsedUp++;
                            }
                            break;
                        case 2:
                            if (current.getCountUsed() == 2) {
                                countUsedUp++;
                            }
                            break;
                    }
                }

                // Case 2: all the straw men in all direction are used up
                if (countUsedUp == 4) {
                    //add Direction = "-" to array list
                    boatDirList.add("-");

                    // Case 1: arrows are too less & cant be captured
                } else {
                    //add Direction = "-" to array list
                    boatDirList.add("*-");
                }

                // add No of arrow = 0 to array list
                arrowReceivedList.add(0);

                if (mode == 2) {
                    // Display total
                    System.out.println("Total: " + totalArrow + "\n");
                }

                continue;
            }

            String boatDir = dirArrow[0];
            int arrowGet = Integer.parseInt(dirArrow[1]);

            //add best direction to array list
            boatDirList.add(boatDir);

            // add max no of arrow to array list
            arrowReceivedList.add(arrowGet);
            totalArrow += arrowGet;

            // ~[Advanced version] show total number
            if (mode == 2) {
                System.out.println("Total: " + totalArrow + "\n");
            }
        }

        //Display the result
        System.out.println("\n--------------------------------------------------------\n");
        displayResult(boatDirList, arrowReceivedList, totalArrow);
        System.out.println("");

    }

    public void displayResult(ArrayList<String> boatDirList, ArrayList<Integer> arrowReceivedList, int totalArrow) {

        System.out.println("Result:\n");
      
        System.out.println("Best boat direction: " + boatDirList.toString());
        System.out.println("Arrow received: " + arrowReceivedList.toString());
        System.out.println("Total = " + totalArrow);

    }

    //return String array including the direction and  max number of arrow received of one arrow wave
    public String[] getMaxNumArrow(int arrowWave, ArrayList<StrawMen> strawMenList, int mode) {

        //int array with 2 element {boat direction, no of arrow received}
        String[] dirArrow = new String[2];
        int max = 0;
        int maxIndex = 0; //index of straw men from straw men list that gives max arrow

        // iterate straw men in each 4 direction
        for (int i = 0; i < strawMenList.size(); i++) {

            StrawMen currentStrawMen = strawMenList.get(i);

            int numStrawMen = currentStrawMen.getNumStrawMen();
            int numArrow = arrowWave * numStrawMen / 100;

            if (numArrow > max) {
                max = numArrow;
                dirArrow[0] = currentStrawMen.getDirection();
                dirArrow[1] = Integer.toString(numArrow);
                maxIndex = i;
            }
        }

        int incCountUsed = 0;

        //increase countUsed of the chosen(max) straw men
        if (dirArrow[0] != null) { // means that "if there is strawmen capture arrows"
            StrawMen maxStrawMen = strawMenList.get(maxIndex);
            incCountUsed = maxStrawMen.getCountUsed() + 1; //increase count of usage
            maxStrawMen.setCountUsed(incCountUsed);
            //update numStrawMen as well since numStrawMen is based on countUsed
            maxStrawMen.setNumStrawMen(maxStrawMen.computeNumStrawMenLeft());
        }     

        return dirArrow;
    }

}

class StrawMen {

    private int mode;  //1 for normal version
    private int countUsed;
    private String direction;
    private int initialNumStrawMen;
    private int numStrawMen;

    public StrawMen(String direction, int initialNumStrawMen, int mode) {
        this.direction = direction;
        this.initialNumStrawMen = initialNumStrawMen;
        this.mode = mode;
        numStrawMen = computeNumStrawMenLeft();
    }

    public StrawMen() {
    }

    public int getCountUsed() {
        return countUsed;
    }

    public void setCountUsed(int countUsed) {
        this.countUsed = countUsed;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getInitialNumStrawMen() {
        return initialNumStrawMen;
    }

    public void setInitialNumStrawMen(int initialNumStrawMen) {
        this.initialNumStrawMen = initialNumStrawMen;
    }

    public int getNumStrawMen() {
        return numStrawMen;
    }

    public void setNumStrawMen(int numStrawMen) {
        this.numStrawMen = numStrawMen;
    }

    //calculate number of straw men left based on number of usage
    public int computeNumStrawMenLeft() {

        int strawMenLeft = 0;

            switch (countUsed) {
                // Used 0 time (100% efficiency)
                case 0:
                    strawMenLeft = initialNumStrawMen;
                    break;
                // Used 1 time (80% efficiency)
                case 1:
                    strawMenLeft = initialNumStrawMen * 80 / 100;
                    break;
                // Used 2 times (40% efficiency)
                case 2:
                    strawMenLeft = initialNumStrawMen * 40 / 100;
                    break;
                // Used 3 time (0% efficiency)
                case 3:
                    strawMenLeft = 0;
                    break;
            }
        

        return strawMenLeft;
    }

    @Override
    public String toString() {
        return "StrawMen{" + "countUsed=" + countUsed + ", direction=" + direction + ", initialNumStrawMen=" + initialNumStrawMen + ", numStrawMen=" + numStrawMen + '}';
    }
}
