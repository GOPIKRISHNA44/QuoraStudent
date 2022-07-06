package com.quorastudent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quorastudent.dto.QuestionDTO;

public interface QuestionRepository extends JpaRepository<QuestionDTO, Long> {

}
