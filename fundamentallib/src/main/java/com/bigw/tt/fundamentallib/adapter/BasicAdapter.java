package com.bigw.tt.fundamentallib.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bigw.tt.fundamentallib.viewholder.BasicViewHolder;

import java.util.AbstractList;
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

    public boolean register(@NonNull Class itemClass, @NonNull ViewHolderFactory factory, @Nullable ActionListener listener) {
        return viewHolderInfoManager.register(itemClass, factory, listener);
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
        holder.bind(mDataList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }


    public List getDataList() {
        return mDataList;
    }

    public int getPosition(Object item) {
        if (item == null) {
            return -1;
        }
        return mDataList.indexOf(item);
    }

    @Override
    public int getItemViewType(int position) {
        Class itemClass = mDataList.get(position).getClass();
        int viewType = viewHolderInfoManager.getViewType(itemClass);
        if (viewType <= 0) {
            throw new RuntimeException("this item --" + itemClass + " has not been registered");
        }
        return viewType;
    }

    /**
     * appendItem a item
     * @param item
     */
    public void appendItem(@NonNull Object item) {
        if (item == null) {
            return;
        }
        checkNotList(item);
        int startPosition = mDataList.size();
        mDataList.add(item);
        notifyItemRangeInserted(startPosition, 1);
    }

    public void insertItem(@NonNull Object item, int position) {
        if (item == null) {
            return;
        }
        checkNotList(item);
        int startPosition = position;
        if (position < 0) {
            startPosition = 0;
        } else if (position > mDataList.size()) {
            startPosition = mDataList.size();
        }
        mDataList.add(startPosition, item);
        notifyItemInserted(startPosition);
    }

    public void removeItem(Object item) {
        if (item == null) {
            return;
        }
        checkNotList(item);
        int startPosition = getPosition(item);
        if (startPosition >= 0) { //find it
            mDataList.remove(startPosition);
            notifyItemRangeRemoved(startPosition, 1);
        }
    }

    private void checkNotList(Object item) {
        if (item instanceof List) {
            throw new RuntimeException("cannot appendItem an item which type is List to current DataList");
        }
    }

}
