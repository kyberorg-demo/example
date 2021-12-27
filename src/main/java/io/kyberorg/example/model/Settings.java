package io.kyberorg.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Settings implements Serializable {
    private String sessionId = null;
    private boolean lightMode;

    /**
     * Creates empty Settings for given session.
     *
     * @param sessionId string with session id linked to settings.
     * @return {@link Settings} with defaults.
     */
    public static Settings createNewForSession(final String sessionId) {
        Settings settings = new Settings();
        settings.setSessionId(sessionId);
        return settings;
    }
}
