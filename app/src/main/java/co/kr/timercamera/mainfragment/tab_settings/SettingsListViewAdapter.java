package co.kr.timercamera.mainfragment.tab_settings;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import co.kr.timercamera.R;
import co.kr.timercamera.util.ListData;

/**
 * Created by si on 2017. 2. 14..
 */

public class SettingsListViewAdapter extends BaseAdapter {

    private Context mContext = null;
    private ArrayList<ListData> mListData = new ArrayList<ListData>();

    public SettingsListViewAdapter(Context mContext) {
        super();
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public Object getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.settings_custom_item, null);

            holder.mIcon = (ImageView) convertView.findViewById(R.id.mImage);
            holder.mText = (TextView) convertView.findViewById(R.id.mText);
            holder.mStatus = (TextView) convertView.findViewById(R.id.status);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ListData mData = mListData.get(position);

        if (mData.mIcon != null) {
            holder.mIcon.setVisibility(View.VISIBLE);
            holder.mIcon.setImageDrawable(mData.mIcon);
        } else {
            holder.mIcon.setVisibility(View.GONE);
        }

        holder.mText.setText(mData.mTitle);
        holder.mStatus.setText(mData.status);

        return convertView;
    }

    public void addItem(Drawable icon, String mTitle, String mStatus){
        ListData addInfo = null;
        addInfo = new ListData();
        addInfo.mIcon = icon;
        addInfo.mTitle = mTitle;
        addInfo.status = mStatus;

        mListData.add(addInfo);
    }

    public void replaceItem(int idx, String mStatus) {
        ListData listData = mListData.get(idx);
        listData.setStatus(mStatus);
        dataChange();
    }

    public void remove(int position){
        mListData.remove(position);
        dataChange();
    }

    public void dataChange(){
        this.notifyDataSetChanged();
    }
}
