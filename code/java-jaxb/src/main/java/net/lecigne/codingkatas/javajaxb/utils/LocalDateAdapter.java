package net.lecigne.codingkatas.javajaxb.utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(String d) {
        return LocalDate.parse(d);
    }

    @Override
    public String marshal(LocalDate d) {
        return d.toString();
    }

}
