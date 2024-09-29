package com.eventify.backend.controllers;

import com.eventify.backend.entities.Image;
import com.eventify.backend.services.servicesInter.ImageServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/image")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ImageController {

    @Autowired
    ImageServiceInter imageServiceInter;

    @PostMapping("/upload/event/{idEvent}")
    public ResponseEntity<String> uploadEventImage(@RequestParam("eventFile") MultipartFile file, @PathVariable Long idEvent) throws IOException {
        return imageServiceInter.uploadEventImage(file,idEvent);
    }

    @GetMapping("/event/{idEvent}")
    public ResponseEntity<Image> getImageByEventId(@PathVariable Long idEvent)
    {
        return imageServiceInter.getEventImage(idEvent);

    }

    @PutMapping("/event/{idEvent}")
    public ResponseEntity<String> updateEventImage(@RequestParam("eventFile") MultipartFile file, @PathVariable Long idEvent) throws IOException {
        return imageServiceInter.updateEventImage(file, idEvent);
    }


    @DeleteMapping("/event/{idEvent}")
    public ResponseEntity<String> deleteEventImage(@PathVariable Long idEvent)
    {
        return imageServiceInter.deleteEventImage(idEvent);
    }
}
