package com.pms.backend.serviceimpl;
 
import org.springframework.stereotype.Service;
 
import com.pms.backend.entity.Benchmark;
import com.pms.backend.entity.Rebalance;
import com.pms.backend.entity.Type;
import com.pms.backend.repository.BenchmarkRepository;
import com.pms.backend.repository.RebalanceRepository;
import com.pms.backend.repository.TypeRepository;
 
import java.util.List;
 
@Service
public class DropdownService {
 
    private final BenchmarkRepository benchmarkRepo;
    private final TypeRepository typeRepo;
    private final RebalanceRepository rebalanceRepo;
 
    public DropdownService(BenchmarkRepository benchmarkRepo, TypeRepository typeRepo, RebalanceRepository rebalanceRepo) {
        this.benchmarkRepo = benchmarkRepo;
        this.typeRepo = typeRepo;
        this.rebalanceRepo = rebalanceRepo;
    }
 
    public List<Benchmark> getBenchmarks() {
        return benchmarkRepo.findAll();
    }
 
    public List<Type> getTypes() {
        return typeRepo.findAll();
    }
 
    public List<Rebalance> getRebalances() {
        return rebalanceRepo.findAll();
    }
}