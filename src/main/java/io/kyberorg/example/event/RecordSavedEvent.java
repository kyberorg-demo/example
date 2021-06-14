package io.kyberorg.example.event;

import io.kyberorg.example.model.Record;
import lombok.Data;

@Data
public class RecordSavedEvent {
    private Record savedRecord;

    public static RecordSavedEvent createWith(Record record) {
        RecordSavedEvent event = new RecordSavedEvent();
        event.savedRecord = record;
        return event;
    }
}
