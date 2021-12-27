package io.kyberorg.example.model;

import lombok.Data;

import java.io.Serializable;

@Data(staticConstructor = "createNewForSession")
public class Settings implements Serializable {
    private final String sessionId;
    private boolean lightMode;
}
