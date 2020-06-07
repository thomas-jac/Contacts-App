package com.example.contactsapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddContact extends AppCompatActivity {

    Context context;
    EditText et_name, et_number, et_email, et_org;
    Button relationship, add;
    String relationshipString;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        context = this;
        et_name = findViewById(R.id.name);
        et_number = findViewById(R.id.number);
        et_email = findViewById(R.id.email);
        et_org = findViewById(R.id.org);

        relationship = findViewById(R.id.relationship);
        add = findViewById(R.id.add);

        relationshipString = "Unspecified";
        dbHandler = new DBHandler(context);

        relationship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence options[] = {"Family", "Friend", "Work", "Acquaintance", "Other"};
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0){
                            relationshipString = "Family";
                        }
                        else if(which == 1){
                            relationshipString = "Friend";
                        }
                        else if(which == 2){
                            relationshipString = "Work";
                        }
                        else if(which == 3){
                            relationshipString = "Acquaintance";
                        }
                        else{
                            relationshipString = "Other";
                        }

                    }
                }).setTitle("Choose Relationship Type").show();

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString();
                String number = et_number.getText().toString();
                String email = et_email.getText().toString();
                String org = et_org.getText().toString();

                if(!TextUtils.isEmpty(name) || !TextUtils.isEmpty(number) || !TextUtils.isEmpty(email) || !TextUtils.isEmpty(org)){
                    Contact contact = new Contact(name, number, email, org, relationshipString);
                    dbHandler.addContact(contact);

                    startActivity(new Intent(context, MainActivity.class));
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Please Enter Complete Information")
                            .setNegativeButton("OK", null)
                            .show();
                }

            }
        });

    }
}