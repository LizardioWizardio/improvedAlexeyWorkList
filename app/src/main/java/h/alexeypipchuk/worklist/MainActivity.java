package h.alexeypipchuk.worklist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter()); //адаптер и modelview в одном
    }

    //////////////// передача данных конкретного дела в активити
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ObserverItemNote event) {
        Intent intent = new Intent(MainActivity.this, NewNoteActivity.class);
        intent.putExtra("caption", event.caption);
        intent.putExtra("date", event.date);
        intent.putExtra("description", event.description);
        intent.putExtra("importance", event.importance);
        intent.putExtra("status", event.status);
        intent.putExtra("isEdit", true);
        intent.putExtra("position", event.position);
        startActivity(intent);
    }

    // подписка/отписка от наблюдателей адаптера

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    ////////////////////////////////////////////// меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new_plan:
                Intent intent = new Intent(this, NewNoteActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
