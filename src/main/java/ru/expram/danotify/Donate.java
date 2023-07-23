package ru.expram.danotify;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;
import java.util.Date;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Donate {
    @JsonProperty("id")
    public int id;

    @JsonProperty("alert_type")
    public int alertType;

    @JsonProperty("is_shown")
    @JsonSerialize(using=NumericBooleanSerializer.class)
    @JsonDeserialize(using=NumericBooleanDeserializer.class)
    public boolean isShown;

    @JsonProperty("billing_system")
    public String billingSystem;

    @JsonProperty("billing_system_type")
    public String billingSystemType;

    @JsonProperty("username")
    public String userName;
    @JsonProperty("amount")
    public float amount;

    @JsonProperty("amount_formatted")
    public String amountFormatted;

    @JsonProperty("amount_main")
    public float amountMain;

    @JsonProperty("currency")
    public String currency;

    @JsonProperty("message")
    public String message;

    @JsonProperty("date_created")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public Date dateCreated;

    @JsonProperty("emotes")
    public String emotes;

    @JsonProperty("ap_id")
    public String apId;

    @JsonProperty("_is_test_alert")
    public boolean isTest;

    public static class NumericBooleanSerializer extends JsonSerializer<Boolean> {

        @Override
        public void serialize(Boolean bool, JsonGenerator generator, SerializerProvider provider) throws IOException, JsonProcessingException {
            generator.writeString(bool ? "1" : "0");
        }
    }

    public static class NumericBooleanDeserializer extends JsonDeserializer<Boolean> {

        @Override
        public Boolean deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
            return !"0".equals(parser.getText());
        }
    }
}
