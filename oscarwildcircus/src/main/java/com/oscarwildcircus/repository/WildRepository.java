package com.oscarwildcircus.repository;

import com.oscarwildcircus.entity.Wild;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WildRepository extends JpaRepository<Wild,Long > {
}
