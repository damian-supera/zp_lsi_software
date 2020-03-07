package com.example.zp_lsi_software.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zp_lsi_software.R;
import com.example.zp_lsi_software.models.ExportModel;
import com.example.zp_lsi_software.viewholders.ExportsHeadLineViewHolder;
import com.example.zp_lsi_software.viewholders.ExportsViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ExportAdapter extends RecyclerView.Adapter<ExportsViewHolder> {

    private final List<Object> exportModels;
    private final ExportsHeadLineViewHolder.DialogListener dialogListener;

    enum Types {
        TYPE_HEADLINES(1),
        TYPE_CONTEXT(2);

        private final int value;

        Types(int value) {
            this.value = value;
        }

        private int getValue() {
            return value;
        }
    }

    public ExportAdapter(List<Object> exportModels, ExportsHeadLineViewHolder.DialogListener dialogListener) {
        this.exportModels = exportModels;
        this.dialogListener = dialogListener;
    }

    @NonNull
    @Override
    public ExportsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView;
        switch (viewType) {
            case 1:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_headlines_db, parent, false);
                return new ExportsHeadLineViewHolder(itemView, dialogListener);
            case 2:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_db, parent, false);
                return new ExportsViewHolder(itemView);
            default:
                return new ExportsViewHolder(parent) {
                };
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ExportsViewHolder holder, int position) {

        if (exportModels.get(position) instanceof ExportModel) {

            ExportModel exportModel = (ExportModel) exportModels.get(position);
            holder.name.setText(exportModel.getName());
            holder.date.setText(exportModel.getDate());
            holder.hour.setText(exportModel.getHour());
            holder.user.setText(exportModel.getUser());
            holder.place.setText(exportModel.getPlace());
        }
    }

    @Override
    public int getItemCount() {

        return exportModels.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0)
            return Types.TYPE_HEADLINES.getValue();
        else
            return Types.TYPE_CONTEXT.getValue();
    }
}
