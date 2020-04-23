package leononly.packag.com.l_instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText edtName, edtPunchPower, edtPuchSpeed, edtKickSpeed, edtKickPower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = findViewById(R.id.btnSave);
        edtName = findViewById(R.id.edtName);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtPuchSpeed = findViewById(R.id.edtPuchSpeed);
        edtKickPower = findViewById(R.id.edtKickPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);

        btnSave.setOnClickListener(SignUp.this);

    }


    @Override
    public void onClick(View v) {
        try {
            final ParseObject kickBoxer = new ParseObject("KickBoxer");
            kickBoxer.put("name", edtName.getText().toString());
            kickBoxer.put("punchSpeed", edtPuchSpeed.getText().toString());
            kickBoxer.put("punchPower", edtPunchPower.getText().toString());
            kickBoxer.put("kickSpeed", edtKickSpeed.getText().toString());
            kickBoxer.put("kickPower", edtKickPower.getText().toString());

            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null)
                        FancyToast.makeText(SignUp.this, kickBoxer.get("name") + " is saved to the server", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS,
                                true).show();
                    else
                        FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                }
            });
        } catch (Exception e) {
            FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
        }

    }
}
