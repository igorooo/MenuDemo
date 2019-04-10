package com.example.menudemo;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    private ActionMode mActionMode = null;

    TextView textView;
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        registerForContextMenu(textView);

        textView1 = (TextView) findViewById(R.id.textView1);

        textView1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

               // Toast.makeText(MainActivity.this,"clicked", Toast.LENGTH_SHORT).show();

                if(mActionMode != null){
                    return false;
                }

                mActionMode = startSupportActionMode(mActionModeCallback);
                return true;
            }
        });




    }


    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            //mode.getMenuInflater().inflate(R.menu.example3_menu, menu);

            MenuInflater menuInflater = mode.getMenuInflater();
            menuInflater.inflate(R.menu.example3_menu, menu);

            mode.setTitle("Choose your option");
            Toast.makeText(MainActivity.this,"created", Toast.LENGTH_SHORT).show();
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            switch (item.getItemId()){
                case R.id.option_4:
                    setItalicOrBold(textView1,true,false);
                    mode.finish();
                    return true;
                case R.id.option_5:
                    setItalicOrBold(textView1,false,false);
                    mode.finish();
                    return true;
                case R.id.option_6:
                    setItalicOrBold(textView1,false,true);
                    mode.finish();
                    return true;
            }

            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example2_menu,menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.option_1:
                setColor(textView, Color.RED);
                break;
            case R.id.option_2:
                setColor(textView,Color.BLUE);
                break;
            case R.id.option_3:
                setColor(textView,Color.GRAY);
                break;
        }

        return super.onContextItemSelected(item);
    }

    public void setItalicOrBold(TextView tv,boolean Italic, boolean Reset){

        SpannableStringBuilder sb = new SpannableStringBuilder(tv.getText());

        StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD); // Span to make text bold
        StyleSpan iss = new StyleSpan(android.graphics.Typeface.ITALIC); //Span to make text italic
        StyleSpan def = new StyleSpan(Typeface.NORMAL);


        if(Italic){
            sb.setSpan(iss,0,sb.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }

        else {
            sb.setSpan(bss,0,sb.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }

        if(Reset){
            sb.clearSpans();
        }

        tv.setText(sb);

    }


    public void setColor(TextView tv, int color){

        tv.setTextColor(color);
    }




}
