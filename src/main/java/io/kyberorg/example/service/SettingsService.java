package io.kyberorg.example.service;

import io.kyberorg.example.dao.SettingsRedisDao;
import io.kyberorg.example.model.redis.Settings;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SettingsService {
    private final SettingsRedisDao settingsDao;

    public void save(final Settings settings) {
        settingsDao.save(settings);
    }

    public Settings getSettings(final String sessionId) {
        Optional<Settings> settings = settingsDao.findById(sessionId);
        return settings.orElse(null);
    }
}
