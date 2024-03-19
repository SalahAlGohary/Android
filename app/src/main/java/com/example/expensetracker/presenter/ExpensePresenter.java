package com.example.expensetracker.presenter;

import android.content.Context;

import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.ExpenseRepository;
import com.example.expensetracker.view.ExpenseView;

import java.util.List;
import java.util.Locale;

public class ExpensePresenter {

    private final ExpenseView view;
    private final ExpenseRepository repository;

    public ExpensePresenter(ExpenseView view, Context context) {
        this.view = view;
        this.repository = new ExpenseRepository(context);
    }

    public void addExpense(String title, double amount, int year, int month, int dayOfMonth) {
        String date = formatDate(year, month, dayOfMonth);
        repository.addExpense(new Expense(title, amount, date));
        loadExpenses(date);
        view.showAddSuccessMessage();
    }

    private String formatDate(int year, int month, int dayOfMonth) {
        // Format the date into a string representation (e.g., YYYY-MM-DD)
        return String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month + 1, dayOfMonth);
    }

    public void loadExpenses(String date) {
        List<Expense> expenses = repository.getExpensesByDate(date);
        view.showExpenses(expenses);
    }
    public void loadAllExpenses() {
        List<Expense> expenses = repository.getAllExpenses();
        view.showExpenses(expenses);
    }

    /*public void removeExpense(int position) {
        repository.removeExpense(position);
        // Optionally, notify the view or perform other actions
    }*/
}
