package vaxsoft.com.vaxphone.CustomViews.RecyclerView;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import vaxsoft.com.vaxphone.R;

class RecyclerViewActionMode
{
    private ActionMode m_objActionMode = null;

    private IRecyclerViewActionMode m_objIRecyclerViewActionMode = null;

    /////////////////////////////////////////////////////////////////////////////////////

    RecyclerViewActionMode(View view, IRecyclerViewActionMode objIRecyclerViewActionMode)
    {
        m_objIRecyclerViewActionMode = objIRecyclerViewActionMode;

        ActivateActionMode(view);
    }

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    public void SetActionModeTitle(String sTitle)
    {
        m_objActionMode.setTitle(sTitle);
    }

    public void SetActionModeSubTitle(String sSubTitle)
    {
        m_objActionMode.setSubtitle(sSubTitle);
    }

    private void ActivateActionMode(View view)
    {
        if (view == null)
            return;

        view.startActionMode(new ActionMode.Callback()
        {
            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu)
            {
                m_objActionMode = actionMode;

                actionMode.getMenuInflater().inflate(R.menu.menu_action_mode, menu);

                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu)
            {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem)
            {
                return m_objIRecyclerViewActionMode.onActionItemClicked(actionMode, menuItem);
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode)
            {
                m_objIRecyclerViewActionMode.onDestroyActionMode(actionMode);
            }
        });
    }

    public void DeactivateActionMode()
    {
        m_objActionMode.finish();
    }
}
