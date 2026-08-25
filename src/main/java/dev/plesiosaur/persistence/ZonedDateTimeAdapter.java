package dev.plesiosaur.persistence;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeAdapter extends XmlAdapter<String, ZonedDateTime> {


    @Override
    public ZonedDateTime unmarshal(String v) throws Exception {
        return v == null ? null : ZonedDateTime.parse(v);
    }

    @Override
    public String marshal(ZonedDateTime v) throws Exception {
        return v == null ? null : v.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }
}
