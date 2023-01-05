package com.himadri_parikh.lab12_contextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvList;
    ArrayList<String> myList = new ArrayList<>();
    ArrayAdapter<String> adt;
    AdapterView.AdapterContextMenuInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myList.add("Maharana Pratap");
        myList.add("Chhatrapati Shivaji");
        myList.add("Bhagat Singh");
        myList.add("Chandrashekhar azad");
        myList.add("Sardar Patel");
        myList.add("Rani Laxmi bai");
        myList.add("Samrat Ashok");
        myList.add("Prithviraj Chauhan");
        myList.add("Bala saheb Thakrey");
        myList.add("Subhash Chandra Bose");
        myList.add("Atal Bihari Vajpayee");
        myList.add("Dr. APJ Abdul Kalam");
        myList.add("Gandhiji");

        lvList = findViewById(R.id.lvList);

        adt = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                myList
        );
        lvList.setAdapter(adt);

        registerForContextMenu(lvList);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        info =(AdapterView.AdapterContextMenuInfo)
                        item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.menuDelete:
                myList.remove(info.position);
                adt.notifyDataSetChanged();
                return true;
            case R.id.menuUpdate:
                openDialog();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final EditText edtTxt = new EditText(getApplicationContext());

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                );
        edtTxt.setLayoutParams(lp);

        builder.setView(edtTxt)
                .setTitle("UpdateElements")
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int index = info.position;
//                        myList.remove(index);
                        myList.set(index, edtTxt.getText().toString());
                        adt.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,
                                "Update Cancelled",
                                Toast.LENGTH_SHORT).show();
                    }
                });

    }
}