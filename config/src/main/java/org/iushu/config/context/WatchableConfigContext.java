package org.iushu.config.context;

import org.iushu.config.context.hotswap.WatchEvent;
import org.iushu.config.document.Document;

/**
 * Watchable means that the ConfigContext can be watching the changes and do something
 * corresponding after changed.
 */
public interface WatchableConfigContext extends ConfigContext {

    void onChange(WatchEvent watchEvent, Document document) throws Exception;

    void watching();
}
