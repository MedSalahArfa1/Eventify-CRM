package com.eventify.backend.services.servicesInter;

import com.eventify.backend.entities.SponsorshipEntity;

import java.util.List;

public interface SponsorshipServiceInter {
    List<SponsorshipEntity> getAllSponsorships();

    SponsorshipEntity getSponsorshipById(Long id);

    SponsorshipEntity createSponsorship(SponsorshipEntity sponsorship);

    SponsorshipEntity updateSponsorship(Long id, SponsorshipEntity sponsorshipDetails);

    void deleteSponsorship(Long id);
}
