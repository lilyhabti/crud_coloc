package com.coloc.crud.coloc.services;

import com.coloc.crud.coloc.models.FlatShareApplication;

import java.util.List;

public interface FlatShareAppService {

    List<FlatShareApplication> getApplicationsByFlatShareId(Long flatShareId);
    FlatShareApplication sendFlatShareApplication(String username, Long flatShareId);
}
