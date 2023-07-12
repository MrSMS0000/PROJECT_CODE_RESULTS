package com.sms.ReactiveJavaDriver.model;

import lombok.*;
import org.springframework.lang.NonNull;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Entity {
    @NonNull
    private String id;
    private Integer field1;
    private String field2;
    private List<Double> field3;
    public Entity() {
    }
}