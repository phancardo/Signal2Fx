package com.projetCloud.projetCloudRESTWS.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import com.projetCloud.projetCloudRESTWS.model.ImageSignalement;
import com.projetCloud.projetCloudRESTWS.model.Status;
import com.projetCloud.projetCloudRESTWS.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetCloud.projetCloudRESTWS.model.Signalement;
import com.projetCloud.projetCloudRESTWS.repository.SignalementRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class SignalementService {
	
	@Autowired
	private SignalementRepository signalementRepository;

	@Autowired
	private StatusService statusService;

	@Autowired
	private ImageSignalementService imageService;
	
	public List<Signalement> getSignalements(){
		return signalementRepository.findAll();
	}
	
	public Signalement getSignalement(final Long id){
		Signalement signalement = null;
		Optional<Signalement> signalementOpt = signalementRepository.findById(id);
		if(signalementOpt.isPresent())
			signalement = signalementOpt.get();
		return signalement;
	}

	public Signalement saveSignalement(Signalement signalement, MultipartFile file) throws IOException {
		Signalement savedSignalement = signalementRepository.save(signalement);
		Status  status = statusService.getByStatusName("Nouveau");
		if(status!=null)
			savedSignalement.setIdStatus(status.getId());
		ImageSignalement imageSignalement = imageService.saveImage(savedSignalement.getId(), file);
		return savedSignalement;
	}
	
	public void deleteSignalement(final Long id) {
		signalementRepository.deleteById(id);
	}
	
	public Signalement updateSignalement(final Long id,Signalement signalement) {
		Optional<Signalement> signal = signalementRepository.findById(id);
		Signalement updatedSignalement = null;
		if(signal.isPresent()) {
			Signalement currentSignal = signal.get();
			Long idUtilisateur = signalement.getIdUtilisateur();
			Long idType = signalement.getIdType();
			Long idRegion = signalement.getIdRegion();
			Long idStatus = signalement.getIdStatus();
			Timestamp dateSignalement = signalement.getDateSignalement();
			String image = signalement.getImage();
			Double latitude = signalement.getLatitude();
			Double longitude = signalement.getLongitude();
			if(idUtilisateur != null)
				currentSignal.setIdUtilisateur(idUtilisateur);
			if(idType != null)
				currentSignal.setIdType(idType);
			if(idRegion != null)
				currentSignal.setIdRegion(idRegion);
			if(idStatus != null)
				currentSignal.setIdStatus(idStatus);
			if(dateSignalement != null)
				currentSignal.setDateSignalement(dateSignalement);
			if(image != null)
				currentSignal.setImage(image);
			if(latitude !=null)
				currentSignal.setLatitude(latitude);
			if(longitude != null)
				currentSignal.setLongitude(longitude);
			updatedSignalement = signalementRepository.save(currentSignal);
		}
		return updatedSignalement;
	}
}
