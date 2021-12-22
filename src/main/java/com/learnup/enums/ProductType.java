package com.learnup.enums;

import lombok.Getter;
import org.codehaus.groovy.vmplugin.v8.IndyGuardsFiltersAndSignatures;

public enum ProductType {
    ORANGE(20649, "ORANGE", 8, "Food"),
    BLACKBERRIES(20650, "Blackberries", 0, "Food"),
    NOTHING(20651,null,null,"Food");


    @Getter
    private Integer id;
    @Getter
    private String title;
    @Getter
    private Integer price;
    @Getter
    private String category;

    ProductType(Integer id, String title, Integer price, String category) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.category = category;
    }
    }
