package com.projetCloud.projetCloudRESTWS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projetCloud.projetCloudRESTWS.model.Signalement;

import java.util.List;

@Repository
public interface SignalementRepository extends JpaRepository<Signalement,Long>{

}
