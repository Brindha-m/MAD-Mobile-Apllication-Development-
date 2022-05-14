package com.brindha.database;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private EditText editTextID;
    private EditText editTextName;
    private EditText editTextNum;

    private String name;
    private int number;
    private String ID;

    private dbHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new dbHelper(this); // to call constructor

        editTextID = findViewById(R.id.editText1);
        editTextName = findViewById(R.id.editText2);
        editTextNum = findViewById(R.id.editText3);

        Button buttonSave = findViewById(R.id.button);
        Button buttonRead = findViewById(R.id.button2);
        Button buttonUpdate = findViewById(R.id.button3);
        Button buttonDelete = findViewById(R.id.button4);
        Button buttonSearch = findViewById(R.id.button5);
        Button buttonDeleteAll = findViewById(R.id.button6);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = editTextName.getText().toString();


                String num = editTextNum.getText().toString();

                if (name.isEmpty() || num.isEmpty()) {

                    Toast.makeText(MainActivity.this, "Cannot Submit Empty Fields", Toast.LENGTH_SHORT).show();
                } else {
                    number = Integer.parseInt(num);


                    try {
                        // Insert Data
                        db.insertData(name, number);

                        // Clear the fields
                        editTextID.getText().clear();
                        editTextName.getText().clear();
                        editTextNum.getText().clear();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1);
                String name;
                String num;
                String id;

                try {

                    Cursor cursor = db.readData();
                    if (cursor != null && cursor.getCount() > 0) {

                        while (cursor.moveToNext()) {

                            id = cursor.getString(0); // get data in column index 0
                            name = cursor.getString(1); // get data in column index 1
                            num = cursor.getString(2); // get data in column index 2

                            // Add SQLite data to listView
                            adapter.add("ID :- " + id + "\n" +
                                    "Name :- " + name + "\n" +
                                    "Number :- " + num + "\n\n");


                        }


                    } else {

                        adapter.add("No Data");
                    }
                    cursor.close();


                } catch (Exception e) {
                    e.printStackTrace();
                }


                // show the saved data in alertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("SQLite saved data");

                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = editTextName.getText().toString();

                String num = editTextNum.getText().toString();
                ID = editTextID.getText().toString();

                if (name.isEmpty() || num.isEmpty() || ID.isEmpty()) {

                    Toast.makeText(MainActivity.this, "Cannot Submit Empty Fields", Toast.LENGTH_SHORT).show();
                } else {
                    number = Integer.parseInt(num);


                    try {
                        // Update Data
                        db.updateData(ID, name, number);

                        // Clear the fields
                        editTextID.getText().clear();
                        editTextName.getText().clear();
                        editTextNum.getText().clear();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ID = editTextID.getText().toString();

                if (ID.isEmpty()) {

                    Toast.makeText(MainActivity.this, "Please enter the ID", Toast.LENGTH_SHORT).show();
                } else {


                    try {
                        // Delete Data
                        db.deleteData(ID);

                        // Clear the fields
                        editTextID.getText().clear();
                        editTextName.getText().clear();
                        editTextNum.getText().clear();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }
        });

        buttonDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Delete all data
                // You can simply delete all the data by calling this method --> db.deleteAllData();
                // You can try this also
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Delete All Data");
                builder.setCancelable(false);
                builder.setMessage("Do you really need to delete your all data ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // User confirmed , now you can delete the data
                        db.deleteAllData();

                        // Clear the fields
                        editTextID.getText().clear();
                        editTextName.getText().clear();
                        editTextNum.getText().clear();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // user not confirmed
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ID = editTextID.getText().toString();

                if (ID.isEmpty()) {

                    Toast.makeText(MainActivity.this, "Please enter the ID", Toast.LENGTH_SHORT).show();
                } else {


                    try {
                        // Search data
                        Cursor cursor = db.searchData(ID);
                        if (cursor.moveToFirst()) {

                            editTextName.setText(cursor.getString(1));
                            editTextNum.setText(cursor.getString(2));
                            Toast.makeText(MainActivity.this, "Data successfully searched", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(MainActivity.this, "ID not found", Toast.LENGTH_SHORT).show();

                            editTextNum.setText("ID Not found");
                            editTextName.setText("ID not found");

                        }


                        cursor.close();


                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }

            }
        });
    }
}