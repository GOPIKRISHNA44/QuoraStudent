package com.quorastudent.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.quorastudent.dto.InterestsDTO;

public interface InterestsRepository extends JpaRepository<InterestsDTO, Integer> {

}
