package entity;

import base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
public class Person extends BaseEntity<Long> {
    String firstName;
    String lastName;
    @Enumerated(EnumType.STRING)
    Gender gender;
    String userName;
    String password;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    List<Ticket> ticketList;
}
