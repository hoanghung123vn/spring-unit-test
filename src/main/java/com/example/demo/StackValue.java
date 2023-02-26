package com.example.demo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "stack_values")
@Getter
@Setter
public class StackValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int val;

    @ManyToOne
    @JoinColumn(name = "stack_id")
    private Stack stack;
}
