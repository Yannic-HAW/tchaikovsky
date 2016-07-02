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
package de.kaizencode.tchaikovsky.businterface;

import org.alljoyn.bus.BusException;
import org.alljoyn.bus.annotation.BusInterface;
import org.alljoyn.bus.annotation.BusMethod;

/**
 * {@link BusInterface} for <code>net.allplay.MCU</code> interface.
 * 
 * @author Dominic Lerbs
 */
@BusInterface(name = "net.allplay.MCU")
public interface MCUInterface {

    @BusMethod(name = "PlayItem", signature = "ssssxss")
    public void playItem(String url, String title, String artist, String thumbnailUrl, long duration, String album,
            String genre) throws BusException;

    @BusMethod(name = "GetCurrentItemUrl")
    public String getCurrentItemUrl() throws BusException;
}