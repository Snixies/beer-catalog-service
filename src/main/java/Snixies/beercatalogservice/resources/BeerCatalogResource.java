package Snixies.beercatalogservice.resources;

import Snixies.beercatalogservice.models.Beer;
import Snixies.beercatalogservice.models.CatalogItem;
import Snixies.beercatalogservice.models.Rating;
import Snixies.beercatalogservice.models.UserRating;
import org.apache.http.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class BeerCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalolog(@PathVariable("userId") int userId){
        UserRating ratings = restTemplate.getForObject("http://beer-rating-data-service/ratingsdata/users/" + userId, UserRating.class);

        return ratings.getUserRatings().stream().map(rating -> {
            //foreach beerId, call beer info svc and get details
            Beer beer = restTemplate.getForObject("http://beer-info-service/beers/" + rating.getBeerId(), Beer.class);
            //put them all together
            return new CatalogItem(beer.getName(), beer.getDescription(), rating.getRating());

        } )
        .collect(Collectors.toList());
    }
    @PostMapping(value = "/addBeer/{beerId}/{beerName}/{beerDiscription}", consumes = "application/json", produces = "application/json")
    public void addBeer(@PathVariable("beerId") int beerId, @PathVariable("beerName") String beerName, @PathVariable("beerDescription") String beerDescription, @RequestBody Beer beer){
        beer.setBeerId(beerId);
        beer.setName(beerName);
        beer.setDescription(beerDescription);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>(beer.toString(), headers);

        restTemplate.postForObject("http://beer-info-service/addBeer/" + beer.getBeerId() + "/" + beer.getName() + "/" + beer.getDescription(), request, Beer.class);
    }

}
