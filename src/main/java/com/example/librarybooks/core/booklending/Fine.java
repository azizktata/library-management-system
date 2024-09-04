package com.example.librarybooks.core.booklending;

public class Fine {
    private double amount;

    public Fine() {
    }

    public Fine(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
