package it.unical.ea.VintedProject.data.service.interfaces;


import it.unical.ea.VintedProject.data.entities.BasicInsertion;
import it.unical.ea.VintedProject.dto.BasicInsertionDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BasicInsertionService {
    void save(BasicInsertion basicInsertion);
    void deleteBasicInsertionById(Long bId);

    Page<BasicInsertionDto> findAllByUserAuthorId(Long uId,int page);

    Page<BasicInsertionDto> getAllPaged(int page);

    Page<BasicInsertionDto> getAllByTitleStartWith(String title, int page);
}
