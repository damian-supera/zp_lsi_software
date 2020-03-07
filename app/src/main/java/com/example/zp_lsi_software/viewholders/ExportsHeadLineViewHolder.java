package com.example.zp_lsi_software.viewholders;

import android.app.Dialog;
import android.content.Context;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zp_lsi_software.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

public class ExportsHeadLineViewHolder extends ExportsViewHolder {

    View view;
    DialogListener dialogListener;

    public ExportsHeadLineViewHolder(@NonNull View itemView, DialogListener dialogListener) {
        super(itemView);
        this.view = itemView;
        this.dialogListener = dialogListener;

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAllertDialog(1,"Podaj filtrowanie po nazwie: ", InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS).show();
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: porpawić klawiaturę
                createAllertDialog(2,"Podaj filtrowanie po dacie: ", InputType.TYPE_CLASS_DATETIME | InputType.TYPE_DATETIME_VARIATION_DATE).show();
            }
        });

        hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: porpawić klawiaturę
                createAllertDialog(3,"Podaj filtrowanie po godzinie: ", InputType.TYPE_DATETIME_VARIATION_TIME).show();
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAllertDialog(4,"Podaj filtrowanie po użytkowniku: ", InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS).show();
            }
        });

        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAllertDialog(5,"Podaj filtrowanie po lokalu: ", InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS).show();
            }
        });

    }

    private Dialog createAllertDialog(final int column, String message, int inputType) {

        LayoutInflater layoutInflater = (LayoutInflater) view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = layoutInflater.inflate(R.layout.alert_dialog, null);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(view.getContext());

        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();

        TextView textView = dialogView.findViewById(R.id.text_view);
        textView.setText(message);

        final EditText editText = dialogView.findViewById(R.id.edit_text);
        editText.setInputType(inputType);

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    dialogListener.applyFilter(column, String.valueOf(editText.getText()));
                    alertDialog.dismiss();
                    return true;
                }
                return false;
            }
        });

        return alertDialog;
    }

    public interface DialogListener{
        void applyFilter(int column , String filter);
    }
}
