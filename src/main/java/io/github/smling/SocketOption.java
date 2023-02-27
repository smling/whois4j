package io.github.smling;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SocketOption {
    private int connectionTimeout;
    private int idleTimeout;
    private boolean tcpNoDelay;
    private boolean keepAlive;
}
