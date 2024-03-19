package com.example.expensetracker.model;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import java.util.List;

public class ExpenseRepository {

    private ExpenseDao expenseDao;
    //private List<Expense> expenses = getAllExpenses();

    public ExpenseRepository(Context context) {
        ExpenseDatabase database = Room.inMemoryDatabaseBuilder(context, ExpenseDatabase.class).build();
        // Verify database initialization
        if (database != null) {
            Log.d("Database", "ExpenseDatabase initialized successfully");

            // Obtain ExpenseDao instance
            ExpenseDao expenseDao = database.expenseDao();

            // Verify Dao retrieval
            if (expenseDao != null) {
                Log.d("Dao", "ExpenseDao retrieved successfully");

                // Now you can use expenseDao for database operations
            } else {
                Log.e("Dao", "ExpenseDao retrieval failed: Dao is null");
            }
        } else {
            Log.e("Database", "ExpenseDatabase initialization failed: Database is null");
        }
        //expenseDao = database.expenseDao();
    }


    public List<Expense> getAllExpenses() {
        return expenseDao.getAllExpenses();
    }

    public List<Expense> getExpensesByDate(String date) {
        return expenseDao.getExpensesByDate(date);
    }

    public void addExpense(Expense expense) {
        expenseDao.insertExpense(expense);
    }

    /*public void removeExpense(int position) {
        if (position >= 0 && position < expenses.size()) {
            expenses.remove(position);
        }
    }*/
    // Add additional methods as needed
}
