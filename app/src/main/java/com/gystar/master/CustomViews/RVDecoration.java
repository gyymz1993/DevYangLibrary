package com.gystar.master.CustomViews;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.utils.gyymz.utils.UIUtils;

public class RVDecoration extends RecyclerView.ItemDecoration{
    private int mSpace = UIUtils.dp2px(20);
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        int pos = parent.getChildAdapterPosition(view);
        outRect.left = 0;
        outRect.top = 0;
        outRect.bottom = 0;
        if (pos == 0) {
            outRect.top = mSpace;
            outRect.bottom = UIUtils.dip2px(15);
        } else if (pos == (childCount - 1)) {
            outRect.bottom = UIUtils.dip2px(15);
        } else {
            outRect.bottom = mSpace;
        }
    }
}
