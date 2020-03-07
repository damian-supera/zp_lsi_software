package com.example.zp_lsi_software.viewholders;

import android.view.View;
import android.widget.TextView;

import com.example.zp_lsi_software.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ExportsViewHolder extends RecyclerView.ViewHolder {

    public final TextView name;
    public final TextView date;
    public final TextView hour;
    public final TextView user;
    public final TextView place;

    public ExportsViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.name);
        date = itemView.findViewById(R.id.date);
        hour = itemView.findViewById(R.id.hour);
        user = itemView.findViewById(R.id.user);
        place = itemView.findViewById(R.id.place);
    }
}
