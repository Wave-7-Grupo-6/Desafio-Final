package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.BatchDTO;
import br.com.dh.meli.desafiofinal.exceptions.NoCompatibleException;
import br.com.dh.meli.desafiofinal.model.Batch;
import br.com.dh.meli.desafiofinal.model.Section;
import br.com.dh.meli.desafiofinal.model.Seller;
import br.com.dh.meli.desafiofinal.model.Warehouse;
import br.com.dh.meli.desafiofinal.repository.BatchRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BatchService implements IBatch{
    private final BatchRepository repo;
    private final SellerService sellerService;
    private final SectionService sectionService;

    @Override
    public Batch save(Batch batch) {
        return repo.save(batch);
    }

    @Override
    public List<BatchDTO> findByDueDateIsBefore(int days, Long sectionId, Long sellerId) {
        validations(sellerId, sectionId);

        return repo.findByDueDateIsBefore(LocalDate.now().plusDays(days)).stream().map(BatchDTO::new).collect(Collectors.toList());
    }

    private void validations(Long sellerId, Long sectionId) {
        Seller seller = sellerService.findById(sellerId);
        Section section = sectionService.findById(sectionId);

        seller.getSections().stream().map(Section::getId).filter(section.getId()::equals).findFirst()
                .orElseThrow(() -> new NoCompatibleException("Seller or section are not compatible"));
    }
}
