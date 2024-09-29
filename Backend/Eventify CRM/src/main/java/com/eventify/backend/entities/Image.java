package com.eventify.backend.entities;

//import jakarta.persistence.*;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Lob
    @Column(name = "picByte", columnDefinition = "LONGBLOB")
    private byte[] picByte;

    @OneToOne(mappedBy = "eventImage")
    private EventEntity event;

}
