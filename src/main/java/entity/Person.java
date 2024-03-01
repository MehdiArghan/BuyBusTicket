package entity;

import base.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
}
