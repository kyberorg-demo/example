package io.kyberorg.example.event;

import lombok.Data;

@Data(staticConstructor = "createWith")
public class UserLeavesChannelEvent {
    private final String username;
}
