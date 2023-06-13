class GeneralNode {
    private String name;
    private String armyType;
    private int strength;
    private int leadership;
    private int intelligence;
    private int politic;
    private int hitPoint;
    private GeneralNode left;
    private GeneralNode right;

    public GeneralNode(String name, String armyType, int strength, int leadership, int intelligence, int politic, int hitPoint) {
        this.name = name;
        this.armyType = armyType;
        this.strength = strength;
        this.leadership = leadership;
        this.intelligence = intelligence;
        this.politic = politic;
        this.hitPoint = hitPoint;
    }

    public String getName() {
        return name;
    }

    public String getArmyType() {
        return armyType;
    }

    public int getStrength() {
        return strength;
    }

    public int getLeadership() {
        return leadership;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getPolitic() {
        return politic;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public GeneralNode getLeft() {
        return left;
    }

    public GeneralNode getRight() {
        return right;
    }

    public void setLeft(GeneralNode left) {
        this.left = left;
    }

    public void setRight(GeneralNode right) {
        this.right = right;
    }
}

class WuKingdomHierarchy {
    private GeneralNode root;

    public void addGeneral(String name, String armyType, int strength, int leadership, int intelligence, int politic, int hitPoint) {
        GeneralNode newGeneral = new GeneralNode(name, armyType, strength, leadership, intelligence, politic, hitPoint);
        if (root == null) {
            root = newGeneral;
        } else {
            insertGeneral(root, newGeneral);
        }
    }

    private void insertGeneral(GeneralNode current, GeneralNode newGeneral) {
        if (newGeneral.getIntelligence() > newGeneral.getStrength()) {
            if (current.getLeft() == null) {
                current.setLeft(newGeneral);
            } else {
                insertGeneral(current.getLeft(), newGeneral);
            }
        } else {
            if (current.getRight() == null) {
                current.setRight(newGeneral);
            } else {
                insertGeneral(current.getRight(), newGeneral);
            }
        }
    }

    public void printHierarchy() {
        printHierarchy(root, 0);
    }

    private void printHierarchy(GeneralNode current, int level) {
        if (current == null) {
            return;
        }

        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indent.append("  ");
        }

        System.out.println(indent.toString() + current.getName() + " (" + current.getArmyType() + ")");
        printHierarchy(current.getLeft(), level + 1);
        printHierarchy(current.getRight(), level + 1);
    }
}

class WuKingdom {
    public static void main(String[] args) {
        WuKingdomHierarchy hierarchy = new WuKingdomHierarchy();

        hierarchy.addGeneral("Sun Quan", "Emperor", 0, 0, 0, 0, 0);
        hierarchy.addGeneral("Zhang Zhao", "Archer", 22, 80, 89, 99, 60);
        hierarchy.addGeneral("Zhou Yu", "Cavalry", 80, 86, 97, 80, 90);
        hierarchy.addGeneral("Xu Sheng", "Archer", 90, 78, 72, 40, 94);
        hierarchy.addGeneral("Zhu Ge Jin", "Archer", 63, 61, 88, 82, 71);
        hierarchy.addGeneral("Lu Su", "Infantry", 43, 87, 84, 88, 53);
        hierarchy.addGeneral("Tai Shi Ci", "Cavalry", 96, 81, 43, 33, 97);
        hierarchy.addGeneral("Xiao Qiao", "Infantry", 42, 52, 89, 77, 34);
        hierarchy.addGeneral("Da Qiao", "Cavalry", 39, 62, 90, 62, 41);
        hierarchy.addGeneral("Zhou Tai", "Infantry", 92, 89, 72, 43, 99);
        hierarchy.addGeneral("Gan Ning", "Archer", 98, 92, 45, 23, 97);
        hierarchy.addGeneral("Lu Meng", "Cavalry", 70, 77, 93, 83, 88);
        hierarchy.addGeneral("Huang Gai", "Infantry", 83, 98, 72, 42, 89);

        hierarchy.printHierarchy();
    }
}

