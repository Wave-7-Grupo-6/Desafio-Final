package br.com.dh.meli.desafiofinal.utils;

import br.com.dh.meli.desafiofinal.dto.ProductDTO;
import br.com.dh.meli.desafiofinal.dto.PurchaseOrderDTO;
import br.com.dh.meli.desafiofinal.enums.OrderStatus;
import br.com.dh.meli.desafiofinal.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TestUtils {

    public static Category getCategory(){
        return new Category(1L, "Category 1", 10);
    }

    public static Section getSection(){
        return new Section(1L,"Section 1", 50.0f, 10.0f, getCategory(), getWarehouse(),getSeller());
    }

    public static Section getValidateSection(){
        return new Section(1L,"Section 1", 50.0f, 10.0f, getCategory(), getWarehouse(),null);
    }

    public static Warehouse getWarehouse(){
        return new Warehouse(1L,"Warehouse 1");
    }

    public static Seller getSeller(){
         Seller seller = new Seller(1L, "seller@seler.com", "aaaaaA1@", Set.of(getSellerRole()),"Seller 1");
         seller.setSections(List.of(getValidateSection()));
         return seller;
    }

    public static ProductType getProductType(){ return new ProductType(1L, "Product Type", null); }

    public static Announcement getAnnouncement(){
        return new Announcement(1L, "Announcement 1", getCategory(), getSeller(), getProductType());
    }

    public static Client getClient(){
        return new Client(1L,"client@client.com","aaaaaA1@",Set.of(getClientRole()), "Client 1");
    }

    public static Batch getBatch(){
        return new Batch(2L, 10.0f,10, LocalDate.now(), LocalTime.now(), 0.5f, LocalDate.now().plusDays(30),new BigDecimal(10.0),getAnnouncement(), null, getSection());
    }

    public static Batch getLowIdBatch(){
        return new Batch(1L, 10.0f,10, LocalDate.now(), LocalTime.now(), 0.5f, LocalDate.now().plusDays(30),new BigDecimal(10.0),getAnnouncement(), null, getSection() );
    }

    public static InboundOrder getInboundOrder(){
        List<Batch> batchList = new ArrayList<Batch>();
        batchList.add(getBatch());
        return new InboundOrder(2L,LocalDate.now(), 1L, batchList, getSection());
    }

    public static PurchaseItem getPurchaseItem(){
        return new PurchaseItem(1L,20, new BigDecimal(10.0), getAnnouncement(), null);
    }

    public static PurchaseOrder getPurchaseOrder(){
        BigDecimal total = getPurchaseItem().getPrice().multiply(new BigDecimal(getPurchaseItem().getQuantity()));
        return new PurchaseOrder(1L,LocalDate.now(), OrderStatus.PROCESSING,total, getClient(), Set.of(getPurchaseItem()));
    }

    public static PurchaseOrderDTO getPurchaseOrderDTO(){
        List<ProductDTO> productDTOs = List.of(new ProductDTO(getAnnouncement().getId(), getPurchaseItem().getQuantity(), getLowIdBatch().getBatchNumber()));
        return new PurchaseOrderDTO(LocalDate.now(), getClient().getId(), OrderStatus.PROCESSING.toString(), productDTOs);
    }

    public static Role getSellerRole(){
        return new Role(1L, "ROLE_SELLER");
    }

    public static Role getClientRole(){
        return new Role(1L, "ROLE_CLIENT");
    }
    public static Role getAdminRole(){
        return new Role(1L, "ROLE_ADMIN");
    }

    public static User getUser(){
        return new User(1L,"user@user.com","aaaaaA1@",Set.of(getAdminRole()));
    }
}
