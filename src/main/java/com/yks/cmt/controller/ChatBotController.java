package com.yks.cmt.controller;


import com.yks.cmt.service.ChatBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chatbot")
public class ChatBotController {
    @Autowired
    private ChatBotService chatBotService;

    @GetMapping("/askQuestion")
    public ResponseEntity<?> askQuestion(@RequestParam("question") String question){

        return chatBotService.askQuestion(question);
    }
}
