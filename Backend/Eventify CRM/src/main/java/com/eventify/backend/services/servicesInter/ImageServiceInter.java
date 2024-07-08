package com.eventify.backend.services.servicesInter;

import com.eventify.backend.entities.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface ImageServiceInter {
    ResponseEntity<String> uploadEventImage(MultipartFile file, Long idEvent) throws IOException;
    ResponseEntity<String> uploadSponsorLogo(MultipartFile file, Long idSponsorship) throws IOException;
    ResponseEntity<String> uploadUserImage(MultipartFile file, Long idUser) throws IOException;
    ResponseEntity<Image> getUserImage(Long idUser);
    ResponseEntity<String> updateUserImage(MultipartFile file, Long idUser) throws IOException;
    ResponseEntity<String> deleteUserImage(Long idUser);
}