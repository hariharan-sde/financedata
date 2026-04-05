package com.finance.financedata.service;

import com.finance.financedata.model.Transaction;
import com.finance.financedata.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;


    public Transaction saveTransaction(Transaction transaction) {
        return repository.save(transaction);
    }

    // Read all the transactions
    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    // Filter by what the type has been given
    public List<Transaction> getByType(String type) {
        return repository.findByType(type);
    }

    // Filter by the given category
    public List<Transaction> getByCategory(String category) {
        return repository.findByCategory(category);
    }

    // Delete the given transaction
    public void deleteTransaction(Long id) {
        repository.deleteById(id);
    }

    // Gives the total income
    public Double getTotalIncome() {
        return repository.getTotalIncome();
    }

    // Gives the total expense
    public Double getTotalExpense() {
        return repository.getTotalExpense();
    }

    // Gives the balance
    public Double getBalance() {
        Double income = getTotalIncome();
        Double expense = getTotalExpense();

        if (income == null) income = 0.0;
        if (expense == null) expense = 0.0;

        return income - expense;
    }
}