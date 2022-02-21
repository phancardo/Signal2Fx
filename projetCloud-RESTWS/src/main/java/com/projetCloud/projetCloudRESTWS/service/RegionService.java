package com.projetCloud.projetCloudRESTWS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetCloud.projetCloudRESTWS.model.Region;
import com.projetCloud.projetCloudRESTWS.repository.RegionRepository;

@Service
public class RegionService {
	
	@Autowired
	private RegionRepository regionRepository;
	
	public List<Region> getRegions(){
		return regionRepository.findAll();
	}
	
	public Region getRegion(final Long id){
		Region region = null;
		Optional<Region> regionOptional = regionRepository.findById(id);
		if(regionOptional.isPresent())
			region = regionOptional.get();
		return region;
	}
	
	public Region saveRegion(Region region) {
		Region savedRegion = regionRepository.save(region);
		return savedRegion;
	}
	
	public void deleteRegion(final Long id) {
		regionRepository.deleteById(id);
	}
	
	public Region updateRegion(final Long id,Region region) {
		Optional<Region> optRegion = regionRepository.findById(id);
		Region updatedRegion = null;
		if(optRegion.isPresent()) {
			Region currentRegion = optRegion.get();
			String nomRegion = region.getNom();
			if(nomRegion!=null)
				currentRegion.setNom(nomRegion);
			updatedRegion = this.saveRegion(currentRegion);
		}
		return updatedRegion;
	}
}
