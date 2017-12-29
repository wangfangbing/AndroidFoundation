package com.bigw.tt.fundamentallib.test.common;

import android.support.annotation.VisibleForTesting;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigw.tt.fundamentallib.R;
import com.bigw.tt.fundamentallib.viewholder.BasicViewHolder;

/**
 * Created by bigw on 18/12/2017.
 */
@VisibleForTesting
public class StringViewHolder extends BasicViewHolder<String, StringViewHolder.ActionListener> {

    private final TextView mStringText;

    public StringViewHolder(View itemView) {
        super(itemView);
        mStringText = itemView.findViewById(R.id.append);
    }

    @Override
    public void bind(String s, int position) {
        super.bind(s, position);
        mStringText.setText(s);
        mStringText.setOnClickListener(mStringViewClickListener);
    }

    private View.OnClickListener mStringViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (getActionListener() != null) {
                getActionListener().onItemClicked(getItem(), getAdapterPosition());
            }
        }
    };

    public interface ActionListener extends BasicViewHolder.ActionListener {
        void onItemClicked(String s, int adapterPosition);
    }

    public static class Factory implements ViewHolderFactory {
        @Override
        public BasicViewHolder createViewHolder(LayoutInflater inflater, ViewGroup parent) {
            View contentView = inflater.inflate(R.layout.test_layout_string_view_holder, parent, false);
            return new StringViewHolder(contentView);
        }
    }
}
