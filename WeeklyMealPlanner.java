
import java.util.*;
import java.io.*;

public class WeeklyMealPlanner {

    static Scanner sc = new Scanner(System.in);

    static String[] days = {
        "Monday", "Tuesday", "Wednesday", "Thursday",
        "Friday", "Saturday", "Sunday"
    };

    static String[] mealSlots = {
        "Breakfast", "Lunch", "Dinner"
    };

    static class Recipe {
        String name;
        HashMap<String, Integer> ingredients;

        Recipe(String name) {
            this.name = name;
            ingredients = new HashMap<>();
        }

        void addIngredient(String item, int quantity) {
            ingredients.put(item, quantity);
        }
    }

    static ArrayList<Recipe> recipes = new ArrayList<>();

    static String[][] weeklyPlan = {
        {"Idli", "Rice and Dal", "Chapati"},
        {"Dosa", "Vegetable Biryani", "Paneer Curry"},
        {"Poha", "Curd Rice", "Sambar Rice"},
        {"Upma", "Rice and Dal", "Egg Curry"},
        {"Idli", "Vegetable Pulao", "Chapati"},
        {"Dosa", "Egg Curry", "Paneer Curry"},
        {"Poha", "Sambar Rice", "Curd Rice"}
    };

    static void initializeRecipes() {
        Recipe r;

        r = new Recipe("Idli");
        r.addIngredient("Rice", 100);
        r.addIngredient("Dal", 30);
        r.addIngredient("Oil", 5);
        recipes.add(r);

        r = new Recipe("Dosa");
        r.addIngredient("Rice", 100);
        r.addIngredient("Dal", 30);
        r.addIngredient("Oil", 10);
        recipes.add(r);

        r = new Recipe("Poha");
        r.addIngredient("Poha", 100);
        r.addIngredient("Onion", 30);
        r.addIngredient("Oil", 5);
        recipes.add(r);

        r = new Recipe("Upma");
        r.addIngredient("Rava", 100);
        r.addIngredient("Onion", 30);
        r.addIngredient("Oil", 5);
        recipes.add(r);

        r = new Recipe("Chapati");
        r.addIngredient("Wheat Flour", 100);
        r.addIngredient("Oil", 5);
        recipes.add(r);

        r = new Recipe("Rice and Dal");
        r.addIngredient("Rice", 100);
        r.addIngredient("Dal", 60);
        r.addIngredient("Tomato", 30);
        recipes.add(r);

        r = new Recipe("Vegetable Biryani");
        r.addIngredient("Rice", 150);
        r.addIngredient("Vegetables", 100);
        r.addIngredient("Oil", 10);
        recipes.add(r);

        r = new Recipe("Paneer Curry");
        r.addIngredient("Paneer", 100);
        r.addIngredient("Onion", 40);
        r.addIngredient("Tomato", 40);
        recipes.add(r);

        r = new Recipe("Curd Rice");
        r.addIngredient("Rice", 100);
        r.addIngredient("Curd", 100);
        recipes.add(r);

        r = new Recipe("Sambar Rice");
        r.addIngredient("Rice", 100);
        r.addIngredient("Dal", 40);
        r.addIngredient("Vegetables", 50);
        recipes.add(r);

        r = new Recipe("Egg Curry");
        r.addIngredient("Eggs", 2);
        r.addIngredient("Onion", 40);
        r.addIngredient("Tomato", 40);
        r.addIngredient("Oil", 5);
        recipes.add(r);

        r = new Recipe("Vegetable Pulao");
        r.addIngredient("Rice", 150);
        r.addIngredient("Vegetables", 100);
        r.addIngredient("Oil", 10);
        recipes.add(r);
    }

    static Recipe findRecipe(String name) {
        for (Recipe r : recipes) {
            if (r.name.equalsIgnoreCase(name.trim())) {
                return r;
            }
        }
        return null;
    }

    static void displayMealPlan() {
        System.out.println("\n===== WEEKLY MEAL PLAN =====");

        for (int i = 0; i < weeklyPlan.length; i++) {
            displayMealPlan(i);
        }
    }

    static void displayMealPlan(int dayIndex) {
        System.out.println("\n" + days[dayIndex]);

        for (int j = 0; j < weeklyPlan[dayIndex].length; j++) {
            System.out.println(
                mealSlots[j] + ": " + weeklyPlan[dayIndex][j]
            );
        }
    }

    static HashMap<String, Integer> generateGroceryList() {
        HashMap<String, Integer> grocery = new HashMap<>();

        for (int i = 0; i < weeklyPlan.length; i++) {
            for (int j = 0; j < weeklyPlan[i].length; j++) {
                Recipe r = findRecipe(weeklyPlan[i][j]);

                if (r != null) {
                    for (String item : r.ingredients.keySet()) {
                        int quantity = r.ingredients.get(item);

                        grocery.put(
                            item,
                            grocery.getOrDefault(item, 0) + quantity
                        );
                    }
                }
            }
        }

        return grocery;
    }

