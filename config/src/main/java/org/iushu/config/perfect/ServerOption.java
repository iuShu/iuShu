package org.iushu.config.perfect;

import org.iushu.config.annotation.AutoConfig;
import org.iushu.config.annotation.ConfigValue;

/**
 * Sample usage.
 *
 * Created by iuShu on 18-10-25
 */
@AutoConfig(name = {"server"})
public class ServerOption {

    @ConfigValue(index = "server.netty.host")
    private String host;

    @ConfigValue(index = "server.netty.port")
    private int port;

    @ConfigValue(index = "server.netty.heartbeat.interval")
    private int heartbeatInterval;

    @ConfigValue(index = "server.netty.heartbeat.offline.valve")
    private int offlineValve; // cut off if heartbeat failure times exceeded valve.

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
        return "ServerOption{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", heartbeatInterval=" + heartbeatInterval +
                ", offlineValve=" + offlineValve +
                '}';
    }
}
