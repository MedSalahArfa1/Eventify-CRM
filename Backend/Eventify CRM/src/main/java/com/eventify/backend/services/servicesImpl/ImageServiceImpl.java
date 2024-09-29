package com.eventify.backend.services.servicesImpl;

import com.eventify.backend.entities.EventEntity;
import com.eventify.backend.entities.Image;
import com.eventify.backend.repositories.EventRepository;
import com.eventify.backend.repositories.ImageRepository;
import com.eventify.backend.repositories.UserRepository;
import com.eventify.backend.services.servicesInter.ImageServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Optional;

import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.io.ByteArrayOutputStream;

@Service
public class ImageServiceImpl implements ImageServiceInter {

    @Autowired
    ImageRepository imageRepository;
    @Autowired
    EventRepository eventRepository;

    @Override
    public ResponseEntity<String> uploadEventImage(MultipartFile file, Long idEvent) throws IOException {
        Optional<EventEntity> eventOptional = eventRepository.findById(idEvent);
        if (eventOptional.isPresent()) {
            if (eventOptional.get().getEventImage() != null) {
                return ResponseEntity.badRequest().body("Event already has an image");

            }
            Image img = new Image();
            img.setName(file.getOriginalFilename());
            img.setPicByte(compressBytes(file.getBytes()));
            img.setEvent(eventOptional.get());
            imageRepository.save(img);
            return ResponseEntity.ok("Image( " + img.getName() + " ) added to event " + eventOptional.get().getEventName());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Image> getEventImage(Long idEvent) {

        Optional<Image> retrievedImage = imageRepository.findByEventEventId(idEvent);
        if(retrievedImage.isPresent())
        {
            Image img =retrievedImage.get();
            img.setPicByte(img.getPicByte());
            return ResponseEntity.ok(img);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<String> updateEventImage(MultipartFile file, Long idEvent) throws IOException {
        Optional<EventEntity> optionalEvent=eventRepository.findById(idEvent);
        if(optionalEvent.isPresent())
        {
            EventEntity user =optionalEvent.get();
            Image image=user.getEventImage();
            image.setName(file.getOriginalFilename());
            image.setPicByte(file.getBytes());
            imageRepository.save(image);
            return ResponseEntity.ok("Updated");
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<String> deleteEventImage(Long idEvent) {

        Optional<EventEntity> optionalEvent=eventRepository.findById(idEvent);
        if(optionalEvent.isPresent())
        {
            EventEntity event=optionalEvent.get();
            Image img = event.getEventImage();
            if(img!=null){
                imageRepository.delete(img);
                return ResponseEntity.ok("Image deleted of user"+idEvent);
            }else{
                return ResponseEntity.notFound().build();
            }
        }else{
            return ResponseEntity.notFound().build();
        }


    }


    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    //there's a problem in decompressBytes
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
        } catch (DataFormatException e) {
            System.err.println("Data format exception: " + e.getMessage());
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                // This should not happen, ByteArrayOutputStream does not throw IOException on close.
                System.err.println("Error closing output stream: " + e.getMessage());
            }
        }
        return outputStream.toByteArray();
    }
}
