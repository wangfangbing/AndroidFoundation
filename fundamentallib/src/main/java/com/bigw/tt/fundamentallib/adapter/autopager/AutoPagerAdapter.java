package com.bigw.tt.fundamentallib.adapter.autopager;

import android.support.annotation.NonNull;
import android.view.View;

import com.bigw.tt.fundamentallib.adapter.BasicAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bigw on 13/12/2017.
 */

public class AutoPagerAdapter extends BasicAdapter {

    private AutoPagerItem mAutoPagerItem = new AutoPagerItem();
    private OnPageDownListener mPageDownListener;

    public AutoPagerAdapter() {
        register(AutoPagerItem.class, new AutoPagerViewHolder.AutoPagerViewHolderFactory(), mActionListener);
    }

    public void setOnPageDownListener(OnPageDownListener listener) {
        this.mPageDownListener = listener;
    }

    public void setPage(int page) {
        mAutoPagerItem.setPage(page);
    }

    public int getPage() {
        return mAutoPagerItem.getPage();
    }

    public void setPageCount(int pageCount) {
        mAutoPagerItem.setPageCount(pageCount);
    }

    public int getPageCount() {
        return mAutoPagerItem.getPageCount();
    }

    public void setShouldShowEndView(boolean should) {
        mAutoPagerItem.setShouldShowEndView(should);
    }

    public boolean shouldShowEndView() {
        return mAutoPagerItem.shouldShowEndView();
    }

    public void setFooterStateAsError() {
        mAutoPagerItem.setFooterState(AutoPagerItem.STATE_ERROR);
        int index = getAdapterPosition(mAutoPagerItem);
        if (index < 0) {
            super.appendItem(mAutoPagerItem);
        } else {
            notifyItemChanged(index);
        }
    }

    /**
     * @param item pass null equals clear
     */
    public void setItem(Object item) {
        if (item == null) {
            super.setItem(item);
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.add(item);
            mAutoPagerItem.resetState();
            arrayList.add(mAutoPagerItem);
            super.setDataList(arrayList);
        }
    }

    /**
     * @param dataList pass null equals clear
     */
    public void setDataList(@NonNull List dataList) {
        if (dataList == null) {
            super.setDataList(dataList);
        } else {
            mAutoPagerItem.resetState();
            dataList.add(mAutoPagerItem);
            super.setDataList(dataList);
        }
    }

    @Override
    public void insertItem(@NonNull Object item, int position) {
        int newPosition = position;
        if (position >= getItemCount()) {
            int index = getAdapterPosition(mAutoPagerItem);
            if (index > 0) {
                newPosition = getItemCount() -1;
            }
        }
        super.insertItem(item, newPosition);
    }

    @Override
    public void insertDataList(@NonNull List dataList, int position) {
        int newPosition = position;
        if (position >= getItemCount()) {
            int index = getAdapterPosition(mAutoPagerItem);
            if (index > 0) {
                newPosition = getItemCount() - 1;
            }
        }
        super.insertDataList(dataList, newPosition);
    }

    @Override
    public void appendItem(@NonNull Object item) {
        if (item == null) {
            return;
        }
        checkIfIsList(item);
        int index = getAdapterPosition(mAutoPagerItem);
        if (index < 0) {
            super.appendItem(item);
        } else {
            super.insertItem(item, index);
        }
    }

    @Override
    public void appendDataList(@NonNull List dataList) {
        if (dataList == null) {
            return;
        }
        int index = getAdapterPosition(mAutoPagerItem);
        if (index < 0 && dataList.size() > 0) {
            mAutoPagerItem.resetState();
            dataList.add(mAutoPagerItem);
            super.appendDataList(dataList);
        } else if (index >= 0){
            super.insertDataList(dataList, index);
            mAutoPagerItem.setPage(mAutoPagerItem.getPage() + 1);
            boolean hasMoreData = dataList.size() > 0;
            if (hasMoreData) {
                mAutoPagerItem.setFooterState(AutoPagerItem.STATE_PENDING_LOAD);
            } else {
                mAutoPagerItem.setFooterState(AutoPagerItem.STATE_END);
            }
            notifyItemChanged(index + dataList.size());//notify the view holder
        }
    }

    @Override
    public int removeItems(int position, int deleteCount) {
        int itemCount = super.removeItems(position, deleteCount);
        removeAutoPageItemIfNeed();
        return itemCount;
    }

    @Override
    public void removeItem(Object item) {
        super.removeItem(item);
        removeAutoPageItemIfNeed();
    }

    @Override
    public void removeItem(int position) {
        super.removeItem(position);
        removeAutoPageItemIfNeed();
    }

    private void removeAutoPageItemIfNeed() {
        if (getItemCount() == 1 && getAdapterPosition(mAutoPagerItem) >= 0) {
            super.removeItem(mAutoPagerItem);
        }
    }

    private AutoPagerViewHolder.ActionListener mActionListener = new AutoPagerViewHolder.ActionListener() {
        @Override
        public void onErrorViewClick(View v) {
            mAutoPagerItem.setFooterState(AutoPagerItem.STATE_PENDING_LOAD);
            int index = getAdapterPosition(mAutoPagerItem);
            if (index >= 0) {
                notifyItemChanged(index);
            }
        }

        @Override
        public void onPageDown() {
            if (mPageDownListener != null) {
                mPageDownListener.onPageDown(mAutoPagerItem.getPage() + 1, mAutoPagerItem.getPageCount());
            }
        }
    };

    public interface OnPageDownListener {
        void onPageDown(int page, int pageCount);
    }
}
