package com.example.sanket.todolist;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

/**
 * Created by Happy on 11/7/2016.
 */
public class SwipeDetailAdapter extends PagerAdapter{
    private List<ListItem> listItems;
    private Context context;
    private LayoutInflater layoutInflater;

    public SwipeDetailAdapter(Context context){
        this.context = context;
        this.listItems = TodoListItems.getTodoListItems();
    }
    @Override
    public int getCount() {
        return this.listItems.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (RelativeLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup viewGroup,int position){
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.show_detail,viewGroup,false);
       // Toast.makeText(context,((TextView)view.findViewById(R.id.show_detail_title)).getText(),Toast.LENGTH_SHORT).show();
        //((TextView)view.findViewById(R.id.show_detail_title)).setText("ddd");
        ((TextView)view.findViewById(R.id.title_heading)).setText("Title ");
        ((EditText)view.findViewById(R.id.title_value)).setText(this.listItems.get(position).getTitle());
        ((TextView)view.findViewById(R.id.detail_heading)).setText("Detail : ");
        //(view.findViewById(R.id.detail_value)).setEnabled(true);
        ((EditText)view.findViewById(R.id.detail_value)).setText(this.listItems.get(position).getDetail());
        Toast.makeText(context,position+"",Toast.LENGTH_SHORT).show();
        if(this.listItems.get(position).ispassword() == 1){
            ((EditText)view.findViewById(R.id.title_value)).setText("*********");
            ((EditText)view.findViewById(R.id.detail_value)).setText("********");
            Toast.makeText(context,"Private Item",Toast.LENGTH_SHORT).show();
        }else if(this.listItems.get(position).ispassword() == 3){
            Toast.makeText(context,"kkkkk",Toast.LENGTH_SHORT).show();
            this.listItems.get(position).setIspassword(1);
        }
        viewGroup.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup viewGroup,int Position,Object object){
        viewGroup.removeView((RelativeLayout)object);
    }

}
