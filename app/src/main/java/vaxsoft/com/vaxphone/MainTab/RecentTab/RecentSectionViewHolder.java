package vaxsoft.com.vaxphone.MainTab.RecentTab;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import vaxsoft.com.vaxphone.R;
import vaxsoft.com.vaxphone.VaxPhoneSIP;

public class RecentSectionViewHolder extends RecyclerView.ViewHolder
{
    TextView mTextViewSectionTitle;

    RecentSectionViewHolder(View itemView)
    {
        super(itemView);

        mTextViewSectionTitle = itemView.findViewById(R.id.header_label);
    }

    void UpdateUI(String sDay)
    {
        mTextViewSectionTitle.setText(sDay);
    }
}