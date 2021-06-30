package io.kyberorg.example.service;

import io.kyberorg.example.dao.RecordDao;
import io.kyberorg.example.event.RecordSavedEvent;
import io.kyberorg.example.model.Record;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.greenrobot.eventbus.EventBus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service, that reports components status.
 *
 * @since 1.0
 */
@Service
public class RecordService {
    private final RecordDao recordDao;

    /**
     * Spring's constructor.
     *
     * @param recordDao DAO that operates with {@link io.kyberorg.example.model.Record} table.
     */
    public RecordService(final RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    /**
     * Writes new record with given text or updates existing record.
     *
     * @param author record's author
     * @param text record's text
     * @return true - if written successfully, false - if failed.
     */
    public boolean writeRecord(final String author, final String text) {
        if (StringUtils.isAllBlank(author, text)) {
            return false;
        }

        Record newRecord = new Record();
        newRecord.setAuthor(author);
        newRecord.setRecord(text);
        recordDao.save(newRecord);

        EventBus.getDefault().post(RecordSavedEvent.createWith(newRecord));
        return true;
    }

    /**
     * Gets all records.
     *
     * @return list of stored records.
     */
    public List<Record> getAllRecords() {
        return Lists.newArrayList(recordDao.findAll().iterator());
    }

    /**
     * Counts how many records are stored.
     *
     * @return number of stored records.
     */
    public long countRecords() {
        return recordDao.count();
    }
}
