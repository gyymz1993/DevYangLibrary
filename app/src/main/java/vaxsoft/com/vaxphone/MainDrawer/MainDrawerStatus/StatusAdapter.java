package vaxsoft.com.vaxphone.MainDrawer.MainDrawerStatus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vaxsoft.com.vaxphone.R;

public class StatusAdapter extends ArrayAdapter<StatusData>
{
    private int m_nGroupId;
    private ArrayList<StatusData> m_StatusList;

    private LayoutInflater m_objLayoutInflater;

    public StatusAdapter(Context context, int nGroupId, int nId, ArrayList<StatusData> StatusList)
    {
        super(context, nId, StatusList);
        m_StatusList = StatusList;
        m_objLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        m_nGroupId = nGroupId;
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent)
    {
        @SuppressLint("ViewHolder") View itemView = m_objLayoutInflater.inflate(m_nGroupId, parent, false);

        ImageView imageView = itemView.findViewById(R.id.img);
        imageView.setImageResource(m_StatusList.get(position).m_sStatusIcon);

        TextView textView = itemView.findViewById(R.id.txt);
        textView.setText(m_StatusList.get(position).GetStatusText());

        return itemView;
    }

    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent)
    {
        return getView(position, convertView, parent);
    }


}
