package io.kyberorg.example.dao;

import io.kyberorg.example.model.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusDao extends CrudRepository<Status, Integer> {
    default Status findOne() {
        Optional<Status> status = findById(1);

        Status statusDown = new Status();
        statusDown.setStatus(false);

        return status.orElse(statusDown);
    }
}
