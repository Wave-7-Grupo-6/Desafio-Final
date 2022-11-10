package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.PurchaseOrderDTO;
import br.com.dh.meli.desafiofinal.model.Announcement;
import br.com.dh.meli.desafiofinal.model.Client;
import br.com.dh.meli.desafiofinal.model.PurchaseOrder;
import br.com.dh.meli.desafiofinal.repository.PurchaseOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseOrderService implements IPurchaseOrder{
    private final PurchaseOrderRepository repository;
    private final IAnnouncement annService;
    private final IClient cliService;

    @Override
    public BigDecimal save(PurchaseOrderDTO purchaseOrderDTO) {
        PurchaseOrder purchaseOrder = createAttributes(purchaseOrderDTO);
        repository.save(purchaseOrder);

        return purchaseOrder.getTotalPrice();
    }

    @Override
    public List<PurchaseOrder> getAll() {
        return repository.findAll();
    }

    @Override
    public PurchaseOrder findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Purchase Order not found!"));
    }

    private PurchaseOrder createAttributes(PurchaseOrderDTO purchaseOrderDTO){
        List<Announcement> announcements = purchaseOrderDTO.getProducts().stream()
                .map(productDTO -> annService.findById(productDTO.getProductId())).collect(Collectors.toList());
        Client client = cliService.findById(purchaseOrderDTO.getBuyerId()).get();

        return new PurchaseOrder(purchaseOrderDTO, client, announcements);
    }
}
