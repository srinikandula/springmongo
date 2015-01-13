package com.beakyn.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.beakyn.model.BusImages;

@Repository
public interface BusinessImagesDAO extends PagingAndSortingRepository<BusImages, String> {

	BusImages findOneByBusinessId(String email);
    Page<BusImages> findAll(Pageable pageable);
}
