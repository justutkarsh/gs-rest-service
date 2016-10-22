package controller;

import dao.entitiy.PlayList;
import domain.PlayListDTO;
import dao.PlayListDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/*
GET /playlists- Retrieves a list of playlists
GET /playlists/12 - Retrieves a specific playlist
POST /playlists - Creates a new playlist
PUT /playlists/12 - Updates playlist #12
PATCH /playlists/12 - Partially updates playlist #12
DELETE /playlists/12 - Deletes playlist #12

GET /playlists/12/play - increment play count of a specific playlist
GET /playlists/12/like - increment like count of a specific playlist


GET /playlists/12/tracks - Retrieves list of tracks for playlist #12
GET /playlists/12/tracks/5 - Retrieves track #5 for playlist #12
POST /playlists/12/tracks - Creates a new track in playlist #12
PUT /playlists/12/tracks/5 - Updates track #5 for playlist #12
PATCH /playlists/12/tracks/5 - Partially updates track #5 for playlist #12
DELETE /playlists/12/tracks/5 - Deletes track #5 for playlist #12


GET /playlists/12/tracks/5/play - increment play count of a specific playlist track
GET /playlists/12/tracks/5/like- increment like count of a specific playlist track

 */

@RestController
public class PlayListController {

    @Autowired
    private PlayListDAO dao;

   //Retrive all playlists
    @RequestMapping("/playlists/")
    public ResponseEntity<List<PlayListDTO>> listAllPlayLists() {
        List<PlayListDTO> playLists = dao.findAll();
        if(playLists.isEmpty())
            return new ResponseEntity<List<PlayListDTO>>(HttpStatus.NO_CONTENT);
        return new  ResponseEntity<List<PlayListDTO>>(playLists, HttpStatus.OK);
    }

    //Retrieve Single PlayList
    @RequestMapping(path = "/playlists/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayListDTO> getPlayList(@PathVariable("id") long id) {
        System.out.println("Fetching Playlist with id " + id);
        PlayListDTO playList = dao.findById(id);
        if (playList == null) {
            System.out.println("Playlist with id " + id + " not found");
            return new ResponseEntity<PlayListDTO>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<PlayListDTO>(playList, HttpStatus.OK);
    }



    //Create a PlayList

    @RequestMapping(path = "/playlists/", method = RequestMethod.POST)
    public ResponseEntity<PlayListDTO> createPlayList(@RequestBody PlayListDTO playList, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating PlayList " + playList.getName());

        if (dao.exists(playList.getId())) {
            System.out.println("A PlayList with name " + playList.getName() + " already exist");
            return new ResponseEntity<PlayListDTO>(HttpStatus.CONFLICT);
        }

        PlayListDTO playListDTO = dao.savePlayList(playList);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/playlists/{id}").buildAndExpand(playList.getId()).toUri());
        return new ResponseEntity<PlayListDTO>(playListDTO,headers,HttpStatus.OK);
    }


    //Update a PlayList

    @RequestMapping(path = "/playlists/{id}", method = RequestMethod.PUT)
    public ResponseEntity<PlayListDTO> updatePlayList(@PathVariable("id") long id, @RequestBody PlayListDTO playList) {
        System.out.println("Updating PlayList " + id);

        PlayListDTO currentPlayList = dao.findById(id);
        if (currentPlayList==null) {
            System.out.println("PlayList with id " + id + " not found");
            return  new ResponseEntity<PlayListDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<PlayListDTO>(dao.savePlayList(playList),HttpStatus.OK);
    }

    //Delete a PlayList

    @RequestMapping(path = "/playlists/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<PlayListDTO> deletePlayList(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting PlayList with id " + id);

        PlayListDTO PlayList = dao.findById(id);
        if (PlayList == null) {
            System.out.println("Unable to delete. PlayList with id " + id + " not found");
            return  new ResponseEntity<PlayListDTO>(HttpStatus.NOT_FOUND);
        }

        dao.deletePlayListById(id);
        return new ResponseEntity<PlayListDTO>(HttpStatus.NO_CONTENT);
    }


    //Delete All PlayLists

    @RequestMapping(path = "/playlists/", method = RequestMethod.DELETE)
    public ResponseEntity<PlayList> deleteAllPlayLists() {
        System.out.println("Deleting All PlayLists");
        dao.deleteAllPlayLists();
        return new ResponseEntity<PlayList>(HttpStatus.NO_CONTENT);
    }

}
