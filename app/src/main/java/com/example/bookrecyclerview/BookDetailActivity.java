package com.example.bookrecyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.example.bookrecyclerview.model.Book;

public class BookDetailActivity extends AppCompatActivity {

    ImageView imgbook;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        imgbook = findViewById(R.id.img_book);
        ratingBar = findViewById(R.id.ratingBar_book);

        Book item = (Book) getIntent().getExtras().getSerializable("bookObject");

        loadBookData(item);
    }

    private void loadBookData(Book item) {

        // bind book data here for now i will only load the book cover image

        Glide.with(this).load(item.getDrawableResource()).into(imgbook);
    }
}