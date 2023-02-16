package net.java.springboot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name ="customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @NotBlank(message="First Name is required")
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @NotBlank(message="Last Name is required")
    @Column(name = "last_name")
    private String lastName;

    @NonNull
    @Column(name = "email_id")
    private String emailId;

    @NotBlank(message="Phone number is required")
    @Column(name = "phone_number")
    @Pattern(regexp="^(01)[0-46-9]*[0-9]{7,8}$", message="Not a valid phone number")
    private String phoneNumber;

    @Column(name = "birth_date")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
//    @Pattern(regexp="^(0[1-9]|[12][0-9]|3[01])[\\/](0[1-9]|1[012])[\\/](19|20)\\d\\d$", message="Must be formatted DD/MM/YYYY")
    private LocalDate dateOfBirth;

    @Transient
    private Integer age;

    public String getDateOfBirth() {
        if (dateOfBirth == null)
            return "";
        return dateOfBirth.toString();
    }

    public Integer getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
