package asia.nainglintun.myintthitar.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import asia.nainglintun.myintthitar.R;
import asia.nainglintun.myintthitar.models.Salehistory;

public class editbindvouchersaleRecyclerAdapter extends RecyclerView.Adapter <editbindvouchersaleRecyclerAdapter.MyViewHolder>{

    private List<Salehistory> salehistories;
    private ArrayList<String> nameList;
    private ArrayList<String> datelist;
    private Context context;

    public editbindvouchersaleRecyclerAdapter(ArrayList<String> nameList, ArrayList<String> datelist, Context context) {
        this.nameList = nameList;
        this.datelist = datelist;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_bindata_edit,parent,false);

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

       public  TextView bindName;
        EditText textDialog;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textDialog = itemView.findViewById(R.id.dialogText);

            bindName = itemView.findViewById(R.id.bindName);
        }
    }


}
