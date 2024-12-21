package com.example.recyclervewsqliteapp;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ClientActivity extends AppCompatActivity {
    private EditText etSurname, etName, etPhone;
    private DBHelper dbHelper;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        etSurname = findViewById(R.id.etSurname);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        dbHelper = new DBHelper(this);

        if (getIntent().hasExtra("client")) {
            client = (Client) getIntent().getSerializableExtra("client");
            etSurname.setText(client.getSurname());
            etName.setText(client.getName());
            etPhone.setText(client.getPhone());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.client_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_save_client) {
            saveClient();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveClient() {
        String surname = etSurname.getText().toString();
        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();

        if (client == null) {
            client = new Client(null, surname, name, phone);
            dbHelper.addClient(client);
        } else {
            client.setSurname(surname);
            client.setName(name);
            client.setPhone(phone);
            dbHelper.updateClient(client);
        }
        finish();
    }
}
