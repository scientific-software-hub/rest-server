package org.tango.web.server;


import org.junit.Test;
import org.tango.TangoRestServer;
import org.tango.client.ez.proxy.TangoProxies;
import org.tango.client.ez.proxy.TangoProxy;

import static org.junit.Assert.assertTrue;

public class ITAccessControlTest {
    private final String tangoHost = System.getenv("TANGO_HOST");

    @Test
    public void testCheckUserCanRead() throws Exception {
        TangoProxy proxy = TangoProxies.newDeviceProxyWrapper("tango://" + tangoHost + "/" + TangoRestServer.SYS_ACCESS_CONTROL_1);
        AccessControl instance = new AccessControl(proxy);

        assertTrue(instance.checkUserCanRead("test", "127.0.0.1", "sys/tg_test/1"));
    }

}