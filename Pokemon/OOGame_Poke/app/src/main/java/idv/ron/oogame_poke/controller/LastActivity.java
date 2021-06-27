package idv.ron.oogame_poke.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import idv.ron.oogame_poke.R;

public class LastActivity extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);
        handleViews();
    }

    //顯示最後的Textview
    private void handleViews() {
        textView = findViewById(R.id.tvYSTl);
        textView.setText(String.format(getString(R.string.textYSTl)));
    }
}
