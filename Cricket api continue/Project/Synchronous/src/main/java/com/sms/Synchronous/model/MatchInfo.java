package com.sms.Synchronous.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Document(collection = "Matches")
public class MatchInfo {
    private String matchId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String venue;
    private String country1;
    private String country2;
    private Integer run1;
    private Integer run2;
    private Integer wicket1;
    private Integer wicket2;
    private Double overs1;
    private Double overs2;
    private String series;
    private String playerOfTheMatch;
    private String winnerCountry;
    public MatchInfo(){}
}
