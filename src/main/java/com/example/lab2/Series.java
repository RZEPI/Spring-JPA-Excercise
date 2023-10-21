package com.example.lab2;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@Builder
@Table(name = "series")
public class Series implements Comparable<Series>, Serializable {
    @Id
    @Column(name = "series_name")
    private String title;
    @Column(name = "seasons")
    private int seasons;
    @ManyToOne
    @JoinColumn(name = "platform_name")
    private Platform platform;

    public Series() {

    }

    public void setPlatform(Platform platform)
    {
        this.platform = platform;
        platform.addSeries(this);
    }
    Series(String title, int seasons, Platform platform)
    {
        this.title = title;
        this.seasons = seasons;
        setPlatform(platform);
    }

    @Override
    public int compareTo(Series series) {
        if(this.title.compareTo(series.getTitle()) != 0)
            return this.title.compareTo(series.getTitle());
        else if (this.seasons == series.seasons)
            return this.platform.compareTo(series.getPlatform());
        else {
            if (this.seasons < series.getSeasons())
                return -1;
            else
                return 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Series series = (Series) o;
        return seasons == series.getSeasons() && Objects.equals(title, series.title) && Objects.equals(platform, series.getPlatform());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, seasons, platform);
    }

    @Override
    public String toString() {
        return "Series{" +
                "title='" + title + '\'' +
                ", seasons=" + seasons +
                ", platform=" + platform.getName() +
                '}';
    }
}
