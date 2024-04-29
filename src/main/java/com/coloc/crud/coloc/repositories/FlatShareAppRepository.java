package com.coloc.crud.coloc.repositories;

import com.coloc.crud.coloc.models.FlatShareApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlatShareAppRepository extends JpaRepository<FlatShareApplication, Long> {
    @Query("SELECT fsa FROM FlatShareApplication fsa WHERE fsa.flatShare.idFlat = :idFlat")
    List<FlatShareApplication> findApplicationsByFlatShareId(Long idFlat);
}
