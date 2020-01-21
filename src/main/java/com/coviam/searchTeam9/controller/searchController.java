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
    public void  addProduct(@RequestBody  ProductInput[] productInputs){
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<ProductInput[]> response=restTemplate.getForEntity("url",ProductInput[].class);
        ProductInput[] productInputs1=response.getBody();
        for(ProductInput x:productInputs1){
            searchService.addProducts(x);
        }
    }

    @GetMapping(path = "/{searchTerm}")
    public ResponseEntity<List<ProductDTO>> getProductsByName(@PathVariable String searchTerm){
        List<Product> list=searchService.searchByCategory(searchTerm);
        List<ProductDTO> returnList=new LinkedList<>();
        for(Product x:list){
            ProductDTO productDTO=new ProductDTO();
            BeanUtils.copyProperties(x,productDTO);
            returnList.add(productDTO);
        }
        return new ResponseEntity<List<ProductDTO>>(returnList,HttpStatus.CREATED);
    }

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

}
