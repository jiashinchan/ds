

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author: 
 */
public class WuKingdomHierarchy {

    WuKingdomHierarchy a;
    TreeNode root;
    String FILENAME = "Soldiers.txt";

    //Main panel must implement this method to access all the methods in this class
    public void wkhSelection() {

        //Initialization of WHK
        a = new WuKingdomHierarchy();
        root = getHierarchy_Tree();

        while (true) {
            Scanner option = new Scanner(System.in);
            System.out.println(" Wu Kingdom's Hierarchy \n");
            System.out.println("1 Display Tree");
            System.out.println("2 Display Member's Ability Table");
            System.out.println("3 Add General");
            System.out.println("-1 Exit Page");
            System.out.print("\nPlease Select: ");
            String opt = option.nextLine();
            System.out.println("\n--------------------------------------------------------\n");

            switch (opt) {
                //display 2D tree
                case "1":
                    System.out.println(" Wu Kingdom Hierarchy \n");
                    a.printHierarchy_Tree(root, "", true);
                    System.out.println("Press Enter to go back to \"Wu Kingdom Hierarchy\" page");
                    option.nextLine();
                    break;

                //display Member Info
                case "2":
                    displayCharacterBio(root);
                    System.out.println("Press Enter to go back to \"Wu Kingdom Hierarchy\" page");
                    option.nextLine();
                    break;

                //Add General
                case "3":
                    a.addGeneral(root);
                    System.out.println("Press Enter to go back to \"Wu Kingdom Hierarchy\" page");
                    option.nextLine();
                    break;

                //Exit interface
                case "-1":
                    return;

                default:
                    System.out.println("Wrong selection. Try again.\n");
            }

        }
    }

