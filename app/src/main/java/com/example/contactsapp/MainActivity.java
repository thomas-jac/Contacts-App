package com.example.contactsapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Context context;
    private DBHandler dbHandler;

    private ListView contactsList;
    private EditText searchText;
    private Button search, add;

    private List<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        contacts = new ArrayList<>();
        dbHandler = new DBHandler(context);
        contacts = dbHandler.getAllContacts();

        String[] namesArray = new String[contacts.size()];

        contactsList = findViewById(R.id.contact_list);
        searchText = findViewById(R.id.search_text);
        search = findViewById(R.id.search);
        add = findViewById(R.id.add);

        for(int i=0; i<contacts.size(); i++){
            namesArray[i] = contacts.get(i).getName();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, namesArray);
        contactsList.setAdapter(adapter);

        contactsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact = contacts.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(contact.getName())
                        .setMessage("Number: " + contact.getNumber() + "\n"
                        + "E-Mail: " + contact.getEmail() + "\n"
                        + "Organization: " + contact.getOrganization() + "\n"
                        + "Relationship: " + contact.getRelationship())
                        .show();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddContact.class);
                startActivity(intent);
            }
        });

    }
}

