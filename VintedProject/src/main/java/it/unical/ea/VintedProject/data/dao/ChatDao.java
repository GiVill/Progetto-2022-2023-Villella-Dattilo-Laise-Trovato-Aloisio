package it.unical.ea.VintedProject.data.dao;

import it.unical.ea.VintedProject.data.entities.BasicInsertion;
import it.unical.ea.VintedProject.data.entities.Chat;
import it.unical.ea.VintedProject.data.entities.ChatMessage;
import it.unical.ea.VintedProject.dto.ChatDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface ChatDao  extends JpaRepository<Chat,Long>, JpaSpecificationExecutor<Chat> {

    List<ChatMessage> findAllById(Long id);
    List<Chat> findAllBybasicInsertionId(Long id);

    Optional<Chat> findByUser1AndUser2AndBasicInsertion(Long user1, Long user2, BasicInsertion insertion);

    List<Chat> findByUser1OrUser2OrderByBasicInsertion(Long user1, Long user2);

    List<Chat> findByUser1OrUser2(Long sender, Long reciver);

    List<Chat> findByUser2OrUser1(Long sender, Long reciver);


}
