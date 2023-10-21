package com.example.lab2;


import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlatformService {
    private final PlatformRepository platformRepository;

    @Autowired
    public PlatformService(PlatformRepository platformRepository)
    {
        this.platformRepository = platformRepository;
    }

    @Transactional
    public Platform getPlatformByName(String name) {
        Platform selectedPlatform = platformRepository.findByName(name);
        Hibernate.initialize(selectedPlatform.getSeries());
        return selectedPlatform;
    }

    public void save(Platform platform) {
        platformRepository.save(platform);
    }

    public List<Platform> getAll()
    {
        return platformRepository.findAll();
    }
}
