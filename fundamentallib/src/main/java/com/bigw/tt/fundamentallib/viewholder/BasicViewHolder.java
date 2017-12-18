package com.bigw.tt.fundamentallib.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by bigw on 13/12/2017.
 */

public abstract class BasicViewHolder<Item, Listener extends BasicViewHolder.ActionListener> extends RecyclerView.ViewHolder {

    private Listener mActionListener;

    private Item mItem;

    public interface ActionListener {
        //empty listener
    }

    public void setActionListener(Listener listener) {
        this.mActionListener = listener;
    }

    public Listener getActionListener() {
        return mActionListener;
    }

    public interface ViewHolderFactory<T extends BasicViewHolder> {
        T createViewHolder(LayoutInflater inflater, ViewGroup parent);
    }

    public BasicViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(Item item, int position) {
        this.mItem = item;
    }

    public Item getItem() {
        return this.mItem;
    }
}
