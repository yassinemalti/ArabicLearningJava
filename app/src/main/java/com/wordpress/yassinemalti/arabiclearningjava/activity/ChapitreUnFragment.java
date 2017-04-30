package com.wordpress.yassinemalti.arabiclearningjava.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;
import com.wordpress.yassinemalti.arabiclearningjava.R;

public class ChapitreUnFragment extends Fragment {

    PDFView pdfView;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ChapitreUnFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ChapitreUnFragment newInstance(String param1, String param2) {
        ChapitreUnFragment fragment = new ChapitreUnFragment();
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

        View rootView = inflater.inflate(R.layout.fragment_chapitre_un, container, false);
        NativeExpressAdView adBanner_chapitre_un =
                (NativeExpressAdView) rootView.findViewById(R.id.adBanner_chapitre_un);
        AdRequest request_chapitre_un = new AdRequest.Builder().build();
        adBanner_chapitre_un.loadAd(request_chapitre_un);

        pdfView = (PDFView) rootView.findViewById(R.id.activity_chapitre_un_pdfView);
        pdfView.fromAsset("MyPDF.pdf").load();

        return rootView;
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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}
