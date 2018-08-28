package vaxsoft.com.vaxphone.CustomViews.RecyclerView;

import android.view.ActionMode;
import android.view.MenuItem;

interface IRecyclerViewActionMode
{
    boolean onActionItemClicked(ActionMode mode, MenuItem item);
    void onDestroyActionMode(ActionMode mode);
}
