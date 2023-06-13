import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaximizeArrows {
    public static void main(String[] args) {
        Map<String, Integer> strawMen = new HashMap<>();
        strawMen.put("Front", 10);
        strawMen.put("Left", 50);
        strawMen.put("Right", 50);
        strawMen.put("Back", 15);

        List<Integer> arrows = new ArrayList<>();
        arrows.add(2000);
        arrows.add(1500);
        arrows.add(1000);
        arrows.add(800);
        arrows.add(600);
        arrows.add(500);
        arrows.add(300);
        arrows.add(300);

        List<String> boatDirection = maximizeArrows(strawMen, arrows);
        List<Integer> arrowsReceived = calculateArrowsReceived(strawMen, arrows);
        int totalArrows = calculateTotalArrows(arrowsReceived);

        System.out.println("Boat direction: " + boatDirection);
        System.out.println("Arrow received: " + arrowsReceived);
        System.out.println("Total = " + totalArrows);
    }

    public static List<String> maximizeArrows(Map<String, Integer> strawMen, List<Integer> arrows) {
        List<String> boatDirection = new ArrayList<>();
        int waves = arrows.size();

        for (int i = 0; i < waves; i++) {
            String direction = getBoatDirection(i);
            boatDirection.add(direction);
            int arrowCount = arrows.get(i);
            int strawMenCount = strawMen.get(direction);
            double efficiency = (double) strawMenCount / 100;
            int capturedArrows = (int) (arrowCount * efficiency);
            strawMen.put(direction, strawMenCount - capturedArrows);
        }

        return boatDirection;
    }

    public static List<Integer> calculateArrowsReceived(Map<String, Integer> strawMen, List<Integer> arrows) {
        List<Integer> arrowsReceived = new ArrayList<>();
        int waves = arrows.size();

        for (int i = 0; i < waves; i++) {
            String direction = getBoatDirection(i);
            int arrowCount = arrows.get(i);
            int strawMenCount = strawMen.get(direction);
            double efficiency = (double) strawMenCount / 100;
            int capturedArrows = (int) (arrowCount * efficiency);
            arrowsReceived.add(capturedArrows);
            strawMen.put(direction, strawMenCount - capturedArrows);
        }

        return arrowsReceived;
    }

    public static int calculateTotalArrows(List<Integer> arrowsReceived) {
        int totalArrows = 0;

        for (int capturedArrows : arrowsReceived) {
            totalArrows += capturedArrows;
        }

        return totalArrows;
    }

    public static String getBoatDirection(int index) {
        switch (index % 4) {
            case 0:
                return "Left";
            case 1:
                return "Right";
            case 2:
                return "Back";
            case 3:
                return "Front";
            default:
                return "";
        }
    }
}
