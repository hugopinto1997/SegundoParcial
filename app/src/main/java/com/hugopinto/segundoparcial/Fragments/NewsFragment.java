package com.hugopinto.segundoparcial.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hugopinto.segundoparcial.APIs.GameNewsAPI;
import com.hugopinto.segundoparcial.APIs.News;
import com.hugopinto.segundoparcial.Adapters.GameAdapter;
import com.hugopinto.segundoparcial.Classes.Game;
import com.hugopinto.segundoparcial.R;
import com.hugopinto.segundoparcial.ROOM.NewsViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment {

    public RecyclerView rv;
    public GameAdapter adapter;
    public GridLayoutManager gManager;
    public Context contexto;
    public NewsViewModel nvmodel;

    public ArrayList<News> noticiasExtraidas;
    public String token;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public NewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1,param1);
        args.putString(ARG_PARAM1,param1);
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
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_news, container, false);
        rv=view.findViewById(R.id.recycler);


        nvmodel = ViewModelProviders.of(this).get(NewsViewModel.class);
        nvmodel.getAllNews().observe(this, new Observer<List<News>>() {
            @Override
            public void onChanged(@Nullable List<News> news) {
                adapter = new GameAdapter((ArrayList<News>) news,getActivity());
                gManager= new GridLayoutManager(getActivity(),2);
                gManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if(position%3==0){
                            return 2;
                        }else {
                            return 1;
                        }
                    }
                });
                rv.setLayoutManager(gManager);
                rv.setAdapter(adapter);
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

    /*public void prepareSeries(){
        series = new ArrayList<>();
        series.add(new Game("Smesh Bras 4", "2", R.drawable.ic_csgo));
        series.add(new Game("Smesh Bras 4", "2", R.drawable.ic_csgo));
        series.add(new Game("Smesh Bras 4", "2", R.drawable.ic_csgo));
        series.add(new Game("Smesh Bras 4", "2", R.drawable.ic_csgo));
        series.add(new Game("Smesh Bras 4", "2", R.drawable.ic_csgo));
        series.add(new Game("Smesh Bras 4", "2", R.drawable.ic_csgo));



    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void cargarnoticias(final Context con, String token){

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

