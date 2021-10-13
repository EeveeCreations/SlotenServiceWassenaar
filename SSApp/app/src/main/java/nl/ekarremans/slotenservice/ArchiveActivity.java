package nl.ekarremans.slotenservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ArchiveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);

        final Button returnB = findViewById(R.id.returnMainButton);
        returnB.setOnClickListener(this::openCalender);
    }


//    Return to CalenderScreen
    private void openCalender(View view) {
        Intent intent = new Intent(this, CalenderActivity.class);
        startActivity(intent);

    }
}