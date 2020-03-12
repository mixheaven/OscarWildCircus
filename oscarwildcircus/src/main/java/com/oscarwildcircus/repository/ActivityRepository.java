package com.oscarwildcircus.repository;

import com.oscarwildcircus.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long>{
}
