package com.qa.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//POJO - plain old java object
@Data               //For all getter / setter / toString()....
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String name;
    String job;
    String id;
    String createdAt;

}
