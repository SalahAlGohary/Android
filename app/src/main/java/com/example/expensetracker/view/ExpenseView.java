package com.example.expensetracker.view;

import com.example.expensetracker.model.Expense;

import java.util.List;

public interface ExpenseView {
    void showExpenses(List<Expense> expenses);
    void showAddSuccessMessage();
}
