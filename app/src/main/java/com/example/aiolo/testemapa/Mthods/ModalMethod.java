package com.example.aiolo.testemapa.Mthods;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aiolo.testemapa.Model.ResponseModel.GroupResponse;
import com.example.aiolo.testemapa.R;

public class ModalMethod {

    public static void ModalGroup(Context context, GroupResponse groupResponse){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_modal_group);

        TextView title = dialog.findViewById(R.id.modal_title);
        TextView desc = dialog.findViewById(R.id.desc_group_modal);
        TextView moto = dialog.findViewById(R.id.moto_modal_group);
        TextView city = dialog.findViewById(R.id.city_group_modal);
        TextView state = dialog.findViewById(R.id.state_group_modal);
        TextView user = dialog.findViewById(R.id.usuario_id_modal);
        Button close  = dialog.findViewById(R.id.buttom_close_group_modal);

        title.setText(groupResponse.getTitleGroup());
        desc.setText(groupResponse.getDescGroup());
        moto.setText(groupResponse.getMotoCategory());
        city.setText(groupResponse.getCityGroup());
        state.setText(groupResponse.getStateGroup());
        user.setText(groupResponse.getIdUser());

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
