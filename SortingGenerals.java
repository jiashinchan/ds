

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class SortingGenerals {
    
    private WuKingdomHierarchy kingdom ;
    
    SortingGenerals() {
        kingdom = new WuKingdomHierarchy();
    }

    private ArrayList<TreeNode> getGeneralsList(TreeNode node) {
        ArrayList<TreeNode> generals = new ArrayList<>();
        
        if (!node.getName().equals("Sun Quan")
                && !node.getName().equals("Zhang Zhao")
                && !node.getName().equals("Zhou Yu")) {
            generals.add(node);
        }

        //iterate all children index
        for (TreeNode child : node.getChildrenList()) {
            generals.addAll(getGeneralsList(child));
        }

        return generals;
    }
    
    protected ArrayList<TreeNode> sortStrengthList() {
        ArrayList<TreeNode> arrList = getGeneralsList(kingdom.getHierarchy_Tree());
        ArrayList<TreeNode> sortStrengthList = new ArrayList<>();
        // Sort the generals based on Strength using Selection Sort
        
        // Selection Sort Algorithm
        for (int i = 0; i < arrList.size() ; i++) {
            int max = arrList.get(i).getStrength();
            int index = i;
            for (int j = i + 1; j < arrList.size() ; j++) {
                if (max < arrList.get(j).getStrength()) {
                    max = arrList.get(j).getStrength();
                    index = j;
                }
            }
            sortStrengthList.add(arrList.get(index));
            if (index != i) {
                arrList.remove(index);
                arrList.add(index, arrList.get(i));
            }
        }
        
        return sortStrengthList;
    }
    
    protected ArrayList<TreeNode> sortLeadershipList() {
        ArrayList<TreeNode> arrList = getGeneralsList(kingdom.getHierarchy_Tree());
        // Sort the generals based on Leadership using Insertion Sort
        
        // Insertion Sort Algorithm
        for (int i = 1 ; i < arrList.size() ; i++) {
            TreeNode currentElement = arrList.get(i);
            int max = currentElement.getLeadership();
            int k;
            for (k = i - 1 ; k >= 0 && arrList.get(k).getLeadership() < max ; k--) {
                arrList.remove(currentElement);
                arrList.add(k, currentElement);
            }
        }
        
        return arrList;
    }
    
    protected ArrayList<TreeNode> sortIntelligenceList() {
        ArrayList<TreeNode> arrList = getGeneralsList(kingdom.getHierarchy_Tree());
        
        boolean needNextPass = true;
        for (int k = 1 ; k < arrList.size() && needNextPass ; k++) {
            needNextPass = false;
            for (int i = 0 ; i < arrList.size() - k ; i++) {
                if (arrList.get(i).getIntelligence() < arrList.get(i + 1).getIntelligence()) {
                    TreeNode buffer = arrList.get(i + 1);
                    arrList.remove(buffer);
                    arrList.add(i, buffer);
                    needNextPass = true;
                }
            }
        }
        
        return arrList;
    }
    
    protected ArrayList<TreeNode> sortPoliticList() {
        ArrayList<TreeNode> arrList = getGeneralsList(kingdom.getHierarchy_Tree());
        // Sort the generals based on Politic using Merge Sort
        
        TreeNode[] arr = listToArray(arrList);
        mergeSort(arr);
        return arrayToList(arr);
    }
    
    protected ArrayList<TreeNode> sortHitPointList() {
        ArrayList<TreeNode> arrList = getGeneralsList(kingdom.getHierarchy_Tree());
        // Sort the generals based on Hit Point using Quick Sort
        
        TreeNode[] arr = listToArray(arrList);
        quickSort(arr);
        return arrayToList(arr);
    }
    
    // Merge Sort Algorithm
    private void mergeSort(TreeNode[] list) {
        if (list.length > 1) {
            TreeNode[] firstHalf = new TreeNode[list.length / 2];
            System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
            mergeSort(firstHalf);
            int secondHalfLength = list.length - list.length / 2;
            TreeNode[] secondHalf = new TreeNode[secondHalfLength];
            System.arraycopy(list, list.length / 2,
            secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf);
            merge(firstHalf, secondHalf, list);
        }
    }
    private void merge(TreeNode[] list1, TreeNode[] list2, TreeNode[] temp) {
        int current1 = 0;
        int current2 = 0;  
        int current3 = 0; 
        while (current1 < list1.length && current2 < list2.length) {
            if (list1[current1].getPolitic() > list2[current2].getPolitic()) temp[current3++] = list1[current1++];
            else temp[current3++] = list2[current2++];
        }
        while (current1 < list1.length) temp[current3++] = list1[current1++];
        while (current2 < list2.length) temp[current3++] = list2[current2++]; 
    }
    
    // Quick Sort Algorithm
    private void quickSort(TreeNode[] list) {
        quickSort(list, 0, list.length - 1);
    }
    private void quickSort(TreeNode[] list, int first, int last) {
        if (last > first) {
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
    }
    private int partition(TreeNode[] list, int first, int last) {
        TreeNode pivot = list[first]; 
        int low = first + 1; 
        int high = last; 
        while (high > low) {
            while (low <= high && list[low].getHitPoint() > pivot.getHitPoint())
            low++;
            while (low <= high && list[high].getHitPoint() <= pivot.getHitPoint())
            high--;
            if (high > low) {
                TreeNode temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }
        while (high > first && list[high].getHitPoint() < pivot.getHitPoint())
        high--;
        if (pivot.getHitPoint() < list[high].getHitPoint()) {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        }
        else {
            return first;
       }
    }
    
    public void printSortingList(ArrayList<TreeNode> sortList, int type) {
        int i = 0;
        for(TreeNode node: sortList){
            switch(type) {
                case 1 : {
                    if (i == 0) System.out.println("\n\t LEADERSHIP \n");
                    System.out.printf("Name: %-12s | Leadership: %-8s \n", node.getName(), node.getLeadership());
                    if (i == sortList.size() - 1) System.out.println("\nSorting method : Insertion Sort\n");
                } break;
                case 2 : {
                    if (i == 0) System.out.println("\n\t STRENGTH \n");
                    System.out.printf("Name: %-12s | Strength: %-8s \n", node.getName(), node.getStrength());
                    if (i == sortList.size() - 1) System.out.println("\nSorting method : Selection Sort\n");
                } break;
                case 3 : {
                    if (i == 0) System.out.println("\n\t INTELLIGENCE\n");
                    System.out.printf("Name: %-12s | Intelligence: %-8s \n", node.getName(), node.getIntelligence());
                    if (i == sortList.size() - 1) System.out.println("\nSorting method : Bubble Sort\n");
                } break;
                case 4 : {
                    if (i == 0) System.out.println("\n\t POLITIC\n");
                    System.out.printf("Name: %-12s | Politic: %-8s \n", node.getName(), node.getPolitic());
                    if (i == sortList.size() - 1) System.out.println("\nSorting method : Merge Sort\n");
                } break;
                case 5 : {
                    if (i == 0) System.out.println("\n\t  HIT POINT\n");
                    System.out.printf("Name: %-12s | Hit Point: %-8s \n", node.getName(), node.getHitPoint());
                    if (i == sortList.size() - 1) System.out.println("\nSorting method : Quick Sort\n");
                } break;
            }
            i++;
        }
    }
    
    private TreeNode[] listToArray(ArrayList<TreeNode> list) {
        TreeNode[] arr = new TreeNode[list.size()];
        for (int i = 0 ; i < arr.length ; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
    private ArrayList<TreeNode> arrayToList(TreeNode[] arr) {
        ArrayList<TreeNode> list = new ArrayList<>();
        for (TreeNode t : arr) {
            list.add(t);
        }
        return list;
    }
}
