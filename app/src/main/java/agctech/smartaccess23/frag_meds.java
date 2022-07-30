package agctech.smartaccess23;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.IntegerRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link frag_meds.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link frag_meds#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frag_meds extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public frag_meds() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frag_meds.
     */
    // TODO: Rename and change types and number of parameters
    public static frag_meds newInstance(String param1, String param2) {
        frag_meds fragment = new frag_meds();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_frag_meds, container, false);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("credentials", Context.MODE_PRIVATE);
        final String str_ACN = sharedPreferences.getString("AC", "");

        DatabaseReference tink = FirebaseDatabase.getInstance().getReference().child(str_ACN).child("Bank");

        // total cost TextView
        final TextView textView = (TextView)view.findViewById(R.id.a_total_cost);
        final TextView textView_b = (TextView)view.findViewById(R.id.a_balance);
        tink.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                textView_b.setText(dataSnapshot.getValue(int.class).toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // checkbox TextViews
        final CheckBox cb1 = (CheckBox)view.findViewById(R.id.cb1);
        final CheckBox cb2 = (CheckBox)view.findViewById(R.id.cb2);
        final CheckBox cb3 = (CheckBox)view.findViewById(R.id.cb3);
        final CheckBox cb4 = (CheckBox)view.findViewById(R.id.cb4);
        final CheckBox cb5 = (CheckBox)view.findViewById(R.id.cb5);
        final CheckBox cb6 = (CheckBox)view.findViewById(R.id.cb6);
        final CheckBox cb7 = (CheckBox)view.findViewById(R.id.cb7);
        final CheckBox cb8 = (CheckBox)view.findViewById(R.id.cb8);

        // individual cost TextViews
        final TextView tv1 = (TextView)view.findViewById(R.id.tv1);
        final TextView tv2 = (TextView)view.findViewById(R.id.tv2);
        final TextView tv3 = (TextView)view.findViewById(R.id.tv3);
        final TextView tv4 = (TextView)view.findViewById(R.id.tv4);
        final TextView tv5 = (TextView)view.findViewById(R.id.tv5);
        final TextView tv6 = (TextView)view.findViewById(R.id.tv6);
        final TextView tv7 = (TextView)view.findViewById(R.id.tv7);
        final TextView tv8 = (TextView)view.findViewById(R.id.tv8);

        // checkbox listeners
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int cost = Integer.parseInt(tv1.getText().toString());
                int exp = Integer.parseInt(textView.getText().toString());
                if(isChecked)
                {
                    textView.setText(String.valueOf(exp + cost));
                }
                else
                {
                    textView.setText(String.valueOf(exp - cost));
                }
            }
        });
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int cost = Integer.parseInt(tv2.getText().toString());
                int exp = Integer.parseInt(textView.getText().toString());
                if(isChecked)
                {
                    textView.setText(String.valueOf(exp + cost));
                }
                else
                {
                    textView.setText(String.valueOf(exp - cost));
                }
            }
        });
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int cost = Integer.parseInt(tv3.getText().toString());
                int exp = Integer.parseInt(textView.getText().toString());
                if(isChecked)
                {
                    textView.setText(String.valueOf(exp + cost));
                }
                else
                {
                    textView.setText(String.valueOf(exp - cost));
                }
            }
        });
        cb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int cost = Integer.parseInt(tv4.getText().toString());
                int exp = Integer.parseInt(textView.getText().toString());
                if(isChecked)
                {
                    textView.setText(String.valueOf(exp + cost));
                }
                else
                {
                    textView.setText(String.valueOf(exp - cost));
                }
            }
        });
        cb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int cost = Integer.parseInt(tv5.getText().toString());
                int exp = Integer.parseInt(textView.getText().toString());
                if(isChecked)
                {
                    textView.setText(String.valueOf(exp + cost));
                }
                else
                {
                    textView.setText(String.valueOf(exp - cost));
                }
            }
        });
        cb6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int cost = Integer.parseInt(tv6.getText().toString());
                int exp = Integer.parseInt(textView.getText().toString());
                if(isChecked)
                {
                    textView.setText(String.valueOf(exp + cost));
                }
                else
                {
                    textView.setText(String.valueOf(exp - cost));
                }
            }
        });
        cb7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int cost = Integer.parseInt(tv7.getText().toString());
                int exp = Integer.parseInt(textView.getText().toString());
                if(isChecked)
                {
                    textView.setText(String.valueOf(exp + cost));
                }
                else
                {
                    textView.setText(String.valueOf(exp - cost));
                }
            }
        });
        cb8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int cost = Integer.parseInt(tv8.getText().toString());
                int exp = Integer.parseInt(textView.getText().toString());
                if(isChecked)
                {
                    textView.setText(String.valueOf(exp + cost));
                }
                else
                {
                    textView.setText(String.valueOf(exp - cost));
                }
            }
        });

        Button button = (Button)view.findViewById(R.id.button_buy);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(textView.getText().toString()) != 0)
                {
                    AlertDialog.Builder purchase = new AlertDialog.Builder(getContext());
                    purchase.setTitle("Confirm?");
                    purchase.setMessage("Are you sure you want to make a purchase worth " + textView.getText().toString() + "?");
                    purchase.setCancelable(false);
                    purchase.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    purchase.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // check balance
                            final DatabaseReference tink = FirebaseDatabase.getInstance().getReference().child(str_ACN).child("Bank");
                            tink.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.getValue(int.class) < Integer.parseInt(textView.getText().toString()))
                                    {
                                        AlertDialog.Builder insuf = new AlertDialog.Builder(getContext());
                                        insuf.setTitle("Insufficient balance!");
                                        insuf.setCancelable(false);
                                        insuf.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        });
                                        insuf.show();
                                    }
                                    else
                                    {
                                        textView_b.setText(String.valueOf(dataSnapshot.getValue(int.class) - Integer.parseInt(textView.getText().toString())));
                                        tink.setValue(dataSnapshot.getValue(int.class) - Integer.parseInt(textView.getText().toString()));

                                        AlertDialog.Builder done = new AlertDialog.Builder(getContext());
                                        done.setTitle("Purchase Successful!");
                                        done.setMessage("The medicines will be delivered to your home address.");
                                        done.setCancelable(false);
                                        done.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                cb1.setChecked(false);
                                                cb2.setChecked(false);
                                                cb3.setChecked(false);
                                                cb4.setChecked(false);
                                                cb5.setChecked(false);
                                                cb6.setChecked(false);
                                                cb7.setChecked(false);
                                                cb8.setChecked(false);
                                            }
                                        });
                                        done.show();
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                    });
                    purchase.show();
                }
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
