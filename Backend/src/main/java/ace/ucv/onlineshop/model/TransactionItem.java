package ace.ucv.onlineshop.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class TransactionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    Integer quantity;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    Double price;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    Transaction transaction;
}
