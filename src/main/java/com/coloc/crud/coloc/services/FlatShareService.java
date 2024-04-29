package com.coloc.crud.coloc.services;

import com.coloc.crud.coloc.models.FlatShare;
import com.coloc.crud.coloc.repositories.FlatShareRepository;

import java.util.List;
import java.util.Optional;

public interface FlatShareService {

    FlatShare createFlatShare(FlatShare flatShare, String ownerUsername);
    FlatShare updateFlatShare(FlatShare flatShare);
    void deleteFlatShare(Long flatShareId);
    Optional<FlatShare> getFlatShareById(Long flatShareId);
    List<FlatShare> getAllAvailableFlatShares();
    void approveFlatShareApplication(Long flatShareId, Long applicationId);


}
