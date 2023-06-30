package it.unical.ea.VintedProject.data.service;

import it.unical.ea.VintedProject.config.i18n.MessageLang;
import it.unical.ea.VintedProject.data.dao.BasicInsertionDao;
import it.unical.ea.VintedProject.data.dao.UserDao;
import it.unical.ea.VintedProject.data.entities.BasicInsertion;
import it.unical.ea.VintedProject.data.service.interfaces.BasicInsertionService;
import it.unical.ea.VintedProject.dto.BasicInsertionDto;
import it.unical.ea.VintedProject.dto.enumeration.Brand;
import it.unical.ea.VintedProject.dto.enumeration.Category;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasicInsertionServiceImpl implements BasicInsertionService {

    //private final DressInsertionDao

    private final BasicInsertionDao basicInsertionDao;
    private final UserDao userDao;
    private final ModelMapper modelMapper;
    private final static int SIZE_FOR_PAGE = 10;

    private final MessageLang messageLang;

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
    public void deleteBasicInsertionById(Long bId) {
        basicInsertionDao.deleteById(bId);
    }

    @Override
    public void deleteAllBasicInsertionByUserId(Long uId) {
        basicInsertionDao.deleteAllByUserId(uId);
    }

    @Override
    public Page<BasicInsertionDto> findAllByUser(Long uId, int page) {
        PageRequest pageRequest = PageRequest.of(SIZE_FOR_PAGE, page);
        List<BasicInsertionDto> collect = basicInsertionDao.findAllByUserId(uId, pageRequest).stream().map(s -> modelMapper.map(s, BasicInsertionDto.class)).collect(Collectors.toList());
        System.out.println(collect);
        return new PageImpl<>(collect);
    }

    //questo è di test
    /*
    @Override
    public Page<BasicInsertionDto> findAllByUserId(Long userId, int page) {
        PageRequest pageRequest = PageRequest.of(SIZE_FOR_PAGE, page);
        Optional<User> user = userDao.findById(userId);

        //List<BasicInsertionDto> collect = basicInsertionDao.findAllByUser(user, pageRequest).stream().map(s -> modelMapper.map(s, BasicInsertionDto.class)).collect(Collectors.toList());
        System.out.println(basicInsertionDao.findAllByUser(user));
        return new PageImpl<>(null);
    }

     */

    @Override
    public Page<BasicInsertionDto> getAllPaged(int page) {
        Page<BasicInsertion> basicInsertions = basicInsertionDao.findAll(PageRequest.of(page, SIZE_FOR_PAGE));
        List<BasicInsertionDto> collect = basicInsertions.stream().map(s -> modelMapper.map(s, BasicInsertionDto.class)).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }

    @Override
    public Page<BasicInsertionDto> getAllByTitleStartWith(String title, int page) {
        PageRequest pageRequest = PageRequest.of(SIZE_FOR_PAGE, page, Sort.by("title").ascending());
        List<BasicInsertionDto> collect = basicInsertionDao.findAllByTitleLike(title, pageRequest).stream().map(s -> modelMapper.map(s, BasicInsertionDto.class)).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }

    @Override
    public BasicInsertionDto getInsertionById(Long id) {
        BasicInsertion basicInsertion = basicInsertionDao.findById(id).orElseThrow(() -> new EntityNotFoundException(messageLang.getMessage("insertion.not.present",id)));
        return modelMapper.map(basicInsertion, BasicInsertionDto.class);
    }

    @Override
    public BasicInsertion findById(Long id) {
        return basicInsertionDao.findById(id).orElseThrow(() -> new EntityNotFoundException(messageLang.getMessage("insertion.not.present",id)));
    }

    @Override
    public Page<BasicInsertionDto> getByBrand(Brand brand, int page){
        PageRequest pageRequest = PageRequest.of(SIZE_FOR_PAGE, page, Sort.by("brand").ascending());
        List<BasicInsertionDto> collect = basicInsertionDao.findByBrand(brand, pageRequest).stream().map(s -> modelMapper.map(s, BasicInsertionDto.class)).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }

    @Override
    public Page<BasicInsertionDto> getByCategory(Category category, int page){
        PageRequest pageRequest = PageRequest.of(SIZE_FOR_PAGE, page, Sort.by("category").ascending());
        List<BasicInsertionDto> collect = basicInsertionDao.findByCategory(category,pageRequest).stream().map(s -> modelMapper.map(s, BasicInsertionDto.class)).collect(Collectors.toList());
        return new PageImpl<>(collect);
    }



}
