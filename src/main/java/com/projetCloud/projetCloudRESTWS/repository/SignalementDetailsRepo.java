package com.projetCloud.projetCloudRESTWS.repository;

import java.util.List;

import com.projetCloud.projetCloudRESTWS.model.Signalement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projetCloud.projetCloudRESTWS.model.SignalementDetails;

@Repository
public interface SignalementDetailsRepo extends JpaRepository<SignalementDetails,Long>{

	@Query(value="SELECT * FROM view_signalementDetails " +
			"WHERE (:iduser is null or idutilisateur=CAST(CAST(:iduser AS VARCHAR) as INTEGER)) AND" +
			" (:idregion is null or idregion=CAST(CAST(:idregion AS VARCHAR) as INTEGER)) AND" +
			" (:idtype is null or idtype=CAST(CAST(:idtype AS VARCHAR) as INTEGER)) AND" +
			"(:idstatus is null or idstatus=CAST(CAST(:idstatus AS VARCHAR) as INTEGER))"
			,nativeQuery = true)
	List<SignalementDetails> getSignalements(@Param("iduser") final Long idUser, @Param("idregion") final Long idRegion, @Param("idtype") final Long idType, @Param("idstatus") final Long idStatus);

	@Query(value="SELECT * FROM view_signalementDetails WHERE idRegion = ?1",nativeQuery=true)
	List<SignalementDetails> findByRegion(final Long idRegion);

	@Query(value="SELECT * FROM view_signalementDetails WHERE idUtilisateur = ?1",nativeQuery=true)
	List<SignalementDetails> findByUser(final Long idUtilisateur);

	@Query(value="SELECT * FROM view_signalementDetails WHERE idUtilisateur = ?1 AND idRegion = ?2",nativeQuery=true)
	List<SignalementDetails> findByUserAndRegion(final Long idUtilisateur,final Long idRegion);

	@Query(value="SELECT * FROM view_signalementDetails WHERE idType = ?1",nativeQuery=true)
	List<SignalementDetails> findByType(final Long idType);

	@Query(value="SELECT * FROM view_signalementDetails WHERE idUtilisateur = ?1 AND idType = ?2",nativeQuery=true)
	List<SignalementDetails> findByUserAndType(final Long idUtilisateur,final Long idType);

	@Query(value="SELECT * FROM view_signalementDetails WHERE idStatus = ?1",nativeQuery=true)
	List<SignalementDetails> findByStatus(final Long idStatus);

	@Query(value="SELECT * FROM view_signalementDetails WHERE idUtilisateur = ?1 AND idStatus = ?2",nativeQuery=true)
	List<SignalementDetails> findByUserAndStatus(final Long idUtilisateur,final Long idStatus);

	@Query(value="SELECT * FROM view_signalementDetails WHERE dateSignalement > TO_TIMESTAMP(?1,'YYYY-MM-DD') AND dateSignalement < TO_TIMESTAMP(?2,'YYYY-MM-DD')",nativeQuery=true)
	List<SignalementDetails> findBetweenDates(String dateDebut,String dateFin);

	@Query(value="SELECT * FROM view_signalementDetails WHERE idUtilisateur = ?1 AND dateSignalement > TO_TIMESTAMP(?2,'YYYY-MM-DD') AND dateSignalement < TO_TIMESTAMP(?3,'YYYY-MM-DD')",nativeQuery=true)
	List<SignalementDetails> findByUserBetweenDates(final Long idUtilisateur,String dateDebut,String dateFin);
}
