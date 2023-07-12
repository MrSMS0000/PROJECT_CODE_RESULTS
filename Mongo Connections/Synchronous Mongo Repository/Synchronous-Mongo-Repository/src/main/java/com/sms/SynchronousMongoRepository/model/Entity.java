package com.sms.SynchronousMongoRepository.model;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Document(collection = "api")
public class Entity {
    @NonNull
    private String id;
    private Integer field1;
    private String field2;
    private List<Double> field3;
}
