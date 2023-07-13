package com.sms.Synchronous.controller;

import com.sms.Synchronous.model.*;
import com.sms.Synchronous.response.CommentaryResponse;
import com.sms.Synchronous.response.HighlightsResponse;
import com.sms.Synchronous.response.HomeResponse;
import com.sms.Synchronous.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    private final Service service;

    @Autowired
    public Controller(Service service){
        this.service = service;
    }
    @GetMapping("/home")
    public HomeResponse getResponse(){
        return service.getHomePage();
    }
    @GetMapping("/commentary/{matchId}")
    public List<MatchCommentary> getCommentary(@PathVariable String matchId){
        return service.getMatchCommentary(matchId);
    }

    @GetMapping("/highlights/{matchId}")
    public HighlightsResponse getHighlights(@PathVariable String matchId){
        return service.getMatchHighlights(matchId);
    }

    @GetMapping("/matches")
    public List<MatchInfo> getMatches(@RequestBody List<String> matchIdList){
        return service.getMatches(matchIdList);
    }
    @GetMapping("/com/{matchId}")
    public List<MatchHighlights> getCom(@PathVariable String matchId){
        return service.commentary(matchId);
    }
    @PostMapping("/add")
    public MatchCommentary addArticle(@RequestBody MatchCommentary article){
        return service.addArticle(article);
    }
}
// ["64a552e30d24d27ca1dbaa9c","64a5531f0d24d27ca1dbaa9e","64a5531f0d24d27ca1dbaaa0","64a5531f0d24d27ca1dbaaa1","64a5531f0d24d27ca1dbaaa2","64a5531f0d24d27ca1dbaaa7","64a5531f0d24d27ca1dbaa9d","64a5531f0d24d27ca1dbaa9f","64a5531f0d24d27ca1dbaaa4","64a5531f0d24d27ca1dbaaa3"]
