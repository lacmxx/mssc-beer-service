package guru.springframework.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private static final String endPoint = "/api/v1/beer/";

    @Test
    void getTest() throws Exception {
        ResultActions resultActions = mockMvc.perform(get(String.format("%s%s", endPoint, UUID.randomUUID().toString()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void saveTest() throws Exception {
        BeerDto beerDto = BeerDto.builder()
                .name("Beer Name")
                .style(BeerStyleEnum.ALE)
                .price(BigDecimal.valueOf(11.23))
                .upc(123458L)
                .build();
        String beerToJson = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform( post(endPoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerToJson)
        ).andExpect(status().isCreated());
    }

    @Test
    void updateTest() throws Exception {
        BeerDto beerDto = BeerDto.builder()
                .name("Beer Name")
                .style(BeerStyleEnum.ALE)
                .price(BigDecimal.valueOf(11.23))
                .upc(123458L)
                .build();
        String beerToJson = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform( put(String.format("%s%s", endPoint, UUID.randomUUID().toString()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerToJson)
        ).andExpect(status().isNoContent());
    }
}