package com.producersapi.resource;

import java.security.MessageDigest;
import java.util.Date;
import java.util.List;
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

	@PostMapping("/")
	public ResponseEntity<?> SendEmail(@RequestParam String email) throws Exception {
		
		Manager manager = service.findByEmail(email); 
		if(manager != null) {
			String password = manager.getPassword();
			long time = new Date().getTime();
			String hash = gerarHash(time+password);
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
		String check = gerarHash(time+password);
		int tempo = (int) ((new Date().getTime()-time)/1000/60);
		if(hash.equals(check) && tempo<=10) {
			return ResponseEntity.ok(manager);
		};
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/")
	public ResponseEntity<?> SetPassword(
			String email,
			long time,
			String hash,
			String newPassword
		) throws Exception {
		Manager manager = service.findByEmail(email); 
		String password = manager.getPassword();
		String check = gerarHash(time+password);
		int tempo = (int) ((new Date().getTime()-time)/1000/60);
		if(hash.equals(check) && tempo<=10) {
			manager.setPassword(newPassword);
			return ResponseEntity.ok(manager);
		};
		return ResponseEntity.notFound().build();
	}
	
	public static String gerarHash(String senha) throws Exception {
		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		byte hash[] = algorithm.digest(senha.getBytes("UTF-8"));
		StringBuilder texto = new StringBuilder();
		for (byte b : hash) {
			texto.append(String.format("%02X", 0xFF & b));
		}
		return texto.toString();
	}

}