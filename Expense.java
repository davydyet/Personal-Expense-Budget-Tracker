import java.time.LocalDate;
import java.util.SplittableRandom;

//This class represents one expense record class.
/*
List of fields
    1. amount
    2. category
    3. date

Constructor: creates a new expense
Methods: getters and a method to display expense info
 */
public class Expense {
    private String category;
    private double amount;
    private LocalDate date;

    public Expense(String category, double amount, LocalDate date){
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public String getCategory(){
        return this.category;
    }

    public double getAmount(){
        return this.amount;
    }

    public LocalDate getDate(){
        return this.date;
    }

    public String toString(){
        return "\t- " + date + " | " + category + " | " + amount + " Yen \n";
    }
}
