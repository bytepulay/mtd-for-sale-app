package asia.nainglintun.myintthitar.Adapters;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import asia.nainglintun.myintthitar.Activities.MainActivity;
import asia.nainglintun.myintthitar.R;
import asia.nainglintun.myintthitar.models.Salehistory;

public class SaleRecyclerAdapter extends RecyclerView.Adapter<SaleRecyclerAdapter.MyViewHolder> {

    private List<Salehistory> salehistoryList;
    private Context context;
    private View view;

    public SaleRecyclerAdapter(List<Salehistory> salehistoryList, Context context) {
        this.salehistoryList = salehistoryList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        view = layoutInflater.inflate(R.layout.sale_history_raw_v1,viewGroup,false);
        MyViewHolder holder =new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
            viewHolder.txtDate.setText("Date :" + salehistoryList.get(i).getSaleDate());
            viewHolder.textViewVoucher.setText("Voucher No" +salehistoryList.get(i).getVoucherNumber());
            viewHolder.txtAmount.setText("Total qty :" +salehistoryList.get(i).getQty());
            viewHolder.txtTypeRing.setText("Types" +salehistoryList.get(i).getRingTitle());
    }

    @Override
    public int getItemCount() {
        return salehistoryList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
         TextView txtDate,txtTypeRing,bangles,necklace,earring,txtAmount,textViewCuponCode,textViewVoucher;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDate = itemView.findViewById(R.id.saleHistoryDate);
            txtTypeRing = itemView.findViewById(R.id.saleItemsType);
            txtAmount = itemView.findViewById(R.id.salesAmount);
            textViewVoucher = itemView.findViewById(R.id.voucher);

        }
    }
}
