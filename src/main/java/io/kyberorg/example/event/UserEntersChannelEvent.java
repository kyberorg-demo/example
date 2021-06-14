package io.kyberorg.example.event;

import lombok.Data;

@Data(staticConstructor = "createWith")
public class UserEntersChannelEvent {
    private final String username;

}
