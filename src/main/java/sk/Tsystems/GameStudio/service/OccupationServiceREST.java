package sk.Tsystems.GameStudio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import sk.Tsystems.GameStudio.entity.Comment;
import sk.Tsystems.GameStudio.entity.Occupation;

import java.util.Arrays;
import java.util.List;

public class OccupationServiceREST implements OccupationService{
    @Value("${remote.server.api}")
    private String url;
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public List<Occupation> getOccupations() {
        return Arrays.asList(restTemplate.getForEntity(url+"/occupation", Occupation[].class).getBody());
    }

    @Override
    public void addOccupation(Occupation occupation) {

    }

    @Override
    public Occupation getOccupation(String occupationName) {
        throw new UnsupportedOperationException("Not supported via web");
    }
}
