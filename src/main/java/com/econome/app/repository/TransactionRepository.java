package com.econome.app.repository;

import com.econome.app.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.econome.app.projection.BalanceProjection;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE YEAR(t.transactionDate) = :year")
    List<Transaction> findAllByYear(@Param("year") int year);

    // This query is used to get the total expense and income of a specific month and year and their respective currencies
    //Still wrong for now - keine Berücksichtung von verschiedenen Currencies
    @Query("SELECT t.amount as amount, currency.name as currency, t.transactionDate as transactionDate FROM Transaction t WHERE YEAR(t.transactionDate) = ?1 AND MONTH(t.transactionDate) = ?2")
    List<BalanceProjection> getTotalBalance(Integer year, Integer month);


    @Query("SELECT DISTINCT YEAR(t.transactionDate) FROM Transaction t ORDER BY YEAR(t.transactionDate)")
    List<Integer> getYears();

    @Query("SELECT DISTINCT MONTH(t.transactionDate) FROM Transaction t ORDER BY MONTH(t.transactionDate)")
    List<Integer> getMonths();

    @Query("SELECT DISTINCT MONTH(t.transactionDate) FROM Transaction t WHERE YEAR(t.transactionDate) = ?1 ORDER BY MONTH(t.transactionDate)")
    List<Integer> getMonthsByYear(Integer year);
}
