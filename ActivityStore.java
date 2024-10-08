import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ActivityStore {
    // Map to store activities, with keys as characters and values as lists of activities
    protected Map<String, List<String>> activities;

    // Constructor to initialize an empty store
    public ActivityStore() {
        activities = new HashMap<>();
    }

    // Constructor to load activities from a file
    public ActivityStore(String fileName) throws IOException {
        this(); // Call the no-argument constructor to initialize the map
        BufferedReader reader = null;
        try {
            // Create a BufferedReader to read the file
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            // Read each line from the file
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    // Extract the first character as the key and convert the line to lowercase
                    String key = line.substring(0, 1).toLowerCase();
                    String item = line.toLowerCase();
                    // Add the activity to the store
                    add(key, item);
                }
            }
        } finally {
            // Close the reader in the finally block to ensure it is always closed
            if (reader != null) {
                reader.close();
            }
        }
    }

    // Method to add an activity to the store
    public void add(String key, String item) {
        key = key.toLowerCase(); // Convert the key to lowercase
        // If the key does not exist in the map, add it with an empty list
        activities.putIfAbsent(key, new ArrayList<>());
        // Add the item to the list of activities associated with the key
        activities.get(key).add(item.toLowerCase());
    }

    // Method to get a random item associated with the given key
    public String getRandomItem(String key) {
        key = key.toLowerCase(); // Convert the key to lowercase
        List<String> itemList = activities.get(key);
        // If the list is empty or null, return null
        if (itemList == null || itemList.isEmpty()) {
            return null;
        } else {
            // Otherwise, select a random item from the list
            Random random = new Random();
            String randomItem = itemList.get(random.nextInt(itemList.size()));
            // Capitalize the characters based on the key and return the result
            return capitalizeKey(randomItem, key);
        }
    }

    // Method to capitalize the characters of an item based on the key
    private String capitalizeKey(String item, String key) {
        StringBuilder result = new StringBuilder();
        // Iterate through each character of the item
        for (int i = 0; i < item.length(); i++) {
            // If the index is within the length of the key
            if (i < key.length()) {
                char ch = key.charAt(i);
                // If the character is a letter, capitalize it
                if (Character.isLetter(ch)) {
                    result.append(Character.toUpperCase(ch));
                } else {
                    // Otherwise, append the character as is
                    result.append(ch);
                }
            } else {
                // If the index is beyond the length of the key, convert the character to lowercase
                result.append(Character.toLowerCase(item.charAt(i)));
            }
        }
        return result.toString(); // Return the capitalized string
    }

    // Method to get all activities from the store
    public List<String> getAllActivities() {
        List<String> allActivities = new ArrayList<>();
        // Iterate through each list of activities in the map and add them to the result list
        for (List<String> list : activities.values()) {
            allActivities.addAll(list);
        }
        return allActivities; // Return the list of all activities
    }
}