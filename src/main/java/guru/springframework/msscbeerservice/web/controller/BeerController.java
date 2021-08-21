package guru.springframework.msscbeerservice.web.controller;

import guru.springframework.msscbeerservice.service.BeerService;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerService beerService;

    @GetMapping("/{id}")
    public ResponseEntity<BeerDto> get(@PathVariable UUID id){
        return ResponseEntity.ok(beerService.getBeerById(id));
    }

    @PostMapping
    public ResponseEntity save(@Validated @RequestBody BeerDto beerDto){
        BeerDto savedDto = beerService.saveNewBeer(beerDto);
        return ResponseEntity.created( URI.create(String.format("/api/v1/beer/%s", savedDto.getId())) ).build();

        /*
        HttpHeaders headers = new HttpHeaders();
        // TODO add hostname for location
        headers.add("Location", String.format("/api/v1/beer/%s", savedDto.getId().toString()));
        return ResponseEntity(headers, HttpStatus.CREATED);
        */

    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable UUID id, @Validated @RequestBody BeerDto beerDto){
        beerService.updateBeer(id, beerDto);
        return ResponseEntity.noContent().build();
    }

}
