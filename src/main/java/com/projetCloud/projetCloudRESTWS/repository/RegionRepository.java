package com.projetCloud.projetCloudRESTWS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetCloud.projetCloudRESTWS.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region,Long>{

}
