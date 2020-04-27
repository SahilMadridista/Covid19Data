package Recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.covid_19.R;
import java.util.List;
import listClass.ListItem;

public class DetailsRecyclerViewAdapter extends RecyclerView.Adapter<DetailsRecyclerViewAdapter.DetailsRecyclerViewHolder> {

    private List<ListItem> listItems;
    private Context context;

    public DetailsRecyclerViewAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }


    @NonNull
    @Override
    public DetailsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card,parent,false);
        return new DetailsRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsRecyclerViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);

        holder.CountryName.setText(listItem.getCountryName());
        holder.TotalNumber.setText(listItem.getTotalCases());
        holder.RecoveredNumber.setText(listItem.getTotalrecovered());
        holder.DeathNumber.setText(listItem.getTotalDeaths());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class DetailsRecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView CountryName,TotalNumber,RecoveredNumber,DeathNumber;


        public DetailsRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            CountryName = itemView.findViewById(R.id.country_name);
            TotalNumber = itemView.findViewById(R.id.total_cases);
            RecoveredNumber = itemView.findViewById(R.id.recovered_cases);
            DeathNumber = itemView.findViewById(R.id.death_number);

        }
    }

}
