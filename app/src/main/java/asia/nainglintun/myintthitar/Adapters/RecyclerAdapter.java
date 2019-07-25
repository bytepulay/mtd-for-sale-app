package asia.nainglintun.myintthitar.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import asia.nainglintun.myintthitar.Activities.MainActivity;
import asia.nainglintun.myintthitar.R;
import asia.nainglintun.myintthitar.models.Calories;

public class RecyclerAdapter extends RecyclerView.Adapter <RecyclerAdapter.MyViewHolder>{

    private List<Calories> calories;
    private Context context;

    public RecyclerAdapter(List<Calories> calories, Context context) {
        this.calories = calories;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_layout,parent,false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {

        holder.name.setText(calories.get(i).getName());
        holder.address.setText(String.valueOf(calories.get(i).getId()));
    }

    @Override
    public int getItemCount() {
        return calories.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name,address;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
        }
    }
}
