package com.coloc.crud.coloc.controllers;

import com.coloc.crud.coloc.models.FlatShare;
import com.coloc.crud.coloc.services.FlatShareService;
import com.coloc.crud.coloc.services.imp.FlatShareServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/flatshares")
public class FlatShareController {


    private final FlatShareService flatShareService;
    @Autowired
    public FlatShareController(FlatShareService flatShareService) {
        this.flatShareService = flatShareService;
    }

    @GetMapping
    public ResponseEntity<List<FlatShare>> getAllFlatShares() {
        return ResponseEntity.ok(flatShareService.getAllAvailableFlatShares());
    }

    @PostMapping
    public ResponseEntity<FlatShare> createFlatShare(@RequestBody FlatShare flatShare, @RequestParam("ownerUsername") String ownerUsername) {
        return ResponseEntity.ok(flatShareService.createFlatShare(flatShare,ownerUsername));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<FlatShare>> getFlatShareById(@PathVariable Long id) {
        return ResponseEntity.ok(flatShareService.getFlatShareById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlatShare> updateFlatShare(@PathVariable Long id, @RequestBody FlatShare flatShare) {
        return ResponseEntity.ok(flatShareService.updateFlatShare(flatShare));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlatShare(@PathVariable Long id) {
        flatShareService.deleteFlatShare(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/available")
    public ResponseEntity<List<FlatShare>> getAllAvailableFlatShares() {
        return ResponseEntity.ok(flatShareService.getAllAvailableFlatShares());
    }
}
