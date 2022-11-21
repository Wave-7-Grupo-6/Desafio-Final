package br.com.dh.meli.desafiofinal.service;

import br.com.dh.meli.desafiofinal.dto.PurchaseOrderDTO;
import br.com.dh.meli.desafiofinal.enums.OrderStatus;
import br.com.dh.meli.desafiofinal.model.*;
import br.com.dh.meli.desafiofinal.repository.PurchaseOrderRepository;
import br.com.dh.meli.desafiofinal.service.IAnnouncement;
import br.com.dh.meli.desafiofinal.service.IBatch;
import br.com.dh.meli.desafiofinal.service.IClient;
import br.com.dh.meli.desafiofinal.service.IPurchaseOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Purchase order service.
 */
@Service
@RequiredArgsConstructor
public class PurchaseOrderService implements IPurchaseOrder {
    private final PurchaseOrderRepository repository;
    private final IAnnouncement annService;
    private final IClient cliService;
    private final IBatch batchService;

    /**
     * > We create a purchase order, update the stock of the products, and save the purchase order
     *
     * @param purchaseOrderDTO This is the object that contains the data that we want to save.
     * @return The total price of the purchase order.
     */
    @Override
    public BigDecimal save(PurchaseOrderDTO purchaseOrderDTO) {
        PurchaseOrder purchaseOrder = createAttributes(purchaseOrderDTO);

        purchaseOrderDTO.getProducts().forEach(product -> {
            batchService.updateStock(product.getBatchId(), product.getProductId(), product.getQuantity());
        });
        repository.save(purchaseOrder);

        return purchaseOrder.getTotalPrice();
    }

    /**
     * "Update the status of the purchase order with the given id to DELIVERED."
     *
     * The function is doing more than that, though. It's also returning the updated purchase order
     *
     * @param id The id of the purchase order you want to update.
     * @return The updated purchase order.
     */
    @Override
    public PurchaseOrder updateStatusToDelivered(Long id){
        PurchaseOrder purchaseOrder = findById(id);

        purchaseOrder.setOrderStatus(OrderStatus.DELIVERED);
        repository.save(purchaseOrder);

        return purchaseOrder;
    }

    /**
     * Get all the purchase orders from the database.
     *
     * @return A list of all the purchase orders in the database.
     */
    @Override
    public List<PurchaseOrder> getAll() {
        return repository.findAll();
    }

    /**
     * If the purchase order exists, return it, otherwise throw an exception.
     *
     * @param id The id of the purchase order to be retrieved.
     * @return A PurchaseOrder object.
     */
    @Override
    public PurchaseOrder findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Purchase Order not found!"));
    }

    /**
     * It creates a PurchaseOrder object from a PurchaseOrderDTO object
     *
     * @param purchaseOrderDTO the object that contains the data that will be used to create the purchase order.
     * @return A PurchaseOrder object with the attributes set.
     */
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
