package server.domain;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "products")
public class Item {
    @Id
    @Column(name = "product_id")
    private String productID;

    @Column(length = 10000)
    private String originalCategory, originalGroup, originalType, originalName, originalBrand, originalAmount, originalPrice;
    private String supplier;

    @Column(length = 20000)
    private String originalAnnotation, originalPic, linkToPic, picsFromRUSBT, annotationFromRUSBT;

    private LocalDate updateDate;

    private Boolean isAvailable;

    private Integer finalPrice, bonus;
}
