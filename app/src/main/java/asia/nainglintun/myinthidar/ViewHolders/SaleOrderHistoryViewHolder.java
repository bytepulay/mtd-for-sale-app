package asia.nainglintun.myinthidar.ViewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import asia.nainglintun.myinthidar.R;

public class SaleOrderHistoryViewHolder extends RecyclerView.ViewHolder {
    public TextView txtDate,txtType,txtAmount,textViewCuponCode;
    public SaleOrderHistoryViewHolder(@NonNull View itemView) {
        super(itemView);
        txtDate = itemView.findViewById(R.id.saleHistoryDate);
        txtType = itemView.findViewById(R.id.saleItemsType);
        txtAmount = itemView.findViewById(R.id.salesAmount);
        //textViewCuponCode = itemView.findViewById(R.id.txtCuponCode);

    }
}
