package Snixies.beercatalogservice.resources;

import Snixies.beercatalogservice.models.Beer;
import Snixies.beercatalogservice.models.CatalogItem;
import Snixies.beercatalogservice.models.Rating;
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

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalolog(@PathVariable("userId") String userId){

        RestTemplate restTemplate = new RestTemplate();


        List<Rating> ratings = Arrays.asList(
              new Rating(1,4),
              new Rating(2,5)
        );

        return ratings.stream().map(rating -> {
            Beer beer = restTemplate.getForObject("http://localhost:8082/beers/" + rating.getBeerId(), Beer.class);
            return new CatalogItem(beer.getName(), beer.getDescription(), rating.getRating());

        } )
        .collect(Collectors.toList());

        //foreach beerId, call beer info svc and get details

        //put them all together

    }
}
