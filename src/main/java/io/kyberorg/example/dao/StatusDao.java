package io.kyberorg.example.dao;

import io.kyberorg.example.model.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * DAO object for operating with {@link Status} table.
 *
 * @since 1.0
 */
@Repository
public interface StatusDao extends CrudRepository<Status, Integer> {
    /**
     * Retrieves first record from {@link Status} table.
     *
     * @return first record or pseudo-record, that indicates failure.
     */
    default Status findOne() {
        Optional<Status> status = findById(1);

        Status statusDown = new Status();
        statusDown.setStatus(false);

        return status.orElse(statusDown);
    }
}
