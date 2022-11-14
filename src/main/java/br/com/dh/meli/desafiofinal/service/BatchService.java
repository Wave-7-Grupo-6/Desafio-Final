package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.model.Batch;
import br.com.dh.meli.desafiofinal.repository.BatchRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BatchService implements IBatch{

        private final BatchRepository repo;

        @Override
        public Batch save(Batch batch) {
            return repo.save(batch);
        }

}
