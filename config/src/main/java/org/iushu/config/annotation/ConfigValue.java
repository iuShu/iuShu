package org.iushu.config.annotation;

import java.lang.annotation.*;

/**
 * According to the index to injects property value.
 *
 * Created by iuShu on 18-10-25
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ConfigValue {

    /**
     * the index of the property which to be injects.
     * e.g. database.jdbc.url >> database.properties contains a pair of jdbc.url=......
     */
    String index();

}
