package dto;

import base.entity.BaseEntity;
import entity.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PersonDto extends BaseEntity<Long> {
    @NotNull(message = "firstName cannot be null")
    @NotEmpty(message = "firstName cannot be empty")
    @Column(nullable = false)
    String firstName;
    @NotNull(message = "lastName cannot be null")
    @NotEmpty(message = "lastName cannot be empty")
    @Column(nullable = false)
    String lastName;
    @Enumerated(EnumType.STRING)
    Gender gender;
    @NotNull(message = "userName cannot be null")
    @NotEmpty(message = "userName cannot be empty")
    @Column(nullable = false)
    String userName;
    @NotNull(message = "password cannot be null")
    @NotEmpty(message = "password cannot be empty")
    @Column(nullable = false)
    String password;
}
