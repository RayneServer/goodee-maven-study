package com.coma.study.rest;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/api/*")
@Slf4j
public class RestTestController {

	@GetMapping("list")
	public void method1() throws Exception {
		// RestTemplate restTemplate = new RestTemplate();
		// HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(null);
		// List<PhotoDTO> result = restTemplate.getForObject("https://jsonplaceholder.typicode.com/photos", List.class, entity);
		
		WebClient webClient = WebClient.builder()
																	 .baseUrl("https://jsonplaceholder.typicode.com")
																	 .build()
																	 ;
		
		Mono<ResponseEntity<PhotoDTO>> entity = webClient.get()
																									   .uri("/photos/1")
																									   .retrieve()
																									   .toEntity(PhotoDTO.class)
																									   ;
		
		PhotoDTO photoDTO = entity.block().getBody();
						 
						 
		log.info("{}", photoDTO);
		
		method2();
		method3();
	}
	
	private void method2() throws Exception {
		WebClient webClient = WebClient.builder()
																	 .baseUrl("https://jsonplaceholder.typicode.com")
																	 .build()
																	 ;

		Flux<UserDTO> entity = webClient.get()
																	  .uri("/users")
																	  .retrieve()
																	  .bodyToFlux(UserDTO.class)
																	  ;
		
		entity.subscribe((data) -> {
			UserDTO userDTO = data;
			log.info("{}", userDTO);			
		});
		
	}
	
	private void method3() throws Exception {
		PostDTO postDTO = new PostDTO();
		postDTO.setTitle("미안하다 이거 보여주려고 어그로 끌었다");
		postDTO.setBody("나루토 사스케 싸움 실화냐? 가슴이 웅장해진다...");
		postDTO.setUserId(1L);
		
		WebClient webClient = WebClient.builder()
																	 .baseUrl("https://jsonplaceholder.typicode.com/posts")
																	 .build()
																	 ;
		
		Mono<PostDTO> response = webClient.post()
																			.bodyValue(postDTO)
																			.accept(MediaType.APPLICATION_JSON)
																			.acceptCharset(StandardCharsets.UTF_8)
																			.retrieve()
																			.bodyToMono(PostDTO.class)
																			;
		
		log.info("{}", response.block());
	}
	
}
