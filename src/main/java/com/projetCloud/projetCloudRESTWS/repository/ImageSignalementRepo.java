package com.projetCloud.projetCloudRESTWS.repository;

import com.projetCloud.projetCloudRESTWS.model.ImageSignalement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageSignalementRepo extends JpaRepository<ImageSignalement,Long> {
}
