package com.example.expensetracker.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Expense.class}, version = 1)
public abstract class ExpenseDatabase extends RoomDatabase {
    public abstract ExpenseDao expenseDao();
}
