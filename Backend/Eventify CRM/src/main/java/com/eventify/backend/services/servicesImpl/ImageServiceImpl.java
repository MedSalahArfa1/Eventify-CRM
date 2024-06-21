package com.eventify.backend.services.servicesImpl;

import com.eventify.backend.entities.EventEntity;
import com.eventify.backend.entities.Image;
import com.eventify.backend.entities.SponsorshipEntity;
import com.eventify.backend.repositories.EventRepository;
import com.eventify.backend.repositories.ImageRepository;
import com.eventify.backend.repositories.SponsorshipRepository;
import com.eventify.backend.services.servicesInter.ImageServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageServiceInter {

    @Autowired
    ImageRepository imageRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    SponsorshipRepository sponsorshipRepository;

    @Override
    public ResponseEntity<String> uploadEventImage(MultipartFile file, Long idEvent) throws IOException {
        Optional<EventEntity> eventOptional = eventRepository.findById(idEvent);
        if (eventOptional.isPresent()) {
            if (eventOptional.get().getEventImage() != null) {
                return ResponseEntity.badRequest().body("Event already has an image");

            }
            Image img = new Image();
            img.setName(file.getOriginalFilename());
            img.setPicByte(file.getBytes());
            img.setEvent(eventOptional.get());
            imageRepository.save(img);
            return ResponseEntity.ok("Image( " + img.getName() + " ) added to event " + eventOptional.get().getEventName());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<String> uploadSponsorLogo(MultipartFile file, Long idSponsorship) throws IOException {
        Optional<SponsorshipEntity> sponsorshipOptional=sponsorshipRepository.findById(idSponsorship);
        if(sponsorshipOptional.isPresent())
        {
            if(sponsorshipOptional.get().getSponsorshipImage()!=null){
                return ResponseEntity.badRequest().body("User already has an image");

            }
            Image img =new Image();
            img.setName(file.getOriginalFilename());
            img.setPicByte(file.getBytes());
            img.setSponsorship(sponsorshipOptional.get());
            imageRepository.save(img);
            return ResponseEntity.ok("Image( "+img.getName()+" ) added to event "+ sponsorshipOptional.get().getSponsorName());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
