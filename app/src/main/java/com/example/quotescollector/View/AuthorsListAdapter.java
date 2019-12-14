package com.example.quotescollector.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quotescollector.Model.AuthorWithNumbOfQ;
import com.example.quotescollector.R;
import com.example.quotescollector.SQLDatabase.Handler.QuotesDatabase;

import java.util.ArrayList;
import java.util.List;

public class AuthorsListAdapter extends RecyclerView.Adapter<AuthorsListAdapter.AuthorViewHolder> {

    private List<AuthorWithNumbOfQ> mAuthors;
    private List<AuthorWithNumbOfQ> mAuthorsCopy = new ArrayList<>();
    private LayoutInflater mInflater;


    public AuthorsListAdapter(Context context, List<AuthorWithNumbOfQ> mAuthors) {
        this.mAuthors = mAuthors;
        mInflater = LayoutInflater.from(context);
        mAuthorsCopy.addAll(mAuthors);
    }


    @Override
    public AuthorsListAdapter.AuthorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.author_row, parent, false);
        AuthorViewHolder vh = new AuthorViewHolder(v, mAuthors);
        return vh;
    }


    @Override
    public void onBindViewHolder(AuthorViewHolder holder, int position) {
        holder.authorNameTextView.setText(mAuthors.get(position).authorName);
        holder.numberOfQuotesTextView.setText(Integer.toString(mAuthors.get(position).numberOfQuotes));
    }

    @Override
    public int getItemCount() {
        return mAuthors.size();
    }




    public void filter(String text) {
        mAuthors.clear();
        if(text.isEmpty()){
            mAuthors.addAll(mAuthorsCopy);
        } else{
            text = text.toLowerCase();
            for(AuthorWithNumbOfQ author: mAuthorsCopy){
                if(author.authorName.toLowerCase().contains(text)) {
                    mAuthors.add(author);
                }
            }
        }
        notifyDataSetChanged();
    }


    public class AuthorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView authorNameTextView, numberOfQuotesTextView;
        private  List<AuthorWithNumbOfQ> authorWNList;
        QuotesDatabase database;

        public AuthorViewHolder(View view, List<AuthorWithNumbOfQ> authorWNList) {
            super(view);

            this.authorWNList = authorWNList;

            this.authorNameTextView = view.findViewById(R.id.tvAuthorNameInAuthorRow);
//            authorNameTextView.setWidth(Resources.getSystem().getDisplayMetrics().widthPixels * 2 / 3);

            view.setOnClickListener(this);

            this.numberOfQuotesTextView = view.findViewById(R.id.tvNumberOfQuotesInAuthorRow);

            database = QuotesDatabase.getInstance(view.getContext());
        }

        @Override
        public void onClick(View view) {
            System.out.println("Działa");
            Toast.makeText(view.getContext(), "Usuwanko", Toast.LENGTH_SHORT).show();
            //show author quotes
        }
    }
}
