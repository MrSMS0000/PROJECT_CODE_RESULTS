package com.sms.Synchronous.service;

import com.sms.Synchronous.model.*;
import com.sms.Synchronous.response.CommentaryResponse;
import com.sms.Synchronous.response.HighlightsResponse;
import com.sms.Synchronous.response.HomeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    private final MongoTemplate template;

    @Autowired
    public Service(MongoTemplate template){
        this.template = template;
    }

    public HomeResponse getHomePage(){
        return new HomeResponse(
                template.findAll(Article.class,"Articles"),
                template.findAll(MatchInfo.class,"Matches"),
                template.findAll(News.class,"News"),
                template.findAll(Video.class,"Videos")
        );
    }
    public List<MatchCommentary> getMatchCommentary(String matchId){
        List<MatchCommentary> answer = new ArrayList<>();
        for (int i=0; i<10; i++)
            answer.add(template.findById(matchId, MatchCommentary.class, "Commentaries"));
        return answer;
    }
    public HighlightsResponse getMatchHighlights(String matchId){
        return new HighlightsResponse(
                template.findById(matchId, MatchInfo.class,"Matches"),
                template.findById(matchId+"_1", MatchHighlights.class, "Highlights"),
                template.findById(matchId+"_2", MatchHighlights.class, "Highlights")
        );
    }

    public List<MatchHighlights> commentary(String matchId){
        List<MatchHighlights> answer = new ArrayList<>();
        for(int i=0; i<50; i++)
            answer.add(template.findById(matchId,MatchHighlights.class,"Highlights"));
        return answer;
    }
    public MatchCommentary addArticle(MatchCommentary article){
        return template.save(article);
    }
    public MatchHighlights addArticle(MatchHighlights article){
        return template.save(article);
    }

    public MatchInfo addArticle(MatchInfo article){
        article.setEndTime(LocalTime.now().plusHours(4));
        article.setStartTime(LocalTime.now());
        article.setDate(LocalDate.now());
        return template.save(article);
    }

    public List<MatchInfo> getMatches(List<String> matchIdList) {
        List<MatchInfo> matchInfoList = new ArrayList<>();
        for (String matchId : matchIdList){
            matchInfoList.add(template.findById(matchId, MatchInfo.class,"Matches"));
        }
        return matchInfoList;
    }
}
