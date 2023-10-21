package com.example.lab2;

import jdk.jfr.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeriesRepository extends JpaRepository<Series, String> {
    List<Series> findByPlatform(Platform platform);
    Series findByTitle(String name);
}
