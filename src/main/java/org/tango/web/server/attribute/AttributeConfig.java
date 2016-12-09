package org.tango.web.server.attribute;

import fr.esrf.Tango.AttributeAlarm;
import fr.esrf.Tango.AttributeConfig_5;
import fr.esrf.Tango.EventProperties;
import org.apache.commons.beanutils.ConvertUtils;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tango.client.ez.data.type.TangoDataTypes;
import org.tango.client.ez.data.type.UnknownTangoDataType;
import org.tango.client.ez.util.TangoUtils;

import java.lang.reflect.Field;

/**
 * @author Igor Khokhriakov <igor.khokhriakov@hzg.de>
 * @since 8/8/16
 */
public class AttributeConfig {
    private final Logger logger = LoggerFactory.getLogger(AttributeConfig.class);

    public AttributeConfig_5 wrapped = new AttributeConfig_5();

    public void setWritable(String value){
        wrapped.writable = TangoUtils.attrWriteTypeFromString(value);
    }

    public void setData_format(String value){
        wrapped.data_format = TangoUtils.attrDataFormatFromString(value);
    }

    public void setData_type(String value) throws UnknownTangoDataType {
        wrapped.data_type = TangoDataTypes.forString(String.valueOf(value)).getAlias();
    }

    public void setLevel(String value){
        wrapped.level = TangoUtils.displayLevelFromString(value);
    }

    public void setAlarms(AttributeAlarm value){
        wrapped.att_alarm = value;
    }

    public void setEvents(EventProperties value){
        wrapped.event_prop = value;
    }

    public void setEnum_label(String[] value){
        wrapped.enum_labels = value;
    }

    @JsonAnySetter
    public void set(String name, Object value) throws IllegalAccessException {
        try {
            Field fld = AttributeConfig_5.class.getDeclaredField(name);
            Object converted = ConvertUtils.convert(value, fld.getType());
            fld.set(wrapped, converted);
        } catch (NoSuchFieldException e) {
            logger.warn("NoSuchFieldException: " + name);
        }
    }
}
