package com.example.archek.geo2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Archek on 30.05.2018.
 */

public class ListActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        final MyAdapter myAdapter = new MyAdapter();
        toolbar = findViewById( R.id.toolbar );
        setSupportActionBar(toolbar);
        recyclerView.setAdapter(myAdapter);
        myAdapter.addAll(createUserList(5));// TODO Call recyclerView,toolbar, addButton

            findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick( View v )
                {
                    myAdapter.add(new User(0, ""));
                }
            } );

    }

        private List<User> createUserList( int count)
        {
            List<User> list = new ArrayList<>();
            for (int i = 0; i < count; i++)
            {
                list.add(new User(i, "Dot-" + i));
            }
            return list;
        }

        public boolean onCreateOptionsMenu(Menu menu)
        {
            getMenuInflater().inflate( R.menu.menu_activity_main, menu );
            return true;
        }

        public boolean onOptionsItemSelected(MenuItem item)
        {
            int id = item.getItemId();

            switch (id)
            {
                case R.id.gmap1:
                    Intent intent = new Intent(this, MapsActivity.class);
                    startActivity(intent);// TODO Call first activity
                    break;
                case R.id.listItem:
                    return true;
                default:
                    return super.onOptionsItemSelected( item );

            }

            return false;
        }
}
