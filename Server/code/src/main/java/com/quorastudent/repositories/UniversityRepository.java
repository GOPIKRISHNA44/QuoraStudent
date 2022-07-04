package com.quorastudent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quorastudent.dto.UniversityDTO;

public interface UniversityRepository extends JpaRepository<UniversityDTO, Integer> {

}
