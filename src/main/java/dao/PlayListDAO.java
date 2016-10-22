package dao;

import dao.entitiy.PlayList;
import dao.repository.PlayListRepository;
import domain.PlayListDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by utkarsh on 14-08-2016.
 */

@Component
public class PlayListDAO {

    @Autowired
    PlayListRepository repository;

    public List<PlayListDTO> findByTags(Set<String> tags) {
        return repository.findByTagsIn(tags).stream().
                sorted(Comparator.comparing(PlayList::getLikes).
                        thenComparing(PlayList::getPlays).reversed()).
                map(list -> contructPlayListDTO(list)).collect(Collectors.toList());

    }

    public List<PlayListDTO> findAll() {
        return repository.findAll().stream().
                map(list -> contructPlayListDTO(list)).collect(Collectors.toList());
    }

    public PlayListDTO findById(Long id){
        return contructPlayListDTO(repository.findOne(id));
    }



  public Boolean exists(Long id){
      return repository.exists(id);
  }

    private PlayListDTO contructPlayListDTO(PlayList playList) {
        PlayListDTO dto = new PlayListDTO();
        BeanUtils.copyProperties(playList, dto);
        return dto;
    }

    public PlayListDTO savePlayList(PlayListDTO playListDTO) {
        PlayList playList = new PlayList();
        BeanUtils.copyProperties(playListDTO, playList);
        return contructPlayListDTO(repository.save(playList));
    }

    public void deletePlayListById(long id) {
        repository.delete(id);
    }

    public void deleteAllPlayLists() {
        repository.deleteAll();
    }
}



