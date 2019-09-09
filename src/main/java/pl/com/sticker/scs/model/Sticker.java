package pl.com.sticker.scs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "stickers")
public class Sticker {

    @Id
    @GeneratedValue(generator = "stickers_generator")
    @SequenceGenerator(
            name = "stickers_generator",
            sequenceName = "stickers_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(name = "content")
    private String text;

    @Column(name = "xpos", nullable = false)
    @Min(value = 0)
    private int xCord;

    @Column(name = "ypos", nullable = false)
    @Min(value = 0)
    private int yCord;

    @Column(name = "zpos", nullable = false)
    private int zCord;

    @Column(name = "user_uid", nullable = false)
    private String userUid;

    @Override
    public String toString() {
        return new StringBuilder()
                .append("id: ")
                .append(this.id)
                .append(", text: ")
                .append(this.text)
                .append(", x: ")
                .append(this.xCord)
                .append(", y: ")
                .append(this.yCord)
                .append(", z: ")
                .append(this.zCord)
                .append(", uid: ")
                .append(this.userUid)
                .toString();
    }
}
