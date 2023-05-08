package it.unical.ea.VintedProject.data.service;

import it.unical.ea.VintedProject.data.dao.BuyingOfferDao;
import it.unical.ea.VintedProject.data.entities.BuyingOffer;
import it.unical.ea.VintedProject.data.service.interfaces.BuyingOfferService;
import it.unical.ea.VintedProject.dto.BuyingOfferDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BuyingOfferServiceImpl implements BuyingOfferService {

    private final ModelMapper modelMapper;
    private final BuyingOfferDao buyingOfferDao;

    @Override
    public void save(BuyingOffer offer) {
        buyingOfferDao.save(offer);
    }

    @Override
    public void deleteOfferById(Long id) {
        buyingOfferDao.deleteById(id);
    }

    @Override
    public Stream<BuyingOfferDto> getById(Long id) {
        return buyingOfferDao.findById(id).stream().map(s -> modelMapper.map(s, BuyingOfferDto.class));

    }

    @Override
    public List<BuyingOfferDto> findAll() {
        return buyingOfferDao.findAll().stream().map(s -> modelMapper.map(s, BuyingOfferDto.class)).collect(Collectors.toList());
    }


}
