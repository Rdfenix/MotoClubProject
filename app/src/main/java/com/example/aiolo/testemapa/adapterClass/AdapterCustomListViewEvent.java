package com.example.aiolo.testemapa.adapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.aiolo.testemapa.Model.ResponseModel.EventResponse;
import com.example.aiolo.testemapa.R;

import java.util.ArrayList;

public class AdapterCustomListViewEvent extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private ArrayList<EventResponse> eventResponses;

    public AdapterCustomListViewEvent(Context context, ArrayList<EventResponse> eventResponses){
        this.eventResponses = eventResponses;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return eventResponses.size();
    }

    @Override
    public Object getItem(int position) {
        return eventResponses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemSuport itemHolder;

        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.event_list_adapter, null);

            itemHolder = new ItemSuport();
            itemHolder.textTitle = convertView.findViewById(R.id.title_event_list);
            itemHolder.textLocal = convertView.findViewById(R.id.local_event_list);
            itemHolder.textHour = convertView.findViewById(R.id.hour_event_list);
            convertView.setTag(itemHolder);
        } else {
            itemHolder = (ItemSuport) convertView.getTag();
        }

        EventResponse response = eventResponses.get(position);

        itemHolder.textTitle.setText(response.getTitle());
        itemHolder.textLocal.setText(response.getLocal());
        itemHolder.textHour.setText(response.getHour());

        return convertView;
    }

    private class ItemSuport{
        TextView textTitle;
        TextView textLocal;
        TextView textHour;
    }
}
