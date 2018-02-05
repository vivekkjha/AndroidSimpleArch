package com.mds.android.helper.layoutmanager;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by vivekjha on 16/06/16.
 */
public class CategoriesSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public CategoriesSpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.left = space/2;
        outRect.right = space/2;
        outRect.bottom = space;

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = space;
        } else {
            outRect.top = 0;
        }
    }
}
