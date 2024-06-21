package com.eventify.backend.services.servicesInter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface ImageServiceInter {
    ResponseEntity<String> uploadEventImage(MultipartFile file, Long idEvent) throws IOException;
    ResponseEntity<String> uploadSponsorLogo(MultipartFile file, Long idSponsorship) throws IOException;
}