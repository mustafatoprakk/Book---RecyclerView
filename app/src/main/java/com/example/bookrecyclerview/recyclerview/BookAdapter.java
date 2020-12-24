package com.example.bookrecyclerview.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.bookrecyclerview.R;
import com.example.bookrecyclerview.model.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.bookviewholder> {

    List<Book> mData;
    BookCallback callback;

    public BookAdapter(List<Book> mData, BookCallback callback) {
        this.mData = mData;
        this.callback = callback;
    }

    @NonNull
    @Override
    public bookviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);

        return new bookviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull bookviewholder holder, int position) {

        // bind book item data here
        // load book image using Glide
        Glide.with(holder.itemView.getContext()).load(mData.get(position).getDrawableResource())  // set the img book Url
                .transforms(new CenterCrop(), new RoundedCorners(16)).into(holder.imgBook);

        holder.ratingBar.setRating((float) 4.5);  // rating
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class bookviewholder extends RecyclerView.ViewHolder {

        ImageView imgBook, imgFav, imgContainer;
        TextView title, author, pages, rate;
        RatingBar ratingBar;

        public bookviewholder(@NonNull View itemView) {
            super(itemView);

            imgBook = itemView.findViewById(R.id.img_book);
            imgContainer = itemView.findViewById(R.id.container);
            title = itemView.findViewById(R.id.txt_book_title);
            author = itemView.findViewById(R.id.txt_book_author);
            pages = itemView.findViewById(R.id.txt_pages_book);
            rate = itemView.findViewById(R.id.txt_score_book);
            ratingBar = itemView.findViewById(R.id.ratingBar_book);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onBookItemClick(getAdapterPosition(),
                            imgContainer,
                            imgBook,
                            title,
                            author,
                            pages,
                            rate,
                            ratingBar);
                }
            });
        }
    }


}
