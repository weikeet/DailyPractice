package io.weicools.daily.practice.room.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

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
    @ColumnInfo(name = "msg_id")
    @Expose
    @NonNull
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
    private long receiveTime;

    @ColumnInfo(name = "logo_avatar")
    @Expose
    @SerializedName("logo_avatar")
    private String logoAvatar;

    public CardDataEntity(String phoneNum, String content, long receiveTime) {
        this.msgId = UUID.randomUUID().toString();
        this.logoAvatar = "http://blog-1251678165.coscd.myqcloud.com/2018-01-01-083828.jpg";
        this.phoneNum = phoneNum;
        this.content = content;
        this.receiveTime = receiveTime;
    }

    @NonNull
    public String getMsgId() {
        return msgId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getContent() {
        return content;
    }

    public long getReceiveTime() {
        return receiveTime;
    }

    public String getLogoAvatar() {
        return logoAvatar;
    }

    public void setMsgId(@NonNull String msgId) {
        this.msgId = msgId;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setReceiveTime(long receiveTime) {
        this.receiveTime = receiveTime;
    }

    public void setLogoAvatar(String logoAvatar) {
        this.logoAvatar = logoAvatar;
    }
}
