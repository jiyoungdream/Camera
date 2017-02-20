package co.kr.timercamera.mainfragment.tab_folders;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import co.kr.timercamera.R;


/**
 * Created by si on 2017. 2. 14..
 */

public class ImageGridDetailActivity extends Activity {

    ImageGridViewApapter customAdapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_grid);

        customAdapter = new ImageGridViewApapter(this);
        customAdapter.addItem("2017-01-01");

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(customAdapter);
        gridview.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


    }
}
