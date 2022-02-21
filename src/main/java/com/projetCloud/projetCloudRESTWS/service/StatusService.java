package com.projetCloud.projetCloudRESTWS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetCloud.projetCloudRESTWS.model.Status;
import com.projetCloud.projetCloudRESTWS.repository.StatusRepository;

@Service
public class StatusService {
	
	@Autowired
	private StatusRepository statusRepo;
	
	public List<Status> getStatus(){
		return statusRepo.findAll();
	}
	
	public Status getStatus(final Long id) {
		Status status = null;
		Optional<Status> statusOptional= statusRepo.findById(id);
		if(statusOptional.isPresent())
			status = statusOptional.get();
		return status;
	}

	public Status getByStatusName(String statusName){
		Optional<Status> optStatus = statusRepo.findByStatus(statusName);
		Status status = null;
		if(optStatus.isPresent())
			status = optStatus.get();
		return status;
	}
	
	public Status saveStatus(Status status) {
		Status savedStatus = statusRepo.save(status);
		return savedStatus;
	}
	
	public void deleteStatus(final Long id) {
		statusRepo.deleteById(id);
	}
	
	public Status updateStatus(final Long id,Status status) {
		Status updatedStatus = null;
		Optional<Status> optStatus = statusRepo.findById(id);
		if(optStatus.isPresent()) {
			Status currentStatus = optStatus.get();
			String libelle = status.getStatus();
			if(libelle != null)
				currentStatus.setStatus(libelle);
			updatedStatus = this.saveStatus(currentStatus);
		}
		return updatedStatus;
	}
}
