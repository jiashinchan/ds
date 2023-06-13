import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Character {
    String name;
    String armyType;
    int strength;
    int leadership;
    int intelligence;
    int politic;
    int hitPoint;

    public Character(String name, String armyType, int strength, int leadership, int intelligence, int politic, int hitPoint) {
        this.name = name;
        this.armyType = armyType;
        this.strength = strength;
        this.leadership = leadership;
        this.intelligence = intelligence;
        this.politic = politic;
        this.hitPoint = hitPoint;
    }

    public int getAbilitySum() {

        return strength + leadership + intelligence + politic +hitPoint ;
    }
}

public class GeneralSortingExample {
    public static void main(String[] args) {
        List<Character> generals = createGenerals();

        // Sort the generals based on different attributes
        sortByLeadership(generals);
        System.out.println("Generals sorted by Leadership:");
        printGenerals(generals);

        sortByStrength(generals);
        System.out.println("Generals sorted by Strength:");
        printGenerals(generals);

        sortByIntelligence(generals);
        System.out.println("Generals sorted by Intelligence:");
        printGenerals(generals);

        sortByPolitic(generals);
        System.out.println("Generals sorted by Politic Skills:");
        printGenerals(generals);

        sortByHitpoint(generals);
        System.out.println("Generals sorted by Hit Point:");
        printGenerals(generals);

        sortByAbilitySum(generals);
        System.out.println("Generals sorted by Ability Sum:");
        printGenerals(generals);


        // Use binary search to find a general with specific ability
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the target ability: ");
        int targetAbility = scanner.nextInt();
        Character foundGeneral = binarySearchByAbility(generals, targetAbility);
        if (foundGeneral != null) {
            System.out.println("General found with ability " + targetAbility + ": " + foundGeneral.name );
        } else {
            System.out.println("No general found with ability \n" + targetAbility);
        }
        System.out.println( );

        // Suggest 3 generals in each field and level
        suggestGeneralsByLevel(generals, 'S');
        suggestGeneralsByLevel(generals, 'A');
        suggestGeneralsByLevel(generals, 'B');
        suggestGeneralsByLevel(generals, 'C');
    }

    public static List<Character> createGenerals() {
        List<Character> generals = new ArrayList<>();

        generals.add(new Character("Xu Sheng", "Archer", 90, 78, 72, 40, 94));
        generals.add(new Character("Zhu Ge Jin", "Archer", 63, 61, 88, 82, 71));
        generals.add(new Character("Lu Su", "Infantry", 43, 87, 84, 88, 53));
        generals.add(new Character("Tai Shi Ci", "Cavalry", 96, 81, 43, 33, 97));
        generals.add(new Character("Xiao Qiao", "Infantry", 42, 52, 89, 77, 34));
        generals.add(new Character("Da Qiao", "Cavalry", 39, 62, 90, 62, 41));
        generals.add(new Character("Zhou Tai", "Infantry", 92, 89, 72, 43, 99));
        generals.add(new Character("Gan Ning", "Archer", 98, 92, 45, 23, 97));
        generals.add(new Character("Lu Meng", "Cavalry", 70, 77, 93, 83, 88));
        generals.add(new Character("Huang Gai", "Infantry", 83, 98, 72, 42, 89));

        return generals;
    }

    public static void sortByLeadership(List<Character> generals) {
        generals.sort(Comparator.comparingInt(a -> a.leadership));
    }

    public static void sortByStrength(List<Character> generals) {
        generals.sort(Comparator.comparingInt(a -> a.strength));
    }

    public static void sortByIntelligence(List<Character> generals) {
        generals.sort(Comparator.comparingInt(a -> a.intelligence));
    }

    public static void sortByPolitic(List<Character> generals) {
        generals.sort(Comparator.comparingInt(a -> a.politic));
    }

    public static void sortByHitpoint(List<Character> generals) {
        generals.sort(Comparator.comparingInt(a -> a.hitPoint));
    }

    public static void sortByAbilitySum(List<Character> generals) {
        generals.sort(Comparator.comparingInt(a -> a.getAbilitySum()));
    }


    public static Character binarySearchByAbility(List<Character> generals, int targetAbility) {
        int left = 0;
        int right = generals.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Character current = generals.get(mid);

            if (current.getAbilitySum() == targetAbility) {
                return current;
            }

            if (current.getAbilitySum() < targetAbility) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return null;
    }

    public static void suggestGeneralsByLevel(List<Character> generals, char level) {
        int minAbilitySum;
        String levelName;

        switch (level) {
            case 'S':
                minAbilitySum = 250;
                levelName = "S level";
                break;
            case 'A':
                minAbilitySum = 220;
                levelName = "A level";
                break;
            case 'B':
                minAbilitySum = 190;
                levelName = "B level";
                break;
            case 'C':
                minAbilitySum = 190;
                levelName = "C level";
                break;
            default:
                return;
        }

        System.out.println("Suggested Generals for " + levelName + ":");

        for (int i = 0; i < generals.size() - 2; i++) {
            for (int j = i + 1; j < generals.size() - 1; j++) {
                for (int k = j + 1; k < generals.size(); k++) {
                    Character general1 = generals.get(i);
                    Character general2 = generals.get(j);
                    Character general3 = generals.get(k);

                    int abilitySum = general1.getAbilitySum() + general2.getAbilitySum() + general3.getAbilitySum();

                    if (level == 'C' && abilitySum <= minAbilitySum) {
                        System.out.println(general1.name + ", " + general2.name + ", " + general3.name);
                    } else if (abilitySum >= minAbilitySum) {
                        System.out.println(general1.name + ", " + general2.name + ", " + general3.name);
                    }
                }
            }
        }

        System.out.println();
    }

    public static void printGenerals(List<Character> generals) {
        for (Character general : generals) {
            System.out.println(general.name);
        }
        System.out.println();
    }
}

