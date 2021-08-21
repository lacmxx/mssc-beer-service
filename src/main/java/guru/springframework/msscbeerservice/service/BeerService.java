package guru.springframework.msscbeerservice.service;

import guru.springframework.msscbeerservice.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {

    BeerDto getBeerById(UUID beerId);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID id, BeerDto beerDto);

    void deleteBeerById(UUID beerId);


}
