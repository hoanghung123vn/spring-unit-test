package com.example.demo;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("stacks")
@AllArgsConstructor
public class StackController {
    private final StackRepository repository;

    private final StackMapper mapper;
    @GetMapping
    public List<StackResponse> findAll() {
        var stacks = repository.findAll();
        return stacks.stream().map(mapper::entityToResponse).toList();
    }

    @GetMapping("{id}")
    public StackResponse getById(@PathVariable(name = "id") int id) {
        var stack = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return mapper.entityToResponse(stack);
    }

    @PostMapping
    public StackResponse addStack(@RequestBody StackRequest request) {
        if (request.getValues().size() > request.getMaxSize())
            throw new RuntimeException("stack max size");
        var stack = mapper.requestToEntity(request);
        stack.setCurrentSize(stack.getValues().size());
        repository.save(stack);
        return mapper.entityToResponse(stack);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") int id) {
        var stack = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        repository.delete(stack);
    }

    @PutMapping("{id}/push")
    public StackResponse push(@PathVariable(name = "id") int id, @RequestBody StackValueRequest request) {
        var stack = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (stack.getCurrentSize() >= stack.getMaxSize())
            throw new RuntimeException("stack size limit");
        var stackValue = mapper.requestToEntity(request);
        stack.getValues().add(stackValue);
        stack.setCurrentSize(stack.getCurrentSize() + 1);
        stackValue.setStack(stack);
        repository.save(stack);
        return mapper.entityToResponse(stack);
    }

    @PutMapping("{id}/pop")
    public StackResponse pop(@PathVariable(name = "id") int id) {
        var stack = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (stack.getCurrentSize() == 0)
            throw new RuntimeException("stack empty");
        stack.getValues().get(stack.getCurrentSize() - 1).setStack(null);
        stack.setCurrentSize(stack.getCurrentSize() - 1);
        repository.save(stack);
        return mapper.entityToResponse(stack);
    }
}
