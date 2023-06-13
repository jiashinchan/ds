import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Characters {
    String name;
    String armyType;
    int strength;
    int leadership;
    int intelligence;
    int politic;
    int hitPoint;

    public Characters(String name, String armyType, int strength, int leadership, int intelligence, int politic, int hitPoint) {
        this.name = name;
        this.armyType = armyType;
        this.strength = strength;
        this.leadership = leadership;
        this.intelligence = intelligence;
        this.politic = politic;
        this.hitPoint = hitPoint;
    }
}

class Nodes {
    Characters character;
    List<Nodes> children;

    public Nodes(Characters character) {
        this.character = character;
        this.children = new ArrayList<>();
    }

    public void addChild(Nodes child) {
        children.add(child);
    }
}

