package com.example.zp_lsi_software.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zp_lsi_software.R;
import com.example.zp_lsi_software.adapters.ExportAdapter;
import com.example.zp_lsi_software.models.ExportModel;
import com.example.zp_lsi_software.sql.ExportDbAdapter;
import com.example.zp_lsi_software.viewholders.ExportsHeadLineViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<ExportModel> list = new ArrayList<>();
    private ExportDbAdapter exportDbAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view);

        prepareDatabase();

        adapter = new ExportAdapter(list, createDialogListener());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void prepareDatabase() {

        exportDbAdapter = new ExportDbAdapter(this).open();
        List<ExportModel> dataBaseList = exportDbAdapter.getAllExports();

        if (dataBaseList.isEmpty()) {

            for (int i = 1; i < 12; i++) {
                for (int j = 1; j < 30; j++) {
                    exportDbAdapter.insertExport("name" + (i + j), j + "-" + i + "-2020", i + ":" + j, "User" + (i * j), "Lokal" + i);
                }
            }
        }

        list.add(new ExportModel("Nazwa", "Data", "Godzina", "Użytkownik", "Lokal"));
        list.addAll(exportDbAdapter.getAllExports());
        exportDbAdapter.close();
    }

    private ExportsHeadLineViewHolder.DialogListener createDialogListener() {
        return new ExportsHeadLineViewHolder.DialogListener() {
            @Override
            public void applyFilter(int column, String filter) {
                exportDbAdapter.open();
                list.clear();
                list.add(new ExportModel("Nazwa", "Data", "Godzina", "Użytkownik", "Lokal"));
                list.addAll(exportDbAdapter.getExportsByFilter(column, filter));
                exportDbAdapter.close();
                adapter.notifyDataSetChanged();
            }
        };
    }
}
