package com.projetCloud.projetCloudRESTWS.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import com.projetCloud.projetCloudRESTWS.model.*;
import com.projetCloud.projetCloudRESTWS.service.ImageSignalementService;
import com.projetCloud.projetCloudRESTWS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projetCloud.projetCloudRESTWS.service.SignalementService;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/v1")
public class SignalementController {
	
	@Autowired
	private SignalementService signalService;

	@Autowired
	private UserService userService;

	@Autowired
	private ImageSignalementService imageService;

	@RolesAllowed("USER")
	@PostMapping("/signalements")
	public Signalement createSignalement(@ModelAttribute Signalement signalement, @RequestParam("file") MultipartFile file, Principal principal) throws IOException {
		String email = principal.getName();
		User user = userService.getUserByEmail(email);
		signalement.setIdUtilisateur(user.getId());
		return signalService.saveSignalement(signalement,file);
	}


	@GetMapping("/signalements")
	public List<Signalement> getSignalements(Principal principal){
		String email = principal.getName();
		User user = userService.getUserByEmail(email);
		Long idRegion = null;
		Region region = user.getRegion();
		if(region!=null) {
			idRegion = region.getId();
		}
		Long roleId = Long.valueOf(2);
		Long idUser = null;
		if(user.getRoles().contains(new Role(roleId,"ROLE_USER"))){
			idUser = user.getId();
		}
		return signalService.getSignalements();
	}
	
	@GetMapping("/signalements/{id}")
	public Signalement getSignalement(@PathVariable("id") final Long id) {
		Signalement signalement = signalService.getSignalement(id);
		return signalement;
	}

	@RolesAllowed("CHIEF")
	@PutMapping("/signalements/{id}")
	public Signalement updateSignalement(@PathVariable("id") final Long id,@ModelAttribute Signalement signalement) {
		Signalement updatedSignalement = signalService.updateSignalement(id, signalement);
		return updatedSignalement;
	}

	@RolesAllowed("CHIEF")
	@DeleteMapping("/signalements/{id}")
	public void deleteSignalement(@PathVariable("id") final Long id) {
		signalService.deleteSignalement(id);
	}

	@RolesAllowed("USER")
	@GetMapping("/image/{id}")
	public ResponseEntity<?> getImage(@PathVariable("id") Long id) throws Exception {
		try
		{
			ImageSignalement imageSignalement = imageService.getImage(id);
			return ResponseEntity.ok()
					.contentType(MediaType.parseMediaType(imageSignalement.getType()))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imageSignalement.getNom() + "\"")
					.body(new ByteArrayResource(imageSignalement.getData()));
		}
		catch(Exception e)
		{
			throw new Exception("Error downloading file");
		}
	}
}
