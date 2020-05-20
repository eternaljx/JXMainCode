package com.smartseat.workgroup.common.event;

/**
 * Created by 49539 on 2018/8/2.
 */

public class BaseEvent {
    /**
     * 网络数据请求错误
     */
    public static class OnWebDataError {

        /** 错误提示语 */
        public String tip = "网络不给力哦...";

        public OnWebDataError(String tip) {
            this.tip = tip;
        }

        public OnWebDataError() {

        }

    }
}
