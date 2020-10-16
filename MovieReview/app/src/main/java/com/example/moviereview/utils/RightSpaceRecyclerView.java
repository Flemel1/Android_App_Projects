package com.example.moviereview.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RightSpaceRecyclerView extends RecyclerView.ItemDecoration {
    private int rightSpace;

    public RightSpaceRecyclerView(int rightSpace){
        this.rightSpace = rightSpace;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.right = rightSpace;
    }
}
