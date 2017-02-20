package co.kr.timercamera.util;

import android.graphics.drawable.Drawable;

/**
 * Created by si on 2017. 2. 14..
 */

public class ListData {
    /**
     * 리스트 정보를 담고 있을 객체 생성
     */
    // 아이콘
    public Drawable mIcon;

    // 제목
    public String mTitle;

    // 상태
    public String status;

    // 사용자가 찍은 이미지 네임
    public String picName;

    public Drawable getmIcon() {
        return mIcon;
    }

    public void setmIcon(Drawable mIcon) {
        this.mIcon = mIcon;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }
}



