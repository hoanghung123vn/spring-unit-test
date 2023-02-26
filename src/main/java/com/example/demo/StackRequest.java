package com.example.demo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StackRequest {
    private int maxSize;

    private List<StackValueRequest> values;
}
