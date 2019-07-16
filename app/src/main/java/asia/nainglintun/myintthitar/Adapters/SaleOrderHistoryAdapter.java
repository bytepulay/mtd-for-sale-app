package asia.nainglintun.myintthitar.activities.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import asia.nainglintun.myintthitar.R;
import asia.nainglintun.myintthitar.activities.ToDo.SaleHistoryToDo;
import asia.nainglintun.myintthitar.activities.ToDo.SaleOrderHistoryToDo;
import asia.nainglintun.myintthitar.activities.ViewHolders.SaleHistoryViewHolder;
import asia.nainglintun.myintthitar.activities.ViewHolders.SaleOrderHistoryViewHolder;

public class SaleOrderHistoryAdapter extends RecyclerView.Adapter <SaleOrderHistoryViewHolder>{



    private ArrayList<SaleOrderHistoryToDo> saleHistoryList;
    private View view;
    private Context context;
    private RecyclerView recyclerView;

    public SaleOrderHistoryAdapter(Context context, ArrayList<SaleOrderHistoryToDo> saleHistoryList) {
        this.saleHistoryList = saleHistoryList;
        this.context = context;
    }

    @NonNull
    @Override
    public SaleOrderHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        view = layoutInflater.inflate(R.layout.sale_history_raw,viewGroup,false);
        SaleOrderHistoryViewHolder holder =new SaleOrderHistoryViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SaleOrderHistoryViewHolder saleHistoryViewHolder, int i) {
        saleHistoryViewHolder.txtDate.setText(saleHistoryList.get(i).getTxtDate());
        saleHistoryViewHolder.txtType.setText(saleHistoryList.get(i).getTxtType());
        saleHistoryViewHolder.txtAmount.setText(saleHistoryList.get(i).getTxtAmount());

    }

    @Override
    public int getItemCount() {
        return saleHistoryList.size();
    }
}
