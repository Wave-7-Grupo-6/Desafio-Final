package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.model.Annoucement;
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
    public Annoucement findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Announcement not found."));
    }

    @Override
    public Annoucement save(Annoucement annoucement) {
        return repository.save(annoucement);
    }

    @Override
    public List<Annoucement> findByCategory(String category) {
        Long id_cat = catService.findByName(category).getId();

        List<Annoucement> list = repository.findByCategory_Id(id_cat);

        if(list.isEmpty()) throw new NotFoundException("Announcement list of category not found");

        return list;
    }
}
