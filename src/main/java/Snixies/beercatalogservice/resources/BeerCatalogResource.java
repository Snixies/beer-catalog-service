package Snixies.beercatalogservice.resources;

import Snixies.beercatalogservice.models.Beer;
import Snixies.beercatalogservice.models.CatalogItem;
import Snixies.beercatalogservice.models.Rating;
import Snixies.beercatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
}
