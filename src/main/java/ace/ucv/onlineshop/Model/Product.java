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
    private String specifications;

    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "provider", nullable = false)
    private String provider;
}
