package com.example.hiber.controllers;

import com.example.hiber.model.entity.CredentialEntity;
import com.example.hiber.model.entity.CredentialEntityInfo;
import com.example.hiber.services.CredentialService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/main")
@RequiredArgsConstructor
public class MainController {

	private final CredentialService credentialService;

	@GetMapping("/credentials/{id}")
	public ResponseEntity<List<CredentialEntityInfo>> getCredentials(@RequestParam Integer page, @RequestParam Integer size) {

		List<CredentialEntityInfo> credentials = this.credentialService.getCredentials(page, size);

		return ResponseEntity.ok(credentials);

	}

	public ResponseEntity<Void> addCredential(@RequestBody CredentialEntity credential) {

		this.credentialService.saveCredential(credential);
		return ResponseEntity.ok().build();
	}

}
