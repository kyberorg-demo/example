package io.kyberorg.example.model;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

/**
 * Records table model.
 *
 * @since 1.1
 */
@Data
@Entity
@Table(name = "records")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "record", nullable = false)
    @Getter
    private String record;
}
