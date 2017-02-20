package co.kr.timercamera.mainfragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import co.kr.timercamera.R;
import co.kr.timercamera.mainfragment.tab_settings.SettingsListViewAdapter;
import co.kr.timercamera.util.Constants;
import co.kr.timercamera.util.SharedPreference;

/**
 * Created by si on 2017. 2. 14..
 */

public class MainFragment2 extends ListFragment {

    SettingsListViewAdapter customAdapter = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        customAdapter = new SettingsListViewAdapter(getActivity());

        // 딜레이 타임 Array
        final CharSequence[] delayItems = getResources().getStringArray(R.array.delay_time);
        int delayTimeIdx = getSavedIdx(Constants.DELAY_VALUE_INDEX, false);
        if (Constants.INT_DEFAULT_NOT_EXIST == delayTimeIdx) {
            customAdapter.addItem(getResources().getDrawable(R.drawable.ic_clock), getResources().getString(R.string.delay_time), (String)delayItems[0]);
        }
        else {
            customAdapter.addItem(getResources().getDrawable(R.drawable.ic_clock), getResources().getString(R.string.delay_time), (String)delayItems[delayTimeIdx]);
        }

        // 딜레이 타임 Array
        final CharSequence[] repeatItems = getResources().getStringArray(R.array.repeat_cycle);
        int repeatCntIdx = getSavedIdx(Constants.REPEAT_CYCLE_VALUE_INDEX, false);
        if (Constants.INT_DEFAULT_NOT_EXIST == repeatCntIdx) {
            customAdapter.addItem(getResources().getDrawable(R.drawable.ic_repeat), getResources().getString(R.string.repeat_count), (String)repeatItems[0]);
        }
        else {
            customAdapter.addItem(getResources().getDrawable(R.drawable.ic_repeat), getResources().getString(R.string.repeat_count), (String)repeatItems[repeatCntIdx]);
        }
        setListAdapter(customAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);

        // 딜레이 시간 설정
        if (position == 0) {
            CharSequence[] items = getResources().getStringArray(R.array.delay_time);
            Drawable icon = getResources().getDrawable(R.drawable.ic_clock);
            String title = getResources().getString(R.string.alert_title_select_delaytime);
            String preferencKey = Constants.DELAY_VALUE_INDEX;

            alertBulid(items, icon, title, 1, preferencKey);
        }
        else if (position == 1) {
            CharSequence[] items = getResources().getStringArray(R.array.repeat_cycle);
            Drawable icon = getResources().getDrawable(R.drawable.ic_repeat);
            String title = getResources().getString(R.string.alert_title_select_repeatcount);
            String preferencKey = Constants.REPEAT_CYCLE_VALUE_INDEX;

            alertBulid(items, icon, title, 2, preferencKey);
        }
//        String item = adapter.getItem(position);
//        Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();
    }

    private int getSavedIdx(String key, boolean isAlertList) {
        if (!isAlertList) {
            return SharedPreference.readIntPreference(getActivity(), key);
        }
        else {
            int value = SharedPreference.readIntPreference(getActivity(), key);
            if (value == Constants.INT_DEFAULT_NOT_EXIST) return 0;
            else return  value;
        }
    }

    /**
     *
     * @param sequences
     * @param icon
     * @param title
     * @param settingType   1. 반복 시간. 2. 촬영 횟수
     * @param preferenceKey sharedPreference 저장 키
     */
    private void alertBulid(CharSequence[] sequences, Drawable icon, String title, final int settingType, final String preferenceKey) {
        // 딜레이 타임 Array
        final CharSequence[] items = sequences;
        int delayTimeIdx = 0;
        if (settingType == 1) {
            delayTimeIdx = getSavedIdx(preferenceKey, true);
        }
        else if (settingType == 2) {
            delayTimeIdx = getSavedIdx(preferenceKey, true);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());     // 여기서 this는 Activity의 this

        // 여기서 부터는 알림창의 속성 설정
        builder.setIcon(R.drawable.ic_clock);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setTitle(title)        // 제목 설정
                .setSingleChoiceItems(items, delayTimeIdx, new DialogInterface.OnClickListener(){
                    // 목록 클릭시 설정
                    public void onClick(DialogInterface dialog, int index){
                        SharedPreference.writeIntPreference(getActivity(), preferenceKey, index);

                        // 해당 설정 화면의 위치 값 변경
                        if (settingType == 1) {
                            customAdapter.replaceItem(0, (String)items[index]);
                        }
                        else if (settingType == 2) {
                            customAdapter.replaceItem(1, (String)items[index]);
                        }
                        dialog.dismiss();
                    }
                });


        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기
    }
}
