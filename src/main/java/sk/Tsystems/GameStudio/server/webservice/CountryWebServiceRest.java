package sk.Tsystems.GameStudio.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.Tsystems.GameStudio.entity.Comment;
import sk.Tsystems.GameStudio.entity.Country;
import sk.Tsystems.GameStudio.service.CommentService;
import sk.Tsystems.GameStudio.service.CountryService;

import java.util.List;

@RestController
@RequestMapping("/api/country")
public class CountryWebServiceRest {
    @Autowired
    CountryService countryService;
    @GetMapping
    public List<Country> getCountries() {

        return countryService.getCountries();
    }

}
