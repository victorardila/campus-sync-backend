package com.facade.pattern.campus_sync.repositories.memory;

import com.facade.pattern.campus_sync.domains.Notification;
import com.facade.pattern.campus_sync.repositories.NotificationRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryNotificationRepository implements NotificationRepository {

    private Map<Long, Notification> notifcationMap = new HashMap<>();
    private long currentId = 1;

    @Override
    public Notification save(Notification notification) {
        if (notification.getId() == null) {
            notification.setId(currentId++);
        }
        notifcationMap.put(notification.getId(), notification);
        return notification;
    }

    @Override
    public List<Notification> findAll() {
        return new ArrayList<>(notifcationMap.values());
    }

    @Override
    public Optional<Notification> findById(Long id) {
        return Optional.ofNullable(notifcationMap.get(id));
    }

    @Override
    public void deleteById(Long id) {
        notifcationMap.remove(id);
    }

    @Override
    public List<Notification> saveAll(List<Notification> notifications) {
        for (Notification notification : notifications) {
            save(notification);
        }
        return notifications;
    }
}
