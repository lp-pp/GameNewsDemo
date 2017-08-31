package com.lp.gameclient.adapter.common;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.SparseArray;
import android.view.View;

import com.lp.gamenewsdemo.R;

/**
 * Created by LP on 2017/8/31/19:25.
 */

final public class RecyclerAdatpterHelper extends BaseAdapterHelper<RecyclerAdatpterHelper>{

    protected ViewHolder viewHolder;

    private RecyclerAdatpterHelper(ViewHolder viewHolder){
        this.viewHolder = viewHolder;
        this.views = new SparseArray<>();
    }

    static RecyclerAdatpterHelper get(ViewHolder viewHolder){
        RecyclerAdatpterHelper helper;
        if (viewHolder.itemView.getTag(R.id.tag_adapter_helper) == null){
            helper = new RecyclerAdatpterHelper(viewHolder);
            viewHolder.itemView.setTag(R.id.tag_adapter_helper, helper);
        } else {
            helper = (RecyclerAdatpterHelper) viewHolder.itemView.getTag(R.id.tag_adapter_helper);
        }
        return helper;
    }

    @Override
    public View getItemView() {
        return viewHolder.itemView;
    }

    public int getItemViewType(){
        return viewHolder.getItemViewType();
    }

    public int getAdapterPosition(){
        return viewHolder.getAdapterPosition();
    }

    public int getLayoutPosition(){
        return viewHolder.getLayoutPosition();
    }

    public int getOldPosition(){
        return viewHolder.getOldPosition();
    }

    public boolean isRecyclable(){
        return viewHolder.isRecyclable();
    }

    public RecyclerAdatpterHelper setIsRecyclable(boolean recyclable){
        viewHolder.setIsRecyclable(recyclable);
        return this;
    }
}
