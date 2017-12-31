package com.bigw.tt.fundamentallib.adapter.autopager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigw.tt.fundamentallib.R;
import com.bigw.tt.fundamentallib.viewholder.BasicViewHolder;

/**
 * Created by bigw on 29/12/2017.
 */

public class AutoPagerViewHolder extends BasicViewHolder<AutoPagerItem, AutoPagerViewHolder.ActionListener> {

    private final TextView mLoadingTextView;
    private final TextView mErrorTextView;
    private final TextView mEndTextView;
    public AutoPagerViewHolder(View itemView) {
        super(itemView);
        mLoadingTextView = itemView.findViewById(R.id.loading);
        mErrorTextView = itemView.findViewById(R.id.error);
        mEndTextView = itemView.findViewById(R.id.end);
    }

    @Override
    public void bind(AutoPagerItem autoPagerItem, int position) {
        super.bind(autoPagerItem, position);
        switch (autoPagerItem.getFooterState()) {
            case AutoPagerItem.STATE_PENDING_LOAD:
                showLoadingView(autoPagerItem);
                pageDown(autoPagerItem);
                break;
            case AutoPagerItem.STATE_END:
                showEndView(autoPagerItem);
                break;
            case AutoPagerItem.STATE_ERROR:
                showErrorView(autoPagerItem);
                break;
            case AutoPagerItem.STATE_LOADING:
                //no nothing
                break;
        }
    }

    private void showEndView(AutoPagerItem item) {
        if (item.shouldShowEndView()) {
            itemView.setVisibility(View.VISIBLE);
            mEndTextView.setVisibility(View.VISIBLE);
            mErrorTextView.setVisibility(View.GONE);
            mLoadingTextView.setVisibility(View.GONE);
        } else {
            itemView.setVisibility(View.GONE);
        }
    }

    private void showErrorView(AutoPagerItem item) {
        itemView.setVisibility(View.VISIBLE);
        mErrorTextView.setVisibility(View.VISIBLE);
        mErrorTextView.setOnClickListener(mErrorViewClickListener);
        mEndTextView.setVisibility(View.GONE);
        mLoadingTextView.setVisibility(View.GONE);
    }

    private void showLoadingView(AutoPagerItem item) {
        itemView.setVisibility(View.VISIBLE);
        mLoadingTextView.setVisibility(View.VISIBLE);
        mErrorTextView.setVisibility(View.GONE);
        mEndTextView.setVisibility(View.GONE);
    }

    private void pageDown(AutoPagerItem item) {
        item.setFooterState(AutoPagerItem.STATE_LOADING);
        if (getActionListener() != null) {
            getActionListener().onPageDown();
        }
    }

    private View.OnClickListener mErrorViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (getActionListener() != null) {
                getActionListener().onErrorViewClick(v);
            }
        }
    };

    public static class AutoPagerViewHolderFactory implements BasicViewHolder.ViewHolderFactory {
        @Override
        public BasicViewHolder createViewHolder(LayoutInflater inflater, ViewGroup parent) {
            View itemView = inflater.inflate(R.layout.item_auto_pager_footer_view, parent, false);
            return new AutoPagerViewHolder(itemView);
        }
    }

    public interface ActionListener extends BasicViewHolder.ActionListener {
        void onErrorViewClick(View v);
        void onPageDown();
    }
}
