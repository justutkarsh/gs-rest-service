package dao.repository;

import dao.entitiy.PlayList;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface PlayListRepository extends CrudRepository<PlayList, Long> {
    List<PlayList> findById(Long id);
    List<PlayList> findAll();
    List<PlayList> findByTagsIn(Set<String> tags);
}
