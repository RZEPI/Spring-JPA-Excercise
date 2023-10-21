package com.example.lab2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements ApplicationRunner {
    private final PlatformService platformService;
    private final SeriesService seriesService;

    @Autowired
    public DataInitializer(PlatformService platformService, SeriesService seriesService) {
        this.platformService = platformService;
        this.seriesService = seriesService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Start");
    }
}
