package com.econome.app.repository;

import com.econome.app.model.Transaction;
import com.econome.app.projection.TransactionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // This query is used to get all transactions with their respective category, payment method, type and currency
    @Query("SELECT t.id as id, t.name as name, t.amount as amount, t.transactionDate as transactionDate, c.name as categoryName, p.name as paymentMethodName, ty.name as typeName, cu.name as currencyName FROM Transaction t JOIN t.category c JOIN t.paymentMethod p JOIN t.type ty JOIN t.currency cu")
    List<TransactionProjection> findAllProjectedBy();

    // This query is used to get the total balance of a specific month and year
    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE YEAR(t.transactionDate) = ?1 AND MONTH(t.transactionDate) = ?2")
    Double getTotalBalance(Integer year, Integer month);
}
