/**
 * Copyright (c) 2010-2019 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.smarthome.core.thing.internal.profiles;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.eclipse.smarthome.core.library.types.OnOffType;
import org.eclipse.smarthome.core.thing.CommonTriggerEvents;
import org.eclipse.smarthome.core.thing.profiles.ProfileCallback;
import org.eclipse.smarthome.core.thing.profiles.TriggerProfile;
import org.eclipse.smarthome.core.types.Command;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

/**
 * Tests for the system:rawbutton-on-off-switch profile
 *
 * @author Mark Hilbush - Initial contribution
 */
public class RawButtonOnOffSwitchProfileTest {

    @Mock
    private ProfileCallback mockCallback;

    @Before
    public void setup() {
        mockCallback = mock(ProfileCallback.class);
    }

    @Test
    public void testOnOffSwitchItem() {
        TriggerProfile profile = new RawButtonOnOffSwitchProfile(mockCallback);
        verifyAction(profile, CommonTriggerEvents.PRESSED, OnOffType.ON);
        verifyAction(profile, CommonTriggerEvents.RELEASED, OnOffType.OFF);
    }

    private void verifyAction(TriggerProfile profile, String trigger, Command expectation) {
        reset(mockCallback);
        profile.onTriggerFromHandler(trigger);
        verify(mockCallback, times(1)).sendCommand(eq(expectation));
    }
}
