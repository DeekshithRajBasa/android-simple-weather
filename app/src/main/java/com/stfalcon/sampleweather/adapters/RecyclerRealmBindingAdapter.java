package com.stfalcon.sampleweather.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stfalcon.sampleweather.ui.custom.base.binding.viewholders.BindingViewHolder;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by Anton Bevza on 01.03.16.
 */
public abstract class RecyclerRealmBindingAdapter<T extends RealmObject, H extends BindingViewHolder> extends RecyclerView.Adapter<H> {

    protected RealmResults<T> items;
    protected ArrayList<Wrapper> wrappers = new ArrayList<>();
    private OnItemClickListener<T> onItemClickListener;
    private OnItemLongClickListener<T> onItemLongClickListener;
    private int itemLayout;
    public Realm realm;
    protected Context context;

    public RecyclerRealmBindingAdapter(RealmResults<T> items, Realm realm) {
        this.realm = realm;
        init(items);
    }

    protected void init(RealmResults<T> items) {
        setItems(items);
    }

    @Override
    public H onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(itemLayout, parent, false);
        return onCreateHolder(v);
    }

    public abstract H onCreateHolder(View view);

    @Override
    public void onBindViewHolder(H holder, int position) {
        final Wrapper item = wrappers.get(position);
        holder.getBinding().getRoot().setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position, item.item);
            }
        });
        holder.getBinding().getRoot().setOnLongClickListener(v -> {
            if (onItemLongClickListener != null) {
                onItemLongClickListener.onItemLongClick(position, item.item);
                return true;
            }
            return false;
        });
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return wrappers.size();
    }

    public OnItemClickListener<T> getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public void setItemLayout(int item_layout) {
        this.itemLayout = item_layout;
    }

    public void setItems(RealmResults<T> items) {
        this.items = items;
        createWrappers();
        notifyDataSetChanged();
    }

    public boolean isEmpty() {
        return items == null || items.size() == 0;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(int position, T item);
    }

    public interface OnItemLongClickListener<T> {
        void onItemLongClick(int position, T item);
    }

    protected void createWrappers() {
        wrappers.clear();
        for (int i = 0; i < items.size(); i++) {
            wrappers.add(createWrapper(items.get(i)));
        }
    }

    public abstract Wrapper createWrapper(T item);

    public abstract class Wrapper {
        protected T item;

        public T getItem() {
            return item;
        }

        public void setItem(T item) {
            this.item = item;
        }
    }

}
