package com.therewillbebugs.todolist;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v4.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity {
    //private members, this should be changed to R.array, temp
    //Drawer Members
    private String[] drawerContent = {"Test 1", "Test 2", "Exit"};
    private DrawerLayout drawerLayout;
    private ListView drawerList;

    //Toolbar menu
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init Drawer
        initDrawer();

        //Init toolbar
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id == R.id.action_add_task){
            //Create a new task here
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //region DRAWER
    private void initDrawer(){
        //Reference: https://developer.android.com/training/implementing-navigation/nav-drawer.html

        //Initialize the Drawer Layout content
        //should call drawerContent = getResources().getStringArray(R.array.xxx); in the future
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerList = (ListView)findViewById(R.id.left_drawer);
        drawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item,drawerContent));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
    }

    class DrawerItemClickListener implements ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id){
            selectItem(position);
        }
    }

    private void selectItem(int position){
        //Swaps the fragments in the main content frame based on selection

        drawerList.setItemChecked(position,true);
        drawerLayout.closeDrawer(drawerList);
    }
    //endregion
}


