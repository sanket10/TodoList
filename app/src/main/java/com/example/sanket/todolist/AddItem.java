package com.example.sanket.todolist;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItem extends AppCompatActivity {
    private Button add_item_button;
    private EditText item_title_field;
    private EditText item_detail_field;
    private ListItem listItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Toolbar toolbar = (Toolbar)findViewById(R.id.add_item_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_item_menu,menu);
        return true;
    }
*/
    @Override
    public void onResume(){
        super.onResume();
        this.add_item_button = (Button)findViewById(R.id.add_item_button);
        this.item_title_field = (EditText)findViewById(R.id.item_title);
        this.item_detail_field = (EditText)findViewById(R.id.item_detail);
        this.add_item_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                addItemIntoList();
            }
        });
    }

    public void addItemIntoList(){
        String title = item_title_field.getText().toString();
        String detail = item_detail_field.getText().toString();
        if(title.isEmpty() || detail.isEmpty()){
            Toast.makeText(AddItem.this,"Please Enter Item's title or detail",Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent();
            intent.putExtra("title",item_title_field.getText().toString());
            intent.putExtra("detail",item_detail_field.getText().toString());
            setResult(1,intent);
            super.finish();
        }
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Log.d("ShowDetail","onBackPressed() enters");
        finish();
        Log.d("ShowDetail","onBackPressed() returns");
    }

    @Override
    public void finish(){
        Intent intent = new Intent();
        setResult(-1,intent);
        super.finish();
    }
}
