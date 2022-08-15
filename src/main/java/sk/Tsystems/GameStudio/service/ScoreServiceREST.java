package sk.Tsystems.GameStudio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import sk.Tsystems.GameStudio.entity.Score;

import java.util.Arrays;
import java.util.List;

public class ScoreServiceREST implements ScoreService {

    @Value("${remote.server.api}")
    private String url;
    @Autowired
    private RestTemplate restTemplate;


    @Override
    public void addScore(Score score) {
        restTemplate.postForEntity(url + "/score", score, Score.class);
    }

    @Override
    public List<Score> getBestScores(String game) {
        return Arrays.asList(restTemplate.getForEntity(url + "/score/" + game, Score[].class).getBody());

    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported via web");
    }
}
