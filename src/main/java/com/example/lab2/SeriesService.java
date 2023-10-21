package com.example.lab2;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesService {
    private final SeriesRepository seriesRepository;

    @Autowired
    public SeriesService(SeriesRepository seriesRepository)
    {
        this.seriesRepository = seriesRepository;
    }
    public List<Series> getSeriesByPlatform(Platform platform)
    {
        return seriesRepository.findByPlatform(platform);
    }

    public void save(Series series) {
        seriesRepository.save(series);
    }

    public List<Series> getAll()
    {
        return seriesRepository.findAll();
    }

    public void addSeries(Series newSeries)
    {
        seriesRepository.save(newSeries);
    }

    public Series getSeriesByName(String name)
    {
        return seriesRepository.findByTitle(name);
    }
    public void deleteSeries(Series series)
    {
        seriesRepository.delete(series);
    }
}
