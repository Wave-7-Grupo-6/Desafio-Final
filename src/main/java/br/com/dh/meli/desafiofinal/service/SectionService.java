package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.model.Section;
import br.com.dh.meli.desafiofinal.repository.SectionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SectionService implements ISection{
    private SectionRepo repo;

    @Override
    public Optional<Section> findById(Long id) {
        return repo.findById(id);
    }
}
