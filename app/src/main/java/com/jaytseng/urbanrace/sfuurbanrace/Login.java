package com.jaytseng.urbanrace.sfuurbanrace;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener{
    private EditText input_username, input_password;
    private Button buttonLogin, buttonRegister;
    // Progress Dialog
    private ProgressDialog pDialog;
    // JSON parser class
    JSONParser jsonParser = new JSONParser();
    private static final String LOGIN_URL = "http://mysql17.000webhost.com/login.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        input_username = (EditText)findViewById(R.id.usernamelEditText);
        input_password = (EditText)findViewById(R.id.passwordlEditText);
        buttonLogin = (Button)findViewById(R.id.loginButton);
        buttonLogin.setOnClickListener(this);
        buttonRegister = (Button)findViewById(R.id.registerButton);
        buttonRegister.setOnClickListener(this);

    }
    @Override
    protected void onStop() {
        super.onStop();

        if(pDialog!= null)
            pDialog.dismiss();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton:
                new AttemptLogin(input_username.getText().toString(),input_password.getText().toString()).execute();
            case R.id.registerButton:
                Intent i = new Intent(this, Register.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }
    class AttemptLogin extends AsyncTask<String, String, String> {
        /** *
         *  Before starting background thread Show Progress Dialog * */
        boolean failure = false;
        String username;
        String password;
        int success;
        public AttemptLogin(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Attempting for login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // here Check for success tag
            int success;
//            String username = input_username.getText().toString();
//            String password = input_password.getText().toString();

            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", username));
                params.add(new BasicNameValuePair("password", password));

                JSONObject json = jsonParser.makeHttpRequest( LOGIN_URL, "POST", params);

                Log.d("request! starting", LOGIN_URL + params );

                // checking log for json response
                Log.d("Login attempt", json.toString());
                // success tag for json
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Log.d("Successfully Login!", json.toString());
                    return json.getString(TAG_MESSAGE);

                }else{
                    Log.d("Login Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);
                } }
            catch (JSONException e) {
                e.printStackTrace();
            } return null;
        } /**
         * Once the background process is done we need to Dismiss the progress dialog asap *
         * **/
        protected void onPostExecute(String file_url) {
            pDialog.dismiss();
            if (file_url != null){
                Toast.makeText(Login.this, file_url, Toast.LENGTH_LONG).show();
            }
            Intent i = new Intent(Login.this, Main.class);
            finish();
            startActivity(i);
        }
    }
}