package com.sms.Synchronous.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Document(collection = "Articles")
public class Article {
    private String imageLink;
    private String title;
    private String content;
    public Article(){}
}
