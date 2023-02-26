package com.example.demo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity
@Table(name = "stacks")
@Getter
@Setter
@DynamicUpdate
public class Stack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int currentSize;

    @Column(updatable = false)
    private int maxSize;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stack")
    private List<StackValue> values;
}
