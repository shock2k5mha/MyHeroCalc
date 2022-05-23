package com.example.myherocalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    public int startOppLife, startSelfLife, oppLife, selfLife, atkSpeed, atkDamage, target, zone, diesRolling;
    public TextView txtOppLife, txtSelfLife, txtSpeed, txtDamage, txtSettOppLife, txtSettSelfLife;
    public Button btnOppLife, btnSelfLife;
    public ImageView imgSpeed;
    public ImageButton btnReset, btnDieRoll;
    public PocketTimer timer;
    public String lifeSlip;
    public Random rng;

    public class PocketTimer implements Runnable {


        public class PrintingTask extends TimerTask
        {
            public void run()
            {

            }
        }
        @Override
        public void run() {

        }
    }

    public void setMainViewButtons(){
        txtOppLife = findViewById(R.id.txtOppLife);
        txtOppLife.setText(Integer.toString(oppLife));
        txtSelfLife = findViewById(R.id.txtSelfLife);
        txtSelfLife.setText(Integer.toString(selfLife));

        btnReset = findViewById(R.id.btnResetLife);
        btnDieRoll = findViewById(R.id.btnDieRoll);
        diesRolling = 0;

        btnReset.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                oppLife = startOppLife;
                txtOppLife.setText(Integer.toString(oppLife));
                selfLife = startSelfLife;
                txtSelfLife.setText(Integer.toString(selfLife));
                btnDieRoll.setImageResource(R.mipmap.die_icon_foreground);
                return true;
            }
        });

        zone = 1;
        atkSpeed = 4;
        atkDamage = 4;
        target = 0;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set Life Variables
        startOppLife = 25;
        oppLife = 25;
        startSelfLife = 25;
        selfLife = 25;
        atkSpeed = 4;
        atkDamage = 4;
        target = 0;
        zone = 2;
        rng = new Random();
        rng.setSeed(System.currentTimeMillis());
        setContentView(R.layout.activity_main);

        setMainViewButtons();
    }

    public void btnAttackOnPress(View view) {

        setContentView(R.layout.attack_calc);

        txtSpeed = findViewById(R.id.txtAtkSpeed);
        txtSpeed.setText(Integer.toString(atkSpeed));
        txtDamage = findViewById(R.id.txtAtkDamage);
        txtDamage.setText(Integer.toString(atkDamage));

        btnOppLife = findViewById(R.id.btnAtkOpp);
        btnOppLife.setText("OppLife\n" + oppLife);
        btnSelfLife = findViewById(R.id.btnAtkSelf);
        btnSelfLife.setText("My Life\n" + selfLife);
        imgSpeed = findViewById(R.id.imgSpeed);
        renderAttackZoneImage();
    }

    public void btnAttackOpponent(View view){
        btnAttackOnPress(view);
        btnAttackTargetOpp(view);
    }

    public void btnAttackSelf(View view){
        btnAttackOnPress(view);
        btnAttackTargetSelf(view);
    }

    public void btnUnblockedAttack(View view) {
        if(atkDamage < 0) return;
        if (target == 1) {
            //attack opp
            oppLife -= atkDamage;
        } else if (target == 2) {
            //attack self
            selfLife -= atkDamage;
        } else {
            Toast.makeText(getApplicationContext(), R.string.strSelectTargetPrompt,
                    Toast.LENGTH_SHORT)
                    .show();
            return;
        }


        setContentView(R.layout.activity_main);
        setMainViewButtons();
    }
    public void btnHalfBlockedAttack(View view) {
        if(atkDamage < 0) return;
        if (target == 1) {
            //attack opp
            oppLife -= atkDamage / 2;
            if(atkDamage % 2 == 1) oppLife--;
        } else if (target == 2) {
            //attack self
            selfLife -= atkDamage / 2;
            if(atkDamage % 2 == 1) selfLife--;
        } else {
            Toast.makeText(getApplicationContext(), R.string.strSelectTargetPrompt,
                    Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        setContentView(R.layout.activity_main);
       setMainViewButtons();
    }
    public void btnFullblockAttack(View view) {
        setContentView(R.layout.activity_main);
        setMainViewButtons();
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

    public void btnAttackTargetOpp(View view){
        target = 1;
        btnOppLife.setBackgroundResource(R.drawable.opp_life_selected_background);
        btnSelfLife.setBackgroundResource(R.drawable.life_unselected_background);
    }
    public void btnAttackTargetSelf(View view){
        target = 2;
        btnSelfLife.setBackgroundResource(R.drawable.my_life_selected_background);
        btnOppLife.setBackgroundResource(R.drawable.life_unselected_background);

    }

    public void btnChangeAttackZone(View view){
        ++zone;
        renderAttackZoneImage();
    }

    public void renderAttackZoneImage(){
        if(zone > 3) zone = 1;
        switch(zone){
            case 1:
                this.imgSpeed.setImageResource(R.mipmap.high_attack_icon_foreground);
                break;
            case 2:
                this.imgSpeed.setImageResource(R.mipmap.mid_attack_icon_foreground);
                break;
            case 3:
                this.imgSpeed.setImageResource(R.mipmap.low_attack_icon_foreground);
                break;
        }
    }

    public void btnResetOnClick(View view){
        Toast.makeText(getApplicationContext(), R.string.strHoldResetPrompt,
                Toast.LENGTH_SHORT)
                .show();
        return;
    }

    public void btnDieRollOnClick(View view){
        Log.d("Current Time from Die Roll: ", Long.toString(System.currentTimeMillis()));
        rng.setSeed(System.currentTimeMillis());
        diesRolling++;
        for (int i = 0; i < 10; i++) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    long temp;

                    while ((temp = rng.nextLong() % 6) < 0) {

                    }
                    Log.d("Die Roll:", Long.toString(temp + 1));
                    switch ((int) ++temp) {
                        case 1:
                            btnDieRoll.setImageResource(R.mipmap.die1_icon_foreground);
                            break;
                        case 2:
                            btnDieRoll.setImageResource(R.mipmap.die2_icon_foreground);
                            break;
                        case 3:
                            btnDieRoll.setImageResource(R.mipmap.die3_icon_foreground);
                            break;
                        case 4:
                            btnDieRoll.setImageResource(R.mipmap.die4_icon_foreground);
                            break;
                        case 5:
                            btnDieRoll.setImageResource(R.mipmap.die5_icon_foreground);
                            break;
                        case 6:
                            btnDieRoll.setImageResource(R.mipmap.die6_icon_foreground);
                            break;
                    }
                }
            }, 75 * i);

        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // yourMethod();
                if(--diesRolling == 0) btnDieRoll.setImageResource(R.mipmap.die_icon_foreground);
            }
        }, 10000);
    }

    public void btnSettingsOnClick(View view){
        setContentView(R.layout.settings);
        Log.d("Clicked Element", getResources().getResourceEntryName(view.getId()));
        Log.d("Starting Self Life: ", Integer.toString(this.startSelfLife));

        txtSettSelfLife = findViewById(R.id.inputSettStartSelfLife);
        txtSettSelfLife.setText(Integer.toString(this.startSelfLife));
        txtSettOppLife = findViewById(R.id.inputSettStartOppLife);
        txtSettOppLife.setText(Integer.toString(this.startOppLife));
    }

    public void btnSettingsReturnOnClick(View view){
        Log.d("Self Life ", txtSettSelfLife.getText().toString());
        this.startSelfLife = new Integer(txtSettSelfLife.getText().toString());
        this.startOppLife = new Integer( txtSettOppLife.getText().toString());
        setContentView(R.layout.activity_main);
        setMainViewButtons();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent){
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setContentView(R.layout.activity_main);
            setMainViewButtons();
            return true;
        }
        return super.onKeyDown(keyCode, keyEvent);
    }

    public void btnSettOppLifePlus(View view){
        Log.d("Opp Life", txtSettOppLife.getText().toString());
        txtSettOppLife.setText(String.valueOf(new Integer(txtSettOppLife.getText().toString()) + 1));
    }
    public void btnSettOppLifeMinus(View view){
        Log.d("Opp Life", txtSettOppLife.getText().toString());
        txtSettOppLife.setText(String.valueOf(new Integer(txtSettOppLife.getText().toString()) - 1));
    }
    public void btnSettSelfLifePlus(View view){
        Log.d("Self Life", txtSettSelfLife.getText().toString());
        txtSettSelfLife.setText(String.valueOf(new Integer(txtSettSelfLife.getText().toString()) + 1));
    }
    public void btnSettSelfLifeMinus(View view){
        Log.d("Self Life", txtSettSelfLife.getText().toString());
        txtSettSelfLife.setText(String.valueOf(new Integer(txtSettSelfLife.getText().toString()) - 1));
    }

}