package com.cotec.desafio.repository;

import com.cotec.desafio.model.Goal;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends PagingAndSortingRepository<Goal, Long> {

    List<Goal> findAllByOrganizationId(@Param("id") Long id);
}
