package vaxsoft.com.vaxphone.CustomViews.RecyclerView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vaxsoft.com.vaxphone.R;

public class CustomRecyclerView extends RecyclerView implements IRecyclerView, IRecyclerViewActionMode
{
    private ArrayList<Integer> m_aSectionRowList = null;
    private ArrayList<Boolean> m_aDeleteList = null;

    private ICustomRecyclerView m_iCustomRecyclerView = null;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    private RecyclerViewActionMode mDeleteActionMode = null;

    public final int VIEW_TYPE_SECTION = 0;
    public final int VIEW_TYPE_ROW = 1;

    private boolean m_bDeleteActivated = false;
    private boolean m_bDeleteSelectAll = false;

    public String m_sActionModeTitle;

    /////////////////////////////////////////////////////////////////////////////////////////

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);

        m_aSectionRowList = new ArrayList<>();
        m_aDeleteList = new ArrayList<>();

        m_iCustomRecyclerView = (ICustomRecyclerView) this;

        mRecyclerViewAdapter = new RecyclerViewAdapter(this);
        setAdapter(mRecyclerViewAdapter);

        setLayoutManager(new LinearLayoutManager(context));
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    public void AddRowAtStart(int nViewType)
    {
        if (m_aSectionRowList == null)
            m_aSectionRowList = new ArrayList<>();

        m_aSectionRowList.add(0, nViewType);
        NotifyItemChanged(0);
    }

    public void AddRowAtPosition(int nPosition, int nViewType)
    {
        if (m_aSectionRowList == null)
            m_aSectionRowList = new ArrayList<>();

        m_aSectionRowList.add(nPosition, nViewType);
        NotifyItemChanged(nPosition);
    }

    public void AddRowAtEnd(int nViewType)
    {
        if (m_aSectionRowList == null)
            m_aSectionRowList = new ArrayList<>();

        m_aSectionRowList.add(nViewType);

        int nPosition = m_aSectionRowList.size() - 1;
        NotifyItemChanged(nPosition);
    }

    public void ClearSectionRowList()
    {
        m_aSectionRowList = null;
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.select_all:

                m_bDeleteSelectAll = !m_bDeleteSelectAll;

                DeleteViewHolderAll(m_bDeleteSelectAll);
                NotifyDataSetChanged();

                UpdateSelectDeleteCount();

                break;

            case R.id.delete:

                ApplyDelete();
                UpdateSelectDeleteCount();

                break;
        }
        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode)
    {
        ActivateDelete(false);
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    private void DeleteActivateEnable()
    {
        m_bDeleteSelectAll = false;
        m_bDeleteActivated = true;
        m_aDeleteList.clear();

        mDeleteActionMode = new RecyclerViewActionMode(this, this);
        mDeleteActionMode.SetActionModeTitle(m_sActionModeTitle);

        for(int nCount = 0; nCount < m_aSectionRowList.size(); nCount++)
        {
            m_aDeleteList.add(false);
        }

        UpdateSelectDeleteCount();
    }

    private void DeleteActivateDisable()
    {
        m_bDeleteSelectAll = false;
        m_bDeleteActivated = false;

        m_aDeleteList.clear();
        mDeleteActionMode = null;
    }

    private void ActivateDelete(boolean bEnable)
    {
        NotifyDataSetChanged();

        if(!bEnable)
        {
            DeleteActivateDisable();
            return;
        }

        DeleteActivateEnable();
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    private void DeleteViewHolder(ViewHolder objViewHolder, int nPosition, boolean bEnable)
    {
        if (!m_bDeleteActivated) return;

        int nViewType = m_aSectionRowList.get(nPosition);
        if(nViewType == VIEW_TYPE_SECTION) return;

        m_aDeleteList.set(nPosition, bEnable);
        UpdateSelectDeleteCount();

        m_iCustomRecyclerView.OnViewHolderDeleteSelected(objViewHolder, bEnable, nPosition, nViewType);
    }

    private void DeleteViewHolderAll(boolean bEnable)
    {
        for(int nPosition = 0; nPosition < m_aSectionRowList.size(); nPosition++)
        {
            if (m_aSectionRowList.get(nPosition) == VIEW_TYPE_ROW)
                m_aDeleteList.set(nPosition, bEnable);
        }
    }

    private void ApplyDelete()
    {
        for (int nPosition = m_aSectionRowList.size() - 1; nPosition >= 0 ; nPosition--)
        {
            if(!CanDelete(nPosition))
                continue;

            int nViewType = m_aSectionRowList.get(nPosition);

            m_aDeleteList.remove(nPosition);
            m_aSectionRowList.remove(nPosition);

            m_iCustomRecyclerView.OnViewHolderDeleted(nPosition, nViewType);
        }

        mDeleteActionMode.DeactivateActionMode();
        NotifyDataSetChanged();
    }

    private boolean CanDelete(int nPosition)
    {
        int nType = m_aSectionRowList.get(nPosition);

        if (nType == VIEW_TYPE_SECTION)
        {
            if (nPosition == m_aSectionRowList.size() - 1)
                return true;

            if (m_aSectionRowList.get(nPosition + 1) == VIEW_TYPE_ROW)
                return false;

            return true;
        }

        return m_aDeleteList.get(nPosition);
    }

    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return m_iCustomRecyclerView.OnViewHolderCreate(parent, viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        int nViewType = m_aSectionRowList.get(position);
        holder.itemView.setTag(holder);

        ///////////////////////////////////////////////////////////////////////

        boolean bDeleteSelected = false;

        if(m_bDeleteActivated)
            bDeleteSelected = m_aDeleteList.get(position);

        m_iCustomRecyclerView.OnViewHolderBindUI(holder, m_bDeleteActivated, bDeleteSelected, position, nViewType);

        ///////////////////////////////////////////////////////////////////////

        if (nViewType == VIEW_TYPE_ROW)
        {
            holder.itemView.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    ViewHolder objViewHolder = (ViewHolder) view.getTag();
                    int nPosition = objViewHolder.getAdapterPosition();

                    if (m_bDeleteActivated)
                    {
                        boolean bEnable = !m_aDeleteList.get(nPosition);
                        DeleteViewHolder(objViewHolder, nPosition, bEnable);

                        return;
                    }

                    m_iCustomRecyclerView.OnViewHolderRowClicked(objViewHolder, nPosition);
                }
            });
        }

        if (nViewType == VIEW_TYPE_ROW)
        {
            holder.itemView.setOnLongClickListener(new OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View view)
                {
                    ViewHolder objViewHolder = (ViewHolder) view.getTag();

                    int nPosition = objViewHolder.getAdapterPosition();
                    int nViewType = m_aSectionRowList.get(nPosition);

                    if (nViewType == VIEW_TYPE_SECTION)
                        return true;

                    ActivateDelete(true);

                    DeleteViewHolder(objViewHolder, nPosition, true);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount()
    {
        if (m_aSectionRowList == null)
            return 0;

        return m_aSectionRowList.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        return m_aSectionRowList.get(position);
    }

    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    public void NotifyItemChanged(int nPosition)
    {
        mRecyclerViewAdapter.NotifyItemChanged(nPosition);
    }

    public void NotifyDataSetChanged()
    {
        mRecyclerViewAdapter.NotifyDataSetChanged();
    }

    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    private void UpdateSelectDeleteCount()
    {
        if (mDeleteActionMode == null)
            return;

        int nSelected = 0;

        for (int nIndex = 0; nIndex < m_aDeleteList.size(); nIndex++)
        {
            if (m_aDeleteList.get(nIndex))
                nSelected++;
        }

        mDeleteActionMode.SetActionModeSubTitle(nSelected + " Selected");
    }
}
