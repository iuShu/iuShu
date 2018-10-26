package org.iushu.config.component.xml;

/**
 * <bean id="xbean" class="xxx.xxx.XBean">
 *     <...> ... </...>
 * </bean>
 *
 * as above, the resolver could catch two DomProperty:
 *      1. DomProperty
 *
 * Created by iuShu on 18-10-25
 */
public class DomProperty {

    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DomProperty{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
