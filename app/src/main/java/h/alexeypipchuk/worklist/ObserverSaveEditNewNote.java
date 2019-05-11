package h.alexeypipchuk.worklist;

public class ObserverSaveEditNewNote {

    // наблюдатель, через который проходят данные для создания новой карточки
    String caption;
    String importance;
    String status;
    String date;
    String description;
    boolean isEdit;
    int position;

    ObserverSaveEditNewNote(String caption, String status, String description, String date, String importance, boolean isEdit, int position){
        this.caption = caption;
        this.importance = importance;
        this.status = status;
        this.date = date;
        this.description = description;
        this.isEdit = isEdit;
        this.position = position;
    }
}
