package app;

import dao.entitiy.PlayList;
import dao.repository.PlayListRepository;
import dao.entitiy.Track;
import dao.repository.TrackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@ComponentScan("controller,domain,dao")
@EnableAutoConfiguration
@EnableJpaRepositories("dao")
@EntityScan("dao")

public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demoplaylists(PlayListRepository repository) {
        return (args) -> {
            // save a couple of PlayLists


            repository.save(new PlayList("playlist sample").setLikes(1).setPlays(2).
                    setTags(Stream.of("indie", "pop").collect(Collectors.toSet())));

            repository.save(new PlayList("playlist sample1").setLikes(100).setPlays(10)
                    .setTags(Stream.of("rock", "pop").collect(Collectors.toSet())));

            repository.save(new PlayList("Chloe O'Brian").setLikes(9)
                    .setTags(Stream.of("alternative").collect(Collectors.toSet())));

            repository.save(new PlayList("Kim Bauer").setLikes(10).setPlays(14)
                    .setTags(Stream.of("electronic", "indie").collect(Collectors.toSet())));

            repository.save(new PlayList("David Palmer").setLikes(5)
                    .setTags(Stream.of("metal", "pop").collect(Collectors.toSet())));

            repository.save(new PlayList("Michelle Dessler").setPlays(100)
                    .setTags(Stream.of("metal", "funk").collect(Collectors.toSet())));


            // fetch all PlayLists
            log.info("PlayLists found with findAll():");
            log.info("-------------------------------");
            for (PlayList playList : repository.findAll()) {
                log.info(playList.toString());
            }
            log.info("");

            // fetch an individual PlayList by ID
            PlayList PlayList = repository.findOne(1L);
            log.info("PlayList found with findOne(1L):");
            log.info("--------------------------------");
            log.info(PlayList.toString());
            log.info("");

            // fetch PlayLists by  tags
            log.info("PlayList found with findByTags('indie','pop'):");
            log.info("--------------------------------------------");
            for (PlayList pl : repository.findByTagsIn(Stream.of("indie","pop")
                    .collect(Collectors.toSet()))) {
                log.info(pl.toString());
            }
            log.info("");
        };
    }

    @Bean
    public CommandLineRunner demotracks(TrackRepository repository) {

        return (args) -> {
            // save a couple of tracks
            repository.save(new Track("moonlight", "Jack Bauer"));
            repository.save(new Track("shine", "Chloe O'Brian"));
            repository.save(new Track("syb", "Kim Bauer"));
            repository.save(new Track("breath", "David Palmer"));
            repository.save(new Track("myin", "Michelle Dessler"));

            // fetch all Tracks
            log.info("Tracks found with findAll():");
            log.info("-------------------------------");
            for (Track Track : repository.findAll()) {
                log.info(Track.toString());
            }
            log.info("");

            // fetch an individual Track by ID
            Track Track = repository.findOne(1L);
            log.info("Track found with findOne(1L):");
            log.info("--------------------------------");
            log.info(Track.toString());
            log.info("");

        };

    }
}
