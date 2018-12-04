package com.example.greendao.allsignin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 430;
    private GoogleSignInClient mGoogleApiClient;
    private SignInButton google_sign_in;
    public LoginButton facebook_sign_in;
    public Button btn_fb_signin, google;
    public CallbackManager callbackManager;
    private GoogleSignInAccount account;
    public String id, name, email, gender, birthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callbackManager = CallbackManager.Factory.create();
        btn_fb_signin = (Button) findViewById(R.id.btn_fb_sign_in);
        google = (Button) findViewById(R.id.google);
        facebook_sign_in = (LoginButton) findViewById(R.id.login_button);

        facebook();
        initializeControls();
        initializeGPlusSettings();
        account = GoogleSignIn.getLastSignedInAccount(this);
    }


    private void facebook() {

        facebook_sign_in.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Intent intent = new Intent(MainActivity.this, ViewPager.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Successful Login", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplication(), ViewPager.class);
                startActivity(i);
                String accessToken = loginResult.getAccessToken()
                        .getToken();
                Log.i("accessToken", accessToken);
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object,
                                                    GraphResponse response) {
                                Log.i("LoginActivity",
                                        response.toString());
                                try {
                                    id = object.getString("id");
                                    try {
                                        URL profile_pic = new URL(
                                                "http://graph.facebook.com/" + id + "/picture?type=large");
                                        Log.i("profile_pic",
                                                profile_pic + "");

                                    } catch (MalformedURLException e) {
                                        e.printStackTrace();
                                    }
                                    Log.e("UserDate", String.valueOf(object));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                System.out.println("onCancel");
            }

            @Override
            public void onError(FacebookException exception) {
                System.out.println("onError");
                Log.v("LoginActivity", exception.getCause().toString());
            }
        });


    }

    private void initializeControls() {

        google_sign_in = (SignInButton) findViewById(R.id.btn_google_sign_in);
        google_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent signInIntent = mGoogleApiClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);*/
                Intent signInIntent = mGoogleApiClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }

    private void initializeGPlusSettings() {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = GoogleSignIn.getClient(this, gso);
       /* mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();*/
        google_sign_in.setSize(SignInButton.SIZE_STANDARD);
        google_sign_in.setScopes(gso.getScopeArray());
    }

    private void signIn() {

       /* Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);*/

    }

    private void signOut() {

       /* Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }
        });*/
    }

    private void handleGPlusSignInResult(GoogleSignInResult result) {

        Log.d(TAG, "handleSignInResult:" + result.isSuccess());

        if (result.isSuccess()) {
            Toast.makeText(this, "Hello pello" , Toast.LENGTH_SHORT).show();

            // google intent
            GoogleSignInAccount acct = result.getSignInAccount();

            //Fetch values
            String personName = acct.getDisplayName();
            String personPhotoUrl = acct.getPhotoUrl().toString();
            String email = acct.getEmail();
            String familyName = acct.getFamilyName();
            Log.e(TAG, "Name: " + personName + ", email: " + email + ", Image: " + personPhotoUrl + ", Family Name: " + familyName);
            updateUI(true);
        } else {
            updateUI(false);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        if (account != null) {
            Intent i = new Intent(this, ViewPager.class);
            startActivity(i);
        }


        //OptionalPendingResult opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode,
                                    Intent data) {
        if (requestCode == RC_SIGN_IN) {
       /*     GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleGPlusSignInResult(result)*/;
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        } else {
            super.onActivityResult(requestCode, responseCode, data);
            callbackManager.onActivityResult(requestCode, responseCode, data);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {



        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            if (account !=null)
            {
                Toast.makeText(this, "zsdfsdfsdf", Toast.LENGTH_SHORT).show();
            }

            // Signed in successfully, show authenticated UI.

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
          /*  Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);*/
        }







    }

    public void onClick(View v) {

        if (v == btn_fb_signin) {
            facebook_sign_in.performClick();
        } else if (v == google) {
            signIn();
        }
    }

    private void updateUI(boolean isSignedIn) {
        if (isSignedIn) {
            google_sign_in.setVisibility(View.GONE);
        } else {
            google_sign_in.setVisibility(View.VISIBLE);
        }
    }


}
