package io.weicools.daily.practice.room.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.weicools.daily.practice.room.databse.StringTypeConverter;

/**
 * Create by weicools on 2017/12/30.
 * <p>
 * Immutable model class for card data.
 * Entity class for {@link com.google.gson.Gson} and {@link android.arch.persistence.room.Room}.
 */

@Entity(tableName = "card_data")
@TypeConverters(StringTypeConverter.class)
public class CardDataEntity {
    @PrimaryKey
    @ColumnInfo(name = "id")
    @Expose
    @SerializedName("id")
    private int id;

    @ColumnInfo(name = "msg_id")
    @Expose
    @SerializedName("msg_id")
    private String msgId;

    @ColumnInfo(name = "phone_num")
    @Expose
    @SerializedName("phone_num")
    private String phoneNum;

    @ColumnInfo(name = "content")
    @Expose
    @SerializedName("content")
    private String content;

    @ColumnInfo(name = "receive_time")
    @Expose
    @SerializedName("receive_time")
    private int receiveTime;

    @ColumnInfo(name = "logo_avatar")
    @Expose
    @SerializedName("logo_avatar")
    private String logoAvatar;

    public int getId() {
        return id;
    }

    public String getMsgId() {
        return msgId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getContent() {
        return content;
    }

    public int getReceiveTime() {
        return receiveTime;
    }

    public String getLogoAvatar() {
        return logoAvatar;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setReceiveTime(int receiveTime) {
        this.receiveTime = receiveTime;
    }

    public void setLogoAvatar(String logoAvatar) {
        this.logoAvatar = logoAvatar;
    }
}
