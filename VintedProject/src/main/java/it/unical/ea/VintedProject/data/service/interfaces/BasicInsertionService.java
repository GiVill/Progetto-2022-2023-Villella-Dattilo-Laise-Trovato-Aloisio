package it.unical.ea.VintedProject.data.service.interfaces;


import it.unical.ea.VintedProject.data.entities.BasicInsertion;
import it.unical.ea.VintedProject.dto.BasicInsertionDto;
import it.unical.ea.VintedProject.dto.enumeration.Brand;
import it.unical.ea.VintedProject.dto.enumeration.Category;
import org.springframework.data.domain.Page;

import java.util.List;


//Dao Notation:
//DAO (JPA): find, delete
//DAO (Service): get, update, delete

public interface BasicInsertionService {//extends Repository<BasicInsertion,Long> {

    void save(BasicInsertion basicInsertion);

    BasicInsertionDto saveDto(BasicInsertionDto basicInsertionDto);

    void deleteBasicInsertionById(Long insertionId);

    void deleteAllBasicInsertionByUserId(Long uId);

    Page<BasicInsertionDto> getAllByUser(Long uId, int page);
    Page<BasicInsertionDto> getAllMyInsertions(Long uId, int page);

    Page<BasicInsertionDto> getAllByUserEmail(String email, int page);

    Page<BasicInsertionDto> getAllPaged(int page);


    Page<BasicInsertionDto> getAllByIsPrivateEqualsFalsePaged(int page);

    Page<BasicInsertionDto> getAllByTitleStartWith(String title, int page);

    BasicInsertionDto getInsertionByIdAndIsPrivateEqualsFalse(Long id);

    List<BasicInsertionDto> getAllInsertionByOrderId(Long orderId);
    BasicInsertionDto getInsertionById(Long id);

    BasicInsertion getById(Long id);

    Page<BasicInsertionDto> getByBrand(Brand brand, int page);

    Page<BasicInsertionDto> getByCategory(Category category, int page);

    BasicInsertionDto modifyUserInsertion(BasicInsertionDto insertionDto);
/*
    @Query("select * from basicinsertion where user=:a")
    Page<BasicInsertion> @Param("a") User;

 */
    String generate24hToken(Long id) ;

    String generateLongTermToken(Long id);

    BasicInsertionDto getPrivateInsertion(String token);


    void deleteBasicInsertionForAdmin(Long insertionId);

    void modifyInsertion(BasicInsertionDto insertionDto);
}
