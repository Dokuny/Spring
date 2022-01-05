package com.dokuny.todolist.controller;

import com.dokuny.todolist.dto.ResponseDTO;
import com.dokuny.todolist.dto.TodoDTO;
import com.dokuny.todolist.model.TodoEntity;
import com.dokuny.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoService service;

//    @GetMapping("/test")
//    public ResponseEntity<?> testTodo() {
//        String s = todoService.testService();
//        List<String> list = new ArrayList<>();
//        list.add(s);
//        ResponseDTO<String> response = ResponseDTO.<String>builder()
//                .data(list)
//                .build();
//
//        return ResponseEntity.ok().body(response);
//    }

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto) {
        try {
            String temporaryUserId = "temporary_user";

            TodoEntity entity = dto.toEntity();

            entity.setId(null);

            entity.setUserId(temporaryUserId);

            List<TodoEntity> entities = service.create(entity);

//            TodoDTO에서 만들어놓은 생성자를 이용한다. TodoEntity가 생성자 매개변수로 들어간다. :: 문법은 클래스 :: 클래스 메소드 형식
            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
                    .data(dtos)
                    .build();
            return ResponseEntity.ok().body(response);

        } catch (Exception e) {
//            에러가 있을 경우 dto 대신 에러메시지를 응답에 넣는다
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
                    .error(error)
                    .build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<?> retrieveTodoList() {
        String temporaryUserId = "temporary_user";

        List<TodoEntity> entities = service.retrieve(temporaryUserId);

        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
                .data(dtos)
                .build();

        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateTodoList(@RequestBody TodoDTO dto) {
        String temporaryUserId = "temporary_user";

        TodoEntity entity = dto.toEntity();

        entity.setUserId(temporaryUserId);

        List<TodoEntity> entities = service.update(entity);

        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
                .data(dtos)
                .build();

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTodo(@RequestBody TodoDTO dto) {
        try {
            String temporaryUserId = "temporary_user";

            TodoEntity entity = dto.toEntity();

            entity.setUserId(temporaryUserId);

            List<TodoEntity> entities = service.delete(entity);

            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
                    .data(dtos)
                    .build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder()
                    .error(e.getMessage())
                    .build();

            return ResponseEntity.badRequest().body(response);
        }
    }
}
