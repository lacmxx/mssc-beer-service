package guru.springframework.msscbeerservice.web.controller;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    @GetMapping("/{id}")
    public ResponseEntity<BeerDto> get(@PathVariable UUID id){
        // TODO impl
        return ResponseEntity.ok( BeerDto.builder().build() );
    }

    @PostMapping
    public ResponseEntity save(@Validated @RequestBody BeerDto beerDto){
        // TODO impl
        return ResponseEntity.created( URI.create(String.format("/api/v1/beer/%s", beerDto.getId())) ).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable UUID id, @Validated @RequestBody BeerDto beerDto){
        // TODO impl
        return ResponseEntity.noContent().build();
    }

}
