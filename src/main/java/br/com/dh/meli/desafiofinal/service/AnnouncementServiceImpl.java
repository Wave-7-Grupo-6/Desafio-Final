package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.model.Annoucement;
import br.com.dh.meli.desafiofinal.repository.AnnouncementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService{
    private final AnnouncementRepository repository;

    @Override
    public Annoucement findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Announcement not found."));
    }

    @Override
    public Annoucement save(Annoucement annoucement) {
        return repository.save(annoucement);
    }
}
