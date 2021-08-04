package pl.chojecki.rent_service.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "prices")
@Getter
@Setter
public class Price {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "price")
    private BigDecimal price;
}
