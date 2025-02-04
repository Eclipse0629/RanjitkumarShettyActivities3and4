import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;

public class StatMuseScraper {

    public static void main(String[] args) {
        try {
            // This is the URL of Steph Currys career stats on StatMuse
            String url = "https://www.statmuse.com/nba/ask/career-stats-steph-curry";
            
            // Go to the page and grab the HTML content
            Document doc = Jsoup.connect(url).get();

            // Find the part of the page that contains the stats (were guessing the class name here)
            Elements statsElements = doc.select(".stat-item");

            // If we cant find any stats, tell the user
            if (statsElements.isEmpty()) {
                System.out.println("Sorry, we couldn't find any stats.");
                return;
            }

            // Variables to hold the stats
            String points = "", rebounds = "", assists = "";

            // Loop through the data to grab the points, rebounds, and assists (based on how the page is structured)
            for (Element stat : statsElements) {
                String statType = stat.select(".stat-type").text();  // This tells us what the stat is
                String statValue = stat.select(".stat-value").text(); // This gives us the value of the stat

                // If its a points stat, store it in the points variable
                if (statType.contains("Points")) {
                    points = statValue;
                } else if (statType.contains("Rebounds")) {
                    rebounds = statValue;
                } else if (statType.contains("Assists")) {
                    assists = statValue;
                }
            }

            // Save the stats to a CSV file (so we can open it in Visual Studio Code)
            saveDataToCSV(points, rebounds, assists);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This function saves the stats to a CSV file
    private static void saveDataToCSV(String points, String rebounds, String assists) {
        try {
            // Create a new file called nba_stats.csv
            FileWriter fileWriter = new FileWriter("nba_stats.csv");

            // Write the header to the file (this will tell us what each column means)
            fileWriter.append("Player,Points,Rebounds,Assists\n");

            // Write the actual data (stats) into the file
            fileWriter.append("Steph Curry," + points + "," + rebounds + "," + assists + "\n");

            // Save and close the file
            fileWriter.flush();
            fileWriter.close();
            System.out.println("Steph Curry's stats saved to nba_stats.csv");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
