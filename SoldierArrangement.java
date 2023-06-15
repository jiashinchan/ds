
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SoldierArrangement {
    
    private ArrayList<TreeNode> leadership;
    private ArrayList<TreeNode> strength;
    private ArrayList<TreeNode> intelligence;
    private ArrayList<TreeNode> political;
    private ArrayList<TreeNode> hitPoint;
    private SortingGenerals sort;
    
    private void init() {
        sort = new SortingGenerals();
        leadership = sort.sortLeadershipList();
        strength = sort.sortStrengthList();
        intelligence = sort.sortIntelligenceList();
        political = sort.sortPoliticList();
        hitPoint = sort.sortHitPointList();
    }
    
    public void soldierArrangementSelection() {
        init();
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Choose Soldier's arrangement based on :");
            System.out.println("1 Leadership");
            System.out.println("2 Strength");
            System.out.println("3 Intelligence");
            System.out.println("4 Political Skill");
            System.out.println("5 Hit Point");
            System.out.println("6 Specific ability");
            System.out.println("-1 Exit Page");
            System.out.print("\nPlease Select: ");
            String reply = input.nextLine();
            System.out.println("\n--------------------------------------------------------\n");
            
            // input validation
            if (reply.matches("\\d") ){
                int options = Integer.parseInt(reply);
                switch(options) {
                    case 1 : sort.printSortingList(leadership, options); break;
                    case 2 : sort.printSortingList(strength, options); break;
                    case 3 : sort.printSortingList(intelligence, options); break;
                    case 4 : sort.printSortingList(political, options); break;
                    case 5 : sort.printSortingList(hitPoint, options); break;
                    case 6 : getGeneralWithSpecialAbility(); break;
                    default : System.out.println("Invalid input : " + reply + ", allowed inputs : [1,2,3,4,5,6,-1]");
                }
                System.out.println("\n--------------------------------------------------------\n");
            } else if (reply.equals("-1")){
                return;
            } else {
                System.out.println("Invalid input : " + reply + ", allowed inputs : [1,2,3,4,5,6,-1]");
                System.out.println("\n--------------------------------------------------------\n");
            }
        }
    }
    
    private void getGeneralWithSpecialAbility() {
        while(true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Choose an ability,");
            System.out.println("1 Leadership");
            System.out.println("2 Strength");
            System.out.println("3 Intelligence");
            System.out.println("4 Political Skill");
            System.out.println("5 Hit Point");
            System.out.println("-1 Exit Page");
            System.out.print("\nPlease Select: ");
            String reply = input.nextLine();
            if (reply.equals("-1")) return;
            System.out.print("Enter the ability score : ");
            String scoreStr = input.nextLine();
            System.out.println("\n--------------------------------------------------------\n");
            
            if (reply.matches("\\d") && scoreStr.matches("\\d+") && scoreStr.length() <= 2){
                int index = -1;
                int score = Integer.parseInt(scoreStr);
                int options = Integer.parseInt(reply);
                String ability = "";
                ArrayList<TreeNode> list = null;
                switch(options) {
                    case 1 : {
                        ability = "Leadership";
                        list = leadership;
                        index = binarySearch(leadership, options, score);
                    } break;
                    case 2 : {
                        ability = "Strength";
                        list = strength;
                        index = binarySearch(strength, options, score);
                    } break;
                    case 3 : {
                        ability = "Intelligence";
                        list = intelligence;
                        index = binarySearch(intelligence, options, score);
                    } break;
                    case 4 : {
                        ability = "Politic";
                        list = political;
                        index = binarySearch(political, options, score);
                    } break;
                    case 5 : {
                        ability = "Hit Point";
                        list = hitPoint;
                        index = binarySearch(hitPoint, options, score);
                    } break;
                    default : System.out.println("Invalid input : " + reply + ", allowed inputs : [1,2,3,4,5,-1]");
                    System.out.println("\n--------------------------------------------------------\n");
                }
                if (index != -1) {
                    System.out.println("General " + ability(list, 6, index) + 
                            " is the soldier you are looking for. [ " +
                            ability + " => " + ability(list, options, index) + " ]");
                    System.out.println("\n--------------------------------------------------------\n");
                    formGeneralTeam(list, index, options, ability);
                } else {
                    System.out.println("No General available");
                    System.out.println("\n--------------------------------------------------------\n");
                }
            } else if (!reply.matches("\\d")){
                System.out.println("Invalid input : " + reply + ", allowed inputs : [1,2,3,4,5,-1]");
                System.out.println("\n--------------------------------------------------------\n");
            } else {
                System.out.println("Invalid input : " + scoreStr + ", allowed inputs : [0 to 99]");
                System.out.println("\n--------------------------------------------------------\n");
            }
        }
    }
    
    private void formGeneralTeam(ArrayList<TreeNode> list, int index, int option, String info) {
        while(true) {
            System.out.println("Do you want to form a random team for this general ?");
            Scanner input = new Scanner(System.in);
            System.out.println("1 yes");
            System.out.println("2 no");
            System.out.print("\nPlease Select: ");
            String reply = input.nextLine();
            System.out.println("\n--------------------------------------------------------\n");
            
            if (reply.matches("\\d")){
                int options = Integer.parseInt(reply);
                if (options == 1) {
                    boolean run = true;
                        while (run) {
                            Random randomIndex = new Random();
                            int index1 = randomIndex.nextInt(10);
                            int index2 = randomIndex.nextInt(10);
                            int getName = 6;
                            int sumOfAbility = 0;
                            if (index != index1 && index != index2 && index1 != index2) {
                                
                                String format = "\n1. %5s\n  ~  %s => %d\n\n2. %5s\n  ~  %s => %d\n\n3. %5s\n  ~  %s => %d" ;
                                System.out.printf("Team of " + info + " : " + format, 
                                                ability(list, getName, index), info , ability(list, option, index),
                                                ability(list, getName, index1), info , ability(list, option, index1),
                                                ability(list, getName, index2), info , ability(list, option, index2));
                                sumOfAbility = (Integer) ability(list, option, index) + (Integer) ability(list, option, index1) + (Integer) ability(list, option, index2);
                                
                                run = false;
                                if (sumOfAbility >= 250) System.out.println("\n\n\n# S level : " + sumOfAbility);
                                else if (sumOfAbility >= 220 && sumOfAbility < 250) System.out.println("\n\n\n# A level : " + sumOfAbility);
                                else if (sumOfAbility >= 190 && sumOfAbility < 220) System.out.println("\n\n\n# B level : " + sumOfAbility);
                                else if (sumOfAbility < 190) System.out.println("\n\n\n# C level : " + sumOfAbility);
                            }
                        }
                } else if (options == 2) {
                    System.out.println("\n--------------------------------------------------------\n");
                    return;
                } else {
                    System.out.println("Invalid input : " + reply + ", allowed inputs : [1,2,-1]");
                }
                System.out.println("\n--------------------------------------------------------\n");
            } else if (!reply.matches("\\d")){
                System.out.println("Invalid input : " + reply + ", allowed inputs : [1,2,-1]");
                System.out.println("\n--------------------------------------------------------\n");
            } 
        }
    }
    
    // Binary Search
    private int binarySearch(ArrayList<TreeNode> list, int ability, int key) { 
        int low = 0;
        int high = list.size() - 1;
        while (high >= low) {
            int mid = (low + high) / 2;
            if (key > (Integer) ability(list, ability, mid)) {
                high = mid - 1;
            } else if (key == (Integer) ability(list, ability, mid)) {
                return mid;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
    
    // To get the specific ability score and name
    private Object ability(ArrayList<TreeNode> list, int ability, int index) {
        Object returnObject;
        switch(ability) {
            case 1 : returnObject = list.get(index).getLeadership(); break;
            case 2 : returnObject = list.get(index).getStrength(); break;
            case 3 : returnObject = list.get(index).getIntelligence(); break;
            case 4 : returnObject = list.get(index).getPolitic(); break;
            case 5 : returnObject = list.get(index).getHitPoint(); break;
            case 6 : returnObject = list.get(index).getName(); break;
            default : returnObject = null;
        }
        return returnObject;
    }
    
}
