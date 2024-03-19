package com.example.expensetracker.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "expense")
public class Expense {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private double amount;
    private String date; // You can use a more appropriate data type like LocalDate

    public Expense(String title, double amount, String date) {
        this.title = title;
        this.amount = amount;
        this.date = date;
    }


    public String getTitle() {
        return title;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getAmount() {
        return amount;
    }
    public String getDate() {
        return date;
    }
    // Override toString() method for debugging/logging
    @Override
    public String toString() {
        return "Expense{" +
                "title='" + title + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                '}';
    }
}
