package io.github.thalesf93.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "services")
@Data
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "type", nullable = false)
    private String serviceType;

    @Column(name = "date", nullable = false)
    @JsonFormat(pattern = "dd/mm/yyyy")
    private LocalDateTime eventDate;

    @Column(name = "price")
    private BigDecimal price;
}
