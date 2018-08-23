package vaxsoft.com.vaxphone.MainTab.RecentTab;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxPhoneSIP;

public class RecentRowViewHolder extends RecyclerView.ViewHolder
{
    AppCompatImageView mIconCallType;
    TextView mTextViewName, mTextViewPhoneNo, mTextViewDuration, mTextViewDate, mTextViewTime;

    RecentRowViewHolder(View view)
    {
        super(view);

        mIconCallType = view.findViewById(R.id.recent_call_icon);
        mTextViewName = view.findViewById(R.id.recent_caller_name);
        mTextViewPhoneNo = view.findViewById(R.id.recent_caller_number);
        mTextViewDuration = view.findViewById(R.id.duration_time);
        mTextViewDate = view.findViewById(R.id.call_day_or_date);
        mTextViewTime = view.findViewById(R.id.call_time);
    }

    void UpdateUI(String sName, String sPhoneNo, String sDate, String nDuration, long nHistoryTypeId, String sTime)
    {
        mIconCallType.setImageResource(GetCallIcon(nHistoryTypeId));
        mTextViewName.setText(sName);
        mTextViewPhoneNo.setText(sPhoneNo);
        mTextViewDuration.setText(nDuration);
        mTextViewDate.setText(sDate);
        mTextViewTime.setText(sTime);
    }

    private int GetCallIcon(long nHistoryTypeId)
    {
        if (nHistoryTypeId == VaxPhoneSIP.VAX_CALL_HISTORY_TYPE_INBOUND)
            return R.drawable.ic_call_received;

        else if (nHistoryTypeId == VaxPhoneSIP.VAX_CALL_HISTORY_TYPE_MISSED)
            return R.drawable.ic_call_missed;

        else if (nHistoryTypeId == VaxPhoneSIP.VAX_CALL_HISTORY_TYPE_REJECTED)
            return R.drawable.ic_call_received;

        else if (nHistoryTypeId == VaxPhoneSIP.VAX_CALL_HISTORY_TYPE_OUTBOUND)
            return R.drawable.ic_call_made;

        return R.drawable.ic_call_made;
    }
}