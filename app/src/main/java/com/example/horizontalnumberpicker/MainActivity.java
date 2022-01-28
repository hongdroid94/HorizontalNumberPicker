package com.example.horizontalnumberpicker;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

public class MainActivity extends AppCompatActivity {

    private CustomPickerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setInitialize();
    }

    private void setInitialize() {

        RecyclerView rvNumberPicker = findViewById(R.id.rv_number_picker);
        rvNumberPicker.setHasFixedSize(true);
        // set item offset
        rvNumberPicker.addItemDecoration(new CustomDecoration(20));
        // snapHelper
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rvNumberPicker);
        mAdapter = new CustomPickerAdapter();
        mAdapter.setCurrentPosition(mAdapter.getItemCount() / 2);
        if ( null != rvNumberPicker.getLayoutManager() )
            rvNumberPicker.getLayoutManager().scrollToPosition((mAdapter.getItemCount() / 2) - 1);
        // set adapter
        rvNumberPicker.setAdapter(mAdapter);

        // check snap position
        rvNumberPicker.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if ( RecyclerView.SCROLL_STATE_IDLE == newState ) {
                    if ( null != recyclerView.getLayoutManager()) {
                        View view = snapHelper.findSnapView(recyclerView.getLayoutManager());
                        int position = recyclerView.getLayoutManager().getPosition(view);
                        mAdapter.setCurrentPosition( position );
                        Log.d("Current Position Value", String.valueOf(mAdapter.getCalcValueIdx(position)));
                    }
                }
            }
        });

        // check button listener
        findViewById(R.id.btn_check).setOnClickListener(view -> {
            if ( null != mAdapter )
                Toast.makeText(MainActivity.this, "position : " + mAdapter.getCalcValueIdx(mAdapter.getCurrentPosition()), Toast.LENGTH_SHORT).show();
        });
    }
}