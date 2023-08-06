package it.unical.ea.VintedProject.data.dao;

import it.unical.ea.VintedProject.data.entities.Chat;
import it.unical.ea.VintedProject.dto.ChatDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;


import java.util.List;

public interface ChatDao extends JpaRepository<Chat,Long>, JpaSpecificationExecutor<Chat> {

    List<Chat> findAllBySender(Long id);

    List<Chat> findAllBySenderOrderByDateAsc(Long id);

    //non so se verra utilizzata
    List<Chat> findAllByReciver(Long id);

    List<Chat> findAllByReciverOrderByDateAsc(Long id);

    List<String> findAllBySenderAndReciver(Long user1, Long user2);



    List<Chat> findBySenderAndReciverOrderByDateAsc(Long sender, Long reciver);

    List<Chat> findByReciverAndSenderOrderByDateAsc(Long sender, Long reciver);

}
