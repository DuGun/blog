package util;

import com.nimbusds.jose.JOSEException;

public class Test {

    @org.junit.Test
    public void show() throws JOSEException {

        SnowFlake snowFlake = new SnowFlake(2, 3);
        for (int i = 0; i < (1 << 12); i++) {
            System.out.println(snowFlake.nextId());
        }

    }
}
