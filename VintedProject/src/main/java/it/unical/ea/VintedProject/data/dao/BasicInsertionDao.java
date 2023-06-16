package it.unical.ea.VintedProject.data.dao;

import it.unical.ea.VintedProject.data.entities.BasicInsertion;
import it.unical.ea.VintedProject.data.entities.User;
import it.unical.ea.VintedProject.dto.enumerated.Brand;
import it.unical.ea.VintedProject.dto.enumerated.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface BasicInsertionDao extends JpaRepository<BasicInsertion,Long> {

    void deleteAllByUserId(Long uId);
    @Override
    Optional<BasicInsertion> findById(Long aLong);

    Page<BasicInsertion> findAllByUserId(Long uLong, Pageable pageable);

    //questo è di test
    //Page<BasicInsertion> findAllByUser(Optional<User> user);


    Page<BasicInsertion> findAllByTitleLike(String title,Pageable pageable);

    List<BasicInsertion> findByPriceLessThanEqual(int price);

    List<BasicInsertion> findByPriceGreaterThanEqual(int price);

    List<BasicInsertion> findByPriceBetween(int from, int to);

    Page<BasicInsertion> findByBrand(Brand brand, Pageable pageable);

    Page<BasicInsertion> findByCategory(Category category, Pageable pageable);

}
