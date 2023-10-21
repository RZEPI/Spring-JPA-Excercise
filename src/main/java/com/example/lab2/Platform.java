package com.example.lab2;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "platforms")
@Builder
public class Platform implements Comparable<Platform>, Serializable {

    @Id
    @Column(name = "platform_name")
    private String name;

    @Column(name = "users_count")
    private int usersCount;

    @OneToMany(mappedBy = "platform",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Series> series;

    Platform(String name, int usersCount, List<Series> series)
    {
        this.name = name;
        this.usersCount = usersCount;
        this.series = series;
    }

    public Platform() {
    }

    @Override
    public int compareTo(Platform o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Platform that = (Platform) o;
        return usersCount == that.usersCount && Objects.equals(name, that.name) && Objects.equals(series, that.series);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, usersCount);
    }

    @Override
    public String toString() {
        return "Platform{" +
                "name='" + name + '\'' +
                ", usersCount=" + usersCount +
                ", series=" + series +
                '}';
    }

    public void addSeries(Series series)
    {
        this.series.add(series);
    }
}
