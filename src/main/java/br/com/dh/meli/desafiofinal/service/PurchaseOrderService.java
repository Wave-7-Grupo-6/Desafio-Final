package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.PurchaseOrderDTO;
import br.com.dh.meli.desafiofinal.enums.OrderStatus;
import br.com.dh.meli.desafiofinal.model.*;
import br.com.dh.meli.desafiofinal.repository.PurchaseOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseOrderService implements IPurchaseOrder{
    private final PurchaseOrderRepository repository;
    private final IAnnouncement annService;
    private final IClient cliService;
    private final IBatch batchService;

    @Override
    public BigDecimal save(PurchaseOrderDTO purchaseOrderDTO) {
        PurchaseOrder purchaseOrder = createAttributes(purchaseOrderDTO);

        purchaseOrderDTO.getProducts().forEach(product -> {
            batchService.updateStock(product.getBatchId(), product.getProductId(), product.getQuantity());
        });
        repository.save(purchaseOrder);

        return purchaseOrder.getTotalPrice();
    }

    @Override
    public PurchaseOrder update(Long id){
        PurchaseOrder purchaseOrder = findById(id);

        purchaseOrder.setOrderStatus(OrderStatus.DELIVERED);
        repository.save(purchaseOrder);

        //TODO update stock

        return purchaseOrder;
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
        Client client = cliService.findById(purchaseOrderDTO.getBuyerId());
        Set<PurchaseItem> purchaseItems = new HashSet<>();

        PurchaseOrder purchaseOrder = new PurchaseOrder(purchaseOrderDTO, client);
        purchaseOrderDTO.getProducts().forEach(
                product -> {
                    Announcement announcement = annService.findById(product.getProductId());
                    Batch batch = batchService.findById(product.getBatchId());
                    purchaseItems.add(new PurchaseItem(product.getQuantity(),batch.getPrice(),announcement, purchaseOrder));
                }
                );

        BigDecimal total = purchaseItems.stream()
                .map(purchaseItem -> purchaseItem.getPrice().multiply(new BigDecimal(purchaseItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        purchaseOrder.setPurchaseItems(purchaseItems);
        purchaseOrder.setTotalPrice(total);

        return purchaseOrder;
    }
}
