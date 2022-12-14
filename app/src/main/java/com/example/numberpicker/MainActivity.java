package com.example.numberpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textButtonT1,textButtonT2,textButtonT3,textButtonNumberBullet,numberOfSeries,textButtonSmartTime,timer;
    View.OnClickListener Close, Save,customSave;
    Dialog dialog;
    int mode=0;
    float  textFt1,textSt1,textFt2,textSt2,textFt3,textSt3;
    int seriesCount,shotInSeries,bulletCount,minValueF,maxValueF,minValueS,maxValueS;
    String[] textSplit,textSaveValue,displayValueF, displayValueS;             //F = first, S = second
    TextView idTextView;
    NumberPicker firstNumberPicker,secondNumberPicker;
    Button buttonCancel,buttonSave;
    String[] massForNumberBullet,mass10To180,mass10To60,mass2To30,mass1To10,mass30To300,customMass01To5forT2,
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
        checkOnOffSmartMode();
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
        customSave = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idTextView.setText(String.valueOf(firstNumberPicker.getDisplayedValues()[firstNumberPicker.getValue()-firstNumberPicker.getMinValue()]));
                dialog.dismiss();
                setSettingsSeriesNumberPicker();
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
                setSettingsForNumberPicker(idTextView);
                setNumberPicker(firstNumberPicker,minValueF,maxValueF,displayValueF);
                setNumberPickerPosition(getPositionForNumberPicker(displayValueF,textSplit[0],firstNumberPicker),firstNumberPicker);
            }
        });

        textButtonT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idTextView = textButtonT1;


                if (mode==1||mode==2) {
                    openDialogOneNumberPicker();
                    setSettingsForNumberPicker(idTextView);
                    textSplit = String.valueOf(textButtonT1.getText()).split(" ");
                    setNumberPicker(firstNumberPicker, minValueF, maxValueF, displayValueF);
                    setNumberPickerPosition(getPositionForNumberPicker(displayValueF, textSplit[0], firstNumberPicker), firstNumberPicker);

                }else {
                    openDialogTwoNumberPicker();
                    setSettingsForNumberPicker(idTextView);
                    textSplit = String.valueOf(textButtonT1.getText()).split("/");
                    setNumberPicker(firstNumberPicker, minValueF, maxValueF, displayValueF);
                    setNumberPickerPosition(getPositionForNumberPicker(displayValueF, textSplit[0], firstNumberPicker), firstNumberPicker);
                    setNumberPicker(secondNumberPicker, minValueS, maxValueS, displayValueS);
                    setNumberPickerPosition(getPositionForNumberPicker(displayValueS, textSplit[1], secondNumberPicker), secondNumberPicker);

                }

            }
        });

        textButtonT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idTextView = textButtonT2;
                textSplit = String.valueOf(textButtonT2.getText()).split("/");
                openDialogTwoNumberPicker();
                setSettingsForNumberPicker(idTextView);
                setNumberPicker(firstNumberPicker, minValueF, maxValueF, displayValueF);
                setNumberPickerPosition(getPositionForNumberPicker(displayValueF, textSplit[0], firstNumberPicker), firstNumberPicker);
                setNumberPicker(secondNumberPicker, minValueS, maxValueS, displayValueS);
                setNumberPickerPosition(getPositionForNumberPicker(displayValueS, textSplit[1], secondNumberPicker), secondNumberPicker);
            }
        });

        textButtonT3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idTextView=textButtonT3;
                textSplit = String.valueOf(textButtonT3.getText()).split("/");
                openDialogTwoNumberPicker();
                setSettingsForNumberPicker(idTextView);
                setNumberPicker(firstNumberPicker, minValueF, maxValueF, displayValueF);
                setNumberPickerPosition(getPositionForNumberPicker(displayValueF, textSplit[0], firstNumberPicker), firstNumberPicker);
                setNumberPicker(secondNumberPicker, minValueS, maxValueS, displayValueS);
                setNumberPickerPosition(getPositionForNumberPicker(displayValueS, textSplit[1], secondNumberPicker), secondNumberPicker);
            }
        });

        textButtonSmartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idTextView=textButtonSmartTime;
                openDialogOneNumberPicker();
                setSettingsForNumberPicker(idTextView);
                textSplit = String.valueOf(textButtonSmartTime.getText()).split(" ");
                setNumberPicker(firstNumberPicker, minValueF, maxValueF, displayValueF);
                setNumberPickerPosition(getPositionForNumberPicker(displayValueF, textSplit[0], firstNumberPicker), firstNumberPicker);
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

    @SuppressLint("SuspiciousIndentation")
    private void openDialogOneNumberPicker(){
        dialog.setContentView(R.layout.one_number_picker);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        firstNumberPicker=dialog.findViewById(R.id.oneNumberPicker);
        buttonCancel=dialog.findViewById(R.id.oneCancelButton);
        buttonSave=dialog.findViewById(R.id.oneSaveButton);
        buttonCancel.setOnClickListener(Close);
        if (idTextView==textButtonNumberBullet) {
            buttonSave.setOnClickListener(customSave);
        }
        else
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

    private void setSettingsSeriesNumberPicker(){
        switch (String.valueOf(textButtonNumberBullet.getText())){
            case "60":
                numberOfSeries.setText("1??60");
                break;

            case "50":
                numberOfSeries.setText("1??50");
                break;
            case "40":
                numberOfSeries.setText("1??40");
                break;
            case "30":
                numberOfSeries.setText("1??30");
                break;
            case "20":
                numberOfSeries.setText("1??20");
                break;
            case "10":
                numberOfSeries.setText("1??10");
                break;
        }

    }

    private void setTimerTime(int mode){
        bulletCount = Integer.valueOf(String.valueOf(textButtonNumberBullet.getText()));
        String[] splitText  = String.valueOf(numberOfSeries.getText()).split("??");
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


        if (hours>0&&minute==0)          //?????????? ???????????? ?????????? ???????? ???????????? = 0
        timer.setText( String.format("%dh",hours));
        else if (hours==0&&seconds==0)              //?????????? ???????????? ?????????? ???????? ???????? ?? ?????????????? = 0
            timer.setText( String.format("%dm",minute));
        else if (hours==0&&minute==0)               //?????????? ???????????? ???????????? ???????? ???????? ?? ???????????? = 0
            timer.setText( String.format("%ds",seconds));
        else if (hours==0)
            timer.setText( String.format("%dm %ds",minute,seconds));
        else if (hours>0)
            timer.setText( String.format("%dh %dm",hours,minute));
    }

    private void setSettingsForNumberPicker(TextView idTextView){
        if (getResources().getResourceEntryName(idTextView.getId()).equals("numberOfSeries")) {
            switch (String.valueOf(textButtonNumberBullet.getText())) {

                case "60":
                    minValueF=0;
                    maxValueF=7;
                    displayValueF = massSeriesFor60Bullet;
                    break;

                case "50":
                    minValueF=0;
                    maxValueF=4;
                    displayValueF = massSeriesFor50Bullet;
                    break;
                case "40":
                    minValueF=0;
                    maxValueF=4;
                    displayValueF = massSeriesFor40Bullet;
                    break;
                case "30":
                    minValueF=0;
                    maxValueF=5;
                    displayValueF = massSeriesFor30Bullet;
                    break;
                case "20":
                    minValueF=0;
                    maxValueF=3;
                    displayValueF = massSeriesFor20Bullet;
                    break;
                case "10":
                    minValueF=0;
                    maxValueF=2;
                    displayValueF = massSeriesFor10Bullet;
                    break;

            }
        }

        else if (mode==0||mode==3||mode==4){
            switch (getResources().getResourceEntryName(idTextView.getId())){
                case "textButtonT1":
                        minValueF = 0;
                        maxValueF =11;
                        displayValueF= mass10To180;

                        minValueS =0;
                        maxValueS =10 ;
                        displayValueS = mass10To60;

                    break;
                case "textButtonT2":
                    minValueF = 0;
                    maxValueF =28;
                    displayValueF= mass2To30;

                    minValueS =0;
                    maxValueS =28 ;
                    displayValueS = mass2To30;
                break;

                case "textButtonT3":
                    minValueF = 0;
                    maxValueF =18;
                    displayValueF= mass2To30;

                    minValueS =0;
                    maxValueS =18;
                    displayValueS = mass2To30;
                    break;

                case "textButtonSmartTime":
                        if (mode==3) {
                            minValueF = 0;
                            maxValueF = 17;
                            displayValueF = mass1To60;

                        }
                        else if (mode==4){
                            minValueF = 0;
                            maxValueF = 7;
                            displayValueF = mass1To60;
                        }
                          break;
            }
        }
        else if (mode==1||mode==2){
            switch (getResources().getResourceEntryName(idTextView.getId())){
                case "textButtonT1":
                    minValueF = 0;
                    maxValueF =9;
                    displayValueF= mass30To300;

                    break;
                case "textButtonT2":
                    if (mode==1){
                    minValueF = 0;
                    maxValueF =28;
                    displayValueF= mass2To30;

                    minValueS =0;
                    maxValueS =28 ;
                    displayValueS = mass2To30;
                    }
                    else if (mode==2){
                        minValueF = 0;
                        maxValueF =28;
                        displayValueF= mass2To30;

                        minValueS =0;
                        maxValueS =15 ;
                        displayValueS = customMass01To5forT2;
                    }
                    break;

                case "textButtonT3":
                    if (mode==1) {
                        minValueF = 0;
                        maxValueF = 9;
                        displayValueF = mass1To10;

                        minValueS = 0;
                        maxValueS = 9;
                        displayValueS = mass1To10;
                    }
                    else if (mode==2){
                        minValueF = 0;
                        maxValueF =9;
                        displayValueF= mass1To10;

                        minValueS =0;
                        maxValueS =23 ;
                        displayValueS = customMass01To5forT2;
                    }
                    break;
            }
        }
    }

    private void fillingArrays(){

      massForNumberBullet = new String[6];
        for (int i=0;i<massForNumberBullet.length;i++){
            massForNumberBullet[i] = String.valueOf(60-i*10);
        }
        massSeriesFor60Bullet = new String[]{"1??60","2??30","3??20","4??15","6??10","12??5","20??3","30??2"};
        massSeriesFor50Bullet = new String[]{"1??50","2??25","5??10","10??5","25??2"};
        massSeriesFor40Bullet = new String[]{"1??40","2??20","4??10","8??5","20??2"};
        massSeriesFor30Bullet = new String[]{"1??30","2??15","3??10","6??5","10??3","15??2"};
        massSeriesFor20Bullet = new String[]{"1??20","2??10","4??5","10??2"};
        massSeriesFor10Bullet = new String[]{"1??10","2??5","5??2"};


         mass10To180 = new String[12];
         createArray(mass10To180,10,90,10,0,8);
         createArray(mass10To180,120,180,30,9,12);

         mass10To60  = new String[11];
         createArray(mass10To60,10,60,5,0,11);

         mass2To30 = new String[29];
         createArray(mass2To30,2,30,1,0,29);

         mass1To10 = new String[10];
         createArray(mass1To10,1,10,1,0,10);

         mass30To300 = new String[10];
         createArray(mass30To300,30,300,30,0,10);

         customMass01To5forT2 =  new String[16];
         createArray(customMass01To5forT2,0.1f,0.6f,0.1f,0,6);
         createArray(customMass01To5forT2,0.8f,1.2f,0.2f,6,9);
         customMass01To5forT2[9] = "1.5";
         customMass01To5forT2[10] = "1.7";
         customMass01To5forT2[11] = "2.0";
         customMass01To5forT2[12] = "2.5";
         createArray(customMass01To5forT2,3.0f,5.0f,1.0f,13,16);


         customMass05To10ForT3 = new String[24];
         createArray(customMass05To10ForT3,0.5f,1.6f,0.1f,0,11);
         createArray(customMass05To10ForT3,1.8f,2.2f,0.2f,11,14);
         createArray(customMass05To10ForT3,2.5f,4.0f,0.5f,14,18);
         createArray(customMass05To10ForT3,5.0f,10.0f,1.0f,18,24);

        mass1To60 = new String[18];
        createArray(mass1To60,1,10,1,0,10);
        createArray(mass1To60,15,40,5,10,16);
        createArray(mass1To60,50,60,10,16,18);


    }
    private void checkOnOffSmartMode(){
        if (mode == 0||mode==1||mode==2)
            textButtonSmartTime.setEnabled(false);

        else
            textButtonSmartTime.setEnabled(true);

    }
}



