package com.eventify.backend.controllers;

import com.eventify.backend.entities.SponsorshipEntity;
import com.eventify.backend.services.servicesInter.SponsorshipServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sponsorships")
public class SponsorshipController {
    @Autowired
    private SponsorshipServiceInter sponsorshipService;

    @GetMapping
    public List<SponsorshipEntity> getAllSponsorships() {
        return sponsorshipService.getAllSponsorships();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SponsorshipEntity> getSponsorshipById(@PathVariable Long id) {
        SponsorshipEntity sponsorship = sponsorshipService.getSponsorshipById(id);
        return sponsorship != null ? ResponseEntity.ok(sponsorship) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public SponsorshipEntity createSponsorship(@RequestBody SponsorshipEntity sponsorship) {
        return sponsorshipService.createSponsorship(sponsorship);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SponsorshipEntity> updateSponsorship(@PathVariable Long id, @RequestBody SponsorshipEntity sponsorshipDetails) {
        SponsorshipEntity updatedSponsorship = sponsorshipService.updateSponsorship(id, sponsorshipDetails);
        return updatedSponsorship != null ? ResponseEntity.ok(updatedSponsorship) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public void deleteSponsorship(@PathVariable Long id) {
        sponsorshipService.deleteSponsorship(id);
    }
}
