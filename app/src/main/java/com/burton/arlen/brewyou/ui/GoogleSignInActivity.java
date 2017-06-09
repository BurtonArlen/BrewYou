package com.burton.arlen.brewyou.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.burton.arlen.brewyou.Constants;
import com.burton.arlen.brewyou.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.Transaction;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GoogleSignInActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private ProgressDialog mDialog;
    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 360;

    private Intent theIntent;
    private TextView mTitle;
    private TextView mTag;
    private ImageView mImageLogin;
    String TAG = GoogleSignInActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_sign_in);
        createProgressDialog();

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/font.ttf");

        mImageLogin = (ImageView) findViewById(R.id.imageLogin);
        mTitle = (TextView) findViewById(R.id.welcomeText);
        mTag = (TextView) findViewById(R.id.taglineText);

        mTag.setTypeface(font);
        mTitle.setTypeface(font);

        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.emailSignInButton).setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this , this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null){
            toProfile();
        }

    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.clear();
            if (mAuth.getCurrentUser() != null) {
                getMenuInflater().inflate(R.menu.app_flow_menu_1, menu);
            } else {
                getMenuInflater().inflate(R.menu.menu_signed_out, menu);
            }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.action_logout){
            signOut();
            return true;
        }
        if (id == R.id.user_profile){
            Intent intent = new Intent(GoogleSignInActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.about_us){
            Intent intent = new Intent(GoogleSignInActivity.this, AboutApp.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                Log.d(TAG, result.toString());
            }
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mDialog.show();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mDialog.dismiss();
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(GoogleSignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void signIn() {
        createProgressDialog();
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        mAuth.signOut();
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        updateUI(null);
                    }
                });
        findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
        findViewById(R.id.emailSignInButton).setVisibility(View.VISIBLE);
        findViewById(R.id.sign_in_button).setFocusable(true);
        findViewById(R.id.emailSignInButton).setFocusable(true);
    }

    private void createProgressDialog(){
        mDialog = new ProgressDialog(this);
        mDialog.setTitle("Authorizing...");
        mDialog.setMessage("Checking user status...");
        mDialog.setCancelable(false);
    }

    private void updateUI(FirebaseUser user) {
        invalidateOptionsMenu();
        if (user != null) {
            getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            findViewById(R.id.emailSignInButton).setVisibility(View.GONE);
            findViewById(R.id.sign_in_button).setFocusable(false);
            findViewById(R.id.emailSignInButton).setFocusable(false);
            toProfile();

        } else {
            getSupportActionBar().setTitle("Please sign into BrewYou");
        }
    }

    private void toProfile(){
        Intent intent = new Intent(GoogleSignInActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailure:" + connectionResult);
        Toast.makeText(GoogleSignInActivity.this, "AuthFailed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.sign_in_button) {
            signIn();
        }
        if (i == R.id.emailSignInButton) {
            Intent intent = new Intent(GoogleSignInActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}

