package com.mds.android.helper.widget.recycler;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by vivekjha on 14/08/16.
 */
public abstract class GridRecyclerWrapper<T> extends AppRecycler<T>  {


    public GridRecyclerWrapper(Context context) {
        super(context);
    }

    public GridRecyclerWrapper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridRecyclerWrapper(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected DisplayMode getDisplayMode() {
        return DisplayMode.GRID;
    }

    @Override
    protected ViewHolder getListItemViewHolder(int type) {
        return null;
    }

    @Override
    protected void bindListViewHolder(ViewHolder viewHolder, int index, T data) {

    }


}
