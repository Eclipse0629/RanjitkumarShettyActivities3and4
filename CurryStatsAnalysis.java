import java.io.*;
import java.util.*;

public class CurryStatsAnalysis {

    public static void main(String[] args) {
        String csvFile = "Stephen Curry Regularseason Stats.csv"; // Your dataset file
        String line;
        String csvSplitBy = ",";
        
        List<Integer> ages = new ArrayList<>();
        List<Double> points = new ArrayList<>();
        List<Double> assists = new ArrayList<>();
        List<Double> rebounds = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                ages.add(Integer.parseInt(data[0]));
                points.add(Double.parseDouble(data[1]));
                assists.add(Double.parseDouble(data[2]));
                rebounds.add(Double.parseDouble(data[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print headers for stats
        System.out.println("Age | Points    | Assists   | Rebounds");
        System.out.println("------------------------------------------");
        
        // Loop through and display stats using ASCII bar graphs
        for (int i = 0; i < ages.size(); i++) {
            String age = String.format("%-4d", ages.get(i));
            String pointBar = generateBar(points.get(i));
            String assistBar = generateBar(assists.get(i));
            String reboundBar = generateBar(rebounds.get(i));

            System.out.println(age + "| " + pointBar + " | " + assistBar + " | " + reboundBar);
        }
    }

    // Helper method to generate an ASCII bar based on the stat value
    private static String generateBar(double stat) {
        int barLength = (int) stat / 2; // Adjust scaling factor for bar length
        StringBuilder bar = new StringBuilder();
        for (int i = 0; i < barLength; i++) {
            bar.append("#");
        }
        return String.format("%-10s", bar.toString()); // Align bar for neatness
    }
}
