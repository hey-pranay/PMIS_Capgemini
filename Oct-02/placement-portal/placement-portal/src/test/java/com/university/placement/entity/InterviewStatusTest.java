package com.university.placement.entity;

import org.junit.jupiter.api.Test;

import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InterviewStatusTest {

    @Test
    void pipelineMovesForwardOneStepOrRejects() {
        assertEquals(EnumSet.of(InterviewStatus.SHORTLISTED, InterviewStatus.REJECTED),
                InterviewStatus.APPLIED.nextStates());
        assertEquals(EnumSet.of(InterviewStatus.INTERVIEWING, InterviewStatus.REJECTED),
                InterviewStatus.SHORTLISTED.nextStates());
        assertEquals(EnumSet.of(InterviewStatus.OFFERED, InterviewStatus.REJECTED),
                InterviewStatus.INTERVIEWING.nextStates());
    }

    @Test
    void cannotSkipStages() {
        assertFalse(InterviewStatus.APPLIED.canTransitionTo(InterviewStatus.OFFERED));
        assertFalse(InterviewStatus.APPLIED.canTransitionTo(InterviewStatus.INTERVIEWING));
        assertFalse(InterviewStatus.SHORTLISTED.canTransitionTo(InterviewStatus.OFFERED));
    }

    @Test
    void offeredAndRejectedAreFinal() {
        assertTrue(InterviewStatus.OFFERED.nextStates().isEmpty());
        assertTrue(InterviewStatus.REJECTED.nextStates().isEmpty());
    }

    @Test
    void pendingStatuses() {
        assertTrue(InterviewStatus.APPLIED.isPending());
        assertTrue(InterviewStatus.SHORTLISTED.isPending());
        assertTrue(InterviewStatus.INTERVIEWING.isPending());
        assertFalse(InterviewStatus.OFFERED.isPending());
        assertFalse(InterviewStatus.REJECTED.isPending());
    }

    @Test
    void parseIsCaseInsensitiveAndSafe() {
        assertEquals(InterviewStatus.OFFERED, InterviewStatus.parse(" offered ").orElseThrow());
        assertTrue(InterviewStatus.parse("hired").isEmpty());
        assertTrue(InterviewStatus.parse(null).isEmpty());
    }
}
