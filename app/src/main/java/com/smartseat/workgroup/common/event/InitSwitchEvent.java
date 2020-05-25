package com.smartseat.workgroup.common.event;


/**
 * @author jx
 * 首页模块开关事件
 */

public class InitSwitchEvent extends BaseEvent {


    /**
     * 显示编辑设置服务器页面
     */
    public static class showEditSettingPage extends InitSwitchEvent {

    }

    /**
     * 刪除集合中某条数据，更新数据
     */
    public static class deleteSingleListData extends InitSwitchEvent {

    }

    /**
     * 退出登录
     */
    public static class exitLogin extends InitSwitchEvent {

    }

}
