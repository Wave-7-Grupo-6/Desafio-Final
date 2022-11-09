package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.exceptions.NotFoundException;
import br.com.dh.meli.desafiofinal.model.Seller;
import br.com.dh.meli.desafiofinal.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService{
    private final SellerRepository repository;
    @Override
    public Seller findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Seller not found."));
    }
}
