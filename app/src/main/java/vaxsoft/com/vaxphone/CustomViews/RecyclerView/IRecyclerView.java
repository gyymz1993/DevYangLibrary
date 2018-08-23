package vaxsoft.com.vaxphone.CustomViews.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public interface IRecyclerView
{
    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);
    void onBindViewHolder(RecyclerView.ViewHolder holder, int position);
    int getItemCount();
    int getItemViewType(int position);
}
