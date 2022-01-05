package com.dokuny.todolist.service;

import com.dokuny.todolist.model.TodoEntity;
import com.dokuny.todolist.persistence.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository repository;

    public List<TodoEntity> create(final TodoEntity entity) {
//            validations
        validate(entity);

//        entity save
        repository.save(entity);
        log.info("Entity Id : {} is saved.",entity.getId());

        return repository.findByUserId(entity.getUserId());
    }

    public List<TodoEntity> retrieve(final String userId) {
        return repository.findByUserId(userId);
    }

    public List<TodoEntity> update(final TodoEntity entity) {
        validate(entity);

        Optional<TodoEntity> original = repository.findById(entity.getId());
        original.ifPresent(todo ->{
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());
            repository.save(todo);
        } );

        return retrieve(entity.getUserId());
    }

    public List<TodoEntity> delete(final TodoEntity entity) {
        validate(entity);

        try {
            repository.delete(entity);

        } catch (Exception e) {
            log.error("error deleting entity",entity.getId(),e);
            throw new RuntimeException("error deleting entity " + entity.getId());
        }
        return retrieve(entity.getUserId());
    }


    private void validate(TodoEntity entity) {
        if (entity == null) {
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null.");
        }
        if (entity.getUserId() == null) {
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }
}
