//This class represents a general user of the system.
/*
This is an abstract class, stores user's name, monthly budget, list of expenses,
 and expense categories.
 Then, declares abstract methods.

 */

import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.Set;

public abstract class User implements Reportable{
    protected String name;
    protected double monthlyBudget;
    protected ArrayList<Expense> expenses;
    protected Set<String> categories;

    //Constructor
    protected User(String name, double monthlyBudget){
        this.name = name;
        this.monthlyBudget = monthlyBudget;
        this.expenses = new ArrayList<>();
        this.categories = new HashSet<>();
    }

    //Add an expense to the user's list and update the category set.
    public void addExpense(Expense e){
        expenses.add(e);
        categories.add(e.getCategory());
    }

    //Get the expense list
    public ArrayList<Expense> getExpenses(){
        return this.expenses;
    }

    //Get the set of categories
    public Set<String> getCategories(){
        return categories;
    }

    //Get the user's name
    public String getName(){
        return this.name;
    }

    //Get the user's monthly budget
    public double getMonthlyBudget() {
        return this.monthlyBudget;
    }

    //Abstract method
    public abstract double calculateTotalSpending();

    //Implement method from interface with no parameter
    @Override
    public void generateReport() {
        System.out.println("Expense Report for " + name);
        for (Expense e: expenses){
            System.out.println(e);
        }
    }

    //Implement method from interface for writing the file
    public void generateReport(PrintWriter writer) {
        writer.println("Expense Report for " + name);
        writer.println("-Monthly Budget: " + monthlyBudget + " Yen");
        writer.println("-Expenses:");
        for (Expense e : expenses) {
            writer.println("\t- " + e.getDate() + " | " + e.getCategory() + " | " + e.getAmount() + " Yen");
        }
        writer.println();
    }
}
