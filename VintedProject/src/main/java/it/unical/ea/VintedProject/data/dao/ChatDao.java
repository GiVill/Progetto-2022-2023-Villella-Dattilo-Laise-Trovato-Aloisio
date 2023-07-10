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

    List<Chat> findAllByIdUser1(Long id);

    List<Chat> findAllByIdUser1OrderByDateAsc(Long id);

    //non so se verra utilizzata
    List<Chat> findAllByIdUser2(Long id);

    List<Chat> findAllByIdUser2OrderByDateAsc(Long id);

    List<String> findAllByIdUser1AndIdUser2(Long user1, Long user2);



    List<Chat> findByIdUser1AndIdUser2OrderByDateAsc(Long sender, Long reciver);

    List<Chat> findByIdUser2AndIdUser1OrderByDateAsc(Long sender, Long reciver);

}
