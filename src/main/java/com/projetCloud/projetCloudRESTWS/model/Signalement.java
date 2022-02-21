package com.projetCloud.projetCloudRESTWS.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Table(name="signalement")
public class Signalement {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name="idtype")
	private Long idType;
	
	@Column(name="idregion")
	private Long idRegion;
	
	@Column(name="idutilisateur")
	private Long idUtilisateur;
	
	@Column(name="idstatus")
	private Long idStatus;

	@NotNull
	@NotEmpty
	private String description;
	
	private String image;

	@CreationTimestamp
	@Column(name="datesignalement")
	private Timestamp dateSignalement;
	
	private Double latitude;

	private Double longitude;
}