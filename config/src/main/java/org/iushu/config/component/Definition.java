package org.iushu.config.component;

import org.iushu.config.component.prop.PropDefinition;
import org.iushu.config.component.xml.XmlDefinition;

/**
 * Definition indicates the definition in configuration file,
 * default provides implementation:
 * @see XmlDefinition
 * @see PropDefinition
 *
 * Created by iuShu on 18-10-25
 */
public interface Definition {

    void resolve();

}
