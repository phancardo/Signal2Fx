package com.projetCloud.projetCloudRESTWS.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetCloud.projetCloudRESTWS.model.Status;
import com.projetCloud.projetCloudRESTWS.service.StatusService;

@RestController
@RequestMapping("/api/v1")
public class StatusController {
	
	@Autowired
	private StatusService statusService;
	
	@GetMapping("/status")
	public List<Status> getStatus(){
		return statusService.getStatus();
	}
	
	@GetMapping("/status/{id}")
	public Status getStatus(@PathVariable("id") final Long id) {
		Status status = statusService.getStatus(id);
		return status;
	}
	
	@PutMapping("/status/{id}")
	public Status updateStatus(@PathVariable("id") final Long id,@RequestBody Status status) {
		Status updatedStatus = statusService.updateStatus(id, status);
		return updatedStatus;
	}
	
	@PostMapping("/status")
	public Status createStatus(@RequestBody Status status) {
		Status savedStatus = statusService.saveStatus(status);
		return savedStatus;
	}
	
	@DeleteMapping("/status/{id}")
	public void deleteStatus(@PathVariable("id") final Long id) {
		statusService.deleteStatus(id);
	}
}
