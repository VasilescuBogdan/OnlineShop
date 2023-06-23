package ace.ucv.onlineshop.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "specifications", nullable = false)
    @Lob
    private String specifications;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "points", nullable = false)
    private Integer points;

    @Column(name = "provider", nullable = false)
    private String provider;

    @Column(name = "category", nullable = false)
    private String category;

    @JoinColumn(name = "discountId", unique = true)
    @OneToOne()
    private Discount discount;
}
