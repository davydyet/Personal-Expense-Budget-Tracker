import java.time.LocalDate;
import java.util.Scanner;

//This class represents the management of the system which includes all categories of the expense-related functions like a brain of the program.
/*
Responsibilites:
- addExpense method -> Add new expenses to the user's record.
- viewAllExpenses method -> View all recorded expenses.
- viewCategoryTotalSummary method -> Display a summary of total spending by category.
- checkMonthlyBudget method -> Check whether the user is spending within their monthly budget.
- vieExpenseChart method -> Display a visual expense chart with the percentage.
 */
public class ExpenseManager {
    public static void addExpense(User user, Scanner input){
        input.nextLine();
        System.out.println("Option 1: Add Expense");
        System.out.print("\t-Category (Type of expense eg., Food, Travel etc.): ");
        String category = input.nextLine();

        System.out.print("\t-Enter the amount you expensed: ");
        double amount = input.nextDouble();

        LocalDate today = LocalDate.now();
        Expense expense = new Expense(category, amount, today);
        user.addExpense(expense);
        System.out.println("\t-Expense added successfully!");
        System.out.println();
    }

    public static void viewAllExpenses(User user){
        if (user.getExpenses().isEmpty()){
            System.out.println("\t-No expenses recorded yet.");
            return;
        }
        System.out.println("Option 2: Your expenses: ");
        for (Expense e: user.getExpenses()){
            System.out.print(e);
        }
        System.out.println();
    }

    public static void viewCategoryTotalSummary(User user){
        double budget = user.getMonthlyBudget();
        double totalSpentSofar = 0;
        System.out.println("Option 3: Total Spending");
        System.out.println("===============================================================================");
        System.out.println("Category\t\tBudget\t\t\tExpenses\t\tRemained Amount");
        System.out.println("===============================================================================");
        int i = 1;
        for (Expense e : user.getExpenses()){
            totalSpentSofar += e.getAmount();
            double remained = budget - totalSpentSofar;
            String b = i + "." + e.getCategory();

            if (b.length() < 8){
                System.out.print(b + "\t\t\t");
            } else if (b.length() < 12){
                System.out.print(b + "\t\t");
            }else {
                System.out.print(b + "\t");
            }

            System.out.print(budget + " Yen\t\t");
            System.out.print(e.getAmount() + " Yen\t\t");

            if (remained >= 0){
                System.out.println(remained + " Yen");
            } else {
                System.out.println(remained + " Yen (Limit Exceeded!)");
            }
            i++;
        }
        System.out.println("===============================================================================");
        double totalSpent = user.calculateTotalSpending();
        System.out.println("You spent " + totalSpent + " Yen in total.");
        System.out.println("===============================================================================");
        System.out.println();
    }

    public static void checkMonthlyBudget(User user){
        double totalSpending = user.calculateTotalSpending();
        double budget = user.getMonthlyBudget();
        System.out.println("Option 4: Monthly Budget Check");
        if (totalSpending > budget){
            System.out.println("\t-You have exceeded your monthly budget!");
            System.out.println("\t-The exceeded amount: " + (totalSpending - budget) + " Yen");
        } else {
            System.out.println("\t-You are within your monthly budget!");
            System.out.println("\t-Remaining budget: " + (budget - totalSpending) + " Yen");
        }
        System.out.println();
    }

    public static void viewExpenseChart(User user){
        double budget = user.getMonthlyBudget();

        System.out.println("Option 5: Expense Chart");
        for (String c : user.getCategories()){
            double sum = 0;

            for (Expense e : user.getExpenses()){
                if (e.getCategory().equalsIgnoreCase(c)){
                    sum += e.getAmount();
                }
            }
            double percentage = (sum / budget) * 100;
            System.out.print("-" + c);
            if (c.length() < 8){
                System.out.print("\t\t");
            } else if (c.length() < 12){
                System.out.print("\t");
            } else {
                System.out.print("\t");
            }

            int bars = (int) (percentage / 10);
            for (int i = 0; i < bars; i++){
                System.out.print("|");
            }
            System.out.println("  " + (int)percentage + "%");
        }
        System.out.println();
    }
}
