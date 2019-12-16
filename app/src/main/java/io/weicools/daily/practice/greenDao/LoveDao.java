package io.weicools.daily.practice.greenDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by weicools on 2018/1/1.
 */

public class LoveDao {

  /**
   * 插入数据
   *
   * @param shop shop
   */
  public static void insertLove(Shop shop) {
    // PracticeApp.getDaoInstance().getShopDao().insert(shop);
  }

  /**
   * 删除数据
   *
   * @param id id
   */
  public static void deleteLove(long id) {
    // PracticeApp.getDaoInstance().getShopDao().deleteByKey(id);
  }

  /**
   * 更新数据
   *
   * @param shop shop
   */
  public static void updateLove(Shop shop) {
    // PracticeApp.getDaoInstance().getShopDao().update(shop);
  }

  /**
   * 查询条件为Type=TYPE_LOVE的数据
   *
   * @return shopList
   */
  public static List<Shop> queryShop() {
    return new ArrayList<>();
    // return PracticeApp.getDaoInstance().getShopDao().queryBuilder().where(ShopDao.Properties.Type.eq(Shop.TYPE_LOVE)).list();
  }
}
