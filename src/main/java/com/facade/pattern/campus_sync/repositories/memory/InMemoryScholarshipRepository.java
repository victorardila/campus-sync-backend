package com.facade.pattern.campus_sync.repositories.memory;

import com.facade.pattern.campus_sync.domains.Scholarship;
import com.facade.pattern.campus_sync.repositories.ScholarshipRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryScholarshipRepository implements ScholarshipRepository {
    private Map<Long, Scholarship> scholarshipMap = new HashMap<>();
    private long currentId = 1;

    @Override
    public Scholarship save(Scholarship scholarship) {
        if (scholarship.getId() == null) {
            scholarship.setId(currentId++);
        }
        scholarshipMap.put(scholarship.getId(), scholarship);
        return scholarship;
    }

    @Override
    public List<Scholarship> saveAll(List<Scholarship> scholarships) {
        for (Scholarship scholarship : scholarships) {
            save(scholarship);
        }
        return scholarships;
    }

    @Override
    public Scholarship findById(Long id) {
        return scholarshipMap.get(id);
    }

    @Override
    public void deleteById(Long id) {
        scholarshipMap.remove(id);
    }

    @Override
    public List<Scholarship> findAll() {
        return new ArrayList<>(scholarshipMap.values());
    }
}
