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

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.alljoyn.bus.BusException;
import org.alljoyn.bus.Variant;
import org.alljoyn.bus.VariantTypeReference;
import org.alljoyn.bus.annotation.Position;
import org.alljoyn.bus.annotation.Signature;

import de.kaizencode.tchaikovsky.speaker.ZoneInfo;

public class RemoteZoneInfo implements ZoneInfo {

    @Position(0)
    @Signature("s")
    public String zoneId;

    @Position(1)
    @Signature("i")
    public int zoneTimestamp;

    @Position(2)
    @Signature("v")
    public Variant leadPlayerName;

    @Override
    public String getZoneId() {
        return zoneId;
    }

    @Override
    public int getZoneTimestamp() {
        return zoneTimestamp;
    }

    // @Override
    public void getLeadPlayerName() {
        try {
            if ("s".equals(leadPlayerName.getSignature())) {
                System.out.println("leadPlayerName " + leadPlayerName.getObject(String.class));
            } else if ("a{si}".equals(leadPlayerName.getSignature())) {
                Map<String, Integer> map = leadPlayerName
                        .getObject(new VariantTypeReference<TreeMap<String, Integer>>() {
                        });
                System.out.println("Size: " + map.size());
                for (Entry<String, Integer> entry : map.entrySet()) {
                    System.out.println("String: " + entry.getKey() + ", " + entry.getValue());
                }
            } else {
                System.out.println("Signature: " + leadPlayerName.getSignature());
            }
        } catch (BusException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // return leadPlayerName;
    }

    @Override
    public String toString() {
        return zoneId + "-" + zoneTimestamp + "-" + leadPlayerName;
    }

}
