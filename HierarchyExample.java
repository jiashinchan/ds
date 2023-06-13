import java.util.ArrayList;
import java.util.List;

class Character {
    String name;
    String position;
    String armyType;
    int strength;
    int leadership;
    int intelligence;
    int politic;
    int hitPoint;

    public Character(String name,String position, String armyType, int strength, int leadership, int intelligence, int politic, int hitPoint) {
        this.name = name;
        this.position=position;
        this.armyType = armyType;
        this.strength = strength;
        this.leadership = leadership;
        this.intelligence = intelligence;
        this.politic = politic;
        this.hitPoint = hitPoint;
    }
}

class Node {
    Character character;
    List<Node> children;

    public Node(Character character) {
        this.character = character;
        this.children = new ArrayList<>();
    }

    public void addChild(Node child) {
        children.add(child);
    }
}

public class HierarchyExample {
    public static void main(String[] args) {
        // Create characters
        Character sunQuan = new Character("Sun Quan", "Emperor","Cavalry", 96, 98, 72, 77, 95);
        Character zhangZhao = new Character("Zhang Zhao","Chief Of Management", "Archer", 22, 80, 89, 99, 60);
        Character zhouYu = new Character("Zhou Yu", "Chief Of Military","Cavalry", 80, 86, 97, 80, 90);
        Character xuSheng = new Character("Xu Sheng", "General","Archer", 90, 78, 72, 40, 94);
        Character zhuGeJin = new Character("Zhu Ge Jin","General", "Archer", 63, 61, 88, 82, 71);
        Character luSu = new Character("Lu Su", "General","Infantry", 43, 87, 84, 88, 53);
        Character taiShiCi = new Character("Tai Shi Ci", "General","Cavalry", 96, 81, 43, 33, 97);
        Character xiaoQiao = new Character("Xiao Qiao","General", "Infantry", 42, 52, 89, 77, 34);
        Character daQiao = new Character("Da Qiao", "General","Cavalry", 39, 62, 90, 62, 41);
        Character zhouTai = new Character("Zhou Tai","General", "Infantry", 92, 89, 72, 43, 99);
        Character ganNing = new Character("Gan Ning","General","Archer", 98, 92, 45, 23, 97);
        Character luMeng = new Character("Lu Meng","General", "Cavalry", 70, 77, 93, 83, 88);
        Character huangGai = new Character("Huang Gai","General", "Infantry", 83, 98, 72, 42, 89);

        // Create the hierarchy
        Node sunQuanNode = new Node(sunQuan);
        Node zhangZhaoNode = new Node(zhangZhao);
        Node zhouYuNode = new Node(zhouYu);

        Node xuShengNode = new Node(xuSheng);
        Node zhuGeJinNode = new Node(zhuGeJin);
        Node luSuNode = new Node(luSu);
        Node taiShiCiNode = new Node(taiShiCi);
        Node xiaoQiaoNode = new Node(xiaoQiao);
        Node daQiaoNode = new Node(daQiao);
        Node zhouTaiNode = new Node(zhouTai);
        Node ganNingNode = new Node(ganNing);
        Node luMengNode = new Node(luMeng);
        Node huangGaiNode = new Node(huangGai);

        sunQuanNode.addChild(zhangZhaoNode);
        sunQuanNode.addChild(zhouYuNode);
        zhangZhaoNode.addChild(zhuGeJinNode);
        zhangZhaoNode.addChild(luSuNode);
        zhangZhaoNode.addChild(luMengNode);
        zhangZhaoNode.addChild(xiaoQiaoNode);
        zhangZhaoNode.addChild(daQiaoNode);
        zhouYuNode.addChild(zhouTaiNode);
        zhouYuNode.addChild(ganNingNode);
        zhouYuNode.addChild(huangGaiNode);
        zhouYuNode.addChild(taiShiCiNode);
        zhouYuNode.addChild(xuShengNode);

        // Assign generals to departments automatically
        assignDepartment(xuShengNode);
        assignDepartment(zhuGeJinNode);
        assignDepartment(luSuNode);
        assignDepartment(taiShiCiNode);
        assignDepartment(xiaoQiaoNode);
        assignDepartment(daQiaoNode);
        assignDepartment(zhouTaiNode);
        assignDepartment(ganNingNode);
        assignDepartment(luMengNode);
        assignDepartment(huangGaiNode);

        // Print the hierarchy
        printHierarchy(sunQuanNode, 0);
    }

    public static void assignDepartment(Node node) {
        Character character = node.character;
        if (character.intelligence > character.strength) {
            character.armyType = "Management Department";
        } else if (character.strength > character.intelligence) {
            character.armyType = "Military Department";
        }

        for (Node child : node.children) {
            assignDepartment(child);
        }
    }

    public static void printHierarchy(Node node, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("\t");
        }
        System.out.println(node.character.name + " (" + node.character.position+  ") ");
        for (Node child : node.children) {
            printHierarchy(child, level + 1);
        }
    }
}
