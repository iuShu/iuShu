package org.iushu.config.zoo;

import org.iushu.config.annotation.AutoConfig;
import org.iushu.config.annotation.AutoValue;

/**
 * Created by iuShu on 18-11-13
 */
@AutoConfig(name = "database")
public class DatabaseEnvironment {

    @AutoValue(key = "configuration.environment.bean.1#id")
    private String dataSourceId;

    @AutoValue(key = "configuration.environment.bean.1#class")
    private String dataSourceClass;

    @AutoValue(key = "configuration.environment.bean.1.property.1")
    private String url;

    @AutoValue(key = "configuration.environment.bean.1.property.2")
    private String username;

    @AutoValue(key = "configuration.environment.bean.1.property.3")
    private String password;

    @AutoValue(key = "configuration.environment.bean.1.property.4")
    private String driver;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public String getDataSourceClass() {
        return dataSourceClass;
    }

    public void setDataSourceClass(String dataSourceClass) {
        this.dataSourceClass = dataSourceClass;
    }

    @Override
    public String toString() {
        return "DatabaseEnvironment{" +
                "url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", driver='" + driver + '\'' +
                ", dataSourceId='" + dataSourceId + '\'' +
                ", dataSourceClass='" + dataSourceClass + '\'' +
                '}';
    }
}
