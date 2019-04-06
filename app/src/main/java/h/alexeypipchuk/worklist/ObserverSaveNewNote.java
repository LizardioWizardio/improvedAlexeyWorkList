package h.alexeypipchuk.worklist;

public class ObserverSaveNewNote {

    // наблюдатель, через который проходят данные для создания новой карточки
    String caption;
    String importance;
    String status;
    String date;
    String description;

    ObserverSaveNewNote(String caption, String status, String description, String date, String importance){
        this.caption = caption;
        this.importance = importance;
        this.status = status;
        this.date = date;
        this.description = description;
    }
}
