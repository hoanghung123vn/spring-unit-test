package com.example.demo;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StackMapper {
    StackValue requestToEntity(StackValueRequest request);

    StackValueResponse entityToResponse(StackValue entity);

    Stack requestToEntity(StackRequest request);

    StackResponse entityToResponse(Stack entity);
}
