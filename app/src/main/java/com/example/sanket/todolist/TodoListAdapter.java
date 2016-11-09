package com.example.sanket.todolist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.List;

import static android.support.v4.app.ActivityCompat.startActivity;
import static android.support.v4.app.ActivityCompat.startActivityForResult;

/**
 * Created by Happy on 11/6/2016.
 */
public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder> {

    private List<ListItem> list_items;
    private Context context;
    public class TodoListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView item_title;
        private TextView last_update_date;
        private ImageView lock;
        private ListItem listItem;

        public TodoListViewHolder(View view){
            super(view);
            view.setOnClickListener(this);
            this.item_title = (TextView) view.findViewById(R.id.list_item_todo_title_text_view);
            this.last_update_date = (TextView) view.findViewById(R.id.list_item_todo_last_updated_date);
            //this.lock = (ImageView) view.findViewById(R.id.list_item_todo_lock_image);
        }

        public void bindList(ListItem listItem){
            this.listItem = listItem;
            item_title.setText(listItem.getTitle());
            last_update_date.setText(listItem.getLast_update_date().toString());

        }
        @Override
        public void onClick(View view){

            ListItem ob=new ListItem(this.listItem.getTitle(),this.listItem.getDetail(),this.listItem.getLast_update_date());
            MainActivity ob1=new MainActivity();
//            ob1.senddata(ob);

        }
    }

    public TodoListAdapter(List<ListItem> list_items, Context applicationContext){
        this.list_items = list_items;
        context = applicationContext;
    }

    @Override
    public TodoListViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.todo_list_item,parent,false);
        return new TodoListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TodoListViewHolder holder,int postion){
        ListItem listItem = list_items.get(postion);
        holder.bindList(listItem);
    }

    @Override
    public int getItemCount(){
        return list_items.size();
    }
}
