package com.sms.Synchronous.response;

import com.sms.Synchronous.model.Article;
import com.sms.Synchronous.model.MatchInfo;
import com.sms.Synchronous.model.News;
import com.sms.Synchronous.model.Video;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class HomeResponse {
    List<Article> articles;
    List<MatchInfo> matches;
    List<News> news;
    List<Video> videos;
    public HomeResponse(){}
}
