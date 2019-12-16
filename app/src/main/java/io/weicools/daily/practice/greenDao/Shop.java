package io.weicools.daily.practice.greenDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Create by weicools on 2018/1/1.
 */

@Entity
public class Shop {
  // 购物车
  public static final int TYPE_CART = 0x01;
  // 收藏夹
  public static final int TYPE_LOVE = 0x02;

  @Id
  private Long id;

  // 商品名称
  @Unique
  private String name;

  // 商品价格
  @Property(nameInDb = "price")
  private String price;

  // 销售数量
  private int sell_num;

  // 物品图标
  private String image_url;

  // 商家地址
  private String address;

  // 商品列表类型
  private int type;

  @Generated(hash = 1304458862)
  public Shop(Long id, String name, String price, int sell_num, String image_url,
              String address, int type) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.sell_num = sell_num;
    this.image_url = image_url;
    this.address = address;
    this.type = type;
  }

  @Generated(hash = 633476670)
  public Shop() {
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPrice() {
    return this.price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public int getSell_num() {
    return this.sell_num;
  }

  public void setSell_num(int sell_num) {
    this.sell_num = sell_num;
  }

  public String getImage_url() {
    return this.image_url;
  }

  public void setImage_url(String image_url) {
    this.image_url = image_url;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getType() {
    return this.type;
  }

  public void setType(int type) {
    this.type = type;
  }
}
