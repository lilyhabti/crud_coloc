package com.coloc.crud.coloc.repositories;

import com.coloc.crud.coloc.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query("SELECT e FROM Expense e WHERE e.flatShareExpenses.idFlat = :idFlat")
    List<Expense> findExpensesByFlatShareId(Long idFlat);
}
