package Snixies.beercatalogservice.resources;

import Snixies.beercatalogservice.models.CatalogItem;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class BeerCatalogResource {

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalolog(@PathVariable("userId") String userId){

        return Collections.singletonList(
                new CatalogItem("Hertog Jan Weizener", "Weizen bier van brouwerij Hertog Jan", 4)
        );
    }
}
