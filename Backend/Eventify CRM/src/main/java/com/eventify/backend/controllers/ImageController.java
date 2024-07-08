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

    @PostMapping("/upload/sponsor/{idSponsorship}")
    public ResponseEntity<String> uploadSponsorLogo(@RequestParam("sponsorFile") MultipartFile file, @PathVariable Long idSponsorship) throws IOException {
        return imageServiceInter.uploadSponsorLogo(file,idSponsorship);
    }

    @PostMapping("/upload/user/{idUser}")
    public ResponseEntity<String> uploadUserImage(@RequestParam("userFile") MultipartFile file, @PathVariable Long idUser) throws IOException {
        return imageServiceInter.uploadUserImage(file,idUser);
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<Image> getImageByUserId(@PathVariable Long idUser)
    {
        return imageServiceInter.getUserImage(idUser);

    }

    @PutMapping("/user/{idUser}")
    public ResponseEntity<String> updateUserImage(@RequestParam("userFile") MultipartFile file, @PathVariable Long idUser) throws IOException {
        return imageServiceInter.updateUserImage(file, idUser);
    }


    @DeleteMapping("/user/{idUser}")
    public ResponseEntity<String> deleteUserImage(@PathVariable Long idUser)
    {
        return imageServiceInter.deleteUserImage(idUser);
    }
}
