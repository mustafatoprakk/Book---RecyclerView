package com.example.bookrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.bookrecyclerview.model.Book;
import com.example.bookrecyclerview.recyclerview.BookAdapter;
import com.example.bookrecyclerview.recyclerview.BookCallback;
import com.example.bookrecyclerview.recyclerview.CustomItemAnimator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BookCallback {

    private RecyclerView rvBooks;
    private BookAdapter bookAdapter;
    private List<Book> mData;
    private Button btnAdd,btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initmdataBooks();
        setupBookAdapter();
    }

    private void setupBookAdapter() {

        bookAdapter=new BookAdapter(mData,this);
        rvBooks.setAdapter(bookAdapter);
    }

    private void initmdataBooks() {

        // for testing purpose I'm creating a random set of books

        mData=new ArrayList<>();
        mData.add(new Book(R.drawable.sozler));
        mData.add(new Book(R.drawable.asayimusa));
        mData.add(new Book(R.drawable.iman));
        mData.add(new Book(R.drawable.lemalar));
        mData.add(new Book(R.drawable.mektubat));
        mData.add(new Book(R.drawable.sualar));
        mData.add(new Book(R.drawable.tarihce));
    }

    private void initViews() {
        btnAdd=findViewById(R.id.btn_add);
        btnDelete=findViewById(R.id.btn_delete);
        rvBooks=findViewById(R.id.recyclerView);

        rvBooks.setLayoutManager(new LinearLayoutManager(this));
        rvBooks.setHasFixedSize(true);

        // we need first to setup the custom item animator that we just create
        rvBooks.setItemAnimator(new CustomItemAnimator());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBook();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeBook();
            }
        });
    }

    private void removeBook() {
        mData.remove(1);
        bookAdapter.notifyItemRemoved(1);
    }

    private void addBook() {
        Book book=new Book(R.drawable.tarihce);
        mData.add(1,book);
        bookAdapter.notifyItemInserted(1);
    }

    @Override
    public void onBookItemClick(int pos,
                                ImageView imgContainer, ImageView imgBook,
                                TextView title, TextView authorName, TextView nbPages,
                                TextView score, RatingBar ratingBar) {

        // create intent and send book object to Details activity

        Intent intent=new Intent(this,BookDetailActivity.class);
        intent.putExtra("bookObject",mData.get(pos));

        // shared Animation
        Pair<View,String> p1=Pair.create((View)imgContainer,"containerTN");
        Pair<View,String> p2=Pair.create((View)imgBook,"bookTN");
        Pair<View,String> p3=Pair.create((View)title,"booktitleTN");
        Pair<View,String> p4=Pair.create((View)authorName,"authorTN");
        Pair<View,String> p5=Pair.create((View)nbPages,"bookpagesTN");
        Pair<View,String> p6=Pair.create((View)score,"scoreTN");
        Pair<View,String> p7=Pair.create((View)ratingBar,"rateTN");

        ActivityOptionsCompat optionsCompat=ActivityOptionsCompat.makeSceneTransitionAnimation(this,p1,p2,p3,p4,p5,p6,p7);

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN){
            startActivity(intent,optionsCompat.toBundle());
        }
        else {
            startActivity(intent);
        }

    }
}