package asia.nainglintun.myintthitar.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import asia.nainglintun.myintthitar.R;
import asia.nainglintun.myintthitar.models.Notification;
import asia.nainglintun.myintthitar.models.Orderhistory;

public class NotificationRecyclerAdapter extends RecyclerView.Adapter <NotificationRecyclerAdapter.MyViewHolder>{

    private List<Notification> orderhistories;
    private Context context;



    public NotificationRecyclerAdapter(List<Notification> orderhistories, Context context) {
        this.orderhistories = orderhistories;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_sale_list_layout,parent,false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {

        holder.saleDate.setText("date : " + orderhistories.get(i).getNoti_one());
        holder.voucherNo.setText("voucher no :" + orderhistories.get(i).getNoti_group());
        holder.shopName.setText("shop: " +orderhistories.get(i).getCustomer_id());
//        holder.town.setText("Town : " + orderhistories.get(i).getCustomerTwon());
    }

    @Override
    public int getItemCount() {
        return orderhistories.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView shopName,saleDate,voucherNo,town;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            shopName = itemView.findViewById(R.id.shopName);
            saleDate = itemView.findViewById(R.id.sDate);
            voucherNo = itemView.findViewById(R.id.voucherNo);
            town = itemView.findViewById(R.id.town);
        }
    }
}
