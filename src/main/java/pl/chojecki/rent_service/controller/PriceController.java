package pl.chojecki.rent_service.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.chojecki.rent_service.dto.PriceDTO;
import pl.chojecki.rent_service.model.Price;
import pl.chojecki.rent_service.service.PriceService;

@RestController
@RequestMapping("/price")
@AllArgsConstructor
public class PriceController {

    private final PriceService priceService;

    @PostMapping
    public ResponseEntity<PriceDTO> addPrice(@RequestBody PriceDTO newPrice) {
        return new ResponseEntity<>(convertToDTO(priceService.addPrice(convertToEntity(newPrice))), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<PriceDTO> editPrice(@RequestBody PriceDTO newPrice) {
        return new ResponseEntity<>(convertToDTO(priceService.editPrice(newPrice.getId(), convertToEntity(newPrice))),HttpStatus.OK);
    }


    private Price convertToEntity(PriceDTO priceDTO) {
        Price price = new Price();
        price.setId(priceDTO.getId());
        price.setPrice(priceDTO.getPrice());

        return price;
    }

    private PriceDTO convertToDTO(Price price) {
        PriceDTO priceDTO = new PriceDTO();

        priceDTO.setId(price.getId());
        priceDTO.setPrice(price.getPrice());

        return priceDTO;
    }
}
