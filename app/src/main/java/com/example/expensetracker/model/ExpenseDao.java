package com.example.expensetracker.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExpenseDao {

    @Insert
    void insertExpense(Expense expense);

    @Delete
    void deleteExpense(Expense expense);

    @Query("SELECT * FROM Expense")
    List<Expense> getAllExpenses();

    @Query("SELECT SUM(amount) FROM Expense")
    double getTotalExpenseAmount();

    @Query("SELECT * FROM Expense WHERE date = :date")
    List<Expense> getExpensesByDate(String date);
}
