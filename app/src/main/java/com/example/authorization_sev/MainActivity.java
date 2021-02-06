package com.example.authorization_sev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Объявляем об использовании следующих объектов:
    private EditText username;
    private EditText password;
    private Button login;
    private TextView loginlocked;
    private TextView attempts;
    private TextView numberofAttempts;

    // Число для подсчета попыток залогиниться:
    int numberOfRemainingLoginAttempts = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Связываемся с элементами нашего интерфейса:
        username = (EditText) findViewById(R.id.edLogin);
        password = (EditText) findViewById(R.id.edPassword);
        login = (Button) findViewById(R.id.btnSignIn);
        loginlocked = (TextView) findViewById(R.id.tvLoginLocked);
        attempts = (TextView) findViewById(R.id.tvAttempts);
        numberofAttempts = (TextView) findViewById(R.id.tvNumberofAttempts);

        numberofAttempts.setText(Integer.toString(numberOfRemainingLoginAttempts));
    }


    public void Login (View v) {
        // Если введенные логин и пароль будут словом "admin",
        // показываем Toast сообщение об успешном входе:
        if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
            Toast.makeText(getApplicationContext(), "Вход выполнен!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
// В другом случае выдаем сообщение с ошибкой:
        else {
            Toast.makeText(getApplicationContext(), "Неправильные данные!", Toast.LENGTH_SHORT).show();
            numberOfRemainingLoginAttempts--;
        }

        // Делаем видимыми текстовые поля, указывающие на количество оставшихся попыток:
        attempts.setVisibility(View.VISIBLE);
        numberofAttempts.setVisibility(View.VISIBLE);
        numberofAttempts.setText(Integer.toString(numberOfRemainingLoginAttempts));

        // Когда выполнено 3 безуспешных попытки залогиниться,
        // делаем видимым текстовое поле с надписью, что все пропало и выставляем
        // кнопке настройку невозможности нажатия setEnabled(false):
        if (numberOfRemainingLoginAttempts == 0) {
            login.setEnabled(false);
            loginlocked.setVisibility(View.VISIBLE);
            loginlocked.setBackgroundColor(Color.RED);
            loginlocked.setText("Вход заблокирован!!!");
    }
    }

}