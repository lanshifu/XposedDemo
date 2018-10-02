package com.lanshifu.xposeddemo.bean;

import java.util.List;

/**
 * 好友详情，查看哪些能量可收，id
 * Created by lanshifu on 2018/9/29.
 */

public class FriendDetailBean {

    /**
     * bizNo : 03eb85f4-74db-471a-9b6f-62daa367fe75-1538232687951
     * bubbles : [{"business":{"bigIconDisplayName":"线下支付","bizType":"xianxiazhifu","dayIconUrl":"https://zos.alipayobjects.com/rmsportal/yTDRgLNTIRQYhOP.png","id":7,"nightIconUrl":"https://zos.alipayobjects.com/rmsportal/RWtkxNvBYnuZylq.png","smallIconDisplayName":"线下支付"},"canHelpCollect":false,"collectStatus":"AVAILABLE","fullEnergy":5,"id":117651384,"produceTime":1538193587000,"remainEnergy":3,"userId":"2088702893665066"}]
     * combineHandlerVOMap : {"eventConfig":{"resultCode":"LOW_VERSION","resultDesc":"系统繁忙，请稍后重试","success":true},"plantTreeCount":{"resultCode":"LOW_VERSION","resultDesc":"系统繁忙，请稍后重试","success":true}}
     * needGuide : false
     * nextAction : Cultivate
     * now : 1538232687950
     * properties : {"antfarmEntry":"Y","degradeSendCustomizedBarrage":"Y","exchangeMode":"Y","lastLogin":"1538152298980","weather":"Y"}
     * resultCode : SUCCESS
     * resultDesc : 成功
     * retriable : false
     * success : true
     * totalActivityCertificates : 0
     * treeEnergy : {"alias":"shijie","allPhases":[{"index":1,"maxPercent":0,"minPercent":0,"needEnergy":0},{"index":2,"maxPercent":0,"minPercent":0,"needEnergy":895},{"index":3,"maxPercent":0,"minPercent":0,"needEnergy":2685},{"index":4,"maxPercent":0,"minPercent":0,"needEnergy":5370},{"index":5,"maxPercent":0,"minPercent":0,"needEnergy":8950},{"index":6,"maxPercent":0,"minPercent":0,"needEnergy":12530},{"index":7,"maxPercent":200,"minPercent":100,"needEnergy":999999999}],"certificateId":"HE170406222927660566398207","currentEnergy":4589,"currentPhase":{"index":3,"maxPercent":0,"minPercent":0,"needEnergy":2685},"distance":781,"fullEnergy":999999999,"id":535544,"nextPhase":{"index":4,"maxPercent":0,"minPercent":0,"needEnergy":5370},"projectId":0,"templateId":2,"treeName":"世界树","type":"COLLECT","userId":"2088702893665066"}
     * userEnergy : {"canCollectEnergy":false,"canCollectLaterTime":-1,"canHelpCollect":false,"collectableBubbleCount":0,"displayName":"蓝晓彬","energySummation":4669,"forestUser":false,"headPortrait":"","realName":true,"treeAmount":0,"userId":"2088702893665066"}
     * usingUserProps : []
     */

    private String bizNo;
    private CombineHandlerVOMapBean combineHandlerVOMap;
    private boolean needGuide;
    private String nextAction;
    private long now;
    private PropertiesBean properties;
    private String resultCode;
    private String resultDesc;
    private boolean retriable;
    private boolean success;
    private int totalActivityCertificates;
    private TreeEnergyBean treeEnergy;
    private UserEnergyBean userEnergy;
    private List<BubblesBean> bubbles;
    private List<?> usingUserProps;

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public CombineHandlerVOMapBean getCombineHandlerVOMap() {
        return combineHandlerVOMap;
    }

    public void setCombineHandlerVOMap(CombineHandlerVOMapBean combineHandlerVOMap) {
        this.combineHandlerVOMap = combineHandlerVOMap;
    }

    public boolean isNeedGuide() {
        return needGuide;
    }

    public void setNeedGuide(boolean needGuide) {
        this.needGuide = needGuide;
    }

    public String getNextAction() {
        return nextAction;
    }

