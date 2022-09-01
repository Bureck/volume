package com.burek.volume.Repositories;

import com.burek.volume.Classes.Plan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends CrudRepository<Plan, Long> {
    Plan findByUserId(long id);
}
