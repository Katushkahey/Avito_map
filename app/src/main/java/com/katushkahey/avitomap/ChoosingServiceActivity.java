package com.katushkahey.avitomap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChoosingServiceActivity extends AppCompatActivity {

    private Button ok;
    private Button cancel;
    private List<String> services;
    private ServiceAdapter adapter;
    private List<String> resultNames = new ArrayList<>();
    private List<Service> resultListOfService = new ArrayList<>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing_service);
        final RecyclerView recyclerView = findViewById(R.id.activity_choosing_service__rr);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        services = getIntent().getStringArrayListExtra("names");
        adapter = new ServiceAdapter(services, new ServiceAdapter.Listener() {
            @Override
            public void onServiceClick(Service service) {
                if (!service.getChecked()) {
                    service.setChecked(true);
                    resultListOfService.add(service);
                    adapter.notifyDataSetChanged();
                } else {
                    service.setChecked(false);
                    resultListOfService.remove(service);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        recyclerView.setAdapter(adapter);

        ok = findViewById(R.id.activity_choosing_service__ok);
        cancel = findViewById(R.id.activity_choosing_service__cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                getResultNames();
                intent.putStringArrayListExtra("resultServices", (ArrayList<String>) resultNames);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    public List<String> getResultNames() {
        for (int i = 0; i < resultListOfService.size(); i++) {
            resultNames.add(resultListOfService.get(i).getName());
        }
        return resultNames;
    }

}