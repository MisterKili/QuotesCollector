package com.example.quotescollector.View;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quotescollector.Model.QuoteFull;
import com.example.quotescollector.R;

import java.util.List;

public class QuotesListAdapter extends RecyclerView.Adapter<QuotesListAdapter.QuotesViewHolder> {

    private List<QuoteFull> mQuotes;

    public static class QuotesViewHolder extends RecyclerView.ViewHolder {

        TextView quoteTextView, authorTextView;

        public QuotesViewHolder(View view) {
            super(view);

            this.quoteTextView = view.findViewById(R.id.quoteTV);
            this.authorTextView = view.findViewById(R.id.authorTV);
        }
    }

    public QuotesListAdapter(List<QuoteFull> mQuotes) {
        this.mQuotes = mQuotes;
    }


    @Override
    public QuotesListAdapter.QuotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.quote_row, parent, false);

        QuotesViewHolder vh = new QuotesViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(QuotesViewHolder holder, int position) {

        holder.quoteTextView.setText(mQuotes.get(position).quote);
        holder.authorTextView.setText(mQuotes.get(position).getAuthor());

    }

    @Override
    public int getItemCount() {
        return mQuotes.size();
    }


}
