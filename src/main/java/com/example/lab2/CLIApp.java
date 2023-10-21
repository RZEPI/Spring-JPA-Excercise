package com.example.lab2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class CLIApp implements CommandLineRunner, ApplicationRunner {
    private final PlatformService platformService;
    private final SeriesService seriesService;

    @Autowired
    public CLIApp(PlatformService platformService, SeriesService seriesService)
    {
        this.platformService = platformService;
        this.seriesService = seriesService;
    }


    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean end = false;
        while(!end)
        {
            System.out.print("Commands:\n1.List platforms\n2. List series\n3.Add new series\n4. Delete series\n5. Exit\nInput:");
            int command = scanner.nextInt();
            scanner.nextLine();

            switch (command)
            {
                case 1:
                    listPlatforms();
                break;
                case 2:
                    listSeries();
                break;
                case 3:
                    addNewSeries(scanner);
                break;
                case 4:
                    deleteSeries(scanner);
                break;
                case 5:
                    end = true;
                    break;
            }
        }
    }

    private void deleteSeries(Scanner scanner) {
        System.out.println("Insert series name: ");
        String name = scanner.nextLine();
        Series deleteSeries = seriesService.getSeriesByName(name);
        seriesService.deleteSeries(deleteSeries);
    }

    public void listPlatforms()
    {
        List<Platform> platformList = platformService.getAll();

        for(Platform plat : platformList)
            System.out.println(plat.getName());
    }
    public void listSeries()
    {
        List<Series> seriesList = seriesService.getAll();

        for(Series series : seriesList)
            System.out.println(series);
    }
    public void addNewSeries(Scanner scanner)
    {
        System.out.println("Name: ");
        String name = scanner.nextLine();
        System.out.println("Seasons amount: ");
        int seasons = scanner.nextInt();
        System.out.println("Platform(1 - Netflix, 2 - HBO, 3 - Prime): ");
        int platformNum = scanner.nextInt();
        Platform platform;
        if(platformNum == 1)
            platform = platformService.getPlatformByName("Netflix");
        else if (platformNum == 2)
            platform = platformService.getPlatformByName("HBO");
        else  if (platformNum == 3)
            platform = platformService.getPlatformByName("Prime video");
        else {
            System.out.println("Invalid platform name");
            return;
        }
        seriesService.addSeries(new Series(name, seasons, platform));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Platform> platforms = initPlatforms();
        for (Platform platform : platforms)
            platformService.save(platform);
        List<Series> seriesList = initSeries(platforms);
        for (Series series : seriesList)
            seriesService.save(series);

    }

    private List<Platform> initPlatforms()
    {
        return new ArrayList<>(){{
            add(Platform.builder()
                    .name("Netflix")
                    .usersCount(1000000)
                    .series(new ArrayList<>())
                    .build());
            add(Platform.builder()
                    .name("HBO")
                    .usersCount(25000)
                    .series(new ArrayList<>())
                    .build());
            add(Platform.builder()
                    .name("Prime video")
                    .usersCount(12000)
                    .series(new ArrayList<>())
                    .build());
        }};
    }
    private List<Series> initSeries(List<Platform> platforms)
    {
        return new ArrayList<>(){{
            add(Series.builder()
                    .title("Vikings")
                    .seasons(6)
                    .platform(platforms.get(0))
                    .build());
            add(Series.builder()
                    .title("Lucifer")
                    .seasons(6)
                    .platform(platforms.get(0))
                    .build());
            add(Series.builder()
                    .title("One Peace")
                    .seasons(1)
                    .platform(platforms.get(0))
                    .build());
            add(Series.builder()
                    .title("Lupin")
                    .seasons(3)
                    .platform(platforms.get(0))
                    .build());
            add(Series.builder()
                    .title("Doom patrol")
                    .seasons(4)
                    .platform(platforms.get(1))
                    .build());
            add(Series.builder()
                    .title("The Last of Us")
                    .seasons(1)
                    .platform(platforms.get(1))
                    .build());
            add(Series.builder()
                    .title("Twisted Metal")
                    .seasons(1)
                    .platform(platforms.get(1))
                    .build());
            add(Series.builder()
                    .title("Peacemaker")
                    .seasons(1)
                    .platform(platforms.get(1))
                    .build());

            add(Series.builder()
                    .title("The Legend of Vox Machina")
                    .seasons(2)
                    .platform(platforms.get(2))
                    .build());
            add(Series.builder()
                    .title("The Boys")
                    .seasons(3)
                    .platform(platforms.get(2))
                    .build());
            add(Series.builder()
                    .title("Gen V")
                    .seasons(1)
                    .platform(platforms.get(2))
                    .build());
            add(Series.builder()
                    .title("Continental")
                    .seasons(1)
                    .platform(platforms.get(2))
                    .build());
        }};
    }
}
