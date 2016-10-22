package controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import dao.entitiy.Track;
import dao.TrackDAO;
import domain.TrackDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
/*
GET /tracks - Retrieves list of tracks for playlist #12
GET /tracks/5 - Retrieves track #5 for playlist #12
POST /tracks - Creates a new track in playlist #12
PUT /tracks/5 - Updates track #5 for playlist #12
PATCH /tracks/5 - Partially updates track #5 for playlist #12
DELETE /tracks/5 - Deletes track #5 for playlist #12
GET /tracks/5/play - increment play count of a specific playlist track
GET /tracks/5/like- increment like count of a specific playlist track
 */


public class TrackController {

    @Autowired
    private TrackDAO dao;

    @RequestMapping("/tracks/")
    public ResponseEntity<List<TrackDTO>> listAllTracks() {
        List<TrackDTO> tracks = dao.findAll();
        if (tracks.isEmpty())
            return new ResponseEntity<List<TrackDTO>>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<TrackDTO>>(tracks,HttpStatus.OK);
    }


    //Retrieve Single Track

    @RequestMapping(path = "/tracks/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TrackDTO> getTrack(@PathVariable("id") long id) {
        System.out.println("Fetching Track with id " + id);
        TrackDTO track = dao.findById(id);
        if (track == null) {
            System.out.println("Track with id " + id + " not found");
            return new ResponseEntity<TrackDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<TrackDTO>(track,HttpStatus.OK);
    }



    //Create a Track

    @RequestMapping(path = "/tracks/", method = RequestMethod.POST)
    public ResponseEntity<TrackDTO> createTrack(@RequestBody TrackDTO track, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Track " + track.getName());

        if (dao.exists(track.getId())) {
            System.out.println("A Track with name " + track.getName() + " already exist");
            return  new ResponseEntity<TrackDTO>(HttpStatus.ALREADY_REPORTED);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/Tracks/{id}").buildAndExpand(track.getId()).toUri());
        return new ResponseEntity<TrackDTO>(dao.saveTrack(track),HttpStatus.OK);
    }


    //Update a Track

    @RequestMapping(path = "/tracks/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TrackDTO> updateTrack(@PathVariable("id") long id, @RequestBody TrackDTO track) {
        System.out.println("Updating Track " + id);

        TrackDTO currentTrack = dao.findById(id);
        if (currentTrack==null) {
            System.out.println("Track with id " + id + " not found");
            return new ResponseEntity<TrackDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<TrackDTO>(dao.saveTrack(track),HttpStatus.OK);
    }

    //Delete a Track

    @RequestMapping(path = "/tracks/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<TrackDTO> deleteTrack(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Track with id " + id);

        TrackDTO Track = dao.findById(id);
        if (Track == null) {
            System.out.println("Unable to delete. Track with id " + id + " not found");
            return new ResponseEntity<TrackDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<TrackDTO>(HttpStatus.NO_CONTENT);
    }


    //Delete All Tracks

    @RequestMapping(path = "/tracks/", method = RequestMethod.DELETE)
    public ResponseEntity<Track> deleteAllTracks() {
        System.out.println("Deleting All Tracks");

        dao.deleteAllTracks();
        return new ResponseEntity<Track>(HttpStatus.NO_CONTENT);
    }

}
