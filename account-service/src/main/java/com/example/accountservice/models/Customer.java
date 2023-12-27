package com.example.accountservice.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter

public class Customer implements Serializable {
    private Long id;
    private String fistName;
    private String lastName ;
    private String email;
}
