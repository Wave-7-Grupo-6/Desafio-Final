package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.model.Section;
import br.com.dh.meli.desafiofinal.repository.SectionRepo;
import br.com.dh.meli.desafiofinal.service.ISection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

/**
 * The type Section service.
 */
@Service
@RequiredArgsConstructor
public class SectionService implements ISection {
    private final SectionRepo repo;

    /**
     * If the section exists, return it, otherwise throw an exception.
     *
     * @param id The id of the section you want to find.
     * @return A Section object
     */
    @Override
    public Section findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Section not found!"));
    }

    /**
     * Save the section to the database.
     *
     * @param section The section object that is being saved.
     * @return The section that was saved.
     */
    @Override
    public Section save(Section section) {
        return repo.save(section);
    }
}
