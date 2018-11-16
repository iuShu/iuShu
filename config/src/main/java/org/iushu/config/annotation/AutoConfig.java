package org.iushu.config.annotation;

import java.lang.annotation.*;

/**
 * Indicates that a configuration Bean could be inject property automatically.
 *
 * @see AutoValue
 *
 * Created by iuShu on 18-10-25
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AutoConfig {

    /**
     * configuration name for corresponding Bean,
     * and it's able to import multiple configurations in one Bean.
     */
    String[] name();

}
