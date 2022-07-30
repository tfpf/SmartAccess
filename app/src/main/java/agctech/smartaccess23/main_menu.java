package agctech.smartaccess23;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

//import static agctech.smartaccess23.R.id.AC_num;
import static agctech.smartaccess23.R.layout.fragment_frag_bank;
import static agctech.smartaccess23.R.layout.login;
import static agctech.smartaccess23.R.layout.send;
import static android.R.attr.data;
import static android.R.attr.dial;
import static android.R.id.input;

public class main_menu extends AppCompatActivity
        implements
        frag_details.OnFragmentInteractionListener,
        frag_bank.OnFragmentInteractionListener,
        frag_meds.OnFragmentInteractionListener,
        frag_emer.OnFragmentInteractionListener,
        NavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("credentials", Context.MODE_PRIVATE);

        if(!sharedPreferences.contains("AC"))
        {
            final SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();

            LayoutInflater layoutInflater = LayoutInflater.from(main_menu.this);
            final View prompt = layoutInflater.inflate(login, null);
            AlertDialog.Builder details = new AlertDialog.Builder(main_menu.this);
            details.setView(prompt);
            details.setTitle("Enter your credentials to proceed.");
            details.setCancelable(false);
            details.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                }
            });
            details.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // read Aadhar
                    EditText editText1 = (EditText)prompt.findViewById(R.id.AC_num);
                    final String str_ACN = editText1.getText().toString();

                    // is Aadhar field empty
                    if(str_ACN == null || str_ACN.isEmpty())
                    {
                        AlertDialog.Builder noac = new AlertDialog.Builder(main_menu.this);
                        noac.setTitle("Aadhar field empty!");
                        noac.setCancelable(false);
                        noac.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });
                        noac.show();
                    }
                    // is Aadhar correct
                    else
                    {
                        final DatabaseReference tink = FirebaseDatabase.getInstance().getReference();
                        tink.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if(!dataSnapshot.child("Users").child(str_ACN).exists())
                                {
                                    AlertDialog.Builder wrongac = new AlertDialog.Builder(main_menu.this);
                                    wrongac.setTitle("Wrong Aadhar number!");
                                    wrongac.setCancelable(false);
                                    wrongac.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            android.os.Process.killProcess(android.os.Process.myPid());
                                            System.exit(1);
                                        }
                                    });
                                    wrongac.show();
                                }
                                // Aadhar number is correct
                                else
                                {
                                    // read amount
                                    EditText editText2 = (EditText)prompt.findViewById(R.id.code);
                                    final String number = editText2.getText().toString();

                                    // is code field empty
                                    if(number == null || number.isEmpty())
                                    {
                                        AlertDialog.Builder nopc = new AlertDialog.Builder(main_menu.this);
                                        nopc.setTitle("Passcode field empty!");
                                        nopc.setCancelable(false);
                                        nopc.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                android.os.Process.killProcess(android.os.Process.myPid());
                                                System.exit(1);
                                            }
                                        });
                                        nopc.show();
                                    }
                                    // check passcode
                                    else
                                    {
                                        final int code = Integer.parseInt(number);
                                        tink.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                final int pass = dataSnapshot.child(str_ACN).child("PC").getValue(int.class);
                                                if(code != pass)
                                                {
                                                    AlertDialog.Builder wrongpc = new AlertDialog.Builder(main_menu.this);
                                                    wrongpc.setTitle("Wrong passcode!");
                                                    wrongpc.setCancelable(false);
                                                    wrongpc.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            android.os.Process.killProcess(android.os.Process.myPid());
                                                            System.exit(1);
                                                        }
                                                    });
                                                    wrongpc.show();
                                                }
                                                // green light !!!!!!!!!!!!
                                                else
                                                {
                                                    editor.putString("AC", str_ACN);
                                                    editor.commit();
                                                    navigationView.setCheckedItem(R.id.nav_details);
                                                    FragmentManager fragmentManager = getSupportFragmentManager();
                                                    fragmentManager.beginTransaction().replace(R.id.mainFrame, new frag_details()).commit();
                                                }
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                }
            });
            details.show();
        }
        else
        {
            navigationView.setCheckedItem(R.id.nav_details);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.mainFrame, new frag_details()).commit();
        }
    }

    // let us try again

    public void send_money(View view)
    {
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("credentials", Context.MODE_PRIVATE);
        final String str_ACN = sharedPreferences.getString("AC", "");

        LayoutInflater layoutInflater = LayoutInflater.from(main_menu.this);
        final View prompt = layoutInflater.inflate(send, null);
        final AlertDialog.Builder getinfo = new AlertDialog.Builder(main_menu.this);
        getinfo.setView(prompt);
        getinfo.setTitle("Enter transaction information.");
        getinfo.setCancelable(false);
        getinfo.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        getinfo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // read Aadhar number
                EditText editText1 = (EditText)prompt.findViewById(R.id.rec_AC);
                final String str_ACM = editText1.getText().toString();

                // is Aadhar field empty
                if(str_ACM == null || str_ACM.isEmpty())
                {
                    AlertDialog.Builder noac = new AlertDialog.Builder(main_menu.this);
                    noac.setTitle("Aadhar field empty!");
                    noac.setCancelable(false);
                    noac.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    noac.show();
                }

                // Aadhar field not empty; is it valid
                else
                {
                    final DatabaseReference tink = FirebaseDatabase.getInstance().getReference();
                    tink.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(!dataSnapshot.child("Users").child(str_ACM).exists())
                            {
                                AlertDialog.Builder wrongac = new AlertDialog.Builder(main_menu.this);
                                wrongac.setTitle("Wrong Aadhar number!");
                                wrongac.setCancelable(false);
                                wrongac.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                wrongac.show();
                            }
                            // Aadhar field valid
                            else
                            {
                                // read amount
                                EditText editText2 = (EditText)prompt.findViewById(R.id.rec_amt);
                                final String number = editText2.getText().toString();

                                // is amount field empty
                                if(number == null || number.isEmpty())
                                {
                                    AlertDialog.Builder noam = new AlertDialog.Builder(main_menu.this);
                                    noam.setTitle("Amount field empty!");
                                    noam.setCancelable(false);
                                    noam.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });
                                    noam.show();
                                }

                                // amount field not empty
                                else
                                {
                                    final int amount = Integer.parseInt(number);
                                    // check if balance is sufficient
                                    tink.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            int rem = dataSnapshot.child(str_ACN).child("Bank").getValue(int.class);

                                            if(amount > rem)
                                            {
                                                // insufficient balance
                                                AlertDialog.Builder insuf = new AlertDialog.Builder(main_menu.this);
                                                insuf.setTitle("Insufficient balance!");
                                                insuf.setCancelable(false);
                                                insuf.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {

                                                    }
                                                });
                                                insuf.show();
                                            }

                                            // green light!!!!!!!!!!!
                                            else
                                            {
                                                tink.child(str_ACN).child("Bank").setValue(dataSnapshot.child(str_ACN).child("Bank").getValue(int.class) - amount);
                                                tink.child(str_ACM).child("Bank").setValue(dataSnapshot.child(str_ACM).child("Bank").getValue(int.class) + amount);
                                                AlertDialog.Builder sent = new AlertDialog.Builder(main_menu.this);
                                                sent.setTitle("Sent money successfully!");
                                                sent.setCancelable(false);
                                                sent.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {

                                                    }
                                                });
                                                sent.show();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }

            }
        });
        getinfo.show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            SharedPreferences sharedPreferences;
            sharedPreferences = getSharedPreferences("credentials", Context.MODE_PRIVATE);
            final SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_money)
        {
            fragment = new frag_bank();
        }
        else if (id == R.id.nav_meds)
        {
            fragment = new frag_meds();
        }
        else if (id == R.id.nav_emer)
        {
            fragment = new frag_emer();
        }
        else if (id == R.id.nav_details)
        {
            fragment = new frag_details();
        }

        if (fragment != null)
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.mainFrame, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri)
    {
        ;
    }
}
