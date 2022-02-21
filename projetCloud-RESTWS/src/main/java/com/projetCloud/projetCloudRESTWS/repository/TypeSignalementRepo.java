package com.projetCloud.projetCloudRESTWS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetCloud.projetCloudRESTWS.model.TypeSignalement;

@Repository
public interface TypeSignalementRepo extends JpaRepository<TypeSignalement,Long>{

}
