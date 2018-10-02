package com.lanshifu.xposeddemo.bean;

import java.util.List;

/**
 * 收取能量结果
 * Created by lanshifu on 2018/9/30.
 */

public class CollectionResultBean {
    /**
     * bubbles : [{"business":{"bizType":"xianxiazhifu","id":0},"canHelpCollect":false,"collected":false,"collectedEnergy":1,"collectorUserId":"2088702893665066","fullEnergy":5,"id":114934686,"insufficient":false,"noShow":false,"produceTime":1538107146000,"remainEnergy":3,"robbedToday":false,"unavailable":false,"userId":"2088902124760652","waiting":false,"wastedEnergy":0}]
     * crossPhase : false
     * failedBubbleIds : []
     * resultCode : SUCCESS
     * resultDesc : 成功
     * retriable : false
     * success : true
     * treeEnergy : {"alias":"shijie","allPhases":[{"index":1,"maxPercent":0,"minPercent":0,"needEnergy":0},{"index":2,"maxPercent":0,"minPercent":0,"needEnergy":895},{"index":3,"maxPercent":0,"minPercent":0,"needEnergy":2685},{"index":4,"maxPercent":0,"minPercent":0,"needEnergy":5370},{"index":5,"maxPercent":0,"minPercent":0,"needEnergy":8950},{"index":6,"maxPercent":0,"minPercent":0,"needEnergy":12530},{"index":7,"maxPercent":200,"minPercent":100,"needEnergy":999999999}],"certificateId":"HE170406222927660566398207","currentEnergy":4463,"currentPhase":{"index":3,"maxPercent":0,"minPercent":0,"needEnergy":2685},"distance":907,"fullEnergy":999999999,"id":535544,"nextPhase":{"index":4,"maxPercent":0,"minPercent":0,"needEnergy":5370},"projectId":0,"templateId":2,"treeName":"世界树","type":"COLLECT","userId":"2088702893665066"}
     */

    private boolean crossPhase;
    private String resultCode;
    private String resultDesc;
    private boolean retriable;
    private boolean success;
    private TreeEnergyBean treeEnergy;
    private List<BubblesBean> bubbles;
    private List<?> failedBubbleIds;

    public boolean isCrossPhase() {
        return crossPhase;
    }

    public void setCrossPhase(boolean crossPhase) {
        this.crossPhase = crossPhase;
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

    public TreeEnergyBean getTreeEnergy() {
        return treeEnergy;
    }

    public void setTreeEnergy(TreeEnergyBean treeEnergy) {
        this.treeEnergy = treeEnergy;
    }

    public List<BubblesBean> getBubbles() {
        return bubbles;
    }

    public void setBubbles(List<BubblesBean> bubbles) {
        this.bubbles = bubbles;
    }

    public List<?> getFailedBubbleIds() {
        return failedBubbleIds;
    }

    public void setFailedBubbleIds(List<?> failedBubbleIds) {
        this.failedBubbleIds = failedBubbleIds;
    }

    public static class TreeEnergyBean {
        /**
         * alias : shijie
         * allPhases : [{"index":1,"maxPercent":0,"minPercent":0,"needEnergy":0},{"index":2,"maxPercent":0,"minPercent":0,"needEnergy":895},{"index":3,"maxPercent":0,"minPercent":0,"needEnergy":2685},{"index":4,"maxPercent":0,"minPercent":0,"needEnergy":5370},{"index":5,"maxPercent":0,"minPercent":0,"needEnergy":8950},{"index":6,"maxPercent":0,"minPercent":0,"needEnergy":12530},{"index":7,"maxPercent":200,"minPercent":100,"needEnergy":999999999}]
         * certificateId : HE170406222927660566398207
         * currentEnergy : 4463
         * currentPhase : {"index":3,"maxPercent":0,"minPercent":0,"needEnergy":2685}
         * distance : 907
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

    public static class BubblesBean {
        /**
         * business : {"bizType":"xianxiazhifu","id":0}
         * canHelpCollect : false
         * collected : false
         * collectedEnergy : 1
         * collectorUserId : 2088702893665066
         * fullEnergy : 5
         * id : 114934686
         * insufficient : false
         * noShow : false
         * produceTime : 1538107146000
         * remainEnergy : 3
         * robbedToday : false
         * unavailable : false
         * userId : 2088902124760652
         * waiting : false
         * wastedEnergy : 0
         */

        private BusinessBean business;
        private boolean canHelpCollect;
        private boolean collected;
        private int collectedEnergy;
        private String collectorUserId;
        private int fullEnergy;
        private int id;
        private boolean insufficient;
        private boolean noShow;
        private long produceTime;
        private int remainEnergy;
        private boolean robbedToday;
        private boolean unavailable;
        private String userId;
        private boolean waiting;
        private int wastedEnergy;

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

        public boolean isCollected() {
            return collected;
        }

        public void setCollected(boolean collected) {
            this.collected = collected;
        }

        public int getCollectedEnergy() {
            return collectedEnergy;
        }

        public void setCollectedEnergy(int collectedEnergy) {
            this.collectedEnergy = collectedEnergy;
        }

        public String getCollectorUserId() {
            return collectorUserId;
        }

        public void setCollectorUserId(String collectorUserId) {
            this.collectorUserId = collectorUserId;
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

        public boolean isInsufficient() {
            return insufficient;
        }

        public void setInsufficient(boolean insufficient) {
            this.insufficient = insufficient;
        }

        public boolean isNoShow() {
            return noShow;
        }

        public void setNoShow(boolean noShow) {
            this.noShow = noShow;
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

        public boolean isRobbedToday() {
            return robbedToday;
        }

        public void setRobbedToday(boolean robbedToday) {
            this.robbedToday = robbedToday;
        }

        public boolean isUnavailable() {
            return unavailable;
        }

        public void setUnavailable(boolean unavailable) {
            this.unavailable = unavailable;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public boolean isWaiting() {
            return waiting;
        }

        public void setWaiting(boolean waiting) {
            this.waiting = waiting;
        }

        public int getWastedEnergy() {
            return wastedEnergy;
        }

        public void setWastedEnergy(int wastedEnergy) {
            this.wastedEnergy = wastedEnergy;
        }

        public static class BusinessBean {
            /**
             * bizType : xianxiazhifu
             * id : 0
             */

            private String bizType;
            private int id;

            public String getBizType() {
                return bizType;
            }

            public void setBizType(String bizType) {
                this.bizType = bizType;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
