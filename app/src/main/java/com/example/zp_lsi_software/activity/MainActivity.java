package com.example.zp_lsi_software.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zp_lsi_software.R;
import com.example.zp_lsi_software.adapters.ExportAdapter;
import com.example.zp_lsi_software.sql.ExportDbAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    View view;
    RecyclerView.Adapter adapter;
    final List<Object> list = new ArrayList<>();
    ExportDbAdapter exportDbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view);

        prepareDatabase();
        prepareContentData();

        adapter = new ExportAdapter(list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }

    private void prepareDatabase() {

        exportDbAdapter = new ExportDbAdapter(this);
        exportDbAdapter.open();
        exportDbAdapter.insertExport("Nazwa", "Data", "Godzina", "Użytkownik", "Lokal");

        for (int i = 0; i < 100; i++) {
            exportDbAdapter.insertExport("Nazwa" + i, "Data" + i, "Godzina" + i, "Użytkownik" + i, "Lokal" + i);
        }
    }

    private void prepareContentData() {

        list.addAll(exportDbAdapter.getAllExports());
        exportDbAdapter.close();
    }
}
