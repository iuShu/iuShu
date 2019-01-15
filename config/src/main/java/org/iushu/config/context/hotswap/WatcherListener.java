package org.iushu.config.context.hotswap;

import org.iushu.config.document.Document;

/**
 * Created by iuShu on 19-1-15
 */
public interface WatcherListener {

    void afterChange(WatchEvent event, Document document);

}
