package com.example.kapk;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.paging.DatabasePagingOptions;
import com.firebase.ui.database.paging.FirebaseRecyclerPagingAdapter;


//step4............................................
public  class ItemAdapter extends FirebaseRecyclerPagingAdapter<Book, ItemViewHolder> {

    private final ClickListner mclicklistner;

    /**
     * Construct a new FirestorePagingAdapter from the given {@link DatabasePagingOptions}.
     *
     * @param options
     * @param mclicklistner
     */
    public ItemAdapter(@NonNull DatabasePagingOptions<Book> options, ClickListner mclicklistner) {
        super(options);
        this.mclicklistner = mclicklistner;
    }

    @Override
    protected void onBindViewHolder(@NonNull ItemViewHolder viewHolder, int position, @NonNull Book model) {
        viewHolder.bind(model);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mclicklistner.onItemClick(viewHolder.getAbsoluteAdapterPosition(),model);

            }
        });
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_layout, parent, false);
        return new ItemViewHolder(view);
    }

    public interface ClickListner {
        void onItemClick(int position, Book book);
    }
}
//step3...........................................

class ItemViewHolder extends RecyclerView.ViewHolder {
    private TextView titleTextView;
    private TextView bodyTextView,priceTextView;

    public ItemViewHolder(View itemView) {
        super(itemView);
        titleTextView = itemView.findViewById(R.id.productlayoutname);
        bodyTextView = itemView.findViewById(R.id.productlayoutdescriptiontext);
        priceTextView = itemView.findViewById(R.id.ruppeestext);
    }
    public void bind(Book item) {
        titleTextView.setText(item.getName());
        priceTextView.setText("Rs. "+item.getMrp());
        bodyTextView.setText(item.getStandard());
    }


}





