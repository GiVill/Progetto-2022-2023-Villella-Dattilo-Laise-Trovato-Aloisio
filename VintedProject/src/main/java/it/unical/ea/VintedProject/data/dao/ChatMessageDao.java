package it.unical.ea.VintedProject.data.dao;

import it.unical.ea.VintedProject.data.entities.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import java.util.List;

public interface ChatMessageDao extends JpaRepository<ChatMessage,Long>, JpaSpecificationExecutor<ChatMessage> {

    List<ChatMessage> findAllBySender(Long id);

    List<ChatMessage> findAllBySenderOrderByDateAsc(Long id);

    //non so se verra utilizzata
    List<ChatMessage> findAllByReciver(Long id);

    List<ChatMessage> findAllByReciverOrderByDateAsc(Long id);

    List<String> findAllBySenderAndReciver(Long user1, Long user2);



    List<ChatMessage> findBySenderAndReciverOrderByDateAsc(Long sender, Long reciver);

    List<ChatMessage> findByReciverAndSenderOrderByDateAsc(Long sender, Long reciver);

}