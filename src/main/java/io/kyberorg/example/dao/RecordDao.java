package io.kyberorg.example.dao;

import io.kyberorg.example.model.Record;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO object for {@link Record} model.
 *
 * @since 1.1
 */
@Repository
public interface RecordDao extends CrudRepository<Record, Integer> {
}
