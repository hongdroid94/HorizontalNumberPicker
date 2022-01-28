package com.example.horizontalnumberpicker;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 리사이클러 뷰 아이템 사이 간격을 늘리기 위해 사용한 데코레이션 클래스
 */
public class CustomDecoration extends RecyclerView.ItemDecoration {
    private final int miDivWidth;

    public CustomDecoration(int _iDivWidth) {
        miDivWidth = _iDivWidth;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left  = miDivWidth;
        outRect.right = miDivWidth;
    }
}
