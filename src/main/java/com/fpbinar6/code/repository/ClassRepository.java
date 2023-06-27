package com.fpbinar6.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpbinar6.code.models.Class;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {

}
