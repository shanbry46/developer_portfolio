package edu.gatech.seclass.sdpencryptor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SDPEncryptorActivity extends AppCompatActivity {

public String messageInput;
public Integer keyNumber;
public Integer incrementNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdpencryptor);
        final Button encryptButton = findViewById(R.id.encryptButton);
        final EditText messageInput = findViewById(R.id.messageInput);
        final EditText keyNumber = findViewById(R.id.keyNumber);
        final EditText incrementNumber = findViewById(R.id.incrementNumber);
        final TextView cipherText = findViewById(R.id.cipherText);
        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Temp Vars
                int iterator = 0;
                char c;
                //Objects
                StringBuilder constructText;
                constructText = new StringBuilder(messageInput.getText().toString());
                //Evaluation Vars
                String string = messageInput.getText().toString();
                int keyNum = Integer.parseInt(keyNumber.getText().toString());
                int incNum = Integer.parseInt(incrementNumber.getText().toString());
                boolean eval = validateInputs(messageInput.getText().toString(), keyNum, incNum);
                if(eval == true) {
                    return;
                }
                for (int i=0; i<messageInput.length(); i++) {
                    char ch = messageInput.getText().toString().charAt(i);
                    if (Character.isLetterOrDigit(ch)) {
                        if (i == 0) {
                            c = flipItAndRipIt(keyNum, ch);
                            iterator = keyNum + incNum;
                        } else {
                            c = flipItAndRipIt(iterator, ch);
                            iterator = iterator + incNum;
                        }
                        constructText.setCharAt(i, c);
                    }
                }
                cipherText.setText(constructText);
            }

            public char flipItAndRipIt(int keyNum, char ch) {
                char c;
                if (Character.isUpperCase(ch)) {
                    c = (char) (((int) ch + keyNum - 65) % 26 + 65);
                } else {
                    c = (char) (((int) ch + keyNum - 97) % 26 + 97);
                }
                return c;
            }

            public boolean validateInputs(String msg, int keyNum, int incNum) {
                boolean bool = false;
                if (msg == "" || msg.length() <= 0 || !msg.matches(".*[a-zA-Z]+.*")) {
                    bool = true;
                    messageInput.setError("Invalid Message");
                }
                if (keyNum <1 || keyNum >= 26){
                    bool = true;
                    keyNumber.setError("Invalid Key Number");
                }
                if (incNum <1 || incNum >= 26){
                    bool = true;
                    incrementNumber.setError("Invalid Increment Number");
                }
                return bool;
            }
        });
    }
}
