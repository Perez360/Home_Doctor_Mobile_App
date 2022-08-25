package com.example.home_doctor.Chat_Feature;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.home_doctor.Bottom_Navigation_Pages.Fragments.Chat_person_Fragment;
import com.example.home_doctor.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class Chat_Activity extends AppCompatActivity {
    public static TextView clear_Badge;
    public LinearLayout prevLayoutBackground;
    int SELECT_PICTURE = 100;
    int MAKE_CALL = 200;
    Button sendButton;
    EditText typeMessageField;
    LinearLayout chatLinearLayout;
    TextView usernameText;
    Toolbar chatToolbar;
    ScrollView scrollView;
    RelativeLayout backLayout;
    ImageButton addphoto;
    TextView nomessage;
    TextView getUsernameText;
    CircleImageView getProfilePic;
    int getColor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);
        initComp();

    }


    private void initComp() {
        usernameText = findViewById(R.id.userNameText);
        sendButton = findViewById(R.id.send_message_button);
        typeMessageField = findViewById(R.id.type_message_field);
        backLayout = findViewById(R.id.backlayout);
        chatLinearLayout = findViewById(R.id.chat_linearView);
        nomessage = findViewById(R.id.no_message);
        //scrollView = findViewById(R.id.scroll);
        getUsernameText = findViewById(R.id.userNameText);
        getProfilePic = findViewById(R.id.chat_profile_pic);
        addphoto = findViewById(R.id.addPhotos);
        scrollView=findViewById(R.id.scrollChat);


        nomessage.setText("You have no messages with " + (String) getIntent().getExtras().get("username"));

        getUsernameText.setText((String) getIntent().getExtras().get("username"));
        Glide.with(getApplicationContext()).load(((int) getIntent().getExtras().get("image"))).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(getProfilePic);

        typeMessageField.setText(Chat_person_Fragment.holdTypedTextForFuture);
        typeMessageField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    sendButton.setBackgroundResource(R.drawable.ripple_record_button);
                    sendButton.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_for_chat_button));
                } else {
                    sendButton.setBackgroundResource(R.drawable.ripple_for_send_button);
                    if (s.toString().length() == 1 && before != 2) {
                        sendButton.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_for_chat_button));
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        typeMessageField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {

                        scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
            }
        });


        chatToolbar = findViewById(R.id.chat_tool);
        chatToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.call:
                        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(Chat_Activity.this, new String[]{Manifest.permission.CALL_PHONE}, MAKE_CALL);
                        } else {
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:0249065507".trim()));
                            startActivity(intent);
                        }
                        return true;
                    case R.id.chat_search:
                        Toast.makeText(Chat_Activity.this, "Will be added soon", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.wallpaper:
                        Toast.makeText(Chat_Activity.this, "Will be added soon", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }
        });

        backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().hasExtra("type")) {
                    if (getIntent().getExtras().get("type").equals("chat_person")) {
                        clear_Badge.setVisibility(View.GONE);
                    }
                }
                finish();
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                String getText = typeMessageField.getText().toString().trim();
                if (!getText.equals("")) {
                    chatLinearLayout.removeView(nomessage);
                   /* LinearLayout.LayoutParams linearLayoutParamsMain = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    if (chatLinearLayout.getChildCount() == 0) {
                        linearLayoutParamsMain.setMargins(0, 20, 0, 0);
                    }

                    //Main
                    RelativeLayout relativeLayoutHolder = new RelativeLayout(getApplicationContext());
                    RelativeLayout.LayoutParams relaLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    relativeLayoutHolder.setGravity(Gravity.END);
                    relaLayoutParams.setMargins(0, 3, 0, 3);
                    relativeLayoutHolder.setLayoutParams(linearLayoutParamsMain);

                    //Sub
                    LinearLayout linearLayoutSmall = new LinearLayout(getApplicationContext());
                    linearLayoutSmall.setBackgroundResource(R.drawable.out_chat_bubble);
                    linearLayoutSmall.setPadding(50, 25, 50, 25);
                    LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    linearLayoutSmall.setLayoutParams(relaLayoutParams);


                    TextView chatView = new TextView(getApplicationContext());
                    TextView time = new TextView(getApplicationContext());
                    ImageView check = new ImageView(getApplicationContext());

                    relativeLayoutHolder.setOnTouchListener(new View.OnTouchListener() {
                        @SuppressLint({"ClickableViewAccessibility", "UseCompatLoadingForDrawables"})
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            switch (event.getAction()) {
                                case MotionEvent.ACTION_DOWN:
                                    getColor = linearLayoutSmall.getSolidColor();
                                    // linearLayoutSmall.setBackground(getResources().getDrawable(R.drawable.chatlist_black));
                                    // MyToast.makeText(Chat_Activity.this, "Pressed", MyToast.LENGTH_SHORT).show();
                                    return true;
                                case MotionEvent.ACTION_UP:
                                    //linearLayoutSmall.setBackground(getResources().getDrawable(R.drawable.chatlist));
                                    return true;
                                default:
                                    return false;
                            }
                        }
                    });

                    relativeLayoutHolder.setOnLongClickListener(new View.OnLongClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public boolean onLongClick(View view) {
                            relativeLayoutHolder.setBackgroundColor(getResources().getColor(R.color.layoutSelect));
                            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                vibrator.vibrate(VibrationEffect.createOneShot(30, VibrationEffect.DEFAULT_AMPLITUDE));
                            }

                            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(Chat_Activity.this);
                            alertBuilder.setMessage("Are you sure you want to delete this message?");
                            alertBuilder.setTitle("Delete");
                            alertBuilder.setCancelable(false);
                            alertBuilder.setIcon(R.drawable.ic_baseline_delete_message_24);
                            alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    chatLinearLayout.removeView(relativeLayoutHolder);

                                }
                            });
                            alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    relativeLayoutHolder.setBackgroundColor(getResources().getColor(R.color.empty));
                                }
                            });
                            alertBuilder.show();

                            return true;
                        }
                    });


                    chatView.setText(getText);
                    chatView.setPadding(0, 0, 30, 0);
                    chatView.setTextColor(Color.WHITE);
                    chatView.setMaxWidth(700);
                    chatView.setTextSize(17f);
                    chatView.setLayoutParams(linearLayoutParams);

                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
                    String getTime = simpleDateFormat.format(calendar.getTime());
                    if (getTime.charAt(0) == '0') {
                        getTime = getTime.substring(1, getTime.length());
                    }

                    time.setTextSize(13f);
                    time.setPadding(0, 0, 5, 0);
                    time.setTextColor(getResources().getColor(R.color.ash));
                    time.setText(getTime);
                    time.setGravity(Gravity.BOTTOM);
                    time.setLayoutParams(linearLayoutParams);


                    check.setBackground(getResources().getDrawable(R.drawable.ic_baseline_check_24));
                    check.setLayoutParams(linearLayoutParams);

                    linearLayoutSmall.addView(chatView);
                    linearLayoutSmall.addView(time);
                    linearLayoutSmall.addView(check);
                    linearLayoutSmall.setAnimation(AnimationUtils.loadAnimation(getBaseContext(),R.anim.my_popup_anim));


                    relativeLayoutHolder.addView(linearLayoutSmall);
                    chatLinearLayout.addView(relativeLayoutHolder);
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            scrollView.scrollTo(0, scrollView.getBottom());
                            scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                        }
                    });


                    SqlLiteDatabaseHelper sqlLiteDatabaseHelper = new SqlLiteDatabaseHelper(getApplicationContext(), typeMessageField.getText().toString());
                    Intent intent = getIntent();
                    String userName = intent.getExtras().get("username").toString();
                    //String location = intent.getExtras().get("location").toString();

                    String date = LocalDate.now().toString();
//                    long ans = sqlLiteDatabaseHelper.insertData(userName, typeMessageField.getText().toString(), getTime, date, location);

                    typeMessageField.setText("");
                    getText = "";

*/

                    LinearLayout msmLayout = findViewById(R.id.chat_linearView);

                    View messageView = getLayoutInflater().inflate(R.layout.chat_layout, null, false);

                    TextView messageText = messageView.findViewById(R.id.msg);
                    TextView messageTime = messageView.findViewById(R.id.msg_time);
                    ImageView messageCheck = messageView.findViewById(R.id.msg_check);
                    LinearLayout messageLayout = messageView.findViewById(R.id.messageLayout);

                    messageLayout.setAnimation(AnimationUtils.loadAnimation(getBaseContext(), R.anim.chat_popup_anim_out));
                    if (prevLayoutBackground != null) {
                        messageLayout.setBackgroundResource(R.drawable.out_chat_bubble_old);
                    }
                    prevLayoutBackground = messageLayout;


                    messageText.setText(typeMessageField.getText().toString().trim());


                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
                    String getTime = simpleDateFormat.format(calendar.getTime());
                    if (getTime.charAt(0) == '0') {
                        getTime = getTime.substring(1, getTime.length());
                    }
                    messageTime.setText(getTime);

                    chatLinearLayout.addView(messageView);
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            scrollView.scrollTo(0, scrollView.getBottom());
                            scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                        }
                    });
                    typeMessageField.setText("");


                }
            }
        });

        addphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Choose picture"), SELECT_PICTURE);
            }
        });

    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == SELECT_PICTURE) {
            Uri uri = data.getData();
            if (null != uri) {
                chatLinearLayout.removeView(nomessage);
                LinearLayout.LayoutParams linearLayoutParamsMain = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                if (chatLinearLayout.getChildCount() == 0) {
                    linearLayoutParamsMain.setMargins(0, 20, 0, 0);
                }

                //Main
                RelativeLayout relativeLayoutHolder = new RelativeLayout(getApplicationContext());
                RelativeLayout.LayoutParams relaLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                relativeLayoutHolder.setGravity(Gravity.END);
                relaLayoutParams.setMargins(0, 3, 0, 3);
                relativeLayoutHolder.setLayoutParams(linearLayoutParamsMain);

                //Sub
                LinearLayout linearLayoutSmall = new LinearLayout(getApplicationContext());
                linearLayoutSmall.setBackgroundResource(R.drawable.out_chat_bubble);
                linearLayoutSmall.setPadding(5, 5, 5, 5);
                LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 300);
                linearLayoutSmall.setLayoutParams(relaLayoutParams);


                ImageView picture = new ImageView(getApplicationContext());
                TextView time = new TextView(getApplicationContext());
                ImageView sent = new ImageView(getApplicationContext());

                relativeLayoutHolder.setOnTouchListener(new View.OnTouchListener() {
                    @SuppressLint({"ClickableViewAccessibility", "UseCompatLoadingForDrawables"})
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                getColor = linearLayoutSmall.getSolidColor();
                                // linearLayoutSmall.setBackground(getResources().getDrawable(R.drawable.chatlist_black));
                                // MyToast.makeText(Chat_Activity.this, "Pressed", MyToast.LENGTH_SHORT).show();
                                return true;
                            case MotionEvent.ACTION_UP:
                                //linearLayoutSmall.setBackground(getResources().getDrawable(R.drawable.chatlist));
                                return true;
                            default:
                                return false;
                        }
                    }
                });

                relativeLayoutHolder.setOnLongClickListener(new View.OnLongClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public boolean onLongClick(View view) {
                        relativeLayoutHolder.setBackgroundColor(getResources().getColor(R.color.layoutSelect));
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            vibrator.vibrate(VibrationEffect.createOneShot(30, VibrationEffect.DEFAULT_AMPLITUDE));
                        }

                        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(Chat_Activity.this);
                        alertBuilder.setMessage("Are you sure you want to delete this message?");
                        alertBuilder.setTitle("Delete");
                        alertBuilder.setCancelable(false);
                        alertBuilder.setIcon(R.drawable.ic_baseline_delete_message_24);
                        alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                chatLinearLayout.removeView(relativeLayoutHolder);

                            }
                        });
                        alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                relativeLayoutHolder.setBackgroundColor(getResources().getColor(R.color.empty));
                            }
                        });
                        alertBuilder.show();

                        return true;
                    }
                });

                picture.setPadding(0, 0, 30, 0);
                picture.setMaxWidth(30);
                picture.setMaxHeight(30);
                picture.setLayoutParams(linearLayoutParams);
                Glide.with(getApplicationContext()).load(uri).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(picture);


                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
                String getTime = simpleDateFormat.format(calendar.getTime());
                if (getTime.charAt(0) == '0') {
                    getTime = getTime.substring(1, getTime.length());
                }

                time.setTextSize(13f);
                time.setPadding(0, 0, 5, 0);
                time.setTextColor(getResources().getColor(R.color.ash));
                time.setText(getTime);
                time.setGravity(Gravity.BOTTOM);
                time.setLayoutParams(linearLayoutParams);


                sent.setBackground(getResources().getDrawable(R.drawable.ic_baseline_check_24));
                sent.setLayoutParams(linearLayoutParams);

                linearLayoutSmall.addView(picture);
                linearLayoutSmall.addView(time);
                linearLayoutSmall.addView(sent);


                relativeLayoutHolder.addView(linearLayoutSmall);
                chatLinearLayout.addView(relativeLayoutHolder);
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.scrollTo(0, scrollView.getBottom());
                        scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
            }
        }
    }
*/
    @Override
    public void onBackPressed() {
        Chat_person_Fragment.holdTypedTextForFuture = typeMessageField.getText().toString().trim();
        if (getIntent().hasExtra("type")) {
            if (getIntent().getExtras().get("type").equals("chat_person")) {
                clear_Badge.setVisibility(View.GONE);
            }
        }
        finish();
    }


}