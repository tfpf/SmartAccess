package agctech.smartaccess23;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link frag_details.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link frag_details#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frag_details extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public frag_details() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frag_details.
     */
    // TODO: Rename and change types and number of parameters
    public static frag_details newInstance(String param1, String param2) {
        frag_details fragment = new frag_details();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    SharedPreferences sharedPreferences;


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
        // Inflate the layout for this fragment
        sharedPreferences = this.getActivity().getSharedPreferences("credentials", Context.MODE_PRIVATE);
        final String str_ACN = sharedPreferences.getString("AC", "");
        // String str_PCN = sharedPreferences.getString("PC", "");
        final View view = inflater.inflate(R.layout.fragment_frag_details, container, false);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference tink = database.getReference().child(str_ACN);

        tink.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TextView textView1 = (TextView)view.findViewById(R.id.a_education);
                textView1.setText(dataSnapshot.child("Education").getValue(String.class));
                TextView textView2 = (TextView)view.findViewById(R.id.a_name);
                textView2.setText(dataSnapshot.child("Name").getValue(String.class));
                TextView textView3 = (TextView)view.findViewById(R.id.a_bg);
                textView3.setText(dataSnapshot.child("Blood Type").getValue(String.class));
                TextView textView4 = (TextView)view.findViewById(R.id.a_dob);
                textView4.setText(dataSnapshot.child("Date of Birth").getValue(String.class));
                TextView textView5 = (TextView)view.findViewById(R.id.a_add);
                textView5.setText(dataSnapshot.child("Address").getValue(String.class));
                TextView textView6 = (TextView)view.findViewById(R.id.a_work);
                textView6.setText(dataSnapshot.child("Workplace").getValue(String.class));
                TextView textView7 = (TextView)view.findViewById(R.id.a_email);
                textView7.setText(dataSnapshot.child("e-mail").getValue(String.class));
                TextView textView8 = (TextView)view.findViewById(R.id.a_number);
                textView8.setText(dataSnapshot.child("Number").getValue(long.class).toString());
                TextView textView9 = (TextView)view.findViewById(R.id.a_profession);
                textView9.setText(dataSnapshot.child("Profession").getValue(String.class));
                TextView textView10 = (TextView)view.findViewById(R.id.a_AC);
                textView10.setText(str_ACN);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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
