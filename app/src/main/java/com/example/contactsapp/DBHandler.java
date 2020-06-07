package com.example.contactsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "Contacts_DB";

    private static final String CONTACTS_TABLE = "Contacts";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String NUMBER = "number";
    private static final String EMAIL = "email";
    private static final String ORGANIZATION = "organization";
    private static final String RELATIONSHIP = "relationship";

    public DBHandler(@Nullable Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE =
                "CREATE TABLE " + CONTACTS_TABLE
                        + "(" + ID + " integer PRIMARY KEY autoincrement, "
                        + NAME + " TEXT, "
                        + NUMBER + " TEXT, "
                        + EMAIL + " TEXT, "
                        + ORGANIZATION + " TEXT, "
                        + RELATIONSHIP + " TEXT)";

        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE " + CONTACTS_TABLE;
        db.execSQL(sql);
        onCreate(db);
    }

    public void addContact(Contact contact){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME, contact.getName());
        values.put(NUMBER, contact.getNumber());
        values.put(EMAIL, contact.getEmail());
        values.put(ORGANIZATION, contact.getOrganization());
        values.put(RELATIONSHIP, contact.getRelationship());

        db.insert(CONTACTS_TABLE, null, values);
        db.close();
    }

    public Contact getContact(int id){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                CONTACTS_TABLE,
                new String[]{ID, NAME, NUMBER, EMAIL, ORGANIZATION, RELATIONSHIP},
                ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null
        );

        if(cursor != null){
            cursor.moveToFirst();
            Contact contact = new Contact(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)
            );
            cursor.close();
            return contact;
        }
        else{
            return null;
        }
    }

    public List<Contact> getAllContacts(){
        SQLiteDatabase db = getReadableDatabase();
        List<Contact> contacts = new ArrayList<>();

        String query = "SELECT * FROM " + CONTACTS_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Contact contact = new Contact();

                if(!TextUtils.isEmpty(cursor.getString(0))){
                    contact.setId(Integer.parseInt(cursor.getString(0)));
                }
                else{
                    contact.setId(0);
                }

                contact.setName(cursor.getString(1));
                contact.setNumber(cursor.getString(2));
                contact.setEmail(cursor.getString(3));
                contact.setOrganization(cursor.getString(4));
                contact.setRelationship(cursor.getString(5));

                contacts.add(contact);
            }while (cursor.moveToNext());

            cursor.close();
        }
        return contacts;
    }

    public int updateContact(Contact contact){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME, contact.getName());
        values.put(NUMBER, contact.getNumber());
        values.put(EMAIL, contact.getEmail());
        values.put(ORGANIZATION, contact.getOrganization());
        values.put(RELATIONSHIP, contact.getRelationship());

        return db.update(CONTACTS_TABLE,
                values,
                ID + " = ?",
                new String[]{String.valueOf(contact.getId())});
    }

    public void deleteContact(Contact contact){
        SQLiteDatabase db = getWritableDatabase();

        db.delete(CONTACTS_TABLE,
                ID + " = ?",
                new String[]{String.valueOf(contact.getId())});

        db.close();
    }

    public int contactCount(){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + CONTACTS_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        int count = cursor.getCount();

        cursor.close();
        return count;
    }
}


