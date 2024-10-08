import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BirthdayPlanner {

    // Activity stores to store main activities, cafes, and restaurants
    private final ActivityStore mainActivityStore;
    private final CafeStore cafeStore;
    private final RestaurantStore restaurantStore;

    // Random object for generating random values
    private final Random random = new Random();

    // Constructor to initialize the planner with activity stores
    public BirthdayPlanner(ActivityStore mainActivityStore, CafeStore cafeStore, RestaurantStore restaurantStore) {
        this.mainActivityStore = mainActivityStore;
        this.cafeStore = cafeStore;
        this.restaurantStore = restaurantStore;
    }

    // Method to generate a birthday plan based on the input word
    public List<String> generate(String input) {
        List<String> plan = new ArrayList<>();
        char[] characters = input.toLowerCase().toCharArray(); // Convert input to lowercase characters
        int currentPrefixLength = random.nextInt(2) + 1; // Randomize the starting prefix length

        // Iterate through each character in the input word
        for (char ch : characters) {
            String key = String.valueOf(ch);
            String activity = getRandomActivity(key, currentPrefixLength);

            // If no activity found for the current character, try shorter prefixes
            if (activity == null) {
                for (int prefixLength = currentPrefixLength - 1; prefixLength >= 1; prefixLength--) {
                    activity = getRandomActivity(key, prefixLength);
                    if (activity != null) {
                        currentPrefixLength = prefixLength;
                        break;
                    }
                }
            }

            // If activity found, add it to the plan
            if (activity != null) {
                plan.add(activity);
            }
        }

        // Ensure the last activity is not a cafe
        if (!plan.isEmpty() && plan.get(plan.size() - 1).endsWith("(cafe)")) {
            String lastMainActivity = getRandomActivity(String.valueOf(characters[characters.length - 1]), currentPrefixLength);
            if (lastMainActivity != null) {
                plan.add(lastMainActivity);
            }
        }

        return plan;
    }

    // Helper method to get a random activity for a given key and prefix length
    private String getRandomActivity(String key, int prefixLength) {
        switch (key.toLowerCase()) {
            case "cafe":
                return cafeStore.getRandomItem(key);
            case "restaurant":
                return restaurantStore.getRandomItem(key);
            default:
                return mainActivityStore.getRandomItem(key);
        }
    }

    // Main method to run the BirthdayPlanner class
    public static void main(String[] args) {
        try {
            // Load activity stores from files
            ActivityStore mainActivityStore = new ActivityStore("main-activities.txt");
            CafeStore cafeStore = new CafeStore("cafes.txt");
            RestaurantStore restaurantStore = new RestaurantStore("restaurants.txt");

            // Create an instance of BirthdayPlanner with the loaded activity stores
            BirthdayPlanner birthdayPlanner = new BirthdayPlanner(mainActivityStore, cafeStore, restaurantStore);

            // Generate and print the birthday plan based on the input word
            if (args.length > 0) {
                String input = args[0];
                List<String> plan = birthdayPlanner.generate(input);

                for (String activity : plan) {
                    System.out.println(activity);
                }
            } else {
                System.out.println("Please provide a word as a command line argument.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load activity stores.");
        }
    }
}
