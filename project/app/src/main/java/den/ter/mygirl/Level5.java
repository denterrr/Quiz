package den.ter.mygirl;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class Level5 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

    public int numLeft;
    public int numRight;
    Array array = new Array();
    Random random = new Random();
    public int count = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level5);


        final ImageView img_left = (ImageView)findViewById(R.id.img_left);
        img_left.setClipToOutline(true);

        final ImageView img_right = (ImageView)findViewById(R.id.img_right);
        img_right.setClipToOutline(true);

        final TextView text_left = findViewById(R.id.text_left);
        final TextView text_right = findViewById(R.id.text_right);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.previewdialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        ImageView previewimg = (ImageView)dialog.findViewById(R.id.previewimg);
        previewimg.setImageResource(R.drawable.preview_city);



        TextView btnclose = (TextView)dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(Level5.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch(Exception e){

                }
                dialog.dismiss();

            }
        });

        Button btncontinue = (Button)dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    dialog.dismiss();
                }catch(Exception e){

                }
            }
        });


        dialog.show();


        //-----------------------------------
        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEnd.setContentView(R.layout.dialogend);
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogEnd.setCancelable(false);

        TextView textfin = (TextView)dialogEnd.findViewById(R.id.textdesc);
        textfin.setText(R.string.textfin);
        textfin.setTextSize(14);

        TextView btnclose2 = (TextView)dialogEnd.findViewById(R.id.btnclose);
        btnclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(Level5.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch(Exception e){

                }
                dialogEnd.dismiss();

            }
        });

        Button btncontinue2 = (Button)dialogEnd.findViewById(R.id.btncontinue);
        btncontinue2.setText(R.string.ok);
        btncontinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Level5.this,GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
                dialogEnd.dismiss();

            }});




        //-----------------------------------


        Button btn_back = (Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(Level5.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch(Exception e){}
            }
        });


    final int[] progress = {
            R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
            R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
    };


    final Animation a = AnimationUtils.loadAnimation(Level5.this, R.anim.alpha);

    numLeft = random.nextInt(10);
    img_left.setImageResource(array.images5[numLeft]);
    text_left.setText(array.texts5[numLeft]);
    numRight = random.nextInt(10);



    while (numLeft == numRight){
        numRight = random.nextInt(10);
    }
    img_right.setImageResource(array.images5[numRight]);
    text_right.setText(array.texts5[numRight]);

    img_left.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction()== MotionEvent.ACTION_DOWN){
              img_right.setEnabled(false);
              if(numLeft>numRight){
                  img_left.setImageResource(R.drawable.img_true);
              }else{
                  img_left.setImageResource(R.drawable.img_false);
              }
            }else if(motionEvent.getAction()== MotionEvent.ACTION_UP){
                if(numLeft>numRight){
                    if (count<10){
                        count= count+1;
                    }
                    for (int i =0;i<10;i++){
                        TextView tv = findViewById(progress[i]);
                        tv.setBackgroundResource(R.drawable.style_points);
                    }

                for(int i =0;i<count;i++){
                    TextView tv = findViewById(progress[i]);
                    tv.setBackgroundResource(R.drawable.style_points_blue);
                    }

                }else{
                    if (count>0){
                        if(count==1){
                            count=0;
                        }else{
                            count = count-2;
                        }
                    }
                    for (int i =0;i<9;i++){
                        TextView tv = findViewById(progress[i]);
                        tv.setBackgroundResource(R.drawable.style_points);
                    }

                    for(int i =0;i<count;i++){
                        TextView tv = findViewById(progress[i]);
                        tv.setBackgroundResource(R.drawable.style_points_blue);
                    }

                }

                if (count==10){
                    //ВЫХОД ИЗ УРОВНЯ
                    dialogEnd.show();

                }else{
                    numLeft = random.nextInt(10);
                    img_left.setImageResource(array.images5[numLeft]);
                    img_left.startAnimation(a);
                    text_left.setText(array.texts5[numLeft]);
                    numRight = random.nextInt(10);



                    while (numLeft == numRight){
                        numRight = random.nextInt(10);
                    }
                    img_right.setImageResource(array.images5[numRight]);
                    img_right.startAnimation(a);
                    text_right.setText(array.texts5[numRight]);
                    img_right.setEnabled(true); //включаем правую картинку обратно
                }
            }

            return true;
        }
    });
    //конец левой

       //начало правой

        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction()== MotionEvent.ACTION_DOWN){
                    img_left.setEnabled(false);
                    if(numLeft<numRight){
                        img_right.setImageResource(R.drawable.img_true);
                    }else{
                        img_right.setImageResource(R.drawable.img_false);
                    }
                }else if(motionEvent.getAction()== MotionEvent.ACTION_UP){
                    if(numLeft<numRight){
                        if (count<10){
                            count= count+1;
                        }
                        for (int i =0;i<10;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for(int i =0;i<count;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_blue);
                        }

                    }else{
                        if (count>0){
                            if(count==1){
                                count=0;
                            }else{
                                count = count-2;
                            }
                        }
                        for (int i =0;i<9;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        for(int i =0;i<count;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_blue);
                        }

                    }

                    if (count==10){
                        //ВЫХОД ИЗ УРОВНЯ
                        dialogEnd.show();

                    }else{
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images5[numLeft]);
                        img_left.startAnimation(a);
                        text_left.setText(array.texts5[numLeft]);
                        numRight = random.nextInt(10);



                        while (numLeft == numRight){
                            numRight = random.nextInt(10);
                        }
                        img_right.setImageResource(array.images5[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts5[numRight]);
                        img_left.setEnabled(true); //включаем правую картинку обратно
                    }
                }

                return true;
            }
        });




    }

    @Override
    public void onBackPressed(){
        try{
            Intent intent = new Intent(Level5.this, GameLevels.class);
            startActivity(intent);
            finish();
        }catch(Exception e){

        }

    }
}