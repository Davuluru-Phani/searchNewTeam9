package com.coviam.searchTeam9.service.impl;

import com.coviam.searchTeam9.document.Product;
import com.coviam.searchTeam9.dto.ProductDTO;
import com.coviam.searchTeam9.dto.ProductInput;
import com.coviam.searchTeam9.repository.SearchRepository;
import com.coviam.searchTeam9.service.SearchService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    SearchRepository searchRepository;

//    @Override
//    public List<Product> searchByName(String productName) {
//        return searchRepository.findByName(productName);
//    }

    //    public List<Product> searchByCategory(String category){
//        return searchRepository.findByCategory(category);
//    }
//
//    public List<Product> searchByDescription(String description){
//        return searchRepository.findByName(description);
//    }
    public void addProducts(ProductInput productInput) {
        Product product = new Product();
        product.setProductId(productInput.getProductId());
        product.setProductName(productInput.getProductName());
        product.setDescription(productInput.getDescription());
        StringJoiner str = new StringJoiner(", ");
        for (Map.Entry<String, String> entry : productInput.getAttributes().entrySet()) {
            str.add(entry.getKey() + " " + entry.getValue());
        }
        product.setSearchFields(str.toString());
        product.setCategoryName(productInput.getCategoryName());
        product.setProductRating(String.valueOf(productInput.getProductRating()));
        product.setPrice(String.valueOf(productInput.getPrice()));
        product.setUrl1(productInput.getUrl1());
        product.setUrl2(productInput.getUrl2());
        product.setUrl3(productInput.getUrl3());
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
