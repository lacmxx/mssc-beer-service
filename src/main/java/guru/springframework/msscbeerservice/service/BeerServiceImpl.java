package guru.springframework.msscbeerservice.service;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repository.BeerRepository;
import guru.springframework.msscbeerservice.web.mapper.BeerMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getBeerById(UUID beerId) {
        log.debug("Find beer with Id: " + beerId);
        return beerRepository.findById(beerId)
                .map(beer -> beerMapper.toDto(beer))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        Beer beer = beerMapper.toDomain(beerDto);
        return beerMapper.toDto(
                beerRepository.save( beer )
        );
    }

    @Override
    public BeerDto updateBeer(UUID id, BeerDto beerDto) {
        return beerRepository.findById(id)
                .map(beer -> {
                    beer.setName( beerDto.getName() );
                    beer.setStyle( beerDto.getStyle().name() );
                    beer.setPrice( beerDto.getPrice() );
                    beer.setUpc(beerDto.getUpc() );
                    return beerMapper.toDto( beerRepository.save(beer) );
                })
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void deleteBeerById(UUID beerId) {

    }
}
