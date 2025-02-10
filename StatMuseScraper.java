import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
 
    public class StatMuseScraper {
        public static void main(String[] args) {
            // The StatMuse URL for Stephen Curry's career stats
            String url = "https://www.statmuse.com/nba/player/stephen-curry-787/career-stats";
    
            try {
                // Fetch the HTML content of the page
                Document doc = Jsoup.connect(url).get();
    
                // Locate the table containing the career stats (adjust the selector based on the structure)
                Elements rows = doc.select("table tbody tr"); // Get all rows of the table
    
                // Iterate over each row and extract the data
                for (Element row : rows) {
                    // Extract data from each column in the row
                    String season = row.select("td:nth-child(1)").text();  // Season year
                    String points = row.select("td:nth-child(6)").text();  // Points per game
                    String assists = row.select("td:nth-child(3)").text(); // Assists per game
                    String rebounds = row.select("td:nth-child(4)").text(); // Rebounds per game
    
                    // Print the extracted stats (or store them in variables/collections as needed)
                    System.out.println("Season: " + season + ", Points: " + points + ", Assists: " + assists + ", Rebounds: " + rebounds);
                }
    
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    