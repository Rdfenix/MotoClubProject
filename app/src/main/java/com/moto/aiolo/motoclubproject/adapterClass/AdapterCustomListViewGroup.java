package com.moto.aiolo.motoclubproject.adapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.moto.aiolo.motoclubproject.Model.ResponseModel.GroupResponse;
import com.moto.aiolo.motoclubproject.R;

import java.util.ArrayList;

public class AdapterCustomListViewGroup extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<GroupResponse> groupResponses;

    public AdapterCustomListViewGroup(Context context, ArrayList<GroupResponse> groupResponses){
        //itens que vão preencher o ListView
        this.groupResponses = groupResponses;

        //responsavel por pegar o layout
        inflater = LayoutInflater.from(context);
    }

    //retorna a quantidade de itens
    @Override
    public int getCount() {
        return groupResponses.size();
    }

    //retorna o item de acordo com a posicao do item na tela
    @Override
    public Object getItem(int position) {
        return groupResponses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemSuport itemHolder;

        //se a classe estiver nulla, inflamos o layout da tela
        if(convertView == null){
            //infla o layout para podermos pegar a view
            convertView = inflater.inflate(R.layout.group_list_adapter, null);
            //cria um item de suporte para nao precisarmos sempre inflar as mesmas informações
            itemHolder = new ItemSuport();
            itemHolder.textTitle = convertView.findViewById(R.id.title_group_list);
            itemHolder.textCity = convertView.findViewById(R.id.city_group_list);
            itemHolder.textState = convertView.findViewById(R.id.state_group_list);
            itemHolder.textQdt = convertView.findViewById(R.id.qdt_group_list);
            convertView.setTag(itemHolder);
        } else {
            //se a view ja existe pega os itens
            itemHolder = (ItemSuport) convertView.getTag();
        }
        //pega os dados da lista e define os valores nos itens
        GroupResponse group = groupResponses.get(position);

        itemHolder.textTitle.setText(group.getTitleGroup());
        itemHolder.textCity.setText(group.getCityGroup());
        itemHolder.textState.setText(group.getStateGroup());
        itemHolder.textQdt.setText(group.getQdtMembers());

        return convertView;
    }

    private class ItemSuport{
        TextView textTitle;
        TextView textCity;
        TextView textState;
        TextView textQdt;
    }
}
