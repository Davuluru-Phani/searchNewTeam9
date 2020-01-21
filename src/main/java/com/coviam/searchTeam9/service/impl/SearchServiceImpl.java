package com.coviam.searchTeam9.service.impl;

import com.coviam.searchTeam9.document.Product;
import com.coviam.searchTeam9.dto.ProductDTO;
import com.coviam.searchTeam9.repository.SearchRepository;
import com.coviam.searchTeam9.service.SearchService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    SearchRepository searchRepository;

    @Override
    public List<Product> searchByName(String productName){
        return searchRepository.findByName(productName);
    }

//    public List<Product> searchByCategory(String category){
//        return searchRepository.findByCategory(category);
//    }
//
//    public List<Product> searchByDescription(String description){
//        return searchRepository.findByName(description);
//    }
    public void addProducts(Product product){
        Product product1=new Product();
        product1.setProductId(product.getProductId());
        product1.setProductName(product.getProductName());
        product1.setDescription(product.getDescription());
        StringJoiner str = new StringJoiner(", ");

        searchRepository.save(product);
    }

    @Override
    public List<Product> searchByCategory(String categoryName) {
//        return searchRepository.findByCategory(categoryName);
        return Collections.emptyList();
    }


    @Override
    public List<Product> searchByCustomQuery(String searchTerm) {
        return searchRepository.findByCustomQuery(searchTerm);
    }

}
