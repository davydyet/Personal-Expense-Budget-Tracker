//This class represents the actual user of the program.
/*
This class extends User class, stores monthly budget, and
implements abstract method.
 */
public class RegularUser extends User {
    public RegularUser(String name, double monthlyBudget){
        super(name, monthlyBudget);
    }

    @Override
    public double calculateTotalSpending() {
//        double total = 0;
//        for (Expense e: expenses){
//            total += e.getAmount();
//        }
//        return total;
//    }
        //Calculates the total amount spent by the user using Java Streams
        return expenses.stream().mapToDouble(e -> e.getAmount())
                .sum();
    }
}
