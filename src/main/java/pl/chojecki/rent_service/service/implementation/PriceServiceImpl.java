package pl.chojecki.rent_service.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.chojecki.rent_service.model.Price;
import pl.chojecki.rent_service.repository.PriceRepository;
import pl.chojecki.rent_service.service.PriceService;

@RequiredArgsConstructor
@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public Price addPrice(Price price) {
        price = priceRepository.save(price);
        return price;
    }

    @Override
    public Price getPriceById(Long id) {
        return priceRepository.findById(id).orElseThrow();
    }

    @Override
    public Price editPrice(Long id, Price price) {
        Price priceToEdit = priceRepository.findById(id).orElseThrow();

        priceToEdit.setPrice(price.getPrice());

        priceToEdit = priceRepository.save(priceToEdit);

        return priceToEdit;
    }
}
