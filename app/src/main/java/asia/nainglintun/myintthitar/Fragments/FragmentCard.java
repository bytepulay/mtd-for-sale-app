package asia.nainglintun.myintthitar.Fragments;


import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import asia.nainglintun.myintthitar.Activities.MainActivity;
import asia.nainglintun.myintthitar.Activities.RecyclerTouchListener;
import asia.nainglintun.myintthitar.Activities.SalesActivity;
import asia.nainglintun.myintthitar.Adapters.CardAdapter;
import asia.nainglintun.myintthitar.R;
import asia.nainglintun.myintthitar.ToDo.CardTodo;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCard extends Fragment {

    private View view;
    private ArrayList<CardTodo> todoList;
    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private ImageView sProfile;
    private TextView name;
    public FragmentCard() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_card, container, false);

        ((SalesActivity)getActivity()).setTitle("Home");

        todoList = new ArrayList<>();

        sProfile = view.findViewById(R.id.saleProfile);
        name = view.findViewById(R.id.saleName);

        name.setText(MainActivity.prefConfig.readName());



//        todoList.add(new CardTodo("This is the first"));
//        todoList.add(new CardTodo("This is the seCond"));
//        todoList.add(new CardTodo("This is the third"));
//        todoList.add(new CardTodo("This is the four"));
//        todoList.add(new CardTodo("This is the five"));
//        todoList.add(new CardTodo("This is the Seven"));
//        todoList.add(new CardTodo("This is the Eight"));
//        todoList.add(new CardTodo("This is the Nine"));

        todoList.add(new CardTodo("Sale Invoice",R.drawable.create_invoice));
        todoList.add(new CardTodo("Order Invoice",R.drawable.create_order));
        todoList.add(new CardTodo("Add Customer ",R.drawable.create_customer));
        todoList.add(new CardTodo("Sale History",R.drawable.my_history));
        todoList.add(new CardTodo(" Order History ",R.drawable.order_history));
        todoList.add(new CardTodo("Setting",R.drawable.ic_settings_black_24dp));


        recyclerView = view.findViewById(R.id.reView);
        adapter = new CardAdapter(getContext(), todoList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                CardTodo card = todoList.get(position);
                if(position==0) {
                    SalesActivity.fragmentManager.beginTransaction().replace(R.id.frame_layout_sales,new SaleInvoiceCreate()).addToBackStack(null).commit();




                }
                else if(position==1) {
                    SalesActivity.fragmentManager.beginTransaction().replace(R.id.frame_layout_sales,new OrderInvoiceCreate()).addToBackStack(null).commit();


                }

                else if(position==2) {
                    SalesActivity.fragmentManager.beginTransaction().replace(R.id.frame_layout_sales,new SaleAddCustomer()).addToBackStack(null).commit();
                  //  ((SalesActivity)getActivity()).setTitle("Add New Customer");

                }

                else if(position==3) {
                    SalesActivity.fragmentManager.beginTransaction().replace(R.id.frame_layout_sales,new SaleListFragment()).addToBackStack(null).commit();



                }

                else if(position==4) {
                    SalesActivity.fragmentManager.beginTransaction().replace(R.id.frame_layout_sales,new OrderListFragment()).addToBackStack(null).commit();

                }

                else if(position==5) {
                    SalesActivity.fragmentManager.beginTransaction().replace(R.id.frame_layout_sales,new SaleSettingFragment()).addToBackStack(null).commit();

                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return view;
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


}