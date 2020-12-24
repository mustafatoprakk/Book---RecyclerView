package com.example.bookrecyclerview.recyclerview;

import android.view.animation.AnimationUtils;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookrecyclerview.R;

public class CustomItemAnimator extends DefaultItemAnimator {

    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {

        // remove item
        holder.itemView.setAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.viewholder_remove_animation));  // Animation apply

        return super.animateRemove(holder);
    }

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {

        // this method will be called when a new item will be added to the list
        // we will handle the add animation to the item here

        holder.itemView.setAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.viewholder_add_anim));  // Animation apply

        return super.animateAdd(holder);
    }

    // we can also customize the duration of the add animation

    @Override
    public long getAddDuration() {

        return 500;
    }

    @Override
    public long getRemoveDuration() {
        return 500;
    }
}
