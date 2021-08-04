package pl.chojecki.rent_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.chojecki.rent_service.dto.PriceDTO;
import pl.chojecki.rent_service.repository.PriceRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@Transactional
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PriceRepository priceRepository;

    @Test
    void testAddPrice() throws Exception {
        //given
        PriceDTO price = new PriceDTO();
        price.setId(1L);
        price.setPrice(BigDecimal.valueOf(1.2));
        //when
        MvcResult mvcResult = mockMvc.perform(post("/price")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(price)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
        //then
        PriceDTO priceDTOResult = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), PriceDTO.class);
        assertThat(priceDTOResult).isNotNull();
        assertThat(priceDTOResult.getId()).isEqualTo(price.getId());
        assertThat(priceDTOResult.getPrice()).isEqualTo(price.getPrice());
    }

    @Test
    void testEditPrice() throws Exception {
        //given
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setId(1L);
        priceDTO.setPrice(BigDecimal.valueOf(1.2));

        PriceDTO priceDTOToEdit = new PriceDTO();
        priceDTOToEdit.setId(1L);
        priceDTOToEdit.setPrice(BigDecimal.valueOf(1.3));

        mockMvc.perform(post("/price")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(priceDTO)))
                .andDo(print())
                .andExpect(status().isCreated());
        //when
        MvcResult mvcResult = mockMvc.perform(put("/price")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(priceDTOToEdit)))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        //then
        PriceDTO priceDTOResult = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), PriceDTO.class);
        assertThat(priceDTOResult).isNotNull();
        assertThat(priceDTOResult.getId()).isEqualTo(priceDTOToEdit.getId());
        assertThat(priceDTOResult.getPrice()).isEqualTo(priceDTOToEdit.getPrice());
    }
}