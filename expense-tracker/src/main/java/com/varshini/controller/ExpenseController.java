package com.varshini.controller;

import com.varshini.entity.Expense;
import com.varshini.repository.ExpenseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/expenses")
@CrossOrigin
public class ExpenseController {

    private final ExpenseRepository repo;

    public ExpenseController(ExpenseRepository repo) {
        this.repo = repo;
    }

    // CREATE
    @PostMapping
    public Expense add(@RequestBody Expense e) {
        return repo.save(e);
    }

    // READ
    @GetMapping
    public List<Expense> getAll() {
        return repo.findAll();
    }

    // UPDATE (PATCH)
    @PatchMapping("/{id}")
    public ResponseEntity<Expense> updateAmount(
            @PathVariable Long id,
            @RequestBody Expense req) {

        return repo.findById(id)
                .map(e -> {
                    if (req.getAmount() != null) {
                        e.setAmount(req.getAmount());
                    }
                    return ResponseEntity.ok(repo.save(e));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}




