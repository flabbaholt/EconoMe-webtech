package com.econome.app.repository;

import com.econome.app.model.Transaction;
import com.econome.app.projection.TransactionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.econome.app.projection.BalanceProjection;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // This query is used to get all transactions with their respective category, payment method, type and currency
    @Query("SELECT t.id as id, t.name as name, t.amount as amount, t.transactionDate as transactionDate, c.name as categoryName, p.name as paymentMethodName, ty.name as typeName, cu.name as currencyName FROM Transaction t JOIN t.category c JOIN t.paymentMethod p JOIN t.type ty JOIN t.currency cu")
    List<TransactionProjection> findAllProjectedBy();

    // This query is used to get the total expense and income of a specific month and year and their respective currencies
    //Still wrong for now - keine Berücksichtung von verschiedenen Currencies
    @Query("SELECT t.amount as amount, currency.name as currency, t.transactionDate as transactionDate FROM Transaction t WHERE YEAR(t.transactionDate) = ?1 AND MONTH(t.transactionDate) = ?2")
    List<BalanceProjection> getTotalBalance(Integer year, Integer month);

    // This query is used to get all years with transactions
    @Query("SELECT DISTINCT YEAR(t.transactionDate) FROM Transaction t ORDER BY YEAR(t.transactionDate)")
    List<Integer> getYears();

    // This query is used to get all months with transactions
    @Query("SELECT DISTINCT MONTH(t.transactionDate) FROM Transaction t ORDER BY MONTH(t.transactionDate)")
    List<Integer> getMonths();

    // This query is used to get all months of a specific year with transactions
    @Query("SELECT DISTINCT MONTH(t.transactionDate) FROM Transaction t WHERE YEAR(t.transactionDate) = ?1 ORDER BY MONTH(t.transactionDate)")
    List<Integer> getMonthsByYear(Integer year);

    // This query is used to get all transactions of a specific year
    @Query("SELECT t FROM Transaction t WHERE YEAR(t.transactionDate) = :year")
    List<Transaction> findAllByYear(@Param("year") int year);
}
