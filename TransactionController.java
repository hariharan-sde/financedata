package com.finance.financedata.controller;

import com.finance.financedata.model.Transaction;
import com.finance.financedata.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;

    // 🔴 CREATE (ADMIN ONLY)
    @PostMapping
    public Transaction createTransaction(
            @RequestBody Transaction transaction,
            @RequestHeader String role) {

        if (!role.equals("ADMIN")) {
            throw new RuntimeException("Access Denied: Only ADMIN can create");
        }

        return service.saveTransaction(transaction);
    }

    // 🟢 GET ALL (ALL ROLES)
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return service.getAllTransactions();
    }

    // 🔵 FILTER BY TYPE (ADMIN + ANALYST)
    @GetMapping("/type/{type}")
    public List<Transaction> getByType(
            @PathVariable String type,
            @RequestHeader String role) {

        if (!(role.equals("ADMIN") || role.equals("ANALYST"))) {
            throw new RuntimeException("Access Denied");
        }

        return service.getByType(type);
    }

    // 🟣 FILTER BY CATEGORY (ADMIN + ANALYST)
    @GetMapping("/category/{category}")
    public List<Transaction> getByCategory(
            @PathVariable String category,
            @RequestHeader String role) {

        if (!(role.equals("ADMIN") || role.equals("ANALYST"))) {
            throw new RuntimeException("Access Denied");
        }

        return service.getByCategory(category);
    }

    // 🔴 DELETE (ADMIN ONLY)
    @DeleteMapping("/{id}")
    public String deleteTransaction(
            @PathVariable Long id,
            @RequestHeader String role) {

        if (!role.equals("ADMIN")) {
            throw new RuntimeException("Access Denied");
        }

        service.deleteTransaction(id);
        return "Deleted successfully";
    }

    // 💰 TOTAL INCOME
    @GetMapping("/summary/income")
    public Double totalIncome() {
        return service.getTotalIncome();
    }

    // 💸 TOTAL EXPENSE
    @GetMapping("/summary/expense")
    public Double totalExpense() {
        return service.getTotalExpense();
    }

    // 📊 BALANCE
    @GetMapping("/summary/balance")
    public Double balance() {
        return service.getBalance();
    }
}