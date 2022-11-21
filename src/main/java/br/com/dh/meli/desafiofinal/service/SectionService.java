package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.model.Section;
import br.com.dh.meli.desafiofinal.repository.SectionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SectionService implements ISection{
    private final SectionRepo repo;

    @Override
    public Section findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Section not found!"));
    }

    @Override
    public Section save(Section section) {
        return repo.save(section);
    }
}
