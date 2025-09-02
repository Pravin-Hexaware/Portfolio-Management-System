package com.pms.backend.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.backend.entity.Benchmark;
 
public interface BenchmarkRepository extends JpaRepository<Benchmark, Long> {
}