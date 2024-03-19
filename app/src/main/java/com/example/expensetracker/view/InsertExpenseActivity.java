package com.example.expensetracker.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.expensetracker.R;
import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.ExpenseRepository;

import java.util.Calendar;

public class InsertExpenseActivity extends AppCompatActivity {

    private ExpenseRepository expenseRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_expense);

        // Initialize ExpenseRepository
        expenseRepository = new ExpenseRepository(this);

        // Find views
        EditText titleEditText = findViewById(R.id.titleEditText);
        EditText amountEditText = findViewById(R.id.amountEditText);
        DatePicker datePicker = findViewById(R.id.datePicker);
        Button insertButton = findViewById(R.id.insertButton);

        // Set click listener for insertButton
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve data from EditText fields
                String title = titleEditText.getText().toString();
                String amountStr = amountEditText.getText().toString();

                // Retrieve date from DatePicker
                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int dayOfMonth = datePicker.getDayOfMonth();

                // Check if title and amount are not empty
                if (!title.isEmpty() && !amountStr.isEmpty()) {
                    double amount = Double.parseDouble(amountStr);

                    // Create Calendar instance and set selected date
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, month, dayOfMonth);

                    // Create Expense object
                    Expense expense = new Expense(title, amount, calendar.getTime().toString());

                    // Insert expense into database using ExpenseRepository
                    expenseRepository.addExpense(expense);

                    /*if (result != -1) {
                        // If insertion was successful, display a success message
                        Toast.makeText(InsertExpenseActivity.this, "Expense saved successfully", Toast.LENGTH_SHORT).show();
                        // Clear EditText fields after saving
                        titleEditText.setText("");
                        amountEditText.setText("");
                    } else {
                        // If insertion failed, display an error message
                        Toast.makeText(InsertExpenseActivity.this, "Failed to save expense", Toast.LENGTH_SHORT).show();
                    }*/
                } else {
                    // If title or amount is empty, show a Toast message
                    Toast.makeText(InsertExpenseActivity.this, "Please enter both title and amount", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
