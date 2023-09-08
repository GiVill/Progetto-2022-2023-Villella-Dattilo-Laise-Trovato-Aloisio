package it.unical.ea.VintedProject.data.service;

import com.nimbusds.jose.JOSEException;
import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.config.newsletter.EmailSender;
import it.unical.ea.VintedProject.core.detail.LoggedUserMethod;
import it.unical.ea.VintedProject.data.dao.BasicInsertionDao;
import it.unical.ea.VintedProject.data.dao.OrderDao;
import it.unical.ea.VintedProject.data.dao.UserDao;
import it.unical.ea.VintedProject.data.entities.BasicInsertion;
import it.unical.ea.VintedProject.data.entities.Order;
import it.unical.ea.VintedProject.data.service.interfaces.BasicInsertionService;
import it.unical.ea.VintedProject.data.service.interfaces.UserService;
import it.unical.ea.VintedProject.dto.BasicInsertionDto;
import it.unical.ea.VintedProject.dto.enumeration.Brand;
import it.unical.ea.VintedProject.dto.enumeration.Category;
import it.unical.ea.VintedProject.security.TokenStore;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasicInsertionServiceImpl implements BasicInsertionService {

    private final BasicInsertionDao basicInsertionDao;
    private final OrderDao orderDao;
    private final ModelMapper modelMapper;
    private final static int SIZE_FOR_PAGE = 10;
    private final MessageLang messageLang;
    private final LoggedUserMethod loggedUserMethod;


    @Override
    public void save(BasicInsertion basicInsertion) {
        basicInsertionDao.save(basicInsertion);
    }

    @Override
    public BasicInsertionDto saveDto(BasicInsertionDto basicInsertionDto) {
        BasicInsertion basicInsertion  = modelMapper.map(basicInsertionDto,BasicInsertion.class);
        basicInsertionDao.save(basicInsertion);
        return modelMapper.map(basicInsertion, BasicInsertionDto.class);
    }

    @Override
    public Page<BasicInsertionDto> getAllByUser(Long uId, int page) {
        PageRequest pageRequest = PageRequest.of(page, SIZE_FOR_PAGE);
        List<BasicInsertionDto> collect = basicInsertionDao.findAllByUserIdAndIsPrivateIsFalseAndAvailableIsTrue(uId, pageRequest).stream().map(s -> modelMapper.map(s, BasicInsertionDto.class)).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }

    @Override
    public Page<BasicInsertionDto> getAllMyInsertions(Long uId, int page) {
        PageRequest pageRequest = PageRequest.of(page, SIZE_FOR_PAGE);
        List<BasicInsertionDto> collect = basicInsertionDao.findAllByUserId(uId, pageRequest).stream().map(s -> modelMapper.map(s, BasicInsertionDto.class)).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }

    @Override
    public Page<BasicInsertionDto> getAllByUserEmail(String email, int page) {
        PageRequest pageRequest = PageRequest.of(page, SIZE_FOR_PAGE);
        List<BasicInsertionDto> collect = basicInsertionDao.findAllByUserEmail(email, pageRequest).stream().map(s -> modelMapper.map(s, BasicInsertionDto.class)).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }

    @Override
    public Page<BasicInsertionDto> getAllPaged(int page) {
        Page<BasicInsertion> basicInsertions = basicInsertionDao.findAll(PageRequest.of(page, SIZE_FOR_PAGE));
        List<BasicInsertionDto> collect = basicInsertions.stream().map(s -> modelMapper.map(s, BasicInsertionDto.class)).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }

    @Override
    public Page<BasicInsertionDto> getAllByIsPrivateEqualsFalsePaged(int page) {
        Page<BasicInsertion> basicInsertions = basicInsertionDao.findAllByIsPrivateIsFalseAndAvailableIsTrue(PageRequest.of(page, SIZE_FOR_PAGE));
        List<BasicInsertionDto> collect = basicInsertions.stream().map(s -> modelMapper.map(s, BasicInsertionDto.class)).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }

    @Override
    public Page<BasicInsertionDto> getAllByTitleStartWith(String title, int page) {
        PageRequest pageRequest = PageRequest.of(page, SIZE_FOR_PAGE, Sort.by("title").ascending());
        List<BasicInsertionDto> collect = basicInsertionDao.findAllByTitleContainingIgnoreCaseAndIsPrivateIsFalseAndAvailableIsTrue(title, pageRequest).stream().map(s -> modelMapper.map(s, BasicInsertionDto.class)).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }

    @Override
    public BasicInsertionDto getInsertionByIdAndIsPrivateEqualsFalse(Long id) {
        BasicInsertion basicInsertion = basicInsertionDao.findByIdAndIsPrivateIsFalse(id).orElseThrow(() -> new EntityNotFoundException(messageLang.getMessage("insertion.not.present",id)));
        return modelMapper.map(basicInsertion, BasicInsertionDto.class);
    }

    @Override
    public List<BasicInsertionDto> getAllInsertionByOrderId(Long orderId) {
        Optional<Order> order = orderDao.findById(orderId);
        if(order.isPresent()){
            loggedUserMethod.checkLoggedUser(order.get().getUser().getId());
            List<BasicInsertionDto> collect = basicInsertionDao.findAllByOrder(order.get()).stream().map(s -> modelMapper.map(s, BasicInsertionDto.class)).collect(Collectors.toList());
            return collect;
        } else {
             throw new EntityNotFoundException(messageLang.getMessage("insertion.not.present",orderId));
        }
    }

    @Override
    public BasicInsertionDto getInsertionById(Long id) {
        BasicInsertion basicInsertion = basicInsertionDao.findById(id).orElseThrow(() -> new EntityNotFoundException(messageLang.getMessage("insertion.not.present",id)));
        return modelMapper.map(basicInsertion, BasicInsertionDto.class);
    }

    @Override
    public BasicInsertion getById(Long id) {
        return basicInsertionDao.findById(id).orElseThrow(() -> new EntityNotFoundException(messageLang.getMessage("insertion.not.present",id)));
    }

    @Override
    public Page<BasicInsertionDto> getByBrand(Brand brand, int page){
        PageRequest pageRequest = PageRequest.of(page, SIZE_FOR_PAGE, Sort.by("brand").ascending());
        List<BasicInsertionDto> collect = basicInsertionDao.findByBrandAndIsPrivateIsFalseAndAvailableIsTrue(brand, pageRequest).stream().map(s -> modelMapper.map(s, BasicInsertionDto.class)).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }

    @Override
    public Page<BasicInsertionDto> getByCategory(Category category, int page){
        PageRequest pageRequest = PageRequest.of(page, SIZE_FOR_PAGE, Sort.by("category").ascending());
        List<BasicInsertionDto> collect = basicInsertionDao.findByCategoryAndIsPrivateIsFalseAndAvailableIsTrue(category,pageRequest).stream().map(s -> modelMapper.map(s, BasicInsertionDto.class)).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }

    @Override
    public BasicInsertionDto modifyUserInsertion(BasicInsertionDto insertionDto) {
        loggedUserMethod.checkLoggedUser(insertionDto.getUserId());
        basicInsertionDao.save(modelMapper.map(insertionDto,BasicInsertion.class));
        return insertionDto;
    }


    @Override
    public String generate24hToken(Long id) {
        BasicInsertion insertion = basicInsertionDao.findById(id).orElseThrow(() -> new EntityNotFoundException(messageLang.getMessage("insertion.not.present", id)));
        loggedUserMethod.checkLoggedUser(insertion.getUser().getId());
        Map<String, Object> claims  = new HashMap<>();
        claims.put("id",insertion.getId());
        try {
            return TokenStore.getInstance().create24hToken(claims);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String generateLongTermToken(Long id) {
        BasicInsertion insertion = basicInsertionDao.findById(id).orElseThrow(() -> new EntityNotFoundException(messageLang.getMessage("insertion.not.present", id)));
        loggedUserMethod.checkLoggedUser(insertion.getUser().getId());
        Map<String, Object> claims  = new HashMap<>();
        claims.put("id",insertion.getId());
        try {
            return TokenStore.getInstance().createLongTermToken(claims);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BasicInsertionDto getPrivateInsertion(String token) {
        try {
            Long id = TokenStore.getInstance().verifyToken(token);
            if(id != null){
                BasicInsertion insertion = basicInsertionDao.findById(id).orElseThrow(() -> new EntityNotFoundException(messageLang.getMessage("insertion.not.present", id)));
                return modelMapper.map(insertion, BasicInsertionDto.class);
            }
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void deleteBasicInsertionForAdmin(Long insertionId) {
        basicInsertionDao.deleteById(insertionId);
    }

    @Override
    public void deleteBasicInsertionById(Long insertionId) {
        basicInsertionDao.deleteById(insertionId);
    }

    @Override
    public void deleteAllBasicInsertionByUserId(Long uId) {
        basicInsertionDao.deleteAllByUserId(uId);
    }


    @Override
    public void modifyInsertion(BasicInsertionDto insertionDto) {
        basicInsertionDao.save(modelMapper.map(insertionDto,BasicInsertion.class));
    }


}
