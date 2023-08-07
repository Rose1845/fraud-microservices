package com.rose.customer;


import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
//    @SequenceGenerator(name = "customer_sequence_id",sequenceName = "customer_sequence_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator =
                    "customer_id_sequence"
    )
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
}
