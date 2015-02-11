package com.example.bogdan.modernartui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;


import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;


import static android.graphics.Color.alpha;
import static android.graphics.Color.argb;
import static android.graphics.Color.blue;
import static android.graphics.Color.green;
import static android.graphics.Color.red;


public class MainActivity extends ActionBarActivity {

    private LinearLayout mBackgraoundLayout;
    private FrameLayout fLayoutLeftUp, fLayoutLeftDown, fLayoutRightUp, fLayoutRightCetre, fLayoutRightDown;
    SeekBar seekBar;
    AlertDialog.Builder ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBackgraoundLayout = (LinearLayout)findViewById(R.id.backgroundLayout);
        mBackgraoundLayout.setBackgroundColor(Color.BLACK);
        fLayoutLeftUp = (FrameLayout)findViewById(R.id.fLayout1_1);
        fLayoutLeftUp.setBackgroundColor(Color.CYAN);
        fLayoutLeftDown = (FrameLayout)findViewById(R.id.fLayout1_2);
        fLayoutLeftDown.setBackgroundColor(Color.MAGENTA);
        fLayoutRightUp = (FrameLayout)findViewById(R.id.fLayout2_1);
        fLayoutRightUp.setBackgroundColor(Color.RED);
        fLayoutRightCetre = (FrameLayout)findViewById(R.id.fLayout2_2);
        fLayoutRightCetre.setBackgroundColor(Color.GRAY);
        fLayoutRightDown = (FrameLayout)findViewById(R.id.fLayout2_3);
        fLayoutRightDown.setBackgroundColor(Color.BLUE);

        seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        ad = new AlertDialog.Builder(MainActivity.this);
        /*ad.setTitle("Inspired by the works of artists such as Piet Mondrian and Ben Nicholson.");*/
        /*ad.setMessage("Inspired by the works of artists such as Piet Mondrian and Ben Nicholson.\n\n"
                + "Click below to learn more!");*/
        TextView msg = new TextView(this);
        msg.setGravity(Gravity.CENTER);
        msg.setTextColor(Color.WHITE);
        msg.setText("Inspired by the works of artists such as Piet Mondrian and Ben Nicholson.\n\n"
                + "Click below to learn more!");

        ad.setView(msg);
        ad.setNegativeButton("Visit MOMA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org"));
                startActivity(intent);

            }
        });
        ad.setPositiveButton("Not Now", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

    }
    private SeekBar.OnSeekBarChangeListener seekBarChangeListener =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {     
                    updateColors();

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };

    private void updateColors() {
        int seekB = seekBar.getProgress();

        fLayoutLeftUp.setBackgroundColor(argb(alpha(Color.CYAN), red(Color.CYAN),
                green(Color.CYAN), blue(Color.CYAN)));

        fLayoutLeftDown.setBackgroundColor(Color.MAGENTA);

        fLayoutRightUp.setBackgroundColor(Color.RED + seekB);

        fLayoutRightCetre.setBackgroundColor(Color.GRAY + seekB);

        fLayoutRightDown.setBackgroundColor(Color.BLUE + seekB);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            ad.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
