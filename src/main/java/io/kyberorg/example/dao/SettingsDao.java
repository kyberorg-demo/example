package io.kyberorg.example.dao;

import io.kyberorg.example.model.redis.Settings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsDao extends CrudRepository<Settings, String> {
}
