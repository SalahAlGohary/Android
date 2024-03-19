package com.example.expensetracker.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensetracker.R;
import com.example.expensetracker.model.Expense;
import com.example.expensetracker.presenter.ExpensePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ExpenseActivity extends AppCompatActivity implements ExpenseView {

    private ExpensePresenter presenter;
    private RecyclerView recyclerView;
    private ExpenseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        presenter = new ExpensePresenter(this, getApplicationContext());

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.expensesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ExpenseAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        // Load all expenses button click listener
        Button loadAllExpensesButton = findViewById(R.id.loadAllExpensesButton);
        loadAllExpensesButton.setOnClickListener(v -> presenter.loadAllExpenses());

        // Load expenses by date button click listener
        Button loadExpensesByDateButton = findViewById(R.id.loadExpensesByDateButton);
        loadExpensesByDateButton.setOnClickListener(v -> showDatePickerDialog());

        // Insert new expense button click listener
        Button insertExpenseButton = findViewById(R.id.insertExpenseButton);
        insertExpenseButton.setOnClickListener(v -> {
            // Navigate to insert expense activity or show a dialog to insert expense
            // For simplicity, I'm assuming you navigate to another activity to insert a new expense
            startActivity(new Intent(ExpenseActivity.this, InsertExpenseActivity.class));
        });
    }

    @Override
    public void showExpenses(List<Expense> expenses) {
        adapter.updateData(expenses);
    }

    // Method to show date picker dialog
    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            String selectedDate = formatDate(year, month, dayOfMonth);
            presenter.loadExpenses(selectedDate);
        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    // Helper method to format date
    private String formatDate(int year, int month, int dayOfMonth) {
        return String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month + 1, dayOfMonth);
    }
    @Override
    public void showAddSuccessMessage() {
        Toast.makeText(this, "Expense added successfully", Toast.LENGTH_SHORT).show();
    }

}
