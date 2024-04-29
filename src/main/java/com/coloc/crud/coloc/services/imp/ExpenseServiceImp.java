package com.coloc.crud.coloc.services.imp;

import com.coloc.crud.coloc.models.Category;
import com.coloc.crud.coloc.models.Expense;
import com.coloc.crud.coloc.models.FlatShare;
import com.coloc.crud.coloc.repositories.CategoryRepository;
import com.coloc.crud.coloc.repositories.ExpenseRepository;
import com.coloc.crud.coloc.repositories.FlatShareRepository;
import com.coloc.crud.coloc.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImp implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    private final FlatShareRepository flatShareRepository;

    @Autowired
    public ExpenseServiceImp(ExpenseRepository expenseRepository, CategoryRepository categoryRepository, FlatShareRepository flatShareRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
        this.flatShareRepository = flatShareRepository;
    }

    @Override
    public Expense createExpense(Expense expense, String nameCat, Long idFlat) {
        Category category = categoryRepository.findByName(nameCat);
        FlatShare flatShare = flatShareRepository.findById(idFlat).orElse(null);


        expense.setCategory(category);
        expense.setFlatShareExpenses(flatShare);

        category.getExpenses().add(expense);
        assert flatShare != null;
        flatShare.getExpenses().add(expense);

        expenseRepository.save(expense);

        return expense;
    }

    @Override
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
    }

    @Override
    public List<Expense> getAllExpenses(Long flatShareId) {
        return expenseRepository.findExpensesByFlatShareId(flatShareId);
    }

    @Override
    public Expense updateExpense(Long id, Expense expense) {
        Expense existingExpense = getExpenseById(id);
        existingExpense.setDescriptionE(expense.getDescriptionE());
        existingExpense.setAmount(expense.getAmount());
        existingExpense.setReceipt(expense.getReceipt());
        existingExpense.setCategory(expense.getCategory());
        return expenseRepository.save(existingExpense);
    }

    @Override
    public void deleteExpense(Long id) {
        Expense expense = getExpenseById(id);
        expenseRepository.delete(expense);
    }
}
