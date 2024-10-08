import java.io.IOException;

public class BirthdayPlannerTest {
    public static void main(String[] args) {
        testLoadFile();
        testGetRandomItemAfterLoading();
        new CafeStore();
    }

    // Test loading activities from the file
    private static void testLoadFile() {
        try {
            ActivityStore store = new ActivityStore("main-activities.txt");
            // Verify that activities are loaded by checking if getRandomItem returns a non-null value
            assert store.getRandomItem("a") != null;
            assert store.getRandomItem("b") != null;
            assert store.getRandomItem("c") != null;
            System.out.println("File loaded successfully");
            
            // Instantiate CafeStore with the filename
            CafeStore cafeStore = new CafeStore("cafes.txt");
            // Verify that activities are loaded by checking if getRandomItem returns a non-null value
            assert cafeStore.getRandomItem("a") != null;
            assert cafeStore.getRandomItem("b") != null;
            assert cafeStore.getRandomItem("c") != null;
            System.out.println("Cafe file loaded successfully");
            
            // Instantiate RestaurantStore with the filename
            RestaurantStore restaurantStore = new RestaurantStore("restaurants.txt");
            // Verify that activities are loaded by checking if getRandomItem returns a non-null value
            assert restaurantStore.getRandomItem("a") != null;
            assert restaurantStore.getRandomItem("b") != null;
            assert restaurantStore.getRandomItem("c") != null;
            System.out.println("Restaurant file loaded successfully");
            
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    // Test getting random item after loading activities from the file
    private static void testGetRandomItemAfterLoading() {
        try {
            ActivityStore store = new ActivityStore("main-activities.txt");
            // Verify that getRandomItem returns a non-null value for keys with loaded activities
            assert store.getRandomItem("a") != null;
            assert store.getRandomItem("b") != null;
            assert store.getRandomItem("c") != null;
            // Verify that getRandomItem returns null for keys with no loaded activities
            assert store.getRandomItem("d") == null;
            System.out.println("Get random item after loading: Passed");
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
}
