import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class StatMuseScraper {
    public static void main(String[] args) {
        // The StatMuse URL for Stephen Curry's career stats
        String url = "https://www.statmuse.com/nba/player/stephen-curry-787/career-stats";

        // Set to keep track of processed seasons to avoid duplicates
        Set<String> processedSeasons = new HashSet<>();

        try {
            // Fetch the HTML content of the page
            Document doc = Jsoup.connect(url).get();

            // Locate the table containing the career stats (adjust the selector based on the structure)
            Elements rows = doc.select("table tbody tr"); // Get all rows of the table

            // Iterate over each row and extract the data
            for (Element row : rows) {
                // Extract data from each column in the row
                String season = row.select("td:nth-child(1)").text();  // Season year
                String points = row.select("td:nth-child(6)").text();  // Points per game (6th column)
                String assists = row.select("td:nth-child(8)").text(); // Assists per game
                String rebounds = row.select("td:nth-child(7)").text(); // Rebounds per game

                // Skip rows that have invalid or empty stats (non-numeric or empty data)
                if (season.isEmpty() || points.isEmpty() || assists.isEmpty() || rebounds.isEmpty()) {
                    continue;
                }

                // Check if we've already processed this season
                if (processedSeasons.contains(season)) {
                    continue; // Skip if season is already processed
                }

                // Add the season to the set to avoid processing it again
                processedSeasons.add(season);

                // Try to parse the stats to check if they're valid numbers
                try {
                    Double.parseDouble(points);  // Try to parse points as a number
                    Double.parseDouble(assists); // Try to parse assists as a number
                    Double.parseDouble(rebounds); // Try to parse rebounds as a number

                    // Print the extracted stats (or store them in variables/collections as needed)
                    System.out.println("Season: " + season + ", Points: " + points + ", Assists: " + assists + ", Rebounds: " + rebounds);
                } catch (NumberFormatException e) {
                    // If parsing fails, skip the row (invalid data)
                    continue;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
