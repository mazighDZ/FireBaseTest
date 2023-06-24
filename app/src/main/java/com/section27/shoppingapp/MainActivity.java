package com.section27.shoppingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private DatabaseReference oDatabase;
    private DatabaseReference roDatabase;
    Button btn ;
    EditText editText;
    TextView     priceText;

    //
    TextView mileage ,engine,bodyType,doors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init btn and txview
        btn= findViewById(R.id.btnUpdate);
        editText= findViewById(R.id.editText);
        priceText = findViewById(R.id.textView);

        // Initialize Firebase with the updated database URL
// connect to firebase
        //in data base we create key = GoldPrice and value = 1900;
        mDatabase = FirebaseDatabase.getInstance("https://shoopingapp-a39b4-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("CarPrice").child("price");
        // write data to firebase , simple data (no custom object )
        // we change price value on data base with new value 200


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.setValue(editText.getText().toString());
            }
        });

        //  read simple data from data base
    mDatabase.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            // when any change in the fire base occurs
            priceText.setText(snapshot.getValue().toString() + "$");

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });


    //passing Object into fireBase
        //writing Customer Object on FireBase
        oDatabase  = FirebaseDatabase.getInstance("https://shoopingapp-a39b4-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("MyUsers");

        // widgets
        Button createUser = findViewById(R.id.btnCreateUser);
        EditText username = findViewById(R.id.editTextUserName);
        EditText password = findViewById(R.id.editTextpassword);


        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //write data to firebase
                DataModel user1 = new DataModel(username.getText().toString() ,password.getText().toString() );

                oDatabase.setValue(user1);
            }
        });


///read Customer object from fireBase

        mileage = findViewById(R.id.tvMileage);
        doors = findViewById(R.id.tvDoors);
        engine = findViewById(R.id.tvEngine);
        bodyType = findViewById(R.id.tvBodyType);

        roDatabase  = FirebaseDatabase.getInstance("https://shoopingapp-a39b4-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("Car");

        List<Car> listCar = new ArrayList<>();
        roDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Car car = dataSnapshot.getValue(Car.class);
               listCar.add(car);
                }
                if (!listCar.isEmpty()) {
                    Car firstCar = listCar.get(0);
                    // Access the properties of the first car
                    String mileageValue = firstCar.getMileage();
                    String doorsValue = firstCar.getDoors();
                    String engineValue = firstCar.getEngine();
                    String bodyTypeValue = firstCar.getBodyType();

                    // Update your TextViews or perform any other operations with the retrieved values
                    mileage.setText("Mileage\n"+mileageValue);
                    doors.setText("Doors\n"+doorsValue);
                    engine.setText("Engine\n"+engineValue);
                    bodyType.setText("Body type\n"+bodyTypeValue);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}