package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.model.Charities;
import br.com.dh.meli.desafiofinal.repository.CharitiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CharitiesService implements ICharities {

    private final CharitiesRepository repository;

    @Override
    public Charities save(Charities charities) {
        if(!validateCharity(charities)){
            return null;
        }
        return repository.save(charities);
    }

    @Override
    public List<Charities> findAll() {
        return repository.findAll();
    }

    @Override
    public Charities findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Charities update(Long id, Charities charity) {

        if(repository.existsById(id)){
            charity.setId(id);
            return repository.save(charity);
        }
        throw new NotFoundException("Charity not found.");
    }

    @Override
    public boolean deleteById(Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    boolean validateCharity(Charities charity){
        return charity.getName() != null && charity.getCnpj() != null && charity.getEmail() != null;
    }
}
