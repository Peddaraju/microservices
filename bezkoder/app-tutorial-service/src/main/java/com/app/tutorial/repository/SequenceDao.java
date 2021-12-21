package com.app.tutorial.repository;

import com.app.tutorial.exception.SequenceException;

public interface SequenceDao {
    long getNextSequenceId(String key) throws SequenceException;
}
