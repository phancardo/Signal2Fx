package com.projetCloud.projetCloudRESTWS.controller;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import com.projetCloud.projetCloudRESTWS.model.Region;
import com.projetCloud.projetCloudRESTWS.model.Role;
import com.projetCloud.projetCloudRESTWS.model.User;
import com.projetCloud.projetCloudRESTWS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projetCloud.projetCloudRESTWS.model.SignalementDetails;
import com.projetCloud.projetCloudRESTWS.service.SignalementsDetailsService;

@RestController
@RequestMapping("/api/v1")
public class SignalementDetailsController {
	
	@Autowired
	private SignalementsDetailsService signalDetailsService;

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/signalementdetails",method=RequestMethod.GET)
	public List<SignalementDetails> getSignalementDetails(@RequestParam(name="idtype",required = false)Long idType, @RequestParam(name="idstatus",required = false) Long idStatus, Principal principal){
		String email = principal.getName();
		User user = userService.getUserByEmail(email);
		Long idUser = null;
		Set<Role> roles = user.getRoles();
		if(!roles.contains(new Role(Long.valueOf(2),"ROLE_CHIEF")) && !roles.contains(new Role(Long.valueOf(1),"ROLE_ADMIN")))
			idUser = user.getId();
		Long idRegion = null;
		Region region = user.getRegion();
		if(region!=null)
			idRegion = region.getId();
		return signalDetailsService.getSignalements(idUser,idRegion,idType,idStatus);
	}
	
	@RequestMapping(value="/signalementdetails/{id}",method=RequestMethod.GET)
	public SignalementDetails getSignalementDetails(@PathVariable("id") final Long id){
		SignalementDetails signalDetails = signalDetailsService.getSignalementDetails(id);
		return signalDetails;
	}
}
