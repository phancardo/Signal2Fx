package com.projetCloud.projetCloudRESTWS.repository;

import com.projetCloud.projetCloudRESTWS.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<ConfirmationToken,Long> {
}
