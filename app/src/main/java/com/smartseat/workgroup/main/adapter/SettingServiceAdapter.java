package com.smartseat.workgroup.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.smartseat.workgroup.R;
import com.smartseat.workgroup.common.event.InitSwitchEvent;
import com.smartseat.workgroup.common.utils.ToaskUtils;
import com.smartseat.workgroup.main.model.SettingServiceModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class SettingServiceAdapter extends BaseAdapter {
    Context context;
    LayoutInflater mInflater;
    ViewHolder holder;
    List<SettingServiceModel> mSettingServiceModelList;
    SettingServiceModel mSettingServiceActivity;

    public SettingServiceAdapter(Context context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public void setList(List<SettingServiceModel> settingServiceModelList) {
        this.mSettingServiceModelList = settingServiceModelList;
    }

    // 选中当前选项时，让其他选项不被选中
    public void select(int position) {
        if (!mSettingServiceModelList.get(position).isSelect()) {
            mSettingServiceModelList.get(position).setSelect(true);
            for (int i = 0; i < mSettingServiceModelList.size(); i++) {
                if (i != position) {
                    mSettingServiceModelList.get(i).setSelect(false);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mSettingServiceModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return mSettingServiceModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_setting_service_list, null);
            holder.radioBtn = convertView
                    .findViewById(R.id.radiobutton);
            holder.radioBtn.setClickable(false);
            holder.textView = convertView
                    .findViewById(R.id.tv_host_name);
            holder.mIvRightArrow = convertView.findViewById(R.id.iv_right_arrow);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        mSettingServiceActivity = mSettingServiceModelList.get(position);
        holder.radioBtn.setChecked(mSettingServiceActivity.isSelect());
        holder.textView.setText(mSettingServiceActivity.getHost());
        holder.mIvRightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToaskUtils.showToast("显示删除页面");
                EventBus.getDefault().post(new InitSwitchEvent.showEditSettingPage());
            }
        });
        return convertView;
    }

    class ViewHolder {
        RadioButton radioBtn;
        TextView textView;
        ImageView mIvRightArrow;
    }
}
