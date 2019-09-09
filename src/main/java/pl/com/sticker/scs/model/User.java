package pl.com.sticker.scs.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@ApiModel(value="User Model",
        description="User model in application")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {

    @ApiModelProperty(notes = "User ID")
    @Id
    @GeneratedValue(generator = "user_id_generator")
    @SequenceGenerator(
            name = "user_id_generator",
            sequenceName = "user_id_seq",
            initialValue = 1000
    )
    private Long id;

    @ApiModelProperty(notes = "User UID")
    @Column(name = "uid", nullable = false)
    private String uid;

    @ApiModelProperty(notes = "User's first name")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @ApiModelProperty(notes = "User's last name")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;
}