    static void displayGroceryList() {
        HashMap<String, Integer> grocery = generateGroceryList();

        System.out.println("\n===== GENERATED GROCERY LIST =====");
        System.out.println("Quantities are approximate recipe units.");

        for (String item : grocery.keySet()) {
            int quantity = grocery.get(item);

            if (quantity > 0) {
                System.out.println(item + " - " + quantity);
            }
        }
    }

    static void searchGroceryItem() {
        System.out.print("Enter ingredient to search: ");
        String search = sc.nextLine().trim();

        HashMap<String, Integer> grocery = generateGroceryList();
        boolean found = false;

        for (String item : grocery.keySet()) {
            if (item.equalsIgnoreCase(search)) {
                System.out.println("Ingredient: " + item);
                System.out.println(
                    "Required quantity: " + grocery.get(item)
                );
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println(
                "Ingredient not found in this week's list."
            );
        }
    }

    static void changeMeal() {
        System.out.print("Enter day number (1-7): ");
        int day = sc.nextInt();

        System.out.println("1. Breakfast");
        System.out.println("2. Lunch");
        System.out.println("3. Dinner");
        System.out.print("Select meal slot: ");
        int slot = sc.nextInt();

        if (day < 1 || day > 7 || slot < 1 || slot > 3) {
            System.out.println("Invalid day or meal slot.");
            return;
        }

        System.out.println("\nAvailable recipes:");

        for (int i = 0; i < recipes.size(); i++) {
            System.out.println(
                (i + 1) + ". " + recipes.get(i).name
            );
        }

        System.out.print("Choose recipe number: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice < 1 || choice > recipes.size()) {
            System.out.println("Invalid recipe choice.");
            return;
        }

        weeklyPlan[day - 1][slot - 1] =
            recipes.get(choice - 1).name;

        System.out.println("Meal updated successfully.");
        System.out.println("Grocery quantities will be recalculated.");
    }

    static void saveMealPlan() {
        try (FileOutputStream fout =
                 new FileOutputStream("mealplan.txt")) {

            for (int i = 0; i < weeklyPlan.length; i++) {
                for (int j = 0; j < weeklyPlan[i].length; j++) {
                    String meal = weeklyPlan[i][j];

                    for (int k = 0; k < meal.length(); k++) {
                        fout.write(meal.charAt(k));
                    }

                    if (j < weeklyPlan[i].length - 1) {
                        fout.write('|');
                    }
                }

                fout.write('\n');
            }

            System.out.println("Meal plan saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving meal plan.");
        }
    }

    static void loadMealPlan() {
        StringBuilder data = new StringBuilder();

        try (FileInputStream fin =
                 new FileInputStream("mealplan.txt")) {

            int ch;

            while ((ch = fin.read()) != -1) {
                data.append((char) ch);
            }

        } catch (IOException e) {
            System.out.println("Error loading meal plan.");
            return;
        }

        String[] lines = data.toString().split("\\R");

        if (lines.length != 7) {
            System.out.println("Invalid meal plan file.");
            return;
        }

        String[][] loadedPlan = new String[7][3];

        for (int i = 0; i < 7; i++) {
            String[] fields = lines[i].split("\\|", -1);

            if (fields.length != 3) {
                System.out.println("Invalid data in meal plan file.");
                return;
            }

            for (int j = 0; j < 3; j++) {
                String mealName = fields[j].trim();

                if (findRecipe(mealName) == null) {
                    System.out.println("Unknown recipe: " + mealName);
                    return;
                }

                loadedPlan[i][j] = mealName;
            }
        }

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 3; j++) {
                weeklyPlan[i][j] = loadedPlan[i][j];
            }
        }

        System.out.println("Meal plan loaded successfully.");
    }

    public static void main(String[] args) {
        initializeRecipes();

        int choice;

        do {
            System.out.println(
                "\n===== WEEKLY MEAL PLANNER & GROCERY GENERATOR ====="
            );
            System.out.println("1. View Weekly Meal Plan");
            System.out.println("2. View Meals for One Day");
            System.out.println("3. Change a Meal");
            System.out.println("4. Generate Grocery List");
            System.out.println("5. Search Grocery Item");
            System.out.println("6. Save Meal Plan");
            System.out.println("7. Load Meal Plan");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    displayMealPlan();
                    break;

                case 2:
                    System.out.print("Enter day number (1-7): ");
                    int day = sc.nextInt();
                    sc.nextLine();

                    if (day >= 1 && day <= 7) {
                        displayMealPlan(day - 1);
                    } else {
                        System.out.println("Invalid day.");
                    }
                    break;

                case 3:
                    changeMeal();
                    break;

                case 4:
                    displayGroceryList();
                    break;

                case 5:
                    searchGroceryItem();
                    break;

                case 6:
                    saveMealPlan();
                    break;

                case 7:
                    loadMealPlan();
                    break;

                case 8:
                    System.out.println(
                        "Thank you for using the planner!"
                    );
                    break;

                default:
                    System.out.println(
                        "Invalid choice. Please try again."
                    );
            }

        } while (choice != 8);

        sc.close();
    }
}
