package com.oscarwildcircus.repository;

import com.oscarwildcircus.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}
