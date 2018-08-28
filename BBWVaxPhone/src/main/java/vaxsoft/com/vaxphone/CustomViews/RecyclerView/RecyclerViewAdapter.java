package vaxsoft.com.vaxphone.CustomViews.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private IRecyclerView m_objIRecyclerView;

    ////////////////////////////////////////////////////////////////////////////////////

    RecyclerViewAdapter(IRecyclerView iRecyclerView)
    {
        m_objIRecyclerView = iRecyclerView;
    }

    ///////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return m_objIRecyclerView.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        m_objIRecyclerView.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemViewType(int position)
    {
        return m_objIRecyclerView.getItemViewType(position);
    }

    @Override
    public int getItemCount()
    {
        return m_objIRecyclerView.getItemCount();
    }

    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    void NotifyItemChanged(int nPosition)
    {
        notifyItemChanged(nPosition);
    }

    void NotifyDataSetChanged()
    {
        notifyDataSetChanged();
    }

    void NotifyItemRemoved(int nPostion)
    {
        notifyItemRemoved(nPostion);
    }

    void NotifyItemRangeRemoved(int nStartPos, int nCount)
    {
        notifyItemRangeRemoved(nStartPos, nCount);
    }
}
