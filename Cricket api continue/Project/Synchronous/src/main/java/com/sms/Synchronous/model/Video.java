package com.sms.Synchronous.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Document(collection = "Videos")
public class Video {
    private String videoLink;
    private String thumbnailLink;
    private String title;
    public Video(){}
}
