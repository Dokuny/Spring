package com.dokuny.todolist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class TodoEntity {

    @Id
    @GeneratedValue(generator = "system_uuid")
    @GenericGenerator(name = "system_uuid",strategy = "uuid")
    private String id;

    private String userId;
    private String title;
    private boolean done;
}
