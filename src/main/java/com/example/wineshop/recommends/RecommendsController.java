/*package com.example.wineshop.recommends;


import com.example.wineshop.entity.Wine;
import com.example.wineshop.repository.WineRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class RecommendsController {
    private final WineRepository repository;

    public RecommendsController(WineRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/api/recommends/best")
    //Find the best wines by rating
    public CollectionModel<EntityModel<Wine>> getBestWines(@RequestParam(required = false, defaultValue = "10") Integer limit) {
        List<EntityModel<Wine>> wines = repository.findTop10ByOrderByRatingDesc().stream()
                .map(wine -> EntityModel.of(wine,
                        linkTo(methodOn(RecommendsController.class).getBestWines(limit)).withSelfRel()))
                .collect(Collectors.toList());
        return CollectionModel.of(wines,
                linkTo(methodOn(RecommendsController.class).getBestWines(limit)).withSelfRel());

    }

    @GetMapping("/api/recommends/expensive")

    }

    @GetMapping("/api/recommends/bang")

    }
}*/
