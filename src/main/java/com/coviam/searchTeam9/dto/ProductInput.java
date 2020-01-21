package com.coviam.searchTeam9.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter @Setter
public class ProductInput {
    private String productId;
    private String productName;
    private String description;
    private Map<String, String> attributes;
    private String categoryName;
    private double productRating;
    private double price;
    private String url1;
    private String url2;
    private String url3;
}
