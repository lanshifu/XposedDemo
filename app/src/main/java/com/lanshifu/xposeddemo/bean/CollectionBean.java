package com.lanshifu.xposeddemo.bean;

/**
 * Created by lanshifu on 2018/9/30.
 *
 * 用户id 跟能量id对应
 */

public class CollectionBean {

    public CollectionBean(String userId, long bubbleIds) {
        this.userId = userId;
        this.bubbleIds = bubbleIds;
    }

    @Override
    public String toString() {
        return "{" +
                "userId='" + userId + '\'' +
                ", bubbleIds=" + bubbleIds +
                '}';
    }

    public String userId;
    public long bubbleIds;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CollectionBean) {
            return ((((CollectionBean) obj).bubbleIds == bubbleIds) &&
                    ((CollectionBean) obj).userId.equals(userId));
        }
        return super.equals(obj);
    }
}