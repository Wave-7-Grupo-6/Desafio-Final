package br.com.dh.meli.desafiofinal.utils;

import br.com.dh.meli.desafiofinal.enums.OrderStatus;
import br.com.dh.meli.desafiofinal.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public class TestUtils {

    public static Category getCategory(){
        return new Category(1L, "Category 1", 10);
    }

    public static Section getSection(){
        return new Section(1L,"Section 1", 50.0f, 10.0f, getCategory(), getWarehouse(),getSeller());
    }

    public static Warehouse getWarehouse(){
        return new Warehouse(1L,"Warehouse 1");
    }

    public static Seller getSeller(){
        return new Seller(1L,"Seller 1");
    }

    public static ProductType getProductType(){ return new ProductType(1L, "Product Type", null); }

    public static Announcement getAnnouncement(){
        return new Announcement(1L, "Announcement 1", new BigDecimal(50.0), getCategory(), getSeller(), getProductType());
    }

    public static Client getClient(){
        return new Client(1L, "Client 1", null);
    }

    public static Batch getBatch(){
        return new Batch(2L, 1L, 10.0f,10, LocalDate.now(), LocalTime.now(), 0.5f, LocalDate.now().plusDays(30),getAnnouncement(), null, getSection());
    }

    public static Batch getLowIdBatch(){
        return new Batch(1L, 1L, 10.0f,10, LocalDate.now(), LocalTime.now(), 0.5f, LocalDate.now().plusDays(30),getAnnouncement(), null, getSection() );
    }

    public static PurchaseItem getPurchaseItem(){
        return new PurchaseItem(1L,20, new BigDecimal(10.0), getAnnouncement(), null);
    }

    public static PurchaseOrder getPurchaseOrder(){
        BigDecimal total = getPurchaseItem().getPrice().multiply(new BigDecimal(getPurchaseItem().getQuantity()));
        return new PurchaseOrder(1L,LocalDate.now(), OrderStatus.PROCESSING,total, getClient(), Set.of(getPurchaseItem()));
    }
}
