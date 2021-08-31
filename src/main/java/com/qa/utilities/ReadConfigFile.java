package com.qa.utilities;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.Sources;

@LoadPolicy(Config.LoadType.MERGE)
@Sources({
        "classpath:config.properties",
})
public interface ReadConfigFile extends Config {

    /*** File config.properties */
    String URL();
    String serviceURL();
}