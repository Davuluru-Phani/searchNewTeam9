package com.coviam.searchTeam9.controller;

import com.coviam.searchTeam9.document.Product;
import com.coviam.searchTeam9.dto.ProductDTO;
import com.coviam.searchTeam9.dto.ProductInput;
import com.coviam.searchTeam9.service.SearchService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class searchController {

    @Autowired
    SearchService searchService;

//    @GetMapping(path = "/{name}")
//    public ResponseEntity<List<ProductDTO>> getProductsByName(@PathVariable String name){
//        List<ProductDTO> list=new LinkedList<ProductDTO>();
//        for(Product p:searchService.searchByName(name)){
//            ProductDTO productDTO=new ProductDTO();
//            BeanUtils.copyProperties(p,productDTO);
//            list.add(productDTO);
//        }
//        return new ResponseEntity<List<ProductDTO>>(list,HttpStatus.CREATED);
//    }
//
//    @GetMapping(path = "/{category}")
//    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable String category){
//        List<ProductDTO> list=new LinkedList<ProductDTO>();
//        for(Product p:searchService.searchByCategory(category)){
//            ProductDTO productDTO=new ProductDTO();
//            BeanUtils.copyProperties(p,productDTO);
//            list.add(productDTO);
//        }
//        return new ResponseEntity<List<ProductDTO>>(list,HttpStatus.CREATED);
//    }
//
//    @GetMapping(path = "/{description}")
//    public ResponseEntity<List<ProductDTO>> getProductsByDescription(@PathVariable String description){
//        List<ProductDTO> list=new LinkedList<ProductDTO>();
//        for(Product p:searchService.searchByDescription(description)){
//            ProductDTO productDTO=new ProductDTO();
//            BeanUtils.copyProperties(p,productDTO);
//            list.add(productDTO);
//        }
//        return new ResponseEntity<List<ProductDTO>>(list,HttpStatus.CREATED);
//    }

    @PostMapping(path = "/addProduct")
    public void addProduct(/*@RequestBody  List<ProductInput> productInputs*/) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ProductInput[]> response = restTemplate.getForEntity("http://10.177.7.28:8082/product/get", ProductInput[].class);
        List<ProductInput> productInputs1 = Arrays.asList(response.getBody());
        for (ProductInput x : productInputs1) {
            searchService.addProducts(x);
        }
    }

    @PostMapping(path = "/addProd")
    public void addProduct(@RequestBody  ProductInput productInputs) {

        searchService.addProducts(productInputs);
    }

//    @GetMapping(path = "/{searchTerm}")
//    public ResponseEntity<List<ProductDTO>> getProductsBySearchName(@PathVariable String searchTerm){
//        List<ProductDTO> returnList=new LinkedList<>();
//        for(Product p:searchService.searchByCustomQuery(searchTerm)){
//            ProductDTO productDTO=new ProductDTO();
//            BeanUtils.copyProperties(p,productDTO);
//            returnList.add(productDTO);
//        }
//        return new ResponseEntity<List<ProductDTO>>(returnList,HttpStatus.CREATED);
//    }

    @GetMapping(path = "/byCategory/{category}")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable String category) {
        List<ProductDTO> list = new LinkedList<ProductDTO>();
        for (Product p : searchService.searchByCategory(category)) {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(p, productDTO);
            productDTO.setProductName(p.getName());
            list.add(productDTO);
        }
        return new ResponseEntity<List<ProductDTO>>(list, HttpStatus.CREATED);
    }

    @GetMapping(path = "/byName/{name}")
    public ResponseEntity<List<ProductDTO>> getProductsByName(@PathVariable String name) {
        List<ProductDTO> list = new LinkedList<ProductDTO>();
        for (Product p : searchService.searchByName(name)) {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(p, productDTO);
            productDTO.setProductName(p.getName());
            list.add(productDTO);
        }
        return new ResponseEntity<List<ProductDTO>>(list, HttpStatus.CREATED);
    }

}
