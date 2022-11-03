package com.example.numberpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textButtonT1,textButtonT2,textButtonT3,textButtonNumberBullet,numberOfSeries,textButtonSmartTime;
    View.OnClickListener Close, Save;
    Dialog dialog;
    String[] textSplit,textSaveValue;
    TextView idTextView;
    NumberPicker firstNumberPicker,secondNumberPicker;
    Button buttonCancel,buttonSave;
    String[] mass,massForNumberBullet,mass10To180,mass10To60,mass2To30,mass1To10,mass30To300,customMass05To5forT2,customMass05To10ForT3,mass1To60,massSeriesFor60Bullet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textButtonT1=findViewById(R.id.textButtonT1);
        textButtonT2=findViewById(R.id.textButtonT2);
        textButtonT3=findViewById(R.id.textButtonT3);
        textButtonNumberBullet = findViewById(R.id.textButtonNumberBullet);
        numberOfSeries=findViewById(R.id.numberOfSeries);
        textButtonSmartTime = findViewById(R.id.textButtonSmartTime);
        dialog= new Dialog(MainActivity.this);
        mass = new String[]{"1","3","10","20","30","15"};
        massSeriesFor60Bullet = new String[]{"1*60","2*30","3*20","4*15","6*10","12*5","20*3","30*2"};
        fillingArrays();
        textSaveValue = new String[]{"0","0"};
        Close = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        };
        Save = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(secondNumberPicker != null){

                  idTextView.setText(String.valueOf(firstNumberPicker.getDisplayedValues()[firstNumberPicker.getValue()])+"/"+String.valueOf(secondNumberPicker.getDisplayedValues()[secondNumberPicker.getValue()]));

                }else {
                  idTextView.setText(String.valueOf(firstNumberPicker.getDisplayedValues()[firstNumberPicker.getValue()]));
              }
                dialog.dismiss();
                secondNumberPicker=null;
                firstNumberPicker=null;
            }
        };


        textButtonNumberBullet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idTextView=textButtonNumberBullet;
                textSplit = String.valueOf(textButtonNumberBullet.getText()).split("/");
                openDialogOneNumberPicker();
                setNumberPicker(firstNumberPicker,0,5,getPositionForNumberPicker(mass,textSplit[0]),mass);
            }
        });

        numberOfSeries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idTextView=numberOfSeries;
                textSplit = String.valueOf(numberOfSeries.getText()).split("/");
                openDialogOneNumberPicker();
                setNumberPicker(firstNumberPicker,3,7,getPositionForNumberPicker(massSeriesFor60Bullet,textSplit[0]),massSeriesFor60Bullet);
            }
        });

        textButtonT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idTextView = textButtonT1;

                textSplit = String.valueOf(textButtonT1.getText()).split("/");
                openDialogTwoNumberPicker();
                setNumberPicker(firstNumberPicker,0,4,getPositionForNumberPicker(mass,textSplit[0]),mass);
                setNumberPicker(secondNumberPicker,0,4,getPositionForNumberPicker(mass,textSplit[1]),mass);


            }
        });

        textButtonT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idTextView = textButtonT2;
                textSplit = String.valueOf(textButtonT2.getText()).split("/");
                openDialogTwoNumberPicker();
                setNumberPicker(firstNumberPicker,0,4,getPositionForNumberPicker(mass,textSplit[0]),mass);
                setNumberPicker(secondNumberPicker,0,4,getPositionForNumberPicker(mass,textSplit[1]),mass);
            }
        });

        textButtonT3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idTextView=textButtonT3;
                textSplit = String.valueOf(textButtonT3.getText()).split("/");
                openDialogTwoNumberPicker();
                setNumberPicker(firstNumberPicker,0,4,getPositionForNumberPicker(mass,textSplit[0]),mass);
                setNumberPicker(secondNumberPicker,0,4,getPositionForNumberPicker(mass,textSplit[1]),mass);
            }
        });

        textButtonSmartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idTextView=textButtonSmartTime;
                textSplit = String.valueOf(textButtonSmartTime.getText()).split("");
                openDialogOneNumberPicker();
                setNumberPicker(firstNumberPicker,0,5,getPositionForNumberPicker(mass,textSplit[0]),mass);
            }
        });
    }

    private void openDialogTwoNumberPicker() {
        dialog.setContentView(R.layout.two_number_pickers);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        firstNumberPicker=dialog.findViewById(R.id.firstNumberPicker);
        secondNumberPicker=dialog.findViewById(R.id.secondNumberPicker);
        buttonCancel=dialog.findViewById(R.id.cancelButton);
        buttonSave=dialog.findViewById(R.id.saveButton);
        buttonCancel.setOnClickListener(Close);
        buttonSave.setOnClickListener(Save);
        dialog.show();
    }

    private void openDialogOneNumberPicker(){
        dialog.setContentView(R.layout.one_number_picker);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        firstNumberPicker=dialog.findViewById(R.id.oneNumberPicker);
        buttonCancel=dialog.findViewById(R.id.oneCancelButton);
        buttonSave=dialog.findViewById(R.id.oneSaveButton);
        buttonCancel.setOnClickListener(Close);
        buttonSave.setOnClickListener(Save);
        dialog.show();
    }

    private void setNumberPicker(NumberPicker numberPicker, int minValue, int maxValue,int position,String[] mass){
        numberPicker.setMinValue(minValue);
        numberPicker.setMaxValue(maxValue);
        numberPicker.setValue(position);
        numberPicker.setDisplayedValues(mass);
    }


    private int getPositionForNumberPicker(String[] mass,String text){
        for (int i=0;i<mass.length;i++){
            if (text.equals(mass[i])){
                return i;
            }
        }
        return 0;
    }

    private void fillingArrays(){
        String[] massForNumberBullet = new String[6];
        for (int i=0;i<massForNumberBullet.length;i++){
            massForNumberBullet[i] = String.valueOf(60-i*10);
        }
    }
}