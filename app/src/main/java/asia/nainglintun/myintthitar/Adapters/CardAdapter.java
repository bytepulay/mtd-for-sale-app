package asia.nainglintun.myintthitar.Adapters;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import asia.nainglintun.myintthitar.Activities.SalesActivity;
import asia.nainglintun.myintthitar.R;
import asia.nainglintun.myintthitar.ToDo.CardTodo;
import asia.nainglintun.myintthitar.ViewHolders.CardTotoViewHolder;

public class CardAdapter extends RecyclerView.Adapter<CardTotoViewHolder> {
    @NonNull

    private ArrayList<CardTodo> todoList;
    private View view;
    private Context context;
    private RecyclerView recyclerView;


    public CardAdapter(Context context, ArrayList<CardTodo> todoList){
        this.todoList = todoList;
        this.context =context;
    }

    @Override
    public CardTotoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        view = layoutInflater.inflate(R.layout.custom_card,viewGroup,false);
        CardTotoViewHolder holder =new CardTotoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardTotoViewHolder cardTotoViewHolder, final int i) {
        cardTotoViewHolder.txtCustom.setText(todoList.get(i).getCustom_card_list());
        cardTotoViewHolder.imageView.setImageResource(todoList.get(i).getImageId());
//        View v = recyclerView.getLayoutManager().findViewByPosition(i);
//        Toast.makeText(context,"positon is" + v, Toast.LENGTH_SHORT).show();
        cardTotoViewHolder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pos = getItemId(i);
                //  Toast.makeText(context,,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, SalesActivity.class);
                context.startActivity(intent);




            }
        });
    }



    @Override
    public int getItemCount() {
        return todoList.size();
    }



}
