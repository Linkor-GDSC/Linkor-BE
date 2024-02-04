package com.example.controller;

import com.example.domain.user.User;
import com.example.domain.user.UserRepository;
import com.example.dto.MessageDto;
import com.example.response.Response;
import com.example.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;
    private final UserRepository userRepository;

    //쪽지 보내기
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{email}")
    public Response<?> sendMessage(@RequestBody MessageDto messageDto, @PathVariable("email") String email) {
        User user = userRepository.findByEmail(email);
        messageDto.setSenderEmail(user.getEmail());

        return new Response<>("성공", "쪽지를 보냈습니다.", messageService.write(messageDto));
    }

    //받은 쪽지 확인
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/received/{email}")
    public Response<?> getReceivedMessage(@PathVariable("email") String email) {
        User user = userRepository.findByEmail(email);
        return new Response<>("성공", "받은 쪽지를 불러왔습니다.", messageService.receivedMessage(user));
    }


    //보낸 편지 확인
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/sent/{email}")
    public Response<?> getSentMessage(@PathVariable("email") String email) {
        User user = userRepository.findByEmail(email);

        return new Response<>("성공", "보낸 쪽지를 불러왔습니다.", messageService.sentMessage(user));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{senderEmail}/{receiverEmail}")
    public Response<?> getAllMessages(@PathVariable("senderEmail") String senderEmail,
                                      @PathVariable("receiverEmail") String receiverEmail) {
        User sender = userRepository.findByEmail(senderEmail);
        User receiver = userRepository.findByEmail(receiverEmail);
        return new Response<>("성공", "쪽지들을 불러왔습니다.", messageService.bothReceivedMessage(sender, receiver));
    }


}