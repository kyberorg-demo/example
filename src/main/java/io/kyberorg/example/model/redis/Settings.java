package io.kyberorg.example.model.redis;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("ExampleApp-Settings")
@Data(staticConstructor = "createNewForSession")
public class Settings implements Serializable {
    private final String id;
    private boolean lightMode = false;
}
