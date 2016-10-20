/**
 * Tchaikovsky - A Java library for controlling AllPlay-compatible devices.
 * Copyright (c) 2016 Dominic Lerbs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.kaizencode.tchaikovsky.speaker.remote;

import org.alljoyn.bus.BusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.kaizencode.tchaikovsky.businterface.ZoneManagerInterface;
import de.kaizencode.tchaikovsky.speaker.ZoneManager;
import de.kaizencode.tchaikovsky.exception.SpeakerException;

public class RemoteZoneManager implements ZoneManager {
    
    private final Logger logger = LoggerFactory.getLogger(RemoteVolume.class);
    private final ZoneManagerInterface zoneManagerInterface;

    public RemoteZoneManager(ZoneManagerInterface zoneManagerInterface) {
        this.zoneManagerInterface = zoneManagerInterface;
    }

    @Override
    public boolean getEnabled() throws SpeakerException {
        try {
            return zoneManagerInterface.getEnabled();
        } catch (BusException e) {
            throw new SpeakerException("Unable to receive enabled", e);
        }
    }

    @Override
    public short getVersion() throws SpeakerException {
        try {
            return zoneManagerInterface.getVersion();
        } catch (BusException e) {
            throw new SpeakerException("Unable to receive version", e);
        }
    }

    @Override
    public RemoteZoneItem createZone(String[] speakers) throws SpeakerException {
        logger.debug("Try to create zone " + speakers.toString());
        try {
            return zoneManagerInterface.createZone(speakers);
        } catch (BusException e) {
            throw new SpeakerException("Unable to create zone", e);
        }
    }

}
