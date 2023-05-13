package it.unical.ea.VintedProject.data.dao;

import it.unical.ea.VintedProject.data.entities.BasicInsertion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasicInsertionDao extends JpaRepository<BasicInsertion,Long> {

    @Override
    Optional<BasicInsertion> findById(Long aLong);

    Page<BasicInsertion> findAllByUserId(Long uLong, Pageable pageable);

    Page<BasicInsertion> findAllByTitleLike(String title,Pageable pageable);

    List<BasicInsertion> findByPriceLessThanEqual(int price);

    List<BasicInsertion> findByPriceGreaterThanEqual(int price);

    List<BasicInsertion> findByPriceBetween(int from, int to);

}
