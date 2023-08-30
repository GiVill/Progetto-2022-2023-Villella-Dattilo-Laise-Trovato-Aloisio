package it.unical.ea.VintedProject.data.service;

import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.core.detail.LoggedUserDetail;
import it.unical.ea.VintedProject.core.detail.LoggedUserMethod;
import it.unical.ea.VintedProject.data.dao.BasicInsertionDao;
import it.unical.ea.VintedProject.data.dao.BuyingOfferDao;
import it.unical.ea.VintedProject.data.dao.UserDao;
import it.unical.ea.VintedProject.data.entities.BasicInsertion;
import it.unical.ea.VintedProject.data.entities.BuyingOffer;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.data.service.interfaces.BuyingOfferService;
import it.unical.ea.VintedProject.data.service.interfaces.UserService;
import it.unical.ea.VintedProject.dto.BuyingOfferDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BuyingOfferServiceImpl implements BuyingOfferService {

    private final ModelMapper modelMapper;
    private final BuyingOfferDao buyingOfferDao;
    private final BasicInsertionDao insertionDao;
    private final MessageLang messageLang;
    private final LoggedUserMethod loggedUserMethod;

    @Override
    public BuyingOfferDto save(BuyingOfferDto offer) {
        BuyingOffer buyingOffer = modelMapper.map(offer,BuyingOffer.class);
        BuyingOffer o = buyingOfferDao.save(buyingOffer);
        return modelMapper.map(o,BuyingOfferDto.class);
    }

    @Override
    public void save(BuyingOffer buyingOffer) {
        buyingOfferDao.save(buyingOffer);
    }


    @Override
    public List<BuyingOfferDto> getAllByUserId(Long userId) {
        return buyingOfferDao.findAllByUserId(userId).stream().map(s -> modelMapper.map(s, BuyingOfferDto.class)).collect(Collectors.toList());
    }

    @Override
    //@PreAuthorize("has")
    public List<BuyingOfferDto> getByInsertionId(Long insertionId) {
        return buyingOfferDao.findByInsertionId(insertionId).stream().map(s -> modelMapper.map(s, BuyingOfferDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<BuyingOfferDto> getAll() {
        return buyingOfferDao.findAll().stream().map(s -> modelMapper.map(s, BuyingOfferDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteOfferById(Long buyingOfferId) {

        Optional<BasicInsertion> insertion = insertionDao.findById(buyingOfferId);
        Optional<BuyingOffer> buyingOffer = buyingOfferDao.findById(buyingOfferId);

        if(loggedUserMethod.getLoggedUserId().equals(buyingOffer.get().getUser().getId()) ||
                loggedUserMethod.getLoggedUserId().equals(insertion.get().getUser().getId())){
            if(buyingOffer.isEmpty()){
                throw new EntityNotFoundException(messageLang.getMessage("user.offer.not.present",buyingOfferId));
            }
            buyingOfferDao.deleteById(buyingOfferId);
        } else {
            throw new BadRequestException(messageLang.getMessage("access.denied"));
        }

    }

    @Override
    public BuyingOfferDto getOfferById(Long offerId) {
        Optional<BuyingOffer> offer = buyingOfferDao.findById(offerId);
        if(offer.isEmpty()){
            throw new EntityNotFoundException(messageLang.getMessage("user.offer.not.present",offerId));
        }
        return modelMapper.map(offer.get(), BuyingOfferDto.class);
    }

    @Override
    public void acceptOffer(BuyingOfferDto buyingOfferDto) {
        Optional<BasicInsertion> insertion = insertionDao.findById(buyingOfferDto.getInsertionId());
        if(loggedUserMethod.getLoggedUserId().equals(insertion.get().getUser().getId())){
            BuyingOffer buyingOffer = modelMapper.map(buyingOfferDto,BuyingOffer.class);
            buyingOfferDao.save(buyingOffer);
        }
        else {
            throw new BadRequestException(messageLang.getMessage("access.denied"));
        }
    }

}
