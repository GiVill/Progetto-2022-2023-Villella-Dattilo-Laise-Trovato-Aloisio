package it.unical.ea.VintedProject.data.dao;

import it.unical.ea.VintedProject.data.entities.Chat;
import it.unical.ea.VintedProject.data.entities.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ChatDao  extends JpaRepository<Chat,Long>, JpaSpecificationExecutor<Chat> {

    List<ChatMessage> findAllById(Long id);
    List<Chat> findAllBybasicInsertionId(Long id);

    List<Chat> findByReciverOrSenderOrderByBasicInsertion(Long user1, Long user2);

    List<Chat> findBySenderAndReciver(Long sender, Long reciver);

    List<Chat> findByReciverAndSender(Long sender, Long reciver);


}
