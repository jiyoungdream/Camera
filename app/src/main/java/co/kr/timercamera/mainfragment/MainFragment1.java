package co.kr.timercamera.mainfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.HashMap;

import co.kr.timercamera.R;
import co.kr.timercamera.mainfragment.tab_folders.ImageGridViewApapter;
import co.kr.timercamera.util.HashMapIO;

import static co.kr.timercamera.R.id.gridview;

/**
 * Created by si on 2017. 2. 14..
 */

public class MainFragment1 extends Fragment {

    ImageGridViewApapter customAdapter = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();

        customAdapter.clearItem();
        HashMap<String, ArrayList<String>> folderList = HashMapIO.readHashMapIO();
        for (String key : folderList.keySet()) {
            customAdapter.addItem(key);
        }
        customAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_tab1 ,container, false);
        GridView gridView = (GridView) view.findViewById(gridview);

        customAdapter = new ImageGridViewApapter(getActivity());
        gridView.setAdapter(customAdapter); // uses the view to get the context instead of getActivity().
        gridView.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return view;

    }
}
