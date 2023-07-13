package com.sms.Synchronous.response;

import com.sms.Synchronous.model.MatchCommentary;
import com.sms.Synchronous.model.MatchInfo;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CommentaryResponse {
    //private MatchInfo matchInfo;
    private MatchCommentary matchCommentary1;
    private MatchCommentary matchCommentary2;
    //private News news;
    public CommentaryResponse(){}
}
