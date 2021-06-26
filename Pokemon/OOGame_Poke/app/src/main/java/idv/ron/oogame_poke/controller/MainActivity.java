package idv.ron.oogame_poke.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import idv.ron.oogame_poke.R;

// 首頁
public class MainActivity extends AppCompatActivity {
    private EditText etuser;

    private ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }

    @SuppressLint("ResourceType")
    private void findViews() {
        etuser = findViewById(R.id.etuser);
        imageButton =(ImageButton) findViewById(R.drawable.login);
    }

    public void onLoginClick(View view) {
        String name = etuser.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        Intent intent = new Intent(this, RolePageActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }


}
