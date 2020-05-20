package com.smartseat.workgroup.main.model;

import android.content.Context;

import com.smartseat.workgroup.common.event.InitSwitchEvent;
import com.smartseat.workgroup.common.utils.GsonUtil;
import com.smartseat.workgroup.common.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jx
 * 设置服务器模型
 */
public class SettingServiceModel implements Serializable {

    //端口号
    private String port;
    //主机号
    private String host;
    //是否选中
    private boolean isSelect;

    private static List<SettingServiceModel> settingServiceModelList = new ArrayList<>();

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    /**
     * 保存设置服务器相关地址、端口号、备注等信息
     */
    public static SettingServiceModel saveSettingServiceModel(Context context, JSONObject jsonObject) {
        SettingServiceModel model = null;
        model = GsonUtil.GsonToBean(jsonObject.toString(), SettingServiceModel.class);
        if (null == model) {
            model = new SettingServiceModel();
        }
        SPUtils.putModel(context, model);
        settingServiceModelList.add(model);
        SPUtils.saveSettingServiceList(context, settingServiceModelList);
        EventBus.getDefault().post(new InitSwitchEvent.getSettingServiceList(settingServiceModelList));
        return model;
    }
}
