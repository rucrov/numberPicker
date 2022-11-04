package com.example.numberpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textButtonT1,textButtonT2,textButtonT3,textButtonNumberBullet,numberOfSeries,textButtonSmartTime,timer;
    View.OnClickListener Close, Save;
    Dialog dialog;
    int mode=0;
    float  textFt1,textSt1,textFt2,textSt2,textFt3,textSt3;
    int seriesCount,shotInSeries,bulletCount;
    String[] textSplit,textSaveValue;
    TextView idTextView;
    NumberPicker firstNumberPicker,secondNumberPicker;
    Button buttonCancel,buttonSave;
    String[] mass,massForNumberBullet,mass10To180,mass10To60,mass2To30,mass1To10,mass30To300,customMass01To5forT2,
            customMass05To10ForT3,mass1To60,massSeriesFor60Bullet,massSeriesFor50Bullet,massSeriesFor40Bullet,massSeriesFor30Bullet,
            massSeriesFor20Bullet,massSeriesFor10Bullet;
    @SuppressLint("MissingInflatedId")
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
        timer= findViewById(R.id.timer);
        dialog= new Dialog(MainActivity.this);
        mass = new String[]{"1","3","10","20","30","15"};
        setTimerTime(mode);
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

                  idTextView.setText(String.valueOf(firstNumberPicker.getDisplayedValues()[firstNumberPicker.getValue()-firstNumberPicker.getMinValue()])+"/"+String.valueOf(secondNumberPicker.getDisplayedValues()[secondNumberPicker.getValue()-secondNumberPicker.getMinValue()]));

                }else {
                  idTextView.setText(String.valueOf(firstNumberPicker.getDisplayedValues()[firstNumberPicker.getValue()-firstNumberPicker.getMinValue()]));
              }

                dialog.dismiss();
                secondNumberPicker=null;
                firstNumberPicker=null;
                setTimerTime(mode);
            }
        };


        textButtonNumberBullet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idTextView=textButtonNumberBullet;
                textSplit = String.valueOf(textButtonNumberBullet.getText()).split(" ");
                openDialogOneNumberPicker();
                setNumberPicker(firstNumberPicker,0,5,massForNumberBullet);
                setNumberPickerPosition(getPositionForNumberPicker(massForNumberBullet,textSplit[0],firstNumberPicker),firstNumberPicker);
            }
        });

        numberOfSeries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idTextView=numberOfSeries;
                textSplit = String.valueOf(numberOfSeries.getText()).split(" ");
                openDialogOneNumberPicker();
                setNumberPicker(firstNumberPicker,0,6,massSeriesFor60Bullet);
                setNumberPickerPosition(getPositionForNumberPicker(massSeriesFor60Bullet,textSplit[0],firstNumberPicker),firstNumberPicker);
            }
        });

        textButtonT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idTextView = textButtonT1;

                textSplit = String.valueOf(textButtonT1.getText()).split("/");
                openDialogTwoNumberPicker();
                setNumberPicker(firstNumberPicker,0,5,mass);
                setNumberPickerPosition(getPositionForNumberPicker(mass,textSplit[0],firstNumberPicker),firstNumberPicker);
                setNumberPicker(secondNumberPicker,0,5,mass);
                setNumberPickerPosition(getPositionForNumberPicker(mass,textSplit[1],secondNumberPicker),secondNumberPicker);


            }
        });

        textButtonT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idTextView = textButtonT2;
                textSplit = String.valueOf(textButtonT2.getText()).split("/");
                openDialogTwoNumberPicker();
                setNumberPicker(firstNumberPicker,0,5,mass);
                setNumberPickerPosition(getPositionForNumberPicker(mass,textSplit[0],firstNumberPicker),firstNumberPicker);
                setNumberPicker(secondNumberPicker,0,5,mass);
                setNumberPickerPosition(getPositionForNumberPicker(mass,textSplit[1],secondNumberPicker),secondNumberPicker);
            }
        });

        textButtonT3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idTextView=textButtonT3;
                textSplit = String.valueOf(textButtonT3.getText()).split("/");
                openDialogTwoNumberPicker();
                setNumberPicker(firstNumberPicker,0,5,mass);
                setNumberPickerPosition(getPositionForNumberPicker(mass,textSplit[0],firstNumberPicker),firstNumberPicker);
                setNumberPicker(secondNumberPicker,0,5,mass);
                setNumberPickerPosition(getPositionForNumberPicker(mass,textSplit[1],secondNumberPicker),secondNumberPicker);
            }
        });

        textButtonSmartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idTextView=textButtonSmartTime;
                textSplit = String.valueOf(textButtonSmartTime.getText()).split(" ");
                openDialogOneNumberPicker();
                setNumberPicker(firstNumberPicker,0,7,mass1To10);
                setNumberPickerPosition(getPositionForNumberPicker(mass1To10,textSplit[0],firstNumberPicker),firstNumberPicker);
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

    private void setNumberPicker(NumberPicker numberPicker, int minValue, int maxValue,String[] mass){
        numberPicker.setMinValue(minValue);
        numberPicker.setMaxValue(maxValue);
        numberPicker.setDisplayedValues(mass);
    }
    private void setNumberPickerPosition(int position,NumberPicker numberPicker){
        numberPicker.setValue(position);
    }

    private  void createArray(String[] array, int firstElem, int lastElem,
                              int inc, int fistNumber, int arraySize){

        for(int i=fistNumber;i<array.length;i++){
            if(firstElem>lastElem || i > arraySize ){
                break;
            }
            array[i]= String.valueOf(firstElem);
            firstElem+=inc;

        }
    }

    private  void createArray(String[] array, float firstElem, float lastElem,
                                   float inc, int fistNumber, int arraySize){

        for(int i=fistNumber;i<array.length;i++){
            if(Math.floor(firstElem * 10) / 10>lastElem || i > arraySize ){
                break;
            }
            array[i]= String.valueOf(Math.floor(firstElem * 10) / 10);
            firstElem+=inc;

        }
    }
    private int getPositionForNumberPicker(String[] mass,String text,NumberPicker numberPicker){
        for (int i=0;i<mass.length;i++){
            if (text.equals(mass[i])){
                return i+numberPicker.getMinValue();
            }
        }
        return 0;
    }

    private void setTimerTime(int mode){
        bulletCount = Integer.valueOf(String.valueOf(textButtonNumberBullet.getText()));
        String[] splitText  = String.valueOf(numberOfSeries.getText()).split("×");
            seriesCount = Integer.valueOf(splitText[0]);
            shotInSeries = Integer.valueOf(splitText[1]);

        splitText = String.valueOf(textButtonT1.getText()).split("/");
        if (mode==1||mode==2) {
            textFt1 = Integer.valueOf(splitText[0]);
            textSt1 = 0;
        } else {
            textFt1 = Integer.valueOf(splitText[0]);
            textSt1 = Integer.valueOf(splitText[1]);
        }

        splitText = String.valueOf(textButtonT2.getText()).split("/");
        textFt2 = Float.valueOf(splitText[0]);
        textSt2 = Float.valueOf(splitText[1]);

        splitText = String.valueOf(textButtonT3.getText()).split("/");
        textFt3 = Float.valueOf(splitText[0]);
        textSt3 = Float.valueOf(splitText[1]);

        float time = (textFt1+textFt2+textFt3+(textSt1+textSt2+textSt3)*(shotInSeries-1))*seriesCount;
        int hours = (int) ((time / (60 * 60)) % 24);
        int minute = (int) ((time / 60) % 60);
        int seconds = (int) time / 1 % 60;


        if (hours>0&&minute==0)          //вывод только часов если минуты = 0
        timer.setText( String.format("%dh",hours));
        else if (hours==0&&seconds==0)              //вывод только минут если часы и секунды = 0
            timer.setText( String.format("%dm",minute));
        else if (hours==0&&minute==0)               //вывод только секунд если часы и минуты = 0
            timer.setText( String.format("%ds",seconds));
        else if (hours==0)
            timer.setText( String.format("%dm %ds",minute,seconds));
        else if (hours>0)
            timer.setText( String.format("%dh %dm",hours,minute));
    }

    private void fillingArrays(){
      massForNumberBullet = new String[6];
        for (int i=0;i<massForNumberBullet.length;i++){
            massForNumberBullet[i] = String.valueOf(60-i*10);
        }
        massSeriesFor60Bullet = new String[]{"1×60","2×30","3×20","4×15","6×10","12×5","20×3","30×2"};
        massSeriesFor50Bullet = new String[]{"1×50","2×25","5×10","10×5","25×2"};
        massSeriesFor40Bullet = new String[]{"1×40","2×20","4×10","8×5","20×2"};
        massSeriesFor30Bullet = new String[]{"1×30","2×15","3×10","6×5","10×3","15×2"};
        massSeriesFor20Bullet = new String[]{"1×20","2×10","4×5","10×2"};
        massSeriesFor10Bullet = new String[]{"1×10","2×5","5×2"};


         mass10To180 = new String[12];
         createArray(mass10To180,10,90,10,0,8);
         createArray(mass10To180,120,180,30,9,12);

         mass10To60  = new String[20];
         createArray(mass10To60,1,9,1,0,8);
         createArray(mass10To60,10,60,5,9,20);

         mass2To30 = new String[29];
         createArray(mass2To30,2,30,1,0,29);

         mass1To10 = new String[10];
         createArray(mass1To10,1,10,1,0,10);

         mass30To300 = new String[19];
         createArray(mass30To300,30,300,30,0,10);

         customMass01To5forT2 =  new String[19];
         createArray(customMass01To5forT2,0.1f,0.6f,0.1f,0,7);
         createArray(customMass01To5forT2,0.8f,1.2f,0.2f,8,11);
         customMass01To5forT2[12] = "1.5";
         customMass01To5forT2[13] = "1.7";
         customMass01To5forT2[14] = "2.0";
         customMass01To5forT2[15] = "2.5";
         createArray(customMass01To5forT2,3.0f,5.0f,1.0f,16,19);


         customMass05To10ForT3 = new String[27];
         createArray(customMass05To10ForT3,0.5f,1.6f,0.1f,0,11);
         createArray(customMass05To10ForT3,1.8f,2.2f,0.2f,12,14);
         createArray(customMass05To10ForT3,2.5f,4.0f,0.5f,15,19);
         createArray(customMass05To10ForT3,5.0f,10.0f,1.0f,20,25);

        mass1To60 = new String[19];
        createArray(mass1To60,1,10,1,0,10);
        createArray(mass1To60,15,40,5,11,16);
        createArray(mass1To60,50,60,10,17,18);


    }
}