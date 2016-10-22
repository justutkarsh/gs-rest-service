package dao;

import dao.entitiy.Track;
import dao.repository.TrackRepository;
import domain.TrackDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by utkarsh on 14-08-2016.
 */
@Component

public class TrackDAO {

    @Autowired
    TrackRepository repository;

    private TrackDTO contructTrackDTO(Track track) {
        TrackDTO dto = new TrackDTO();
        BeanUtils.copyProperties(track, dto);
        return dto;

    }

    public TrackDTO findById(long id) {
        return contructTrackDTO(repository.findOne(id));
    }

    public boolean exists(long id) {
        return repository.exists(id);
    }


    public List<TrackDTO> findAll() {
        return repository.findAll().stream().
                map(list -> contructTrackDTO(list)).collect(Collectors.toList());
    }

    public TrackDTO findById(Long id){
        return contructTrackDTO(repository.findOne(id));
    }


    public Boolean exists(Long id){
        return repository.exists(id);
    }


    public TrackDTO saveTrack(TrackDTO trackDTO) {
        Track track = new Track();
        BeanUtils.copyProperties(trackDTO,track);
        return contructTrackDTO(repository.save(track));
    }

    public void deleteTrackById(long id) {
        repository.delete(id);
    }

    public void deleteAllTracks() {
        repository.deleteAll();
    }
}
