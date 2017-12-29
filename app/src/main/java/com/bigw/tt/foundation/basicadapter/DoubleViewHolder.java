package com.bigw.tt.foundation.basicadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigw.tt.foundation.R;
import com.bigw.tt.fundamentallib.viewholder.BasicViewHolder;

/**
 * Created by bigw on 25/12/2017.
 */

public class DoubleViewHolder extends BasicViewHolder<Double, DoubleViewHolder.ActionListener> {

    private final TextView mTextView;
    public DoubleViewHolder(View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.append);
    }

    @Override
    public void bind(Double aDouble, int position) {
        super.bind(aDouble, position);
        mTextView.setText("Double " + aDouble);
    }

    public static class DoubleViewHolderFactory implements BasicViewHolder.ViewHolderFactory<DoubleViewHolder> {
        @Override
        public DoubleViewHolder createViewHolder(LayoutInflater inflater, ViewGroup parent) {
            View itemView = inflater.inflate(R.layout.test_layout_string_view_holder, parent, false);
            return new DoubleViewHolder(itemView);
        }
    }

    public interface ActionListener extends BasicViewHolder.ActionListener {

    }
}
