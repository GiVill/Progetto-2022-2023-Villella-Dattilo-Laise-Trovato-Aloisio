package it.unical.ea.VintedProject.data.dao;

import it.unical.ea.VintedProject.data.entities.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ChatMessageDao extends JpaRepository<ChatMessage,Long> {

    List<ChatMessage> findAllBySender(Long id);

    List<ChatMessage> findAllBySenderOrderByDateAsc(Long id);


    List<ChatMessage> findAllByReciver(Long id);

    List<ChatMessage> findAllByReciverOrderByDateAsc(Long id);

    List<String> findAllBySenderAndReciver(Long user1, Long user2);


    List<ChatMessage> findBySenderOrReciverOrderByDateAsc(Long sender, Long reciver);

    List<ChatMessage> findByReciverOrSenderOrderByDateAsc(Long sender, Long reciver);

    List<ChatMessage> findAllByChatOrderByDateAsc(Long id);
}
