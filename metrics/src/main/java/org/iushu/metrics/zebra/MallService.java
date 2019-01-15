package org.iushu.metrics.zebra;

/**
 * Created by iuShu on 19-1-14
 */
public class MallService {

    public Object getRecommendItems() {

        // query recommend item data

        return new Object();
    }

    public boolean isVip() {

        // query user vip data

        return true;
    }

    public Object getItemDetail(long itemId) {

        // query item detail by itemId

        return new Object();
    }

    public Object hotItemRanking(int categoryId) {

        // query item hot score by categoryId and sort them

        return new Object();
    }

    public Object hotItemRanking(String keyword) {

        // query item hot score by keyword and sort them

        return new Object();
    }

    public Object getItemListByCategory() {

        // query item data by category

        return new Object();
    }

}