    public TreeNode getHierarchy_Tree() {
        ArrayList<TreeNode> generals = new ArrayList<>();
        TreeNode sunQuan = new TreeNode("", "", "", 0, 0, 0, 0, 0);
        TreeNode chiefOfManagement = new TreeNode("", "", "", 0, 0, 0, 0, 0);
        TreeNode chiefOfMilitary = new TreeNode("", "", "", 0, 0, 0, 0, 0);

        try {
            Scanner read = new Scanner(new FileInputStream(FILENAME));
            String name = "", position = "", armyType = "";
            int strength = 0, leadership = 0, intelligence = 0, politic = 0, hitPoint = 0;
            boolean isGeneral = true;

            while (read.hasNextLine()) {
                String line = read.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                if (!line.isEmpty()) {
                    String[] parts = line.split(": ");
                    String key = parts[0];
                    String value = parts[1];

                    switch (key) {
                        case "Name":
                            name = value;
                            if (value.equals("Sun Quan") || value.equals("Zhang Zhao") || value.equals("Zhou Yu")) {
                                isGeneral = false;
                            } else {
                                isGeneral = true;
                            }
                            break;
                        case "Position":
                            position = value;
                            break;
                        case "Army Type":
                            armyType = value;
                            break;
                        case "Strength":
                            strength = Integer.parseInt(value);
                            break;
                        case "Leadership":
                            leadership = Integer.parseInt(value);
                            break;
                        case "Intelligence":
                            intelligence = Integer.parseInt(value);
                            break;
                        case "Politic":
                            politic = Integer.parseInt(value);
                            break;
                        case "Hit Point":
                            hitPoint = Integer.parseInt(value);

                            //After reaching the last attribute"Hit Point" of the character, store the character into tree node
                            if (isGeneral) {
                                TreeNode general = new TreeNode(name, position, armyType, strength, leadership, intelligence, politic, hitPoint);
                                generals.add(general);
                            } else {
                                switch (name) {
                                    case "Sun Quan":
                                        sunQuan = new TreeNode(name, position, armyType, strength, leadership, intelligence, politic, hitPoint);
                                        break;
                                    case "Zhang Zhao":
                                        chiefOfManagement = new TreeNode(name, position, armyType, strength, leadership, intelligence, politic, hitPoint);
                                        break;
                                    case "Zhou Yu":
                                        chiefOfMilitary = new TreeNode(name, position, armyType, strength, leadership, intelligence, politic, hitPoint);
                                        break;
                                    default:
                                        break;
                                }
                            }
                            break;
                        default:
                            System.out.println("Invalid key");
                            break;
                    }
                }
            }

            read.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        sunQuan.addChild(chiefOfManagement);
        sunQuan.addChild(chiefOfMilitary);

        for (TreeNode general : generals) {
            if (general.getIntelligence() > general.getStrength()) {
                chiefOfManagement.addChild(general);
            } else {
                chiefOfMilitary.addChild(general);
            }
        }

        return sunQuan;
    }


    //Add general
    public void addGeneral(TreeNode root) {

        Scanner sc = new Scanner(System.in);
        String[] temp;
        String newGeneral="";
        TreeNode newGeneralNode;

        while (true) {
            try {
                //Enter the new general you want to add
                System.out.println("Please enter the new general's details in this format: ");
                System.out.println("[name,position,army type,strength,leadership,intelligence,politic,hit point] without bracket or -1 to exit: ");
                newGeneral = sc.nextLine();

                //to exit interface
                if (newGeneral.equalsIgnoreCase("-1")) {
                    return;
                }

                temp = newGeneral.split(",");

                if (temp.length != 8) {
                    throw new IllegalArgumentException("Wrong input, input must be 8 elements\n");
                }

                newGeneralNode = new TreeNode(temp);
                break;

            } catch (IndexOutOfBoundsException ex) {
                System.out.println("Wrong input format\n");
            } catch (NumberFormatException ex) {
                System.out.println("Wrong input format\n");
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
                if(newGeneral.isEmpty()) {
                    sc.nextLine();
                }
            }
        }

        //Assign to the respective division
        int strength = Integer.parseInt(temp[3]);
        int intelligence = Integer.parseInt(temp[5]);

        //if strength>=intelligence Enter military
        if (strength >= intelligence) {
            root.getChild(1).addChild(newGeneralNode);
        } //if strength < intelligence Enter Management
        else{
            root.getChild(0).addChild(newGeneralNode);
        }

        //Write the txt file
        try {
            //Open file in append mode
            FileWriter writer = new FileWriter(FILENAME, true);

            //Write the bio of the new general in the file
            writer.write("\n\nName: " + temp[0] + "\n");
            writer.write("Position: " + temp[1] + "\n");
            writer.write("Army Type: " + temp[2] + "\n");
            writer.write("Strength: " + temp[3] + "\n");
            writer.write("Leadership: " + temp[4] + "\n");
            writer.write("Intelligence: " + temp[5] + "\n");
            writer.write("Politic: " + temp[6] + "\n");
            writer.write("Hit Point: " + temp[7]);

            writer.close();
        } catch (IOException e) {
            System.out.println("Error");
        }

    }

    // print tree
    public void printHierarchy_Tree(TreeNode root, String prefix, boolean isTail) {

        System.out.println(prefix + (isTail ? "└── " : "├── ") + root.getPosition() + "(" + root.getName() + "[" + root.getArmyType() + "])\n");
        ArrayList<TreeNode> children = root.getChildrenList();

        //for loop will be skipped, if the node doesn't have children
        //iterate and print until the last two children
        for (int i = 0; i < children.size() - 1; i++) {
            //isTail in ternary operator is "isTail" of previous node
            printHierarchy_Tree(children.get(i), prefix + (isTail ? "    " : "│   "), false);
        }

        // if statement will be skipped, if the node doesn't have children
        // print last children of a node
        if (children.size() > 0) {
            //isTail in ternary operator is "isTail" of previous node
            printHierarchy_Tree(children.get(children.size() - 1), prefix + (isTail ? "    " : "│   "), true);
        }

    }

    //to display bio of the character
    public static void displayBio(TreeNode i) {
        //get the specific character
        TreeNode character = i;

        System.out.println("--------------------------------------------------------\n");
        System.out.println("Name: " + character.getName());
        System.out.println("Position: " + character.getPosition());
        System.out.println("Army Type: " + character.getArmyType());
        System.out.println("\nAbility Table:");
        System.out.println("Strength: " + character.getStrength());
        System.out.println("Leadership: " + character.getLeadership());
        System.out.println("Intelligence: " + character.getIntelligence());
        System.out.println("Politic: " + character.getPolitic());
        System.out.println("Hit Point: " + character.getHitPoint());
        System.out.println("\n--------------------------------------------------------\n");

    }

    public void displayCharacterBio(TreeNode root) {

        boolean isFound = false;
        Scanner sc = new Scanner(System.in);

        while (!isFound) {

            //always reset isFound = false
            isFound = false;

            System.out.print("Enter Member Name [-1 to exit]: ");
            String name = sc.nextLine();

            if(name.equals("-1"))
                break;

            //if you choose the emperor
            if (name.equalsIgnoreCase("Sun Quan")) {
                displayBio(root);
                isFound = true;
            } //if you choose the chiefs
            else if (name.equalsIgnoreCase("Zhang Zhao")) {
                displayBio(root.getChild(0));
                isFound = true;
            } else if (name.equalsIgnoreCase("Zhou Yu")) {
                displayBio(root.getChild(1));
                isFound = true;
            } //if you choose general
            else {
                for (int i = 0; i < root.getChildSize(); i++) {
                    TreeNode temp = root.getChild(i);
                    for (int j = 0; j < temp.getChildSize(); j++) {
                        TreeNode tempL2 = temp.getChild(j);
                        if (tempL2.getName().equalsIgnoreCase(name)) {
                            displayBio(tempL2);
                            isFound = true;
                        }
                    }

                }
            }

            if (!isFound)
                System.out.println("Unrecognized person. Please enter again.\n");
            else
                break;

        }
    }

}
