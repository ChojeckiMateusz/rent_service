package pl.chojecki.rent_service.service.implementation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.chojecki.rent_service.model.Price;
import pl.chojecki.rent_service.repository.PriceRepository;
import pl.chojecki.rent_service.service.PriceService;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PriceServiceImplTest {

    @Autowired
    private PriceService priceService;

    @Autowired
    PriceRepository priceRepository;

    @Test
    void testAddPrice() {
        //given
        Price price = new Price();
        price.setId(1L);
        price.setPrice(BigDecimal.valueOf(1.2));
        //when
        Price newPrice = priceService.addPrice(price);
        //then
        assertThat(newPrice).isNotNull();
        assertThat(newPrice.getPrice()).isEqualTo(price.getPrice());
    }

    @Test
    void getPriceById() {
        //given
        Price price = new Price();
        price.setId(1L);
        price.setPrice(BigDecimal.valueOf(1.2));
        priceRepository.save(price);
        //when
        Price foundPrice = priceService.getPriceById(price.getId());
        //then
        assertThat(foundPrice).isNotNull();
        assertThat(foundPrice.getPrice()).isEqualTo(price.getPrice());
    }

    @Test
    void editPrice() {
        //given
        Price price = new Price();
        price.setId(1L);
        price.setPrice(BigDecimal.valueOf(1.2));
        priceRepository.save(price);

        Price newPrice = new Price();
        newPrice.setId(1L);
        newPrice.setPrice(BigDecimal.valueOf(2.4));
        //when
        Price editedPrice = priceService.editPrice(1L, newPrice);
        //then
        assertThat(editedPrice).isNotNull();
        assertThat(editedPrice.getPrice()).isEqualTo(newPrice.getPrice());
    }
}