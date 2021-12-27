package io.kyberorg.example.service;

import io.kyberorg.example.dao.SettingsRedisDao;
import io.kyberorg.example.model.Settings;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service to manage {@link Settings}.
 */
@RequiredArgsConstructor
@Service
public class SettingsService {
    private final SettingsRedisDao settingsDao;

    /**
     * Stores {@link Settings} to Redis.
     *
     * @param settings object to save
     */
    public void save(final Settings settings) {
        settingsDao.save(settings);
    }

    /**
     * Gets {@link Settings} by Session ID.
     *
     * @param sessionId string with session id to search against.
     * @return {@link Settings} linked to given session if found or {@code null}.
     */
    public Settings getSettings(final String sessionId) {
        Optional<Settings> settings = settingsDao.get(sessionId);
        return settings.orElse(null);
    }
}
