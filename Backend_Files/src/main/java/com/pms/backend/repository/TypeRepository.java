package com.pms.backend.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import com.pms.backend.entity.Type;
 
public interface TypeRepository extends JpaRepository<Type, Long>{
 
  
}