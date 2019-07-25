package asia.nainglintun.myintthitar.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


import asia.nainglintun.myintthitar.ToDo.SaleHistoryToDo;
import asia.nainglintun.myintthitar.R;
import asia.nainglintun.myintthitar.ViewHolders.SaleHistoryViewHolder;

public class SaleHistoryAdapter extends RecyclerView.Adapter <SaleHistoryViewHolder>{



    private ArrayList<SaleHistoryToDo> saleHistoryList;
    private View view;
    private Context context;
    private RecyclerView recyclerView;

    public SaleHistoryAdapter(Context context, ArrayList<SaleHistoryToDo> saleHistoryList) {
        this.saleHistoryList = saleHistoryList;
        this.context = context;
    }

    @NonNull
    @Override
    public SaleHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        view = layoutInflater.inflate(R.layout.sale_history_raw,viewGroup,false);
        SaleHistoryViewHolder holder =new SaleHistoryViewHolder(view);
        return holder;
    }

    @Override   
    public void onBindViewHolder(@NonNull SaleHistoryViewHolder saleHistoryViewHolder, int i) {
        saleHistoryViewHolder.txtDate.setText(saleHistoryList.get(i).getTxtDate());
        saleHistoryViewHolder.txtType.setText(saleHistoryList.get(i).getTxtType());
        saleHistoryViewHolder.txtAmount.setText(saleHistoryList.get(i).getTxtAmount());

    }

    @Override
    public int getItemCount() {
        return saleHistoryList.size();
    }

    public void updateList(ArrayList<SaleHistoryToDo> newList){
        saleHistoryList = new ArrayList<>();
        saleHistoryList.addAll(newList);
        notifyDataSetChanged();

    }
}
