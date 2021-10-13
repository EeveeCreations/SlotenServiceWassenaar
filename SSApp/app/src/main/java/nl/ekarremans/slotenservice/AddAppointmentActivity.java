package nl.ekarremans.slotenservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddAppointmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);

        final Button returnB = findViewById(R.id.returnMainButton);
        returnB.setOnClickListener(this::returnToMain);
    }


//    Return to CalenderScreen
    private void returnToMain(View view) {
    Intent intent = new Intent(this, CalenderActivity.class);
    startActivity(intent);
    }
}