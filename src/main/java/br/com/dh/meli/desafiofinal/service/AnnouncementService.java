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
    private final ICategory catService;

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

    @Override
    public List<Announcement> findByCategory(String category) {
        Long id_cat = catService.findByName(category).getId();

        List<Announcement> list = repository.findByCategory_Id(id_cat);

        if(list.isEmpty()) throw new NotFoundException("Announcement list of category not found");

        return list;
    }
}
