package ar.edu.udeci.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "inventory_movement")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryMovement {

    @Id
    @Column(name = "movement_id", length = 10)
    private String movementId;


    @Column(name = "movement_date")
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private ProductMaster product;

    @Column(name = "movement_type", length = 20)
    private String movementType;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "order_id", length = 20)
    private String orderId;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

}

