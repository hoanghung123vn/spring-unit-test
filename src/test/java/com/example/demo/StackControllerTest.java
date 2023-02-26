package com.example.demo;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({StackMapperImpl.class, StackController.class})
class StackControllerTest {
    @Autowired
    StackController stackController;

    @Autowired
    StackRepository repository;

    @BeforeEach
    void setUp() {
        var stackValues1 = List.of(
                StackValue.builder().val(1).build(),
                StackValue.builder().val(2).build(),
                StackValue.builder().val(3).build(),
                StackValue.builder().val(4).build()
        );
        var stackValues2 = List.of(
                StackValue.builder().val(1).build(),
                StackValue.builder().val(2).build(),
                StackValue.builder().val(3).build(),
                StackValue.builder().val(4).build()
        );
        var stackValues3 = List.of(
                StackValue.builder().val(1).build(),
                StackValue.builder().val(2).build(),
                StackValue.builder().val(3).build(),
                StackValue.builder().val(4).build()
        );
        var stackValues4 = List.of(
                StackValue.builder().val(1).build(),
                StackValue.builder().val(2).build(),
                StackValue.builder().val(3).build(),
                StackValue.builder().val(4).build()
        );
        var stacks = List.of(
                Stack.builder().maxSize(10).currentSize(4).values(stackValues1).build(),
                Stack.builder().maxSize(10).currentSize(4).values(stackValues2).build(),
                Stack.builder().maxSize(10).currentSize(4).values(stackValues3).build(),
                Stack.builder().maxSize(10).currentSize(4).values(stackValues4).build()
        );
        repository.saveAll(stacks);
    }

    @Test
    void findAll() {
        var stacks = stackController.findAll();
        assertNotNull(stacks);
        assertEquals(4, stacks.size());
        assertEquals(4, stacks.get(0).getCurrentSize());
    }

    @Test
    void getById() {
        var stacks = stackController.findAll();
        var stack0 = stacks.get(0);
        var stack = stackController.getById(stack0.getId());
        assertNotNull(stack);
        assertEquals(4, stack.getCurrentSize());
        assertEquals(10, stack.getMaxSize());
        assertThrows(EntityNotFoundException.class, () -> stackController.getById(0));
    }

    @Test
    void addStack() {
    }

    @Test
    void delete() {
    }

    @Test
    void push() {
    }

    @Test
    void pop() {
    }
}