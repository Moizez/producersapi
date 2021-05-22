package com.producersapi.resource;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.producersapi.service.EmailService;
import com.producersapi.model.Manager;
import com.producersapi.service.ManagerService;
import com.producersapi.util.EntityResource;
import com.producersapi.util.Response;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/recovery")
public class PasswordResource extends Response<Manager> {

	@Autowired
	private ManagerService service;
	
	@Autowired 
	private EmailService emailService;

	// Prazo de validade do link de redefinição de senha
	long prazo = 60; // minutos
	
	@PostMapping("/")
	public ResponseEntity<?> SendEmail(@RequestParam String email) throws Exception {
		
		Manager manager = service.findByEmail(email); 
		if(manager != null) {
			String password = manager.getPassword();
			long time = new Date().getTime();
			String hash = gerarHash(""+password+time);
			String host = "producerpoint.serviceapp.net.br";
			String link = "https://"+host+"/recovery/"+email+"/"+time+"/"+hash;
			try {
				System.out.println("Enviando e-mail para "+email);
				emailService.send(
    				"noreply@serviceapp.net.br",
    				email,
    				"Redefinição de senha Produceer Point",
    				"Esse é um email de recuperação de senha do Producer Point. \n "
    				+"Para redefinir sua senha acesse o link: "
    				+ link
					+ "\n\nEquipe Producer Point"
		        );
				return ResponseEntity.ok().build();
			} catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{email}/{time}/{hash}")
	public ResponseEntity<?> LinkValidate(
			@PathVariable String email,
			@PathVariable long time,
			@PathVariable String hash
		) throws Exception {
		Manager manager = service.findByEmail(email); 
		String password = manager.getPassword();
		String check = gerarHash(""+password+time);
		int tempo = (int) ((new Date().getTime()-time)/1000/60);
		if(hash.equals(check) && tempo<=prazo) {
			return ResponseEntity.ok(manager);
		};
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/")
	public ResponseEntity<?> SetPassword(
			@RequestBody Map<String, String> params
		) throws Exception {
		
		String email = (String) params.get("email");
		long time = Long.parseLong(params.get("time"));
		String hash = (String) params.get("token");
		String newPassword = (String) params.get("newPassword");
		
		Manager manager = service.findByEmail(email); 
		if(manager != null) {
			String password = manager.getPassword();
			String check = gerarHash(""+password+time);
			int tempo = (int) ((new Date().getTime()-time)/1000/60);
			if(hash.equals(check) && tempo<=prazo) {
				manager.setPassword(newPassword);
				service.save(manager);
				return ResponseEntity.ok(manager);
			};
		}
		return ResponseEntity.notFound().build();
	}
	
	public static String gerarHash(String text) throws Exception {
		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		byte hash[] = algorithm.digest(text.getBytes("UTF-8"));
		StringBuilder outText = new StringBuilder();
		for (byte b : hash) {
			outText.append(String.format("%02X", 0xFF & b));
		}
		return outText.toString();
	}

}