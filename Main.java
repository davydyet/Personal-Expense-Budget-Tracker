//This class represents main class.
//Program start
/*Display menu option, switch case, calls to
expense manager & file handler
    1. Add Expense
    2. View All Expenses
    3. View Total Spending
    4. Check Monthly Budget
    5. View Expense Chart
    6. Save and Exit
Calls the related methods from Expense Manager or File Handler based on
user's choice.
Then, ends the program when the user chooses option 6 which is (Save and Exit).
*/


import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Hello, Welcome to the Personal Expense Budget Tracker!");
        System.out.print("Enter your name: ");
        String name = input.nextLine();

        System.out.print("Enter your monthly budget: ");
        double monthlyBudget = input.nextDouble();
        System.out.println();
        User user = new RegularUser(name, monthlyBudget);

        System.out.println("You will see the six options below. Please select one!");

        boolean running = true;
        while (running){
            System.out.print("1. Add Expense\n2. View All Expenses\n3. View Total Spending\n4. Check Monthly Budget\n5. View Expense Chart\n6. Save and Exit\nChoose the option from 1 to 6: ");
            int opt = input.nextInt();
            System.out.println("\n");
            switch (opt){
                case 1:
                    ExpenseManager.addExpense(user, input);
                    break;

                case 2:
                    ExpenseManager.viewAllExpenses(user);
                    break;

                case 3:
                    ExpenseManager.viewCategoryTotalSummary(user);
                    break;

                case 4:
                    ExpenseManager.checkMonthlyBudget(user);
                    break;

                case 5:
                    ExpenseManager.viewExpenseChart(user);
                    break;

                case 6:
                    FileHandler.saveExpenses(user, "expenses.txt");
                    running = false;
                    break;
            }
        }
    }
}
