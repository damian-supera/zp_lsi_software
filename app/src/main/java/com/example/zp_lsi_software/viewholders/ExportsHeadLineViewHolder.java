package com.example.zp_lsi_software.viewholders;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.zp_lsi_software.R;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
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
                createAllertDialog(1, "Podaj filtrowanie po nazwie: ", InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS).show();
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAllertDialogForDate(2, "Podaj filtrowanie po dacie: ").show();
            }
        });

        hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAllertDialogForTime(3, "Podaj filtrowanie po godzinie: ").show();
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAllertDialog(4, "Podaj filtrowanie po użytkowniku: ", InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS).show();
            }
        });

        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAllertDialog(5, "Podaj filtrowanie po lokalu: ", InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS).show();
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

    private Dialog createAllertDialogForDate(final int column, String message) {

        LayoutInflater layoutInflater = (LayoutInflater) view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = layoutInflater.inflate(R.layout.alert_dialog_for_date, null);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(view.getContext());

        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();

        TextView textView = dialogView.findViewById(R.id.text_view);
        textView.setText(message);

        CalendarView calendarView = dialogView.findViewById(R.id.calendar_view);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                dialogListener.applyFilter(column, dayOfMonth + "-" + month + "-" + year);
                alertDialog.dismiss();
            }
        });
        return alertDialog;
    }

    private Dialog createAllertDialogForTime(final int column, String message) {

        LayoutInflater layoutInflater = (LayoutInflater) view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = layoutInflater.inflate(R.layout.alert_dialog_for_time, null);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(view.getContext());

        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();

        TextView textView = dialogView.findViewById(R.id.text_view);
        textView.setText(message);

        final TimePicker timePicker = dialogView.findViewById(R.id.time_picker);
        timePicker.setIs24HourView(true);
        Button button = dialogView.findViewById(R.id.set_button);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                dialogListener.applyFilter(column, timePicker.getHour() + ":" + timePicker.getMinute());
                alertDialog.dismiss();
            }
        });

        return alertDialog;
    }

    public interface DialogListener {
        void applyFilter(int column, String filter);
    }
}
