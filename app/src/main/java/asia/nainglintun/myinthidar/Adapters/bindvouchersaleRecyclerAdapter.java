package asia.nainglintun.myinthidar.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import asia.nainglintun.myinthidar.R;
import asia.nainglintun.myinthidar.models.Salehistory;

public class bindvouchersaleRecyclerAdapter extends RecyclerView.Adapter <bindvouchersaleRecyclerAdapter.MyViewHolder>{

    private List<Salehistory> salehistories;
    private ArrayList<String> nameList;
    private ArrayList<String> datelist;
    private Context context;

    public bindvouchersaleRecyclerAdapter(ArrayList<String> nameList, ArrayList<String> datelist, Context context) {
        this.nameList = nameList;
        this.datelist = datelist;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_bindata,parent,false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {



        holder.bindName.setText(nameList.get(i));
        holder.textDialog.setText(datelist.get(i));

    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textDialog,bindName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textDialog = itemView.findViewById(R.id.dialogText);

            bindName = itemView.findViewById(R.id.bindName);
        }
    }


}
