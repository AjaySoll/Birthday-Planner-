import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RestaurantStore extends ActivityStore {
    // Constructor to load restaurant activities from a file and append the category
    public RestaurantStore(String fileName) throws IOException {
        super(fileName); // Call the superclass constructor to load activities from the file
        appendCategoryToItems("restaurant"); // Append "restaurant" to each item in the store
    }

    // Method to append the category to each item in the store
    private void appendCategoryToItems(String category) {
        try {
            // Create a BufferedReader to read the file
            BufferedReader reader = new BufferedReader(new FileReader("restaurants.txt"));
            String line;

            // Read each line from the file
            while ((line = reader.readLine()) != null) {
                // Append the category to each item and add it to the store
                add(line.toLowerCase(), line + " " + category);
            }

            // Close the reader
            reader.close();
        } catch (IOException e) {
            // Print an error message if an IOException occurs
            System.err.println("Error reading restaurant activities file: " + e.getMessage());
        }
    }
}