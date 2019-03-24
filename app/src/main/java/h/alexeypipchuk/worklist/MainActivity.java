package h.alexeypipchuk.worklist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] caption = new String[Note.notes.size()];
        for (int i = 0; i < caption.length; i++) caption[i] = Note.notes.get(i).getmCaption();
        String[] date = new String[Note.notes.size()];
        for (int i = 0; i < date.length; i++) date[i] = Note.notes.get(i).getmDate();
        String[] importantce = new String[Note.notes.size()];
        for (int i = 0; i < importantce.length; i++) importantce[i] = Note.notes.get(i).getmImportance();
        String[] status = new String[Note.notes.size()];
        for (int i = 0; i < status.length; i++) status[i] = Note.notes.get(i).getmStatus();
        String[] description = new String[Note.notes.size()];
        for (int i = 0; i < description.length; i++) description[i] = Note.notes.get(i).getmDescription();

        myAdapter = new MyAdapter(caption, date, importantce, status, description);

        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);

        myAdapter.setListener(new MyAdapter.Listener() {
            public void onClick(int position) {
                Intent intent = new Intent(MainActivity.this, NoteDetail.class);
                intent.putExtra(NoteDetail.EXTRA_NOTE_NO, position);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new_plan:
                Intent intent = new Intent(this, Plan.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
