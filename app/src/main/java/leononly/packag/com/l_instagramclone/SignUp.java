package leononly.packag.com.l_instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave, btnGetAllData;
    private EditText edtName, edtPunchPower, edtPuchSpeed, edtKickSpeed, edtKickPower;
    private TextView txtGetData;
    private String allKickBoxers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = findViewById(R.id.btnSave);
        btnGetAllData = findViewById(R.id.btnGetAllData);
        edtName = findViewById(R.id.edtName);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtPuchSpeed = findViewById(R.id.edtPuchSpeed);
        edtKickPower = findViewById(R.id.edtKickPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);
        txtGetData = findViewById(R.id.txtGetData);

        btnSave.setOnClickListener(SignUp.this);


        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("XcZrV93o78", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (object != null && e == null) {
                            txtGetData.setText(object.get("name") + " - " + "Punch Power: " + object.get("punchPower"));
                        }
                    }
                });
            }
        });


        btnGetAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allKickBoxers = "";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null) {
                            if (objects.size() > 0) {


                                for (ParseObject kickBoxer : objects) {
                                    allKickBoxers = allKickBoxers + kickBoxer.get("name") + "\n";
                                }
                                FancyToast.makeText(SignUp.this, allKickBoxers, FancyToast.LENGTH_SHORT, FancyToast.SUCCESS,
                                        true).show();
                            } else {
                                FancyToast.makeText(SignUp.this, "Failed", FancyToast.LENGTH_SHORT, FancyToast.ERROR,
                                        true).show();
                            }
                        }
                    }
                });
            }
        });

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
