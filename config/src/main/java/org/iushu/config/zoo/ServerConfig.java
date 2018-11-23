package org.iushu.config.zoo;

import org.iushu.config.annotation.AutoConfig;
import org.iushu.config.annotation.AutoValue;

/**
 * Sample usage of properties file.
 *
 * Created by iuShu on 18-10-25
 */
@AutoConfig(name = "server")
public class ServerConfig {

    @AutoValue(key = "netty.host")
    private String host;

    @AutoValue(key = "netty.port")
    private int port;

    @AutoValue(key = "server.heartbeat.interval")
    private int heartbeatInterval;

    @AutoValue(key = "server.heartbeat.offline.valve")
    private int offlineValve; // cut off the connection if heartbeat failure times exceeded valve.

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getHeartbeatInterval() {
        return heartbeatInterval;
    }

    public void setHeartbeatInterval(int heartbeatInterval) {
        this.heartbeatInterval = heartbeatInterval;
    }

    public int getOfflineValve() {
        return offlineValve;
    }

    public void setOfflineValve(int offlineValve) {
        this.offlineValve = offlineValve;
    }

    @Override
    public String toString() {
        return "ServerConfig{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", heartbeatInterval=" + heartbeatInterval +
                ", offlineValve=" + offlineValve +
                '}';
    }
}
