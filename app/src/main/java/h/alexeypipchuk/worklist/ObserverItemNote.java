package h.alexeypipchuk.worklist;

public class ObserverItemNote {

    int position;
    String caption;
    String importance;
    String status;
    String date;
    String description;

    ObserverItemNote(int position, String caption, String importance, String status, String date, String description) {
        this.caption = caption;
        this.importance = importance;
        this.status = status;
        this.date = date;
        this.description = description;
        this.position = position;
    }
}
