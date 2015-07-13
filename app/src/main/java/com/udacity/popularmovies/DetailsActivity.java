package com.udacity.popularmovies;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class DetailsActivity extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailsFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class DetailsFragment extends Fragment {
        private final String LOG_TAG = DetailsFragment.class.getSimpleName();

        public DetailsFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_details, container, false);
            Intent intent = getActivity().getIntent();
            Bundle bundle = intent.getExtras();
            if (intent != null && bundle.containsKey(Movie.class.getName())) {
                Movie movie = (Movie)bundle.getParcelable(Movie.class.getName());
                ((TextView)rootView.findViewById(R.id.title)).setText(movie.getTitle());
                ((TextView)rootView.findViewById(R.id.rating)).setText("User Rating:" + movie.getVoteAverage() + "/10");
                ((TextView)rootView.findViewById(R.id.release_date)).setText("Release Date: " + movie.getReleaseDate());
                ((TextView)rootView.findViewById(R.id.synopsis)).setText(movie.getOverview());

                ImageView posterView = (ImageView)rootView.findViewById(R.id.poster);
                Picasso.with(getActivity()).load(Movie.getPosterUri(movie)).into(posterView);
            }


            return rootView;
        }
    }
}
