package com.sms.Synchronous.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Document(collection = "Highlights")
public class MatchHighlights {
    @MongoId
    private String matchId;
    private List<String> highlights;
    public MatchHighlights(){}
}
