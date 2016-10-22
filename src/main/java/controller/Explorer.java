package controller;

import domain.PlayListDTO;
import dao.PlayListDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
public class Explorer {

    @Autowired
    PlayListDAO dao;


    @RequestMapping("/explore/{tags}")
    public List<PlayListDTO> explore(@PathVariable String tags) {
        return dao.findByTags( Arrays.stream(tags.split(Pattern.quote("+"))).
                collect(Collectors.toSet()));
    }


}
