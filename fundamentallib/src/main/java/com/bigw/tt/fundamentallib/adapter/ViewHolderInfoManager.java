package com.bigw.tt.fundamentallib.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.annotation.VisibleForTesting;

import com.bigw.tt.fundamentallib.viewholder.BasicViewHolder;
import static com.bigw.tt.fundamentallib.viewholder.BasicViewHolder.*;

/**
 * Created by bigw on 15/12/2017.
 */

public class ViewHolderInfoManager {

    private Node mFirst, mLast;
    private int mNodeCount = 0;

    @UiThread
    public boolean register(Class itemClass, ViewHolderFactory factory, ActionListener listener) {
        if (exists(itemClass)) {
            return false;
        }

        if (mFirst == null) {
            Node node = new Node(1, itemClass, factory, listener);
            mFirst = node;
        } else if (mLast == null) {
            int mLastNodeId = mFirst.id;
            Node node = new Node(mLastNodeId + 1, itemClass, factory, listener);
            mLast = node;
            mFirst.next = mLast;
        } else {
            int mLastNodeId = mLast.id;
            Node node = new Node(mLastNodeId + 1, itemClass, factory, listener);
            mLast.next = node;
            mLast = node;
        }
        mNodeCount += 1;
        return true;
    }

    public int size() {
        return mNodeCount;
    }

    public boolean exists(Class itemClass) {
        for(Node first = mFirst; first != null; first = first.next) {
            if (first.itemClass == itemClass) {
                return true;
            }
        }
        return false;
    }

    public int getViewType(@NonNull Class itemClass) {
        Node node = getViewHolderInfo(itemClass);
        if (node != null) {
            return node.id;
        }
        return -1;
    }

    public Node getViewHolderInfo(@NonNull Class itemClass) {
        for(Node first = mFirst; first != null; first = first.next) {
            if (first.itemClass == itemClass) {
                return first;
            }
        }
        return null;
    }

    public Node getViewHolderInfo(int viewType) {
        for (Node first = mFirst; first != null; first = first.next) {
            if (first.id == viewType) {
                return first;
            }
        }
        return null;
    }

    public static class Node {
        public final int id;
        public final Class itemClass;
        public final BasicViewHolder.ActionListener actionListener;
        public final BasicViewHolder.ViewHolderFactory factory;

        public Node next = null;

        public Node(int id, Class itemClass, ViewHolderFactory factory, ActionListener actionListener) {
            this.id = id;
            this.itemClass = itemClass;
            this.actionListener = actionListener;
            this.factory = factory;
        }

        @VisibleForTesting
        public int getId() {
            return id;
        }

        @VisibleForTesting
        public Class getItemClass() {
            return itemClass;
        }

        @VisibleForTesting
        public ActionListener getActionListener() {
            return actionListener;
        }

        @VisibleForTesting
        public ViewHolderFactory getFactory() {
            return factory;
        }
    }
}
