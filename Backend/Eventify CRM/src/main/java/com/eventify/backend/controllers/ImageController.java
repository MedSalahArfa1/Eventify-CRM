package com.eventify.backend.controllers;

import com.eventify.backend.services.servicesInter.ImageServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ImageServiceInter imageServiceInter;

    @PostMapping("/upload/{idEvent}")
    public ResponseEntity<String> uploadEventImage(@RequestParam("imageFile") MultipartFile file, @PathVariable Long idEvent) throws IOException {
        return imageServiceInter.uploadEventImage(file,idEvent);
    }

    @PostMapping("/upload/{idSponsorship}")
    public ResponseEntity<String> uploadSponsorLogo(@RequestParam("imageFile") MultipartFile file, @PathVariable Long idSponsorship) throws IOException {
        return imageServiceInter.uploadSponsorLogo(file,idSponsorship);
    }
}
