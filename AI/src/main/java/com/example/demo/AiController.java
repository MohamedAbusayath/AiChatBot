package com.example.demo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ollama")
@CrossOrigin(origins = "*")
public class AiController {

	private  ChatClient client;

	public AiController(OllamaChatModel chatModel) {
		this.client = ChatClient.create(chatModel);
	}

	@GetMapping("/{msg}")
	public ResponseEntity<String> getAnswer(@PathVariable String msg) {
		ChatResponse chatResponse = client.prompt(msg).call().chatResponse();
		System.out.println(chatResponse.getMetadata().getModel());
		String response = chatResponse.getResult().getOutput().getText();
		return ResponseEntity.ok(response);
	}

}
