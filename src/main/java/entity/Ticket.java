package entity;

import base.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
public class Ticket extends BaseEntity<Long> {
    String origin;
    String destination;
    @Temporal(TemporalType.DATE)
    LocalDate departureDate;
    String departureTime;
    String travelId;
    @ManyToOne
    Person person;
}