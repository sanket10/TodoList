package com.example.sanket.todolist;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Happy on 11/5/2016.
 */
public class TodoListItems {

    private static List<ListItem> todo_list_items;
    private static TodoListItems todo_list_items_object;

    private TodoListItems(List<ListItem> listItems){
        todo_list_items = new LinkedList<ListItem>();
        this.todo_list_items = listItems;
    }

    public static TodoListItems get(List<ListItem> listItems){
        synchronized (TodoListItems.class){
            if(todo_list_items_object == null){
                todo_list_items_object = new TodoListItems(listItems);
            }
        }
        return todo_list_items_object;
    }

    public static List<ListItem> getTodoListItems(){
        return todo_list_items;
    }

    public ListItem getTodoListItemById(long id){
        for(ListItem obj:todo_list_items){
            if(obj.getItem_id() == id){
                return obj;
            }
        }
        return null;
    }

    public void updateListByTitle(String title,String detail){
        for(ListItem obj:todo_list_items){
            if(obj.getTitle().equals(title)){
                obj.setDetail(detail);
                break;
            }
        }
    }
}
