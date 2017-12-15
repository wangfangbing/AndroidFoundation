package com.bigw.tt.fundamentallib.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bigw.tt.fundamentallib.viewholder.BasicViewHolder;

import java.util.ArrayList;
import java.util.List;

import static com.bigw.tt.fundamentallib.viewholder.BasicViewHolder.ActionListener;
import static com.bigw.tt.fundamentallib.viewholder.BasicViewHolder.ViewHolderFactory;

/**
 * Created by bigw on 13/12/2017.
 */

public class BasicAdapter extends RecyclerView.Adapter<BasicViewHolder> {

    private List mDataList = new ArrayList();
    private ViewHolderInfoManager viewHolderInfoManager = new ViewHolderInfoManager();

    public void register(@NonNull Class itemClass, @NonNull ViewHolderFactory factory, @NonNull ActionListener listener) {
        viewHolderInfoManager.register(itemClass, factory, listener);
    }

    @Override
    public BasicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolderInfoManager.Node node = viewHolderInfoManager.getViewHolderInfo(viewType);
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        BasicViewHolder viewHolder = node.factory.createViewHolder(inflater, parent);
        viewHolder.setActionListener(node.actionListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BasicViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Class itemClass = mDataList.get(position).getClass();
        int viewType = viewHolderInfoManager.getViewType(itemClass);
        if (viewType <= 0) {
            throw new RuntimeException("this item " + itemClass + " has not been registered");
        }
        return viewType;
    }

}
