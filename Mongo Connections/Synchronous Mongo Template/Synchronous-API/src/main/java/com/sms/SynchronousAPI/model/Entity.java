package com.sms.SynchronousAPI.model;

import com.sms.SynchronousAPI.configuration.AppConfig;
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
@Document(collection = AppConfig.collectionName)
public class Entity {
    @NonNull
    @Indexed(unique = true)
    private String id;
    private Integer field1;
    private String field2;
    private List<Double> field3;
}
