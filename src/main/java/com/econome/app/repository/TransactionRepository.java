package com.econome.app.repository;

import com.econome.app.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE YEAR(t.transactionDate) = :year")
    List<Transaction> findAllByYear(@Param("year") int year);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE YEAR(t.transactionDate) = ?1 AND MONTH(t.transactionDate) = ?2")
    Double getTotalBalance(Integer year, Integer month);

    @Query("SELECT DISTINCT YEAR(t.transactionDate) FROM Transaction t ORDER BY YEAR(t.transactionDate)")
    List<Integer> getYears();

    @Query("SELECT DISTINCT MONTH(t.transactionDate) FROM Transaction t ORDER BY MONTH(t.transactionDate)")
    List<Integer> getMonths();

    @Query("SELECT DISTINCT MONTH(t.transactionDate) FROM Transaction t WHERE YEAR(t.transactionDate) = ?1 ORDER BY MONTH(t.transactionDate)")
    List<Integer> getMonthsByYear(Integer year);
}
