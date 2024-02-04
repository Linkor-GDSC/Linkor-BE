package com.example.domain.message;

import com.example.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findAllByReceiver(User user);
    List<Message> findAllBySender(User user);

}