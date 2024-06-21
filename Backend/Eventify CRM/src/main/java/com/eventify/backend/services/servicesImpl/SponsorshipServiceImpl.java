package com.eventify.backend.services.servicesImpl;

import com.eventify.backend.entities.SponsorshipEntity;
import com.eventify.backend.repositories.SponsorshipRepository;
import com.eventify.backend.services.servicesInter.SponsorshipServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SponsorshipServiceImpl implements SponsorshipServiceInter {
    @Autowired
    private SponsorshipRepository sponsorshipRepository;

    @Override
    public List<SponsorshipEntity> getAllSponsorships() {
        return sponsorshipRepository.findAll();
    }

    @Override
    public SponsorshipEntity getSponsorshipById(Long id) {
        return sponsorshipRepository.findById(id).orElse(null);
    }

    @Override
    public SponsorshipEntity createSponsorship(SponsorshipEntity sponsorship) {
        return sponsorshipRepository.save(sponsorship);
    }

    @Override
    public SponsorshipEntity updateSponsorship(Long id, SponsorshipEntity sponsorshipDetails) {
        SponsorshipEntity sponsorship = sponsorshipRepository.findById(id).orElse(null);
        if (sponsorship != null) {
            sponsorship.setSponsorName(sponsorshipDetails.getSponsorName());
            sponsorship.setAmount(sponsorshipDetails.getAmount());
            sponsorship.setDate(sponsorshipDetails.getDate());
            sponsorship.setLogoUrl(sponsorshipDetails.getLogoUrl());
            sponsorship.setEvent(sponsorshipDetails.getEvent());
            return sponsorshipRepository.save(sponsorship);
        }
        return null;
    }

    @Override
    public void deleteSponsorship(Long id) {
        sponsorshipRepository.deleteById(id);
    }
}
