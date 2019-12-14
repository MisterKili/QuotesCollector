package com.example.quotescollector.View;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quotescollector.Model.AuthorWithNumbOfQ;
import com.example.quotescollector.R;
import com.example.quotescollector.SQLDatabase.Handler.QuotesDatabase;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AuthorsListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AuthorsListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AuthorsListFragment extends Fragment {

    private RecyclerView recyclerView;
    private AuthorsListAdapter mAdapter;

    private RecyclerView.LayoutManager layoutManager;

    private List<AuthorWithNumbOfQ> mAuthorsWN;
    QuotesDatabase database;

    Context context;

    private AuthorsListFragment.OnFragmentInteractionListener mListener;

    public AuthorsListFragment() {
        // Required empty public constructor
    }

    public static AuthorsListFragment newInstance() {
        AuthorsListFragment fragment = new AuthorsListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_authors_list, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.authorsRV);
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rootView.getContext(),
                DividerItemDecoration.VERTICAL);

        recyclerView.addItemDecoration(dividerItemDecoration);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        database = QuotesDatabase.getInstance(getContext());
        mAuthorsWN = database.quotesDao().getAuthorWithNumberOfQuotesAll();

        mAdapter = new AuthorsListAdapter(getContext(), mAuthorsWN);
        recyclerView.setAdapter(mAdapter);


        SearchView searchView = rootView.findViewById(R.id.searchAuthors);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.filter(newText);
                return true;
            }
        });
        recyclerView.invalidate();

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
        if (context instanceof AuthorsListFragment.OnFragmentInteractionListener) {
            mListener = (AuthorsListFragment.OnFragmentInteractionListener) context;
            this.context = context;

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
