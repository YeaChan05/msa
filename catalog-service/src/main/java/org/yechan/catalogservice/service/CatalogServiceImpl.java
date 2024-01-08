package org.yechan.catalogservice.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yechan.catalogservice.jpa.CatalogEntity;
import org.yechan.catalogservice.jpa.CatalogRepository;

@Data
@Slf4j
@Service
public class CatalogServiceImpl implements CatalogService{
    private CatalogRepository catalogRepository;
    @Autowired
    public CatalogServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }
    
    @Override
    public Iterable<CatalogEntity> getAllCatalogs() {
        return catalogRepository.findAll();
    }
}
