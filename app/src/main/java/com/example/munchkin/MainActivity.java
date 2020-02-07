package com.example.munchkin;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;

public class MainActivity extends Activity {

    private ImageButton add1,add2,add3,remove1,remove2,remove3;
    private EditText name1,name2,name3;
    private TextView level1,level2,level3;
    private ImageButton sex1,sex2,sex3;
    /*private CheckBox keepScreenOnCB;
    private boolean keepScreenOn;*/

    private NotificationManager notificationManager;

    private final String FILENAME = "munchkin.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add1 = findViewById(R.id.add1);
        add2 = findViewById(R.id.add2);
        add3 = findViewById(R.id.add3);

        remove1 = findViewById(R.id.remove1);
        remove2 = findViewById(R.id.remove2);
        remove3 = findViewById(R.id.remove3);

        name1 = findViewById(R.id.name1);
        name2 = findViewById(R.id.name2);
        name3 = findViewById(R.id.name3);

        level1 = findViewById(R.id.level1);
        level2 = findViewById(R.id.level2);
        level3 = findViewById(R.id.level3);

        sex1 = findViewById(R.id.sex1);
        sex2 = findViewById(R.id.sex2);
        sex3 = findViewById(R.id.sex3);

        //keepScreenOnCB = findViewById(R.id.keepScreenOnCB);

        inizializza();

        //keepScreenOn = true;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        aggiornaFile();

        if(savedInstanceState!=null){
            name1.setText(savedInstanceState.getString("name1"));
            name2.setText(savedInstanceState.getString("name2"));
            name3.setText(savedInstanceState.getString("name3"));

            level1.setText(savedInstanceState.getString("level1"));
            level2.setText(savedInstanceState.getString("level2"));
            level3.setText(savedInstanceState.getString("level3"));

            if(savedInstanceState.getString("sex1").equals("M")){
                sex1.setBackgroundResource(R.mipmap.male_icon2);
            }else {
                sex1.setBackgroundResource(R.mipmap.female_icon2);
            }

            if(savedInstanceState.getString("sex2").equals("M")){
                sex2.setBackgroundResource(R.mipmap.male_icon2);
            }else {
                sex2.setBackgroundResource(R.mipmap.female_icon2);
            }

            if(savedInstanceState.getString("sex3").equals("M")){
                sex3.setBackgroundResource(R.mipmap.male_icon2);
            }else {
                sex3.setBackgroundResource(R.mipmap.female_icon2);
            }

            /*keepScreenOn = savedInstanceState.getBoolean("keepScreenOn");
            keepScreenOnCB.setChecked(keepScreenOn);
            if(keepScreenOn){
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }else{
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }*/
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("name1",name1.getText().toString());
        outState.putString("name2",name2.getText().toString());
        outState.putString("name3",name3.getText().toString());

        outState.putString("level1",level1.getText().toString());
        outState.putString("level2",level2.getText().toString());
        outState.putString("level3",level3.getText().toString());

        outState.putString("sex1",sex1.getTag().toString());
        outState.putString("sex2",sex2.getTag().toString());
        outState.putString("sex3",sex3.getTag().toString());

        //outState.putBoolean("keepScreenOn",keepScreenOn);
    }


    @Override
    protected void onResume() {
        super.onResume();
        caricaDaFile();
    }

    @Override
    protected void onPause() {
        super.onPause();
        aggiornaFile();
    }

    public void addLevel(View v){
        ImageButton b = (ImageButton)v;
        switch (b.getId()){
            case R.id.add1:
                if(Integer.parseInt(level1.getText().toString())<9)
                    level1.setText(Integer.parseInt(level1.getText().toString())+1+"");
                else
                    vittoria(1);
                break;
            case R.id.add2:
                if(Integer.parseInt(level2.getText().toString())<9)
                    level2.setText(Integer.parseInt(level2.getText().toString())+1+"");
                else
                    vittoria(2);
                break;
            case R.id.add3:
                if(Integer.parseInt(level3.getText().toString())<9)
                    level3.setText(Integer.parseInt(level3.getText().toString())+1+"");
                else
                    vittoria(3);
                break;
        }
        creaNotifica();
    }

    public void removeLevel(View v){
        ImageButton b = (ImageButton)v;
        switch (b.getId()){
            case R.id.remove1:
                if(Integer.parseInt(level1.getText().toString())>1)
                    level1.setText(Integer.parseInt(level1.getText().toString())-1+"");
                break;
            case R.id.remove2:
                if(Integer.parseInt(level2.getText().toString())>1)
                    level2.setText(Integer.parseInt(level2.getText().toString())-1+"");
                break;
            case R.id.remove3:
                if(Integer.parseInt(level3.getText().toString())>1)
                    level3.setText(Integer.parseInt(level3.getText().toString())-1+"");
                break;
        }
        creaNotifica();
    }

    public void changeSex(View v){
        ImageButton b = (ImageButton)v;

        if(b.getTag().toString().equals("M")){
            b.setBackgroundResource(R.mipmap.female_icon2);
            b.setTag("F");
        }else {
            b.setBackgroundResource(R.mipmap.male_icon2);
            b.setTag("M");
        }
        creaNotifica();
    }

