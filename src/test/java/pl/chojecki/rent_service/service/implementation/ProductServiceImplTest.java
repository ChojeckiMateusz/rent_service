package pl.chojecki.rent_service.service.implementation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.chojecki.rent_service.model.Product;
import pl.chojecki.rent_service.service.ProductService;

import javax.transaction.Transactional;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    void getProductById() {
        //given
        //when
        Product product = productService.getProductById(1L);
        //then
        assertThat(product).isNotNull();
        assertThat(product.getName()).isEqualTo("PRALKA");
    }

    @Test
    void getProductByName() {
        //given
        //when
        Product product = productService.getProductByName("ODKURZACZ");
        //then
        assertThat(product).isNotNull();
        assertThat(product.getId()).isEqualTo(2L);
    }
}