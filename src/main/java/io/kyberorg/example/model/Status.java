package io.kyberorg.example.model;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

/**
 * Status table model.
 *
 * @since 1.0
 */
@Data
@Entity
@Table(name = "status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic(optional = false)
    @Column(name = "status", nullable = false)
    @Getter
    private Boolean status = true;
}
