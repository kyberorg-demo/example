package io.kyberorg.example.event;

import io.kyberorg.example.model.Record;
import lombok.Data;

@Data
public class RecordSavedEvent {
    private Record savedRecord;

    /**
     * Creates event object from provided record.
     *
     * @param record {@link Record} to send in event
     * @return event object
     */
    public static RecordSavedEvent createWith(final Record record) {
        RecordSavedEvent event = new RecordSavedEvent();
        event.savedRecord = record;
        return event;
    }
}
