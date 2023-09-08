package it.unical.ea.VintedProject.data.dao;

import it.unical.ea.VintedProject.data.entities.BasicInsertion;
import it.unical.ea.VintedProject.data.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatDao  extends JpaRepository<Chat,Long>{

    Optional<Chat> findByUser1AndUser2AndInsertionId(Long user1, Long user2, Long insertionId);

    @Query("SELECT c FROM Chat c WHERE c.user1 = :userId OR c.user2 = :userId")
    List<Chat> findAllChatsForUser(@Param("userId") Long userId);

    List<Chat> findAllByUser1(Long id);
}
