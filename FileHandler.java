import java.io.PrintWriter;
import java.io.IOException;

//This class represents handling file reading and writing.
/*
Save the user's expense record and report to a text file.
But, in the future, loading expenses from a file could be included, but for now it is not implemented yet.

- Writes the user's expenses and summary information to the specified file.
- Includes the full expense report, a table of categories with budget vs. expenses,
  total spending, and an expense percentage chart.
- Prints a confirmation message if saving succeeds, or an error message if it fails.
 */
public class FileHandler {
    public static void saveExpenses(User user, String filename){
        try{
            PrintWriter writer = new PrintWriter("expenses.txt");

            user.generateReport(writer);
            //writer.println();
            writer.println(("-Expenses Report Table"));
            writer.println("===============================================================================");
            writer.println("Category\t\tBudget\t\t\tExpenses\t\tRemained Amount");
            writer.println("===============================================================================");
            double budget = user.getMonthlyBudget();
            double totalSpentSoFar = 0;
            int i = 1;

            for (Expense e : user.getExpenses()) {
                totalSpentSoFar += e.getAmount();
                double remained = budget - totalSpentSoFar;
                String b = i + "." + e.getCategory();

                if (b.length() < 8) {
                    writer.print(b + "\t\t\t");
                } else if (b.length() < 12) {
                    writer.print(b + "\t\t");
                } else {
                    writer.print(b + "\t");
                }
                writer.print(budget + " Yen\t\t");
                writer.print(e.getAmount() + " Yen\t\t");

                if (remained >= 0) {
                    writer.println(remained + " Yen");
                } else {
                    writer.println(remained + " Yen (Limit Exceeded!)");
                }
                i++;
            }
            writer.println("===============================================================================");

            double totalSpent = user.calculateTotalSpending();
            writer.println("You spent " + totalSpent + " Yen in total.");
            writer.println("===============================================================================");
            //writer.println();

            writer.println("\n-Expense Percentage Chart");
            for (String c : user.getCategories()){
                double sum = 0;

                for (Expense e : user.getExpenses()){
                    if (e.getCategory().equalsIgnoreCase(c)){
                        sum += e.getAmount();
                    }
                }
                double percentage = (sum / budget) * 100;
                writer.print(c);
                if (c.length() < 8){
                    writer.print("\t\t");
                } else if (c.length() < 12){
                    writer.print("\t");
                } else {
                    writer.print("\t");
                }

                int bars = (int) (percentage / 10);
                for (int j = 0; j < bars; j++){
                    writer.print("|");
                }
                writer.println("  " + (int)percentage + "%");
            }
            writer.println();
            writer.close();
            System.out.println("Expenses saved successfully!");
        } catch (IOException e){
            System.out.println("Error found while saving the file.");
        }
    }
}
