package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.model.Announcement;
import br.com.dh.meli.desafiofinal.repository.AnnouncementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementService implements IAnnouncement {
    private final AnnouncementRepository repository;

    @Override
    public Announcement findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Announcement not found."));
    }

    @Override
    public Announcement save(Announcement announcement) {
        return repository.save(announcement);
    }

    @Override
    public List<Announcement> findAll() {
        return repository.findAll();
    }
}
