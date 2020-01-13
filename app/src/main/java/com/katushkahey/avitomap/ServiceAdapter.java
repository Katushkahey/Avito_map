package com.katushkahey.avitomap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceHolder> {
    private List<Service> services;
    private final Listener onServiceClickListener;

    public ServiceAdapter(List<String> names, Listener onServiceClickListener) {
        services = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            services.add(new Service(names.get(i)));
        }
        this.onServiceClickListener = onServiceClickListener;
    }

    @NonNull
    @Override
    public ServiceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.service_item, viewGroup, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onServiceClickListener.onServiceClick((Service) v.getTag());
            }
        });
        return new ServiceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceHolder holder, int i) {
        if (services.size() == 0) {
            return;
        } else {
            Service service = services.get(i);
            holder.bind(service);
            holder.itemView.setTag(service);
        }
    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    class ServiceHolder extends RecyclerView.ViewHolder {
        private TextView name;


        public ServiceHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.service_item__textview);
        }

        public void bind(Service service) {
            String n = "Service " + service.getName();
            name.setText(n);
            if (service.getChecked()) {
                name.setEnabled(false);
            } else {
                name.setEnabled(true);
            }
        }
    }

    interface Listener {
        void onServiceClick(Service service);
    }
}
