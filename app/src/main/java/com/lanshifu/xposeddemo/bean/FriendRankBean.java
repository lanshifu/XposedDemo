package com.lanshifu.xposeddemo.bean;

import java.util.List;

/**
 * 好友列表信息，是否有可收能量标志
 * Created by lanshifu on 2018/9/29.
 */

public class FriendRankBean {

    /**
     * friendRanking : [{"canCollectEnergy":false,"canCollectLaterTime":-1,"canHelpCollect":false,"collectableBubbleCount":0,"displayName":"","energySummation":158574,"forestUser":false,"headPortrait":"","rank":1,"realName":false,"treeAmount":11,"userId":"2088112411928236"},{"canCollectEnergy":false,"canCollectLaterTime":-1,"canHelpCollect":false,"collectableBubbleCount":0,"displayName":"","energySummation":147507,"forestUser":false,"headPortrait":"","rank":2,"realName":false,"treeAmount":5,"userId":"2088022063156104"},{"canCollectEnergy":false,"canCollectLaterTime":-1,"canHelpCollect":false,"collectableBubbleCount":0,"displayName":"","energySummation":131396,"forestUser":false,"headPortrait":"","rank":3,"realName":false,"treeAmount":1,"userId":"2088112460189181"},{"canCollectEnergy":false,"canCollectLaterTime":-1,"canHelpCollect":false,"collectableBubbleCount":0,"displayName":"","energySummation":80437,"forestUser":false,"headPortrait":"","rank":4,"realName":false,"treeAmount":3,"userId":"2088112981111843"},{"canCollectEnergy":false,"canCollectLaterTime":1538303648000,"canHelpCollect":false,"collectableBubbleCount":0,"displayName":"","energySummation":79457,"forestUser":false,"headPortrait":"","rank":5,"realName":false,"treeAmount":1,"userId":"2088802351350142"},{"canCollectEnergy":false,"canCollectLaterTime":1538302984000,"canHelpCollect":false,"collectableBubbleCount":0,"displayName":"","energySummation":74724,"forestUser":false,"headPortrait":"","rank":6,"realName":false,"treeAmount":4,"userId":"2088222069645899"},{"canCollectEnergy":false,"canCollectLaterTime":1538303375000,"canHelpCollect":false,"collectableBubbleCount":0,"displayName":"","energySummation":54294,"forestUser":false,"headPortrait":"","rank":7,"realName":false,"treeAmount":2,"userId":"2088112402201353"},{"canCollectEnergy":false,"canCollectLaterTime":-1,"canHelpCollect":false,"collectableBubbleCount":0,"displayName":"","energySummation":52041,"forestUser":false,"headPortrait":"","rank":8,"realName":false,"treeAmount":2,"userId":"2088612427788784"},{"canCollectEnergy":false,"canCollectLaterTime":-1,"canHelpCollect":false,"collectableBubbleCount":0,"displayName":"","energySummation":48295,"forestUser":false,"headPortrait":"","rank":9,"realName":false,"treeAmount":1,"userId":"2088302782321295"},{"canCollectEnergy":false,"canCollectLaterTime":-1,"canHelpCollect":false,"collectableBubbleCount":0,"displayName":"","energySummation":45519,"forestUser":false,"headPortrait":"","rank":10,"realName":false,"treeAmount":1,"userId":"2088302323362293"}]
     * hasMore : true
     * queryTimestamp : 1538301486990
     * resultCode : SUCCESS
     * resultDesc : 成功
     * retriable : false
     * success : true
     */

    private boolean hasMore;
    private long queryTimestamp;
    private String resultCode;
    private String resultDesc;
    private boolean retriable;
    private boolean success;
    private List<FriendRankingBean> friendRanking;

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public long getQueryTimestamp() {
        return queryTimestamp;
    }

    public void setQueryTimestamp(long queryTimestamp) {
        this.queryTimestamp = queryTimestamp;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public boolean isRetriable() {
        return retriable;
    }

    public void setRetriable(boolean retriable) {
        this.retriable = retriable;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<FriendRankingBean> getFriendRanking() {
        return friendRanking;
    }

    public void setFriendRanking(List<FriendRankingBean> friendRanking) {
        this.friendRanking = friendRanking;
    }

    public static class FriendRankingBean {
        /**
         * canCollectEnergy : false
         * canCollectLaterTime : -1
         * canHelpCollect : false
         * collectableBubbleCount : 0
         * displayName :
         * energySummation : 158574
         * forestUser : false
         * headPortrait :
         * rank : 1
         * realName : false
         * treeAmount : 11
         * userId : 2088112411928236
         */

        private boolean canCollectEnergy;
        private long canCollectLaterTime;
        private boolean canHelpCollect;
        private int collectableBubbleCount;
        private String displayName;
        private int energySummation;
        private boolean forestUser;
        private String headPortrait;
        private int rank;
        private boolean realName;
        private int treeAmount;
        private String userId;

        public boolean isCanCollectEnergy() {
            return canCollectEnergy;
        }

        public void setCanCollectEnergy(boolean canCollectEnergy) {
            this.canCollectEnergy = canCollectEnergy;
        }

        public long getCanCollectLaterTime() {
            return canCollectLaterTime;
        }

        public void setCanCollectLaterTime(long canCollectLaterTime) {
            this.canCollectLaterTime = canCollectLaterTime;
        }

        public boolean isCanHelpCollect() {
            return canHelpCollect;
        }

        public void setCanHelpCollect(boolean canHelpCollect) {
            this.canHelpCollect = canHelpCollect;
        }

        public int getCollectableBubbleCount() {
            return collectableBubbleCount;
        }

        public void setCollectableBubbleCount(int collectableBubbleCount) {
            this.collectableBubbleCount = collectableBubbleCount;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public int getEnergySummation() {
            return energySummation;
        }

        public void setEnergySummation(int energySummation) {
            this.energySummation = energySummation;
        }

        public boolean isForestUser() {
            return forestUser;
        }

        public void setForestUser(boolean forestUser) {
            this.forestUser = forestUser;
        }

        public String getHeadPortrait() {
            return headPortrait;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public boolean isRealName() {
            return realName;
        }

        public void setRealName(boolean realName) {
            this.realName = realName;
        }

        public int getTreeAmount() {
            return treeAmount;
        }

        public void setTreeAmount(int treeAmount) {
            this.treeAmount = treeAmount;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
