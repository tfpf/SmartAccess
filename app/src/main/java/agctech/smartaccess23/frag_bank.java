package agctech.smartaccess23;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link frag_bank.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link frag_bank#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frag_bank extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public frag_bank() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frag_bank.
     */
    // TODO: Rename and change types and number of parameters
    public static frag_bank newInstance(String param1, String param2) {
        frag_bank fragment = new frag_bank();
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
        final View view = inflater.inflate(R.layout.fragment_frag_bank, container, false);

        sharedPreferences = this.getActivity().getSharedPreferences("credentials", Context.MODE_PRIVATE);
        String str_ACN = sharedPreferences.getString("AC", "");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference tink = database.getReference().child(str_ACN);

        tink.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TextView textView1 = (TextView)view.findViewById(R.id.a_ac_bal);
                textView1.setText(dataSnapshot.child("Bank").getValue(long.class).toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

    /*public void send_money(View view)
    {
        // get receiver's Aadhar number
        EditText editText1 = (EditText)view.findViewById(R.id.AC_get);
        String RAC = editText1.getText().toString();

        // get amount
        EditText editText2 = (EditText)view.findViewById(R.id.amt_get);
        final int RAM = Integer.parseInt(editText2.getText().toString());

        // subtract amount
        sharedPreferences = this.getActivity().getSharedPreferences("credentials", Context.MODE_PRIVATE);
        String str_ACN = sharedPreferences.getString("AC", "");
        final DatabaseReference tink = FirebaseDatabase.getInstance().getReference().child(str_ACN);
        tink.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int z = dataSnapshot.child("Bank").getValue(int.class);
                // tink.child("Bank").setValue(z - RAM);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/

    public void send_money(View view)
    {
        AlertDialog.Builder getbankinginfo = new AlertDialog.Builder(getContext());
        getbankinginfo.show();
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
