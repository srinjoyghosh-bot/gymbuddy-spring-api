package com.joy.gymbuddy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
public class Random {
    @Id
    @GeneratedValue
    public Integer id;

    public String name;
}
