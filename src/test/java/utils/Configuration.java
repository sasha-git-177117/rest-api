package utils;

import consts.Config;

public class Configuration {

    public static String getURL() {
        return JsonUtil.
                getValueFromJson(Config.BASE_URI_PATH.label, Config.BASE_URI_JSON_PATH.label);
    }
}
