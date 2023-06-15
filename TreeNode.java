
import java.util.ArrayList;

class TreeNode {
    private String name;
    private String position;
    private String armyType;
    private int strength;
    private int leadership;
    private int intelligence;
    private int politic;
    private int hitPoint;
    private ArrayList<TreeNode> childrenList;

    public TreeNode(String name, String position, String armyType, int strength, int leadership, int intelligence, int politic, int hitPoint) {
        this.name = name;
        this.position = position;
        this.armyType = armyType;
        this.strength = strength;
        this.leadership = leadership;
        this.intelligence = intelligence;
        this.politic = politic;
        this.hitPoint = hitPoint;
        this.childrenList = new ArrayList<>();
    }

    public TreeNode(String[]array)
    {
      this.name=array[0];
      this.position=array[1];
      this.armyType=array[2];
      this.strength=Integer.parseInt(array[3]);
      this.leadership=Integer.parseInt(array[4]);
      this.intelligence=Integer.parseInt(array[5]);
      this.politic=Integer.parseInt(array[6]);
      this.hitPoint=Integer.parseInt(array[7]);
      this.childrenList=new ArrayList<>();
      
    }
    
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArmyType() {
        return armyType;
    }

    public void setArmyType(String armyType) {
        this.armyType = armyType;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getLeadership() {
        return leadership;
    }

    public void setLeadership(int leadership) {
        this.leadership = leadership;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getPolitic() {
        return politic;
    }

    public void setPolitic(int politic) {
        this.politic = politic;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }
    
    //to get a specific children for displaying tree and biodata
    public TreeNode getChild(int index)
    {
        return this.childrenList.get(index);
    }
    
     //to remove child into the array list for remove general function
    public void removeChild(TreeNode child)
    {
        this.childrenList.remove(child);
    }
    
    public void addChild(TreeNode child) {
        this.childrenList.add(child);
    }
    
    public int getChildSize (){
        return childrenList.size();
    }

    // get array list
    public ArrayList<TreeNode> getChildrenList() {
        return childrenList;
    }

    // set array list
    public void setChildrenList(ArrayList<TreeNode> childrenList) {
        this.childrenList = childrenList;
    }

    
    public String toString() {
        return String.format("Name: %-12s | Position: %-8s | Army Type: %-10s | Strength: %-2d | Leadership: %-2d | Intelligence: %-2d | Politic: %-2d | Hit Point: %-2d",
                name, position, armyType, strength, leadership, intelligence, politic, hitPoint);
    }
}

