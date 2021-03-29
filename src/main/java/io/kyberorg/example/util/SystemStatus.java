package io.kyberorg.example.util;

/**
 * Human-friendly status instead of true/false.
 *
 * @since 1.0
 */
public enum SystemStatus {
    UP(),
    DOWN();

    /**
     * Converts boolean status to UP or DOWN.
     *
     * @param rawStatus boolean with raw status from DB.
     * @return {@link #UP} if rawStatus if true, {@link #DOWN} if false.
     */
    public static SystemStatus fromBoolean(final boolean rawStatus) {
        return rawStatus ? UP : DOWN;
    }
}
