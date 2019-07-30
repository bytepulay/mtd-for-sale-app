package asia.nainglintun.myintthitar.ViewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import asia.nainglintun.myintthitar.R;

public class CardTotoViewHolder extends RecyclerView.ViewHolder {
    public TextView txtCustom;
    public ImageView imageView;
    public LinearLayout parent_layout;

    public CardTotoViewHolder(@NonNull View itemView) {
        super(itemView);

        txtCustom = itemView.findViewById(R.id.custom_card_list);
        imageView = itemView.findViewById(R.id.cardImage);
        parent_layout = itemView.findViewById(R.id.parent_layout);
    }
}
