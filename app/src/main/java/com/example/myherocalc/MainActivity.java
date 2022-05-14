package com.example.myherocalc;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public int oppLife, selfLife, atkSpeed, atkDamage;
    public TextView txtOppLife, txtSelfLife, txtSpeed, txtDamage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        //Set Life Variables
        oppLife = 25;
        selfLife = 25;
        atkSpeed = 4;
        atkDamage = 4;
        setContentView(R.layout.activity_main);

        txtOppLife = findViewById(R.id.txtOppLife);
        txtOppLife.setText(Integer.toString(oppLife));
        txtSelfLife = findViewById(R.id.txtSelfLife);
        txtSelfLife.setText(Integer.toString(selfLife));
    }

    public void btnAttackOnPress(View view) {
        Log.d(TAG, "btnAttackOnPress: PRESSED");
        setContentView(R.layout.attack_calc);

        txtSpeed = findViewById(R.id.txtAtkSpeed);
        txtSpeed.setText(Integer.toString(atkSpeed));
        txtDamage = findViewById(R.id.txtAtkDamage);
        txtDamage.setText(Integer.toString(atkDamage));
    }

    public void btnSendAttackOnPress(View view) {
        setContentView(R.layout.activity_main);
        txtOppLife = findViewById(R.id.txtOppLife);
        txtOppLife.setText(Integer.toString(oppLife));
        txtSelfLife = findViewById(R.id.txtSelfLife);
        txtSelfLife.setText(Integer.toString(selfLife));
    }

    public void btnOppLifePlus(View view){
        txtOppLife.setText(Integer.toString(++oppLife));
    }
    public void btnOppLifeMinus(View view){
        txtOppLife.setText(Integer.toString(--oppLife));

    }
    public void btnSelfLifePlus(View view){
        txtSelfLife.setText(Integer.toString(++selfLife));
    }
    public void btnSelfLifeMinus(View view){
        txtSelfLife.setText(Integer.toString(--selfLife));
    }
    public void btnSpeedPlus(View view){
        txtSpeed.setText(Integer.toString(++atkSpeed));
    }
    public void btnSpeedMinus(View view){
        txtSpeed.setText(Integer.toString(--atkSpeed));
    }
    public void btnDmgPlus(View view){
        txtDamage.setText(Integer.toString(++atkDamage));
    }
    public void btnDmgMinus(View view){
        txtDamage.setText(Integer.toString(--atkDamage));
    }

    public void btnAttackOpp(View view){

    }

    public void btnAttackSelf(View view){

    }


}