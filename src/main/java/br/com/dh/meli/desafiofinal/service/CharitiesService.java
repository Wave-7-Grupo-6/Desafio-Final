package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.model.Charities;
import br.com.dh.meli.desafiofinal.repository.CharitiesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharitiesService implements ICharities {

    private final CharitiesRepository repository;

    @Override
    public Charities save(Charities charities) {
        return repository.save(charities);
    }
}
