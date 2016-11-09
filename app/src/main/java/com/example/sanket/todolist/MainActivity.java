package com.example.sanket.todolist;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ListItem> listItems = new LinkedList<ListItem>();
    private RecyclerView recyclerView;
    private TodoListAdapter todoListAdapter;
    private TodolistDAO todolistDAO;
    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        this.todolistDAO = new TodolistDAO(getApplicationContext());
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
       // Toast.makeText(this,"kk",Toast.LENGTH_SHORT).show();
       // ((Button)findViewById(R.id.add_new_item)).setBackgroundColor(Color.TRANSPARENT);

    }

    @Override
    public void onResume(){
        super.onResume();
        recyclerView = (RecyclerView) findViewById(R.id.todo_recycler_view);
        //listItems = TodoListItems.get().getTodoListItems();
        listItems = todolistDAO.getAllItems();
        final TodoListItems tmp = TodoListItems.get(listItems);
       // Toast.makeText(this,listItems.size()+"",Toast.LENGTH_SHORT).show();
        todoListAdapter = new TodoListAdapter(listItems,getApplicationContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(todoListAdapter);
        todoListAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu){
        if(menu.getItemId() == R.id.add_new_item){
            Intent intent = new Intent(this,AddItem.class);
            startActivityForResult(intent,1);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        if(resultCode == 1){
            ListItem listItem = new ListItem(intent.getStringExtra("title").toString(),intent.getStringExtra("detail").toString(),(new Date()).toString());
            this.todolistDAO.insertItem(listItem);
            //this.listItems.add(listItem);
            Toast.makeText(this,this.listItems.size()+""+listItem.getLast_update_date(),Toast.LENGTH_SHORT).show();
            //onCreate(null);
        }else if(requestCode == 2){
            Toast.makeText(this,intent.getStringExtra("title")+" "+intent.getStringExtra("detail"),Toast.LENGTH_SHORT).show();
            TodoListItems.get(this.listItems).updateListByTitle(intent.getStringExtra("title"),intent.getStringExtra("detail"));
        }else if(resultCode == -1){
            Toast.makeText(this,"Back",Toast.LENGTH_SHORT).show();
        }
    }

    public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder> {

        private List<ListItem> list_items;
        private Context context;
        public class TodoListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
            private TextView item_title;
            private TextView last_update_date;
            private ImageView lock;
            private ListItem listItem;
            private int position;

            public TodoListViewHolder(View view){
                super(view);
                view.setOnClickListener(this);
                view.setOnLongClickListener(this);
                this.item_title = (TextView) view.findViewById(R.id.list_item_todo_title_text_view);
                this.last_update_date = (TextView) view.findViewById(R.id.list_item_todo_last_updated_date);
                this.lock = (ImageView) view.findViewById(R.id.list_item_todo_lock_image);
            }

            public void bindList(ListItem listItem,int position){
                this.listItem = listItem;
                this.position = position;
                item_title.setText(listItem.getTitle().toUpperCase());
                //Toast.makeText(MainActivity.this,listItem.getTitle()+" "+listItem.getPassword()+" "+listItem.ispassword(),Toast.LENGTH_SHORT).show();
                if(listItem.ispassword() == 1){
                    Bitmap bmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                            getResources(),R.drawable.lock),30,30,true);
                    this.lock.setImageBitmap(bmp);
                }
                String date[] = (listItem.getLast_update_date()).split(" ");
                last_update_date.setText(date[1]+" "+date[2]);

            }
            @Override
            public void onClick(View view){
                if(this.listItem.ispassword() == 1){
                    AlertDialog.Builder input_dialog_box = new AlertDialog.Builder(MainActivity.this);
                    input_dialog_box.setTitle("Enter Password To Open This Item");
                    final EditText edittext = new EditText(MainActivity.this);
                    edittext.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    input_dialog_box.setView(edittext);

                    input_dialog_box.setPositiveButton("Submit",new DialogInterface.OnClickListener(){

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String password = edittext.getText().toString();
                            if(password.isEmpty()){
                                Toast.makeText(MainActivity.this,"Please enter some password before press add button",Toast.LENGTH_SHORT).show();
                            }else{
                                checkPassword(password);
                                //Toast.makeText(MainActivity.this,password,Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    input_dialog_box.show();
                }else {
                    Intent intent = new Intent(MainActivity.this, ShowDetail.class);
                    /*intent.putExtra("detail",this.listItem.getDetail());
                    intent.putExtra("title",this.listItem.getTitle());
                    */
                    intent.putExtra("position", this.position);
                    startActivityForResult(intent, 2);
                }
            }

            private void checkPassword(String password) {
                if(password.equals(listItem.getPassword())){
                    Intent intent = new Intent(MainActivity.this, ShowDetail.class);
                    /*intent.putExtra("detail",this.listItem.getDetail());
                    intent.putExtra("title",this.listItem.getTitle());
                    */
                    intent.putExtra("position", this.position);
                    startActivityForResult(intent, 2);
                }else{
                    Toast.makeText(MainActivity.this,"Wrong Password",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public boolean onLongClick(View view){
                Toast.makeText(MainActivity.this,this.listItem.getTitle(),Toast.LENGTH_SHORT).show();
                if(listItem.ispassword() == 1){
                    Toast.makeText(MainActivity.this,"Already Password",Toast.LENGTH_SHORT).show();
                    return true;
                }
                AlertDialog.Builder input_dialog_box = new AlertDialog.Builder(MainActivity.this);
                input_dialog_box.setTitle("Add Password To An Item");
                final EditText edittext = new EditText(MainActivity.this);
                edittext.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                input_dialog_box.setView(edittext);

                input_dialog_box.setPositiveButton("Add Password",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String password = edittext.getText().toString();
                        if(password.isEmpty()){
                            Toast.makeText(MainActivity.this,"Please enter some password before press add button",Toast.LENGTH_SHORT).show();
                        }else{
                            updatePassword(password);
                            Toast.makeText(MainActivity.this,password,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                input_dialog_box.setNegativeButton("Cancel",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"Thanks for try",Toast.LENGTH_SHORT).show();
                    }
                });
                input_dialog_box.show();
                return true;
            }
            public void updatePassword(String password){
                listItem.setPassword(password);
                Toast.makeText(MainActivity.this,todolistDAO.updatePassword(listItem)+"",Toast.LENGTH_SHORT).show();

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
            holder.bindList(listItem,postion);
        }

        @Override
        public int getItemCount(){
            return list_items.size();
        }
    }

/*    public  void senddata(ListItem ob)
    {
        Intent intent = new Intent(this,ShowDetail.class);
        intent.putExtra("title",ob.getTitle());
        intent.putExtra("detail",ob.getDetail());
        intent.putExtra("password",ob.getPassword());
        intent.putExtra("lastupdate",ob.getLast_update_date());
        startActivityForResult(intent,1);
    }*/
}
