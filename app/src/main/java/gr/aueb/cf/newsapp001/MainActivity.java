package gr.aueb.cf.newsapp001;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.ArrayList;

import gr.aueb.cf.newsapp001.adapters.MyAdapter;
import gr.aueb.cf.newsapp001.models.News;

public class MainActivity extends AppCompatActivity {

    private String[] newsHeadings;
    private int[] newsImages;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private ArrayList<News> newsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Daily News");

        newsArrayList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setData();
        getData();
    }

    private void getData() {
        for (int i = 0; i < newsHeadings.length; i++) {
            News news = new News(newsHeadings[i], newsImages[i]);
            newsArrayList.add(news);
        }

        myAdapter = new MyAdapter(this, newsArrayList);
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }

    private void setData() {
        newsHeadings = new String[]{
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                "Nunc aliquet nisl ut consequat cursus.",
                "Maecenas malesuada neque sit amet varius tincidunt.",
                "Sed eu arcu feugiat, gravida justo id, condimentum nulla.",
                "Morbi at orci et purus pellentesque dapibus.",
                "In lobortis felis sit amet tortor molestie, quis imperdiet nibh sodales.",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                "Nunc aliquet nisl ut consequat cursus.",
                "Maecenas malesuada neque sit amet varius tincidunt.",
                "Sed eu arcu feugiat, gravida justo id, condimentum nulla.",
                "Morbi at orci et purus pellentesque dapibus.",
                "In lobortis felis sit amet tortor molestie, quis imperdiet nibh sodales."
        };

        newsImages = new int[] {
                R.drawable.image01,
                R.drawable.image02,
                R.drawable.image03,
                R.drawable.image04,
                R.drawable.image05,
                R.drawable.image02,
                R.drawable.image01,
                R.drawable.image02,
                R.drawable.image03,
                R.drawable.image04,
                R.drawable.image05,
                R.drawable.image02
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_items, menu);

        MenuItem menuItem = menu.findItem(R.id.search_action);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Search here...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}