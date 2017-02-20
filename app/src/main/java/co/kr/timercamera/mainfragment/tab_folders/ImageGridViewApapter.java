package co.kr.timercamera.mainfragment.tab_folders;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
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

public class ImageGridViewApapter extends BaseAdapter {

    private Context mContext = null;
    private ArrayList<ListData> mListData = new ArrayList<ListData>();
    DisplayMetrics mMetrics;

    public ImageGridViewApapter(Context context) {
        this.mContext = context;

        mMetrics = new DisplayMetrics();
        Activity activity = (Activity) context;
        activity.getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
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


//        int rowWidth = (mMetrics.widthPixels) / 3;
//
//        ImageView imageView;
//        if (convertView == null) {
//            imageView = new ImageView(mContext);
//            imageView.setLayoutParams(new GridView.LayoutParams(rowWidth,rowWidth));
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            imageView.setPadding(1, 1, 1, 1);
//        } else {
//            imageView = (ImageView) convertView;
//        }
//        imageView.setImageResource(mListData.get(position));
//        return imageView;

        ImgGridViewHolder holder;
        if (convertView == null) {
            holder = new ImgGridViewHolder();

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.image_grid_folder, null);

            holder.mFolder = (ImageView) convertView.findViewById(R.id.img_folder);
            holder.mText = (TextView) convertView.findViewById(R.id.txt_folder_name);

            convertView.setTag(holder);
        } else {
            holder = (ImgGridViewHolder) convertView.getTag();
        }

        ListData mData = mListData.get(position);

        if (mData.mIcon != null) {
            holder.mFolder.setVisibility(View.VISIBLE);
            // Folder 고정
            holder.mFolder.setImageDrawable(mData.mIcon);
        } else {
            holder.mFolder.setVisibility(View.GONE);
        }

        holder.mText.setText(mData.mTitle);

        return convertView;
    }

    public void clearItem() {
        if (null != mListData) {
           mListData.clear();
        }
    }

    public void addItem(String date){

        ListData addInfo = null;
        addInfo = new ListData();
        addInfo.mIcon = mContext.getResources().getDrawable(R.drawable.ic_folder);
        addInfo.mTitle = date;

        mListData.add(addInfo);
    }
}

class ImgGridViewHolder {
    public ImageView mFolder;

    public TextView mText;
}
