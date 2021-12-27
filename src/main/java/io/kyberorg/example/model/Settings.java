package io.kyberorg.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Settings implements Serializable {
    private String sessionId = null;
    private boolean lightMode;

    public static Settings createNewForSession(final String sessionId) {
        Settings settings = new Settings();
        settings.setSessionId(sessionId);
        return settings;
    }
}
