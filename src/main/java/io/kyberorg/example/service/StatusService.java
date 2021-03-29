package io.kyberorg.example.service;

import io.kyberorg.example.model.Status;
import io.kyberorg.example.util.SystemStatus;
import io.kyberorg.example.dao.StatusDao;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Service, that reports components status.
 *
 * @since 1.0
 */
@Service
public class StatusService {
    private final StatusDao statusDao;

    /**
     * Spring's constructor.
     *
     * @param statusDao DAO that operates with {@link Status} table.
     */
    public StatusService(final StatusDao statusDao) {
        this.statusDao = statusDao;
    }

    /**
     * Reports application status.
     *
     * @return Always up, because if it works - app is running.
     */
    public SystemStatus getApplicationStatus() {
        //if can execute this - Application is up and running
        return SystemStatus.UP;
    }

    /**
     * Reports database status.
     *
     * @return {@link SystemStatus#UP} if we are able to retrieve record,
     * {@link SystemStatus#DOWN} - if we receive pseudo-record, that indicates failure.
     */
    public SystemStatus getDatabaseStatus() {
        boolean rawStatusFromDb = statusDao.findOne().getStatus();
        return SystemStatus.fromBoolean(rawStatusFromDb);
    }

    /**
     * Just4fun.
     *
     * @return returns that kinda status, based on {@link ThreadLocalRandom#current()}.
     */
    public SystemStatus getRandomStatus() {
       boolean rawStatus = ThreadLocalRandom.current().nextBoolean();
       return SystemStatus.fromBoolean(rawStatus);
    }
}
