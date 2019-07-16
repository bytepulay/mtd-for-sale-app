package asia.nainglintun.myintthitar.activities.ViewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import asia.nainglintun.myintthitar.R;

public class SaleHistoryViewHolder extends RecyclerView.ViewHolder {
    public TextView txtDate,txtType,txtAmount,textViewCuponCode;
    public SaleHistoryViewHolder(@NonNull View itemView) {
        super(itemView);
        txtDate = itemView.findViewById(R.id.saleHistoryDate);
        txtType = itemView.findViewById(R.id.saleItemsType);
        txtAmount = itemView.findViewById(R.id.salesAmount);
        textViewCuponCode = itemView.findViewById(R.id.txtCuponCode);

    }
}
