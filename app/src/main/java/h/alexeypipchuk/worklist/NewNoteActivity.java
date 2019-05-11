package h.alexeypipchuk.worklist;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewNoteActivity extends AppCompatActivity {

    EditText Caption;
    EditText Date;
    EditText Description;
    RadioGroup StatusGroup;
    RadioGroup ImportantGroup;
    FloatingActionButton btn;
    boolean isEdit = false;
    int position = 0;
    Pattern datePattern = Pattern.compile("^(0?[1-9]|1[0-2])[-](0?[1-9]|[12]\\d|3[01])[-](19|20)\\d{2}$");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        Bundle bundle = this.getIntent().getExtras();

        // собираем все введенные данные
        Caption = (EditText) findViewById(R.id.caption);
        Date = (EditText) findViewById(R.id.date);
        Description = (EditText) findViewById(R.id.description);
        StatusGroup = (RadioGroup) findViewById(R.id.StatusGroup);
        ImportantGroup = (RadioGroup) findViewById(R.id.ImportantGroup);
        if (bundle != null) {
            Caption.setText(bundle.getString("caption"));
            Date.setText(bundle.getString("date"));
            Description.setText(bundle.getString("description"));
            isEdit = true;
            position = bundle.getInt("position");
        }

        btn = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                // простенькая валидация обязательных полей
                Matcher dateMatcher = datePattern.matcher(Date.getText());
                if(!dateMatcher.find()) {
                    Toast.makeText(getApplicationContext(), "Введите дату в формате дд-мм-гггг", Toast.LENGTH_LONG).show();
                    return;
                }
                if(Caption.getText() == null || StatusGroup.getCheckedRadioButtonId() == -1 || ImportantGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Заполните обязательные поля", Toast.LENGTH_LONG).show();
                }
                else {
                    View StatusView = StatusGroup.findViewById(StatusGroup.getCheckedRadioButtonId());
                    RadioButton SattusRb = (RadioButton)StatusGroup.getChildAt(StatusGroup.indexOfChild(StatusView));
                    String StatusState = SattusRb.getText().toString();

                    View ImportantView = ImportantGroup.findViewById(ImportantGroup.getCheckedRadioButtonId());
                    RadioButton ImportantRb = (RadioButton)ImportantGroup.getChildAt(ImportantGroup.indexOfChild(ImportantView));
                    String ImportantState = ImportantRb.getText().toString();

                    // собрали данные и отправляем их наблюдателю, у которого адаптер позже заберет их и создаст новый объект модели

                    EventBus.getDefault().post(new ObserverSaveEditNewNote(Caption.getText().toString(), StatusState,
                            Description.getText().toString(), Date.getText().toString(), ImportantState, isEdit, position));
                }
            }
        });
    }

    ///////////////// этот наблюдатель ждет разрешения адаптера на переход обратно на главну активность
    // после успешного создания нового объекта модели
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ObserverNewNote event) {
        startActivity(new Intent(NewNoteActivity.this, MainActivity.class));
    }

    // подписка/отписка
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
}
