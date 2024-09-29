package com.eventify.backend.services.servicesInter;

import com.eventify.backend.entities.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface ImageServiceInter {
    ResponseEntity<String> uploadEventImage(MultipartFile file, Long idEvent) throws IOException;
    ResponseEntity<Image> getEventImage(Long idEvent);
    ResponseEntity<String> updateEventImage(MultipartFile file, Long idEvent) throws IOException;
    ResponseEntity<String> deleteEventImage(Long idEvent);
}