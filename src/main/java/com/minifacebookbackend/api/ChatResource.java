package com.minifacebookbackend.api;

import com.minifacebookbackend.api.common.ResourcePath;
import com.minifacebookbackend.dto.ChatGPTRequest;
import com.minifacebookbackend.dto.ChatGPTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(ResourcePath.CHAT)
public class ChatResource {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;


    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
   public String chat(@RequestParam("prompt") String prompt){
       ChatGPTRequest chatGPTRequest=new ChatGPTRequest(model,prompt);
       ChatGPTResponse chatGPTResponse=restTemplate.postForObject(apiURL,chatGPTRequest,ChatGPTResponse.class);
       return  chatGPTResponse.getChoiceList().get(0).getMessage().getContent();
   }

}
