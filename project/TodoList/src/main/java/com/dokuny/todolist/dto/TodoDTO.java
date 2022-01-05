package com.dokuny.todolist.dto;

import com.dokuny.todolist.model.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TodoDTO {

    private String id;

    private String title;

    private boolean done;

    public TodoDTO(final TodoEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.done = entity.isDone();
    }

    public TodoEntity toEntity() {
        return TodoEntity.builder()
                .id(id)
                .title(title)
                .done(done)
                .build();
    }
}
