package vaxsoft.com.vaxphone.CustomViews.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public interface ICustomRecyclerView
{
    RecyclerView.ViewHolder OnViewHolderCreate(ViewGroup parent, int nViewType);
    void OnViewHolderBindUI(RecyclerView.ViewHolder holder, Boolean bDeleteActivated, Boolean bDeleteSelected, int nPosition, int nViewType);
    void OnViewHolderDeleted(int nPosition, int nViewType);
    void OnViewHolderDeleteSelected(RecyclerView.ViewHolder holder, boolean bDeleteEnabled, int nPosition, int nViewType);
    void OnViewHolderRowClicked(RecyclerView.ViewHolder holder, int nPosition);
}
