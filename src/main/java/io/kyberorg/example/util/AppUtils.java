package io.kyberorg.example.util;

import lombok.Getter;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Application utilities.
 *
 * @since 1.0
 */
@Component
public class AppUtils {
    @Getter
    private final Environment env;

    /**
     * Creates {@link AppUtils}.
     *
     * @param env environment variables
     */
    public AppUtils(final Environment env) {
        this.env = env;
    }
}