    public void vittoria(int numVincitore){

        String vincitore = "";

        switch (numVincitore){
            case 1:
                vincitore = name1.getText().toString();
                break;
            case 2:
                vincitore = name2.getText().toString();
                break;
            case 3:
                vincitore = name3.getText().toString();
                break;
        }

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(vincitore + " ha vinto la partita");
        dialog.setPositiveButton(
                "Ricomincia",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        level1.setText("1");
                        level2.setText("1");
                        level3.setText("1");
                        sex1.setBackgroundResource(R.mipmap.male_icon2);
                        sex2.setBackgroundResource(R.mipmap.male_icon2);
                        sex3.setBackgroundResource(R.mipmap.male_icon2);
                        dialog.cancel();
                        creaNotifica();
                    }
                });

        dialog.setNegativeButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        dialog.show();
    }

    private void inizializza() {
        try {
            File f = new File(getFilesDir(), FILENAME);
            if (!f.exists()) {
                f.createNewFile();
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));

                bw.write("- 1 M");
                bw.newLine();
                bw.write("- 1 M");
                bw.newLine();
                bw.write("- 1 M");
                bw.newLine();
                bw.write(String.valueOf(true));

                bw.close();
            }

            caricaDaFile();
        }
        catch (Exception e) {}
    }

    private void caricaDaFile(){
        File f = new File(getFilesDir(), FILENAME);
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String temp;

            StringTokenizer tokenizer = new StringTokenizer(br.readLine(), " ");
            name1.setText(tokenizer.nextToken());
            level1.setText(tokenizer.nextToken());
            temp = tokenizer.nextToken();
            if (temp.equals("M")) {
                sex1.setBackgroundResource(R.mipmap.male_icon2);
                sex1.setTag("M");
            } else {
                sex1.setBackgroundResource(R.mipmap.female_icon2);
                sex1.setTag("F");
            }

            tokenizer = new StringTokenizer(br.readLine(), " ");
            name2.setText(tokenizer.nextToken());
            level2.setText(tokenizer.nextToken());
            temp = tokenizer.nextToken();
            if (temp.equals("M")) {
                sex2.setBackgroundResource(R.mipmap.male_icon2);
                sex2.setTag("M");
            } else {
                sex2.setBackgroundResource(R.mipmap.female_icon2);
                sex2.setTag("F");
            }

            tokenizer = new StringTokenizer(br.readLine(), " ");
            name3.setText(tokenizer.nextToken());
            level3.setText(tokenizer.nextToken());
            temp = tokenizer.nextToken();
            if (temp.equals("M")) {
                sex3.setBackgroundResource(R.mipmap.male_icon2);
                sex3.setTag("M");
            } else {
                sex3.setBackgroundResource(R.mipmap.female_icon2);
                sex3.setTag("F");
            }

            /*keepScreenOn = Boolean.valueOf(br.readLine());
            if(keepScreenOn){
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }else{
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }*/

            br.close();
        } catch (Exception e){}
    }

    private void aggiornaFile(){
        try {
            File f = new File(getFilesDir(), FILENAME);
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));

            bw.write(name1.getText().toString() + " " + level1.getText().toString() + " " + sex1.getTag().toString());
            bw.newLine();
            bw.write(name2.getText().toString() + " " + level2.getText().toString() + " " + sex2.getTag().toString());
            bw.newLine();
            bw.write(name3.getText().toString() + " " + level3.getText().toString() + " " + sex3.getTag().toString());
            /*bw.newLine();
            bw.write(String.valueOf(keepScreenOn));*/

            bw.close();
        }
        catch (Exception e) {}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_game, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.newGame:
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setMessage("Sei sicuro di voler ricominciare?");
                dialog.setPositiveButton(
                        "Si",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                level1.setText("1");
                                level2.setText("1");
                                level3.setText("1");
                                sex1.setTag("M");
                                sex1.setBackgroundResource(R.mipmap.male_icon2);
                                sex2.setTag("M");
                                sex2.setBackgroundResource(R.mipmap.male_icon2);
                                sex3.setTag("M");
                                sex3.setBackgroundResource(R.mipmap.male_icon2);
                                try {
                                    File f = new File(getFilesDir(), FILENAME);
                                    BufferedWriter bw = new BufferedWriter(new FileWriter(f));

                                    bw.write(name1.getText().toString() + " 1 M");
                                    bw.newLine();
                                    bw.write(name2.getText().toString() + " 1 M");
                                    bw.newLine();
                                    bw.write(name3.getText().toString() + " 1 M");

                                    bw.close();
                                }catch (Exception e){}

                                dialog.cancel();
                                creaNotifica();
                            }
                        });

                dialog.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                dialog.show();
                break;

            case R.id.createNotification:
                creaNotifica();
        }
        return true;
    }

    public void screenOnChanger(View v){
        /*keepScreenOn = !keepScreenOn;
        if(keepScreenOn){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }else{
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }*/
    }

    public void creaNotifica(){
        String contentText = "";

        contentText += name1.getText().toString() + ": " + level1.getText().toString();
        if(sex1.getTag().equals("M")){
            contentText += "M | ";
        }else{
            contentText += "F | ";
        }
        contentText += name2.getText().toString() + ": " + level2.getText().toString();
        if(sex2.getTag().equals("M")){
            contentText += "M | ";
        }else{
            contentText += "F | ";
        }
        contentText += name3.getText().toString() + ": " + level3.getText().toString();
        if(sex3.getTag().equals("M")){
            contentText += "M";
        }else{
            contentText += "F";
        }


        Notification.Builder notification  = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.munchkin_icon)
                .setContentTitle("Partita")
                .setContentText(contentText);

        Intent intentOnClick = new Intent(this,MainActivity.class);

        intentOnClick.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        TaskStackBuilder stackBuilderOnClick = TaskStackBuilder.create(getApplicationContext());
        stackBuilderOnClick.addNextIntentWithParentStack(intentOnClick);

        PendingIntent pendingIntentOnClick = stackBuilderOnClick.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        notification.setContentIntent(pendingIntentOnClick);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0,notification.build());
    }
}
