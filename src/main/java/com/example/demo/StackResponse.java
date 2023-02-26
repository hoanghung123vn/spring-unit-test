package com.example.demo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StackResponse {
    private int id;

    private int currentSize;

    private int maxSize;

    private List<StackValueResponse> values;
}
