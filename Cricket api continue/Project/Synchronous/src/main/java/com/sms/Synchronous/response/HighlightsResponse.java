package com.sms.Synchronous.response;

import com.sms.Synchronous.model.MatchHighlights;
import com.sms.Synchronous.model.MatchInfo;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class HighlightsResponse {
    private MatchInfo matchInfo;
    private MatchHighlights matchHighlights1;
    private MatchHighlights matchHighlights2;
    //private News news;
    public HighlightsResponse(){}
}

