package sprint7.courier;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {
    public Courier generic(){
        return new Courier("abcd", "12345", "alex");
    }

    public Courier random(){
        return new Courier(RandomStringUtils.randomAlphanumeric(10), "12345", "alex");
    }
}

