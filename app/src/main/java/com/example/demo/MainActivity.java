package com.example.demo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ImageView iv_mic;
    private TextView tv_Speech_to_text;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1;
    TextToSpeech textToSpeech;
    String text = "what is means" + "2" + "into" + "2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_mic = findViewById(R.id.iv_mic);
        ImageView repeat = findViewById(R.id.repeat);
        tv_Speech_to_text = findViewById(R.id.tv_speech_to_text);

        iv_mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");

//
                try {
                    startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, " " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {

                        // if No error is found then only it will run
                        if (i != TextToSpeech.ERROR) {
                            // To Choose language of speech
                            textToSpeech.setLanguage(Locale.UK);

                            textToSpeech.speak(String.valueOf(text), TextToSpeech.QUEUE_FLUSH, null);
                        }
                    }
                });
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK) {

                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                String tex = result.get(0);
                String check = "4";


                if (tex.equals(check)) {
                    textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int i) {

                            // if No error is found then only it will run
                            if (i != TextToSpeech.ERROR) {
                                // To Choose language of speech
                                textToSpeech.setLanguage(Locale.UK);

                                textToSpeech.speak("Right Your Excellent", TextToSpeech.QUEUE_FLUSH, null);
                            }
                        }
                    });
                } else {
                    textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int i) {

                            // if No error is found then only it will run
                            if (i != TextToSpeech.ERROR) {
                                // To Choose language of speech
                                textToSpeech.setLanguage(Locale.UK);

                                textToSpeech.speak("Wrong", TextToSpeech.QUEUE_FLUSH, null);
                            }
                        }
                    });
                }
            }



        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        texttospeech();


    }

    void texttospeech() {
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                // if No error is found then only it will run
                if (i != TextToSpeech.ERROR) {
                    // To Choose language of speech
                    textToSpeech.setLanguage(Locale.UK);

                    textToSpeech.speak(String.valueOf(text), TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
    }
}