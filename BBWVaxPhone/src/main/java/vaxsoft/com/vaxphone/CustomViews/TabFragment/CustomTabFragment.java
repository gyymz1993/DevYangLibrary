package vaxsoft.com.vaxphone.CustomViews.TabFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

public class CustomTabFragment extends Fragment
{
    boolean m_bViewCreated = false;
    boolean m_bIsSelected = false;

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
       m_bViewCreated = true;
        super.onViewCreated(view, savedInstanceState);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////

    public void OnFragmentActivated()
    {

    }

    public void OnFragmentDeactivated()
    {

    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public void OnTabSelected(boolean bSelected)
    {
        m_bIsSelected = bSelected;

        if(!m_bViewCreated)
            return;

        if (bSelected)
        {
            OnFragmentActivated();
        }
        else
        {
            OnFragmentDeactivated();
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onResume()
    {
        if(m_bIsSelected)
            OnFragmentActivated();

        super.onResume();
    }

    @Override
    public void onPause()
    {
        if(m_bIsSelected)
            OnFragmentDeactivated();

        super.onPause();
    }
}
