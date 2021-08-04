package pl.chojecki.rent_service.service;

import pl.chojecki.rent_service.model.Price;

public interface PriceService {

    Price addPrice(Price price);

    Price getPriceById(Long id);

    Price editPrice(Long id, Price price);
}
