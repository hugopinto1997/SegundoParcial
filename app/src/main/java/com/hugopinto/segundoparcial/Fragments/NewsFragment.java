package com.hugopinto.segundoparcial.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import com.hugopinto.segundoparcial.APIs.News;
import com.hugopinto.segundoparcial.APIs.player;
import com.hugopinto.segundoparcial.Adapters.GameAdapter;
import com.hugopinto.segundoparcial.Adapters.PlayersAdapter;
import com.hugopinto.segundoparcial.R;
import com.hugopinto.segundoparcial.ROOM.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

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
    private String juego;





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
     * @return A new instance of fragment TopPlayersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(String param1) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_news, container, false);
        SharedPreferences sharedPref = getContext().getSharedPreferences("Juego",Context.MODE_PRIVATE);
        juego = sharedPref.getString("Juegos","");
        rv = view.findViewById(R.id.recyclernews);

        if (juego.contains("Noticias")) {
            nvmodel = ViewModelProviders.of(this).get(NewsViewModel.class);
            nvmodel.getAllNews().observe(this, new Observer<List<News>>() {
                @Override
                public void onChanged(@Nullable List<News> news) {
                    adapter = new GameAdapter((ArrayList<News>) news, getActivity());
                    gManager = new GridLayoutManager(getActivity(), 2);
                    gManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int position) {
                            if (position % 3 == 0) {
                                return 2;
                            } else {
                                return 1;
                            }
                        }
                    });
                    rv.setLayoutManager(gManager);
                    rv.setAdapter(adapter);
                }
            });
        }else if(mParam1.equals("Noticias")){
            nvmodel = ViewModelProviders.of(this).get(NewsViewModel.class);
            nvmodel.getAllNews().observe(this, new Observer<List<News>>() {
                @Override
                public void onChanged(@Nullable List<News> news) {
                    adapter = new GameAdapter((ArrayList<News>) news, getActivity());
                    gManager = new GridLayoutManager(getActivity(), 2);
                    gManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int position) {
                            if (position % 3 == 0) {
                                return 2;
                            } else {
                                return 1;
                            }
                        }
                    });
                    rv.setLayoutManager(gManager);
                    rv.setAdapter(adapter);
                }
            });

        }
        else{
            nvmodel = ViewModelProviders.of(this).get(NewsViewModel.class);
            nvmodel.getNewsByCat().observe(this, new Observer<List<News>>() {
                @Override
                public void onChanged(@Nullable List<News> news) {
                    adapter = new GameAdapter((ArrayList<News>) news, getActivity());
                    gManager = new GridLayoutManager(getActivity(), 2);
                    gManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int position) {
                            if (position % 3 == 0) {
                                return 2;
                            } else {
                                return 1;
                            }
                        }
                    });
                    rv.setLayoutManager(gManager);
                    rv.setAdapter(adapter);
                }
            });
        }/* else if (mParam1.equals("lol")) {
            nvmodel = ViewModelProviders.of(this).get(NewsViewModel.class);
            nvmodel.getLOLNEWS().observe(this, new Observer<List<News>>() {
                @Override
                public void onChanged(@Nullable List<News> news) {
                    adapter = new GameAdapter((ArrayList<News>) news, getActivity());
                    gManager = new GridLayoutManager(getActivity(), 2);
                    gManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int position) {
                            if (position % 3 == 0) {
                                return 2;
                            } else {
                                return 1;
                            }
                        }
                    });
                    rv.setLayoutManager(gManager);
                    rv.setAdapter(adapter);
                }
            });
        } else if (mParam1.equals("overwatch")) {
            nvmodel = ViewModelProviders.of(this).get(NewsViewModel.class);
            nvmodel.getOVERWATCHNEWS().observe(this, new Observer<List<News>>() {
                @Override
                public void onChanged(@Nullable List<News> news) {
                    adapter = new GameAdapter((ArrayList<News>) news, getActivity());
                    gManager = new GridLayoutManager(getActivity(), 2);
                    gManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int position) {
                            if (position % 3 == 0) {
                                return 2;
                            } else {
                                return 1;
                            }
                        }
                    });
                    rv.setLayoutManager(gManager);
                    rv.setAdapter(adapter);
                }
            });
        }*/
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
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
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