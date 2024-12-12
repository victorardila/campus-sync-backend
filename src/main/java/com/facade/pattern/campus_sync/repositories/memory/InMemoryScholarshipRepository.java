package com.facade.pattern.campus_sync.repositories.memory;

import com.facade.pattern.campus_sync.domains.Scholarship;
import com.facade.pattern.campus_sync.repositories.ScholarshipRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public Optional<Scholarship> findById(Long id) {
        return Optional.ofNullable(scholarshipMap.get(id));
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
