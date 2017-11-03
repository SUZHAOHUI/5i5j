
package com.oio.wawj.util;

import javax.persistence.Entity;
import org.springframework.beans.factory.FactoryBean;

import java.util.Properties;

@Entity
public class PropertiesEncryptFactoryBean implements FactoryBean {

    private Properties properties;

    private String key = "9i!3w5r6t%9x8n7b&gx3.-63js?59sp+=-";

    public Object getObject() throws Exception {
        return getProperties();
    }

    public Class getObjectType() {
        return java.util.Properties.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties inProperties) {
        this.properties = inProperties;
        String originalUsername = properties.getProperty("user");
        String originalPassword = properties.getProperty("password");
        if (originalUsername != null){
            String newUsername = SecurityHelper.DESDecrypt(originalUsername, key);
            properties.put("user", newUsername);
        }
        if (originalPassword != null){
            String newPassword = SecurityHelper.DESDecrypt(originalPassword, key);
            properties.put("password", newPassword);
        }
    }
}
