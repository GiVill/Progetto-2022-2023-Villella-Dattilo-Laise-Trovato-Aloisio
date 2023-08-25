package it.unical.ea.VintedProject.data.dao;

import it.unical.ea.VintedProject.data.entities.Chat;
import it.unical.ea.VintedProject.data.entities.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ChatDao  extends JpaRepository<Chat,Long>, JpaSpecificationExecutor<Chat> {

    List<ChatMessage> findAllById(Long id);
    List<Chat> findAllBybasicInsertionId(Long id);

    List<Chat> findByUser1OrUser2OrderByBasicInsertion(Long user1, Long user2);

    List<Chat> findByUser1OrUser2(Long sender, Long reciver);

    List<Chat> findByUser2OrUser1(Long sender, Long reciver);


}
