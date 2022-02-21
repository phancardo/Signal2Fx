package com.projetCloud.projetCloudRESTWS.service;

import java.util.List;
import java.util.Optional;

import com.projetCloud.projetCloudRESTWS.model.Signalement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetCloud.projetCloudRESTWS.model.SignalementDetails;
import com.projetCloud.projetCloudRESTWS.repository.SignalementDetailsRepo;

@Service
public class SignalementsDetailsService {
	
	@Autowired
	private SignalementDetailsRepo signalDetailsRepo;

	public List<SignalementDetails> getSignalements(final Long idUser,final Long idRegion,final Long idType,final Long idStatus){
		return signalDetailsRepo.getSignalements(idUser,idRegion,idType,idStatus);
	}
	
	public List<SignalementDetails> getSignalementDetails() {
		return signalDetailsRepo.findAll();
	}
	
	public SignalementDetails getSignalementDetails(final Long id){
		SignalementDetails signalementDetails = null;
		Optional<SignalementDetails> signalDetailsOpt = signalDetailsRepo.findById(id);
		if(signalDetailsOpt.isPresent())
			signalementDetails = signalDetailsOpt.get();
		return signalementDetails;
	}

	public List<SignalementDetails> getSignalementsDetailsByRegion(final Long idRegion){
		return signalDetailsRepo.findByRegion(idRegion);
	}

	public List<SignalementDetails> getSignalementDetailsByStatus(final Long idStatus){
		return signalDetailsRepo.findByStatus(idStatus);
	}

	public List<SignalementDetails> getSignalementDetailsByType(final Long idType){
		return signalDetailsRepo.findByType(idType);
	}

	public List<SignalementDetails> getSignalementDetailsBetween(String dateDebut,String dateFin){
		return signalDetailsRepo.findBetweenDates(dateDebut, dateFin);
	}

	public List<SignalementDetails> getSignalementUtilisateur(final Long idUser){
		return signalDetailsRepo.findByUser(idUser);
	}

	public List<SignalementDetails> getSignalementByUserInRegion(final Long idUser,final Long idRegion){
		return signalDetailsRepo.findByUserAndRegion(idUser, idRegion);
	}

	public List<SignalementDetails> getSignalementByUserAndType(final Long idUser,final Long idType){
		return signalDetailsRepo.findByUserAndType(idUser, idType);
	}

	public List<SignalementDetails> getSignalementByUserAndStatus(final Long idUser,final Long idType){
		return signalDetailsRepo.findByUserAndStatus(idUser, idType);
	}

	public List<SignalementDetails> getSignalementByUserBetween(final Long idUser,String dateDebut,String dateFin){
		return signalDetailsRepo.findByUserBetweenDates(idUser, dateDebut, dateFin);
	}
}
