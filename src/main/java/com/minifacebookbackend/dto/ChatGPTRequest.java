package com.minifacebookbackend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatGPTRequest {

    private String model;
    private List<Message> messageList;

    public ChatGPTRequest(String model,String prompt) {
        this.model = model;
        this.messageList=new ArrayList<>();
        this.messageList.add(new Message("user",prompt));
    }
}
