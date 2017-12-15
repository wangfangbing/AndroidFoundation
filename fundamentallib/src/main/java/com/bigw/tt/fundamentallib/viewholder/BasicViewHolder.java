package com.bigw.tt.fundamentallib.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by bigw on 13/12/2017.
 */

public class BasicViewHolder extends RecyclerView.ViewHolder {

    private ActionListener mActionListener;

    public interface ViewHolderFactory<T extends BasicViewHolder> {
        T createViewHolder(LayoutInflater inflater, ViewGroup parent);
    }

    public interface ActionListener {
        //empty listener
    }

    public BasicViewHolder(View itemView) {
        super(itemView);
    }

    public void setActionListener(ActionListener listener) {
        this.mActionListener = listener;
    }

    public ActionListener getActionListener() {
        return mActionListener;
    }
}
