/*
 * Copyright 2010 Ning, Inc.
 *
 * Ning licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.ning.metrics.eventtracker;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.ning.metrics.serialization.writer.DiskSpoolEventWriter;
import com.ning.metrics.serialization.writer.ThresholdEventWriter;

class ThresholdEventWriterProvider implements Provider<ThresholdEventWriter>
{
    private final DiskSpoolEventWriter eventWriter;
    private final long flushEventQueueSize;
    private final int refreshDelayInSeconds;

    @Inject
    public ThresholdEventWriterProvider(
        DiskSpoolEventWriter eventWriter,
        EventTrackerConfig config
    )
    {
        this.eventWriter = eventWriter;
        this.flushEventQueueSize = config.getFlushEventQueueSize();
        this.refreshDelayInSeconds = config.getRefreshDelayInSeconds();
    }

    @Override
    public ThresholdEventWriter get()
    {
        return new ThresholdEventWriter(eventWriter, flushEventQueueSize, refreshDelayInSeconds);
    }
}
