package com.finance.financedata.repository;

import com.finance.financedata.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Below are the filtering methods
    List<Transaction> findByType(String type);

    List<Transaction> findByCategory(String category);

    // Below are the Dashboard queries
    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.type = 'CREDIT'")
    Double getTotalIncome();

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.type = 'DEBIT'")
    Double getTotalExpense();
}