    public void setNextAction(String nextAction) {
        this.nextAction = nextAction;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public PropertiesBean getProperties() {
        return properties;
    }

    public void setProperties(PropertiesBean properties) {
        this.properties = properties;
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

    public int getTotalActivityCertificates() {
        return totalActivityCertificates;
    }

    public void setTotalActivityCertificates(int totalActivityCertificates) {
        this.totalActivityCertificates = totalActivityCertificates;
    }

    public TreeEnergyBean getTreeEnergy() {
        return treeEnergy;
    }

    public void setTreeEnergy(TreeEnergyBean treeEnergy) {
        this.treeEnergy = treeEnergy;
    }

    public UserEnergyBean getUserEnergy() {
        return userEnergy;
    }

    public void setUserEnergy(UserEnergyBean userEnergy) {
        this.userEnergy = userEnergy;
    }

    public List<BubblesBean> getBubbles() {
        return bubbles;
    }

    public void setBubbles(List<BubblesBean> bubbles) {
        this.bubbles = bubbles;
    }

    public List<?> getUsingUserProps() {
        return usingUserProps;
    }

    public void setUsingUserProps(List<?> usingUserProps) {
        this.usingUserProps = usingUserProps;
    }

    public static class CombineHandlerVOMapBean {
        /**
         * eventConfig : {"resultCode":"LOW_VERSION","resultDesc":"系统繁忙，请稍后重试","success":true}
         * plantTreeCount : {"resultCode":"LOW_VERSION","resultDesc":"系统繁忙，请稍后重试","success":true}
         */

        private EventConfigBean eventConfig;
        private PlantTreeCountBean plantTreeCount;

        public EventConfigBean getEventConfig() {
            return eventConfig;
        }

        public void setEventConfig(EventConfigBean eventConfig) {
            this.eventConfig = eventConfig;
        }

        public PlantTreeCountBean getPlantTreeCount() {
            return plantTreeCount;
        }

        public void setPlantTreeCount(PlantTreeCountBean plantTreeCount) {
            this.plantTreeCount = plantTreeCount;
        }

        public static class EventConfigBean {
            /**
             * resultCode : LOW_VERSION
             * resultDesc : 系统繁忙，请稍后重试
             * success : true
             */

            private String resultCode;
            private String resultDesc;
            private boolean success;

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

            public boolean isSuccess() {
                return success;
            }

            public void setSuccess(boolean success) {
                this.success = success;
            }
        }

        public static class PlantTreeCountBean {
            /**
             * resultCode : LOW_VERSION
             * resultDesc : 系统繁忙，请稍后重试
             * success : true
             */

            private String resultCode;
            private String resultDesc;
            private boolean success;

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

            public boolean isSuccess() {
                return success;
            }

            public void setSuccess(boolean success) {
                this.success = success;
            }
        }
    }

    public static class PropertiesBean {
        /**
         * antfarmEntry : Y
         * degradeSendCustomizedBarrage : Y
         * exchangeMode : Y
         * lastLogin : 1538152298980
         * weather : Y
         */

        private String antfarmEntry;
        private String degradeSendCustomizedBarrage;
        private String exchangeMode;
        private String lastLogin;
        private String weather;

        public String getAntfarmEntry() {
            return antfarmEntry;
        }

        public void setAntfarmEntry(String antfarmEntry) {
            this.antfarmEntry = antfarmEntry;
        }

        public String getDegradeSendCustomizedBarrage() {
            return degradeSendCustomizedBarrage;
        }

        public void setDegradeSendCustomizedBarrage(String degradeSendCustomizedBarrage) {
            this.degradeSendCustomizedBarrage = degradeSendCustomizedBarrage;
        }

        public String getExchangeMode() {
            return exchangeMode;
        }

        public void setExchangeMode(String exchangeMode) {
            this.exchangeMode = exchangeMode;
        }

        public String getLastLogin() {
            return lastLogin;
        }

        public void setLastLogin(String lastLogin) {
            this.lastLogin = lastLogin;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }
    }

    public static class TreeEnergyBean {
        /**
         * alias : shijie
         * allPhases : [{"index":1,"maxPercent":0,"minPercent":0,"needEnergy":0},{"index":2,"maxPercent":0,"minPercent":0,"needEnergy":895},{"index":3,"maxPercent":0,"minPercent":0,"needEnergy":2685},{"index":4,"maxPercent":0,"minPercent":0,"needEnergy":5370},{"index":5,"maxPercent":0,"minPercent":0,"needEnergy":8950},{"index":6,"maxPercent":0,"minPercent":0,"needEnergy":12530},{"index":7,"maxPercent":200,"minPercent":100,"needEnergy":999999999}]
         * certificateId : HE170406222927660566398207
         * currentEnergy : 4589
         * currentPhase : {"index":3,"maxPercent":0,"minPercent":0,"needEnergy":2685}
         * distance : 781
         * fullEnergy : 999999999
         * id : 535544
         * nextPhase : {"index":4,"maxPercent":0,"minPercent":0,"needEnergy":5370}
         * projectId : 0
         * templateId : 2
         * treeName : 世界树
         * type : COLLECT
         * userId : 2088702893665066
         */

        private String alias;
        private String certificateId;
        private int currentEnergy;
        private CurrentPhaseBean currentPhase;
        private int distance;
        private int fullEnergy;
        private int id;
        private NextPhaseBean nextPhase;
        private int projectId;
        private int templateId;
        private String treeName;
        private String type;
        private String userId;
        private List<AllPhasesBean> allPhases;

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getCertificateId() {
            return certificateId;
        }

        public void setCertificateId(String certificateId) {
            this.certificateId = certificateId;
        }

        public int getCurrentEnergy() {
            return currentEnergy;
        }

        public void setCurrentEnergy(int currentEnergy) {
            this.currentEnergy = currentEnergy;
        }

        public CurrentPhaseBean getCurrentPhase() {
            return currentPhase;
        }

        public void setCurrentPhase(CurrentPhaseBean currentPhase) {
            this.currentPhase = currentPhase;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getFullEnergy() {
            return fullEnergy;
        }

        public void setFullEnergy(int fullEnergy) {
            this.fullEnergy = fullEnergy;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public NextPhaseBean getNextPhase() {
            return nextPhase;
        }

        public void setNextPhase(NextPhaseBean nextPhase) {
            this.nextPhase = nextPhase;
        }

        public int getProjectId() {
            return projectId;
        }

        public void setProjectId(int projectId) {
            this.projectId = projectId;
        }

        public int getTemplateId() {
            return templateId;
        }

        public void setTemplateId(int templateId) {
            this.templateId = templateId;
        }

        public String getTreeName() {
            return treeName;
        }

        public void setTreeName(String treeName) {
            this.treeName = treeName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public List<AllPhasesBean> getAllPhases() {
            return allPhases;
        }

        public void setAllPhases(List<AllPhasesBean> allPhases) {
            this.allPhases = allPhases;
        }

        public static class CurrentPhaseBean {
            /**
             * index : 3
             * maxPercent : 0
             * minPercent : 0
             * needEnergy : 2685
             */

            private int index;
            private int maxPercent;
            private int minPercent;
            private int needEnergy;

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }

            public int getMaxPercent() {
                return maxPercent;
            }

            public void setMaxPercent(int maxPercent) {
                this.maxPercent = maxPercent;
            }

            public int getMinPercent() {
                return minPercent;
            }

            public void setMinPercent(int minPercent) {
                this.minPercent = minPercent;
            }

            public int getNeedEnergy() {
                return needEnergy;
            }

            public void setNeedEnergy(int needEnergy) {
                this.needEnergy = needEnergy;
            }
        }

        public static class NextPhaseBean {
            /**
             * index : 4
             * maxPercent : 0
             * minPercent : 0
             * needEnergy : 5370
             */

            private int index;
            private int maxPercent;
            private int minPercent;
            private int needEnergy;

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }

            public int getMaxPercent() {
                return maxPercent;
            }

            public void setMaxPercent(int maxPercent) {
                this.maxPercent = maxPercent;
            }

            public int getMinPercent() {
                return minPercent;
            }

            public void setMinPercent(int minPercent) {
                this.minPercent = minPercent;
            }

            public int getNeedEnergy() {
                return needEnergy;
            }

            public void setNeedEnergy(int needEnergy) {
                this.needEnergy = needEnergy;
            }
        }

        public static class AllPhasesBean {
            /**
             * index : 1
             * maxPercent : 0
             * minPercent : 0
             * needEnergy : 0
             */

            private int index;
            private int maxPercent;
            private int minPercent;
            private int needEnergy;

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }

            public int getMaxPercent() {
                return maxPercent;
            }

            public void setMaxPercent(int maxPercent) {
                this.maxPercent = maxPercent;
            }

            public int getMinPercent() {
                return minPercent;
            }

            public void setMinPercent(int minPercent) {
                this.minPercent = minPercent;
            }

            public int getNeedEnergy() {
                return needEnergy;
            }

            public void setNeedEnergy(int needEnergy) {
                this.needEnergy = needEnergy;
            }
        }
    }

    public static class UserEnergyBean {
        /**
         * canCollectEnergy : false
         * canCollectLaterTime : -1
         * canHelpCollect : false
         * collectableBubbleCount : 0
         * displayName : 蓝晓彬
         * energySummation : 4669
         * forestUser : false
         * headPortrait :
         * realName : true
         * treeAmount : 0
         * userId : 2088702893665066
         */

        private boolean canCollectEnergy;
        private int canCollectLaterTime;
        private boolean canHelpCollect;
        private int collectableBubbleCount;
        private String displayName;
        private int energySummation;
        private boolean forestUser;
        private String headPortrait;
        private boolean realName;
        private int treeAmount;
        private String userId;

        public boolean isCanCollectEnergy() {
            return canCollectEnergy;
        }

        public void setCanCollectEnergy(boolean canCollectEnergy) {
            this.canCollectEnergy = canCollectEnergy;
        }

        public int getCanCollectLaterTime() {
            return canCollectLaterTime;
        }

        public void setCanCollectLaterTime(int canCollectLaterTime) {
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

    public static class BubblesBean {
        /**
         * business : {"bigIconDisplayName":"线下支付","bizType":"xianxiazhifu","dayIconUrl":"https://zos.alipayobjects.com/rmsportal/yTDRgLNTIRQYhOP.png","id":7,"nightIconUrl":"https://zos.alipayobjects.com/rmsportal/RWtkxNvBYnuZylq.png","smallIconDisplayName":"线下支付"}
         * canHelpCollect : false
         * collectStatus : AVAILABLE
         * fullEnergy : 5
         * id : 117651384
         * produceTime : 1538193587000
         * remainEnergy : 3
         * userId : 2088702893665066
         */

        private BusinessBean business;
        private boolean canHelpCollect;
        private String collectStatus;
        private int fullEnergy;
        private long id;
        private long produceTime;
        private int remainEnergy;
        private String userId;

        public BusinessBean getBusiness() {
            return business;
        }

        public void setBusiness(BusinessBean business) {
            this.business = business;
        }

        public boolean isCanHelpCollect() {
            return canHelpCollect;
        }

        public void setCanHelpCollect(boolean canHelpCollect) {
            this.canHelpCollect = canHelpCollect;
        }

        public String getCollectStatus() {
            return collectStatus;
        }

        public void setCollectStatus(String collectStatus) {
            this.collectStatus = collectStatus;
        }

        public int getFullEnergy() {
            return fullEnergy;
        }

        public void setFullEnergy(int fullEnergy) {
            this.fullEnergy = fullEnergy;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getProduceTime() {
            return produceTime;
        }

        public void setProduceTime(long produceTime) {
            this.produceTime = produceTime;
        }

        public int getRemainEnergy() {
            return remainEnergy;
        }

        public void setRemainEnergy(int remainEnergy) {
            this.remainEnergy = remainEnergy;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public static class BusinessBean {
            /**
             * bigIconDisplayName : 线下支付
             * bizType : xianxiazhifu
             * dayIconUrl : https://zos.alipayobjects.com/rmsportal/yTDRgLNTIRQYhOP.png
             * id : 7
             * nightIconUrl : https://zos.alipayobjects.com/rmsportal/RWtkxNvBYnuZylq.png
             * smallIconDisplayName : 线下支付
             */

            private String bigIconDisplayName;
            private String bizType;
            private String dayIconUrl;
            private int id;
            private String nightIconUrl;
            private String smallIconDisplayName;

            public String getBigIconDisplayName() {
                return bigIconDisplayName;
            }

            public void setBigIconDisplayName(String bigIconDisplayName) {
                this.bigIconDisplayName = bigIconDisplayName;
            }

            public String getBizType() {
                return bizType;
            }

            public void setBizType(String bizType) {
                this.bizType = bizType;
            }

            public String getDayIconUrl() {
                return dayIconUrl;
            }

            public void setDayIconUrl(String dayIconUrl) {
                this.dayIconUrl = dayIconUrl;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getNightIconUrl() {
                return nightIconUrl;
            }

            public void setNightIconUrl(String nightIconUrl) {
                this.nightIconUrl = nightIconUrl;
            }

            public String getSmallIconDisplayName() {
                return smallIconDisplayName;
            }

            public void setSmallIconDisplayName(String smallIconDisplayName) {
                this.smallIconDisplayName = smallIconDisplayName;
            }
        }
    }
}
