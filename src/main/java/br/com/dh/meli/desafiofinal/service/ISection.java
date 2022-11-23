package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.model.Section;

/**
 * The interface Section.
 */
public interface ISection {
    /**
     * Find by id section.
     *
     * @param id the id
     * @return the section
     */
    Section findById(Long id);

    /**
     * Save section.
     *
     * @param section the section
     * @return the section
     */
    Section save(Section section);
}
