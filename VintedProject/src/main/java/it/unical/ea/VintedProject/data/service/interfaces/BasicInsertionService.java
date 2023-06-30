package it.unical.ea.VintedProject.data.service.interfaces;


import it.unical.ea.VintedProject.data.entities.BasicInsertion;
import it.unical.ea.VintedProject.dto.BasicInsertionDto;
import it.unical.ea.VintedProject.dto.enumeration.Brand;
import it.unical.ea.VintedProject.dto.enumeration.Category;
import org.springframework.data.domain.Page;


public interface BasicInsertionService {//extends Repository<BasicInsertion,Long> {

    void save(BasicInsertion basicInsertion);

    BasicInsertionDto saveDto(BasicInsertionDto basicInsertionDto);

    void deleteBasicInsertionById(Long bId);

    void deleteAllBasicInsertionByUserId(Long uId);

    Page<BasicInsertionDto> findAllByUser(Long uId, int page);

    //questo è di test
    //Page<BasicInsertionDto> findAllByUserId(Long user, int page);

    Page<BasicInsertionDto> getAllPaged(int page);

    Page<BasicInsertionDto> getAllByTitleStartWith(String title, int page);

    BasicInsertionDto getInsertionById(Long id);

    BasicInsertion findById(Long id);

    Page<BasicInsertionDto> getByBrand(Brand brand, int page);

    Page<BasicInsertionDto> getByCategory(Category category, int page);
/*
    @Query("select * from basicinsertion where user=:a")
    Page<BasicInsertion> @Param("a") User;

 */

}
