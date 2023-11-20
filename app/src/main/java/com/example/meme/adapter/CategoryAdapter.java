package com.example.meme.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.meme.R;
import com.example.meme.model.Category;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {

    ArrayList<Category> categoryArrayList;
    Context context;

    public CategoryAdapter(ArrayList<Category> categoryArrayList, Context context) {
        this.categoryArrayList = categoryArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return categoryArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        TextView txtCategory;
        ImageView imgCategory;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_listview_loadcategory, null);
            viewHolder.txtCategory = convertView.findViewById(R.id.txtCategory);
            viewHolder.imgCategory = convertView.findViewById(R.id.imgCategory);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.txtCategory.setText(categoryArrayList.get(position).getCategoryName());
            Glide.with(context).load(categoryArrayList.get(position).getImageName()).into(viewHolder.imgCategory);
        }
        Category category = (Category) getItem(position);
        viewHolder.txtCategory.setText(category.getCategoryName());
        Glide.with(context).load(category.getImageName())
                .placeholder(R.drawable.ic_nintendo_switch_logo_48)
                .error(R.drawable.ic_bus_48)
                .into(viewHolder.imgCategory);

        return convertView;
    }
}
