package vaxsoft.com.vaxphone.MainTab.ChatTab;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import VaxVoIP.VaxUserAgentLib.VaxUserAgentLib;
import vaxsoft.com.vaxphone.R;

public class ChatContactRowViewHolder extends RecyclerView.ViewHolder
{
    AppCompatImageView ImageViewUserStatus;
    TextView ContactName;
    TextView LastMessage;
    TextView Date;
    CheckBox checkBoxDeleteRow;
    TextView MissedBadge;

    ChatContactRowViewHolder(View itemView)
    {
        super(itemView);

        ImageViewUserStatus = itemView.findViewById(R.id.stats_icon);
        ContactName = itemView.findViewById(R.id.contact_name);
        LastMessage = itemView.findViewById(R.id.last_message);
        Date = itemView.findViewById(R.id.last_message_date);
        checkBoxDeleteRow = itemView.findViewById(R.id.checkbox_delete);
        MissedBadge = itemView.findViewById(R.id.tv_missed_badge);
    }

    public View getItemView()
    {
        return super.itemView;
    }

    public void setTag(Object tag)
    {
        super.itemView.setTag(tag);
    }

    Boolean IsDeleteEnabled()
    {
        return checkBoxDeleteRow.isChecked();
    }

    void UpdateView(Boolean bDeleteActivated, Boolean bDeleteSelected, int nStatusId, String sContactName, int nMissedCount)
    {
        checkBoxDeleteRow.setChecked(bDeleteSelected);

        ////////////////////////////////////////////////////

        if(bDeleteActivated)
            checkBoxDeleteRow.setVisibility(View.VISIBLE);
        else
            checkBoxDeleteRow.setVisibility(View.GONE);

        ////////////////////////////////////////////////////

        if(sContactName.length() == 0)
            return;

        int nImageId = GetStatusImageId(nStatusId);
        ImageViewUserStatus.setImageResource(nImageId);

        if (ContactName.getText() != sContactName)
            ContactName.setText(sContactName);

        if (nMissedCount <= 0)
        {
            if (MissedBadge.getVisibility() == View.VISIBLE)
                MissedBadge.setVisibility(View.GONE);

            return;
        }

        ////////////////////////////////////////////////////

        MissedBadge.setVisibility(View.VISIBLE);
        MissedBadge.setText(String.valueOf(nMissedCount));
    }

    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////

    private int GetStatusImageId(int nStatusId)
    {
        int nImageId = R.drawable.ic_contact_unknown;

        switch(nStatusId)
        {
            case VaxUserAgentLib.VAX_CHAT_CONTACT_STATUS_ONLINE:
                nImageId = R.drawable.ic_contact_online;
                break;

            case VaxUserAgentLib.VAX_CHAT_CONTACT_STATUS_OFFLINE:
                nImageId = R.drawable.ic_contact_offline;
                break;

            case VaxUserAgentLib.VAX_CHAT_CONTACT_STATUS_AWAY:
                nImageId = R.drawable.ic_contact_away;
                break;

            case VaxUserAgentLib.VAX_CHAT_CONTACT_STATUS_BUSY:
                nImageId = R.drawable.ic_contact_busy;
                break;

            case VaxUserAgentLib.VAX_CHAT_CONTACT_STATUS_ON_PHONE:
                nImageId = R.drawable.ic_contact_on_phone;
                break;

            case VaxUserAgentLib.VAX_CHAT_CONTACT_STATUS_UNKNOWN:
                nImageId = R.drawable.ic_contact_unknown;
                break;
        }

        return nImageId;
    }
}