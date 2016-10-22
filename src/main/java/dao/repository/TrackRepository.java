package dao.repository;

import dao.entitiy.Track;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrackRepository extends CrudRepository<Track, Long> {

    List<Track> findById(Long id);
    List<Track> findAll();

}
