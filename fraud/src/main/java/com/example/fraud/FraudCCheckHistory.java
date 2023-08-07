package com.example.fraud;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class FraudCCheckHistory {

    @Id
    @SequenceGenerator(name = "fraud_id_sequence",sequenceName = "fraud_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "fraud_id_sequence"
    )
    private Long id;
    private Integer customerId;
    private Boolean isFraud;
    private LocalDateTime createdAt;

}
