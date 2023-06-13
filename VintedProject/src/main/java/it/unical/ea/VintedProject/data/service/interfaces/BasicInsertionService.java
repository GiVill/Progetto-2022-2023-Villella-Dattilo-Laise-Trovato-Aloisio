package it.unical.ea.VintedProject.data.service.interfaces;


import it.unical.ea.VintedProject.data.entities.BasicInsertion;
import it.unical.ea.VintedProject.dto.BasicInsertionDto;
import it.unical.ea.VintedProject.dto.enumerated.Brand;
import it.unical.ea.VintedProject.dto.enumerated.Category;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BasicInsertionService {

    void save(BasicInsertion basicInsertion);

    BasicInsertionDto saveDto(BasicInsertionDto basicInsertionDto);

    void deleteBasicInsertionById(Long bId);

    void deleteAllBasicInsertionByUserId(Long uId);

    Page<BasicInsertionDto> findAllByUser(Long uId,int page);

    Page<BasicInsertionDto> getAllPaged(int page);

    Page<BasicInsertionDto> getAllByTitleStartWith(String title, int page);

    BasicInsertionDto getInsertionById(Long id);

    BasicInsertion findById(Long id);

    Page<BasicInsertionDto> getByBrand(Brand brand, int page);

    Page<BasicInsertionDto> getByCategory(Category category, int page);

}
