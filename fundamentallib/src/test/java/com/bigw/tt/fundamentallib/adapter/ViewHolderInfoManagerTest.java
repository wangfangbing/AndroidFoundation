package com.bigw.tt.fundamentallib.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bigw.tt.fundamentallib.viewholder.BasicViewHolder;

import org.junit.Test;

import static com.bigw.tt.fundamentallib.viewholder.BasicViewHolder.ActionListener;
import static com.bigw.tt.fundamentallib.viewholder.BasicViewHolder.ViewHolderFactory;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;

/**
 * Created by bigw on 15/12/2017.
 */
public class ViewHolderInfoManagerTest {

    @Test
    public void exists() throws Exception {
        ViewHolderInfoManager manager = new ViewHolderInfoManager();
        assertFalse(manager.exists(String.class));

        manager.register(String.class, null, null);
        assertTrue(manager.exists(String.class));

        manager.register(Double.class, factory1, listener1);
        assertTrue(manager.exists(Double.class));
        assertFalse(manager.exists(Long.class));
    }

    @Test
    public void register() throws Exception {
        ViewHolderInfoManager manager = new ViewHolderInfoManager();
        assertEquals(0, manager.size());

        boolean success = manager.register(Double.class, factory2, listener1);
        assertTrue(success);
        assertEquals(1, manager.size());
        ViewHolderInfoManager.Node node = manager.getViewHolderInfo(Double.class);
        assertThat(node, hasProperty("itemClass", equalTo(Double.class)));
        assertThat(node, hasProperty("id", equalTo(1)));
        assertThat(node, hasProperty("factory", equalTo(factory2)));
        assertThat(node, hasProperty("actionListener", equalTo(listener1)));

        manager.register(String.class, factory1, listener1);
        manager.register(Character.class, factory1, listener1);
        assertEquals(3, manager.size());

        assertThat(manager.getViewHolderInfo(Character.class), hasProperty("id", equalTo(3)));

        success = manager.register(Character.class, factory1, listener2);
        assertFalse(success);
        assertEquals(3, manager.size());
    }

    @Test
    public void getViewHolderInfo() throws Exception {
        ViewHolderInfoManager manager = new ViewHolderInfoManager();
        manager.register(Double.class, factory2, listener2);
        manager.register(Character.class, factory2, listener2);
        manager.register(Long.class, factory2, listener2);
        manager.register(String.class, factory1, listener1);
        manager.register(Double.class, factory1, listener1); //no effect

        ViewHolderInfoManager.Node node = manager.getViewHolderInfo(String.class);
        assertThat(node, hasProperty("id", equalTo(4)));
        assertThat(node, hasProperty("itemClass", equalTo(String.class)));
        assertThat(node, hasProperty("factory", equalTo(factory1)));
        assertThat(node, hasProperty("actionListener", equalTo(listener1)));

        node = manager.getViewHolderInfo(Double.class);
        assertThat(node, hasProperty("id", equalTo(1)));
        assertThat(node, hasProperty("itemClass", equalTo(Double.class)));
        assertThat(node, hasProperty("factory", equalTo(factory2)));
        assertThat(node, hasProperty("actionListener", equalTo(listener2)));
    }

    @Test
    public void getViewType() throws Exception {
        ViewHolderInfoManager manager = new ViewHolderInfoManager();
        manager.register(String.class, factory1, listener1);
        int viewType = manager.getViewType(String.class);
        assertEquals(1, viewType);

        manager.register(Double.class, factory2, listener2);
        assertEquals(2, manager.getViewType(Double.class));

        manager.register(Double.class, factory2, listener2);
        manager.register(Long.class, factory2, listener2);
        manager.register(Character.class, factory2, listener2);
        manager.register(Short.class, factory2, listener2);
        assertEquals(5, manager.getViewType(Short.class));
    }

    @Test
    public void getViewHolderInfo1() throws Exception {
        ViewHolderInfoManager manager = new ViewHolderInfoManager();
        manager.register(String.class, factory2, listener2);
        manager.register(Double.class, factory3, listener2);

        ViewHolderInfoManager.Node node = manager.getViewHolderInfo(1);
        assertThat(node, hasProperty("id", equalTo(1)));
        assertThat(node, hasProperty("factory", equalTo(factory2)));

        node = manager.getViewHolderInfo(2);
        assertThat(node, hasProperty("id", equalTo(2)));
        assertThat(node, hasProperty("actionListener", equalTo(listener2)));
    }

    private ActionListener listener1 = new ActionListener() {
    };

    private ActionListener listener2 = new ActionListener() {
    };

    private ViewHolderFactory factory1 = new AbsFactory();
    private ViewHolderFactory factory2 = new AbsFactory();
    private ViewHolderFactory factory3 = new AbsFactory();
    private ViewHolderFactory factory4 = new AbsFactory();
    private ViewHolderFactory factory5 = new AbsFactory();
    private ViewHolderFactory factory6 = new AbsFactory();
    private ViewHolderFactory factory7 = new AbsFactory();

    private static class AbsFactory implements ViewHolderFactory {
        @Override
        public BasicViewHolder createViewHolder(LayoutInflater inflater, ViewGroup parent) {
            return null;
        }
    }
}