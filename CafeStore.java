import java.io.IOException;
import java.util.ArrayList; // Import the ArrayList class

public class CafeStore extends ActivityStore {
    // No-argument constructor
    public CafeStore() {
        super(); // Call the superclass constructor to initialize an empty store
    }

    // Constructor to load cafe activities from a file and append the category
    public CafeStore(String fileName) throws IOException {
        super(fileName); // Call the superclass constructor to load activities from the file
        appendCategoryToItems("cafe"); // Append "cafe" to each item in the store
    }
    

    private void appendCategoryToItems(String category) {
        for (String key : activities.keySet()) {
            // Iterate over each item in the list associated with the key
            for (int i = 0; i < activities.get(key).size(); i++) {
                String item = activities.get(key).get(i);
                // Check if the item already ends with "(cafe)"
                if (!item.toLowerCase().endsWith("(cafe)")) {
                    // Append "(cafe)" to the item
                    activities.get(key).set(i, item + " (cafe)");
                }
            }
        }
    }
    @Override
public void add(String key, String item) {
    key = key.toLowerCase();
    item = item.toLowerCase(); // Convert item to lowercase
    // Check if the item already ends with "(cafe)"
    if (!item.endsWith("(cafe)")) {
        item += " (cafe)"; // Append "(cafe)" to the item
    }
    activities.putIfAbsent(key, new ArrayList<>());
    activities.get(key).add(item); // Add the modified item to the activities
}

}
