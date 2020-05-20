package com.smartseat.workgroup.common.event;


import com.smartseat.workgroup.main.model.SettingServiceModel;

import java.util.List;

/**
 * @author jx
 * 首页模块开关事件
 */

public class InitSwitchEvent extends BaseEvent {

    public static class getSettingServiceList extends InitSwitchEvent {
        private List<SettingServiceModel> mSettingServiceModelList;

        public getSettingServiceList(List<SettingServiceModel> settingServiceModelList) {
            this.mSettingServiceModelList = settingServiceModelList;
        }

        public List<SettingServiceModel> getmSettingServiceModelList() {
            return mSettingServiceModelList;
        }

        public void setmSettingServiceModelList(List<SettingServiceModel> mSettingServiceModelList) {
            this.mSettingServiceModelList = mSettingServiceModelList;
        }
    }

    /**
     * 从选择服务器选项中保存发送到登录页面
     */
    public static class sendSelectedToLoginPage extends InitSwitchEvent {
        private SettingServiceModel mSettingServiceModel;

        public sendSelectedToLoginPage(SettingServiceModel settingServiceModel) {
            this.mSettingServiceModel = settingServiceModel;
        }

        public SettingServiceModel getmSettingServiceModel() {
            return mSettingServiceModel;
        }

        public void setmSettingServiceModel(SettingServiceModel mSettingServiceModel) {
            this.mSettingServiceModel = mSettingServiceModel;
        }
    }

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
