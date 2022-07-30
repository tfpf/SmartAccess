package agctech.smartaccess23;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link frag_emer.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link frag_emer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frag_emer extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    TextView textView;
    LocationManager locationManager;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public frag_emer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frag_emer.
     */
    // TODO: Rename and change types and number of parameters
    public static frag_emer newInstance(String param1, String param2) {
        frag_emer fragment = new frag_emer();
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
        final View view = inflater.inflate(R.layout.fragment_frag_emer, container, false);

        LocationManager locationManager = (LocationManager)getContext().getSystemService(Context.LOCATION_SERVICE);

        ///
        class MyLocationListener implements LocationListener
        {
            TextView textView_titl = (TextView)view.findViewById(R.id.title_location);
            TextView textView_qlat = (TextView)view.findViewById(R.id.q_latitude);
            TextView textView_qlon = (TextView)view.findViewById(R.id.q_longitude);
            TextView textView_alat = (TextView)view.findViewById(R.id.a_latitude);
            TextView textView_alon = (TextView)view.findViewById(R.id.a_longitude);

            @Override
            public void onLocationChanged(Location location)
            {
                double lat = location.getLatitude();
                double lon = location.getLongitude();

                /*Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                List<Address> addresses;
                try
                {
                    addresses = geocoder.getFromLocation(lat, lon, 5);
                    if(addresses.size() > 0)
                    {
                        textView_titl.setText(addresses.get(0).getLocality());
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }*/

                textView_qlat.setText("latitude");
                textView_qlon.setText("longitude");
                textView_alat.setText(String.valueOf(lat));
                textView_alon.setText(String.valueOf(lon));
                textView_titl.setText("Your current location:");
            }

            @Override
            public void onProviderEnabled(String provider)
            {

            }

            @Override
            public void onProviderDisabled(String provider)
            {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras)
            {

            }
        }
        ///

        LocationListener locationListener = new MyLocationListener();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

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
