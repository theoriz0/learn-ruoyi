package cn.theoriz.ruoyi.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ruoyi")
public class RuoyiConfig {
    private static boolean addressEnabled;

    public static boolean isAddressEnabled() {
        return addressEnabled;
    }

    public static void setAddressEnabled(boolean addressEnabled) {
        RuoyiConfig.addressEnabled = addressEnabled;
    }
}
