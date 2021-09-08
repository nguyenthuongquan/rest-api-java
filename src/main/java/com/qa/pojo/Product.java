package com.qa.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//POJO - plain old java object
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    String name;
    int price;
    String color;
    String[] sellerNames;

}
