package com.librarycommon.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubResponseVo {
    @JsonProperty("subResponseCode")
    private Object subResponseCode;

    @JsonProperty("subResponseMessage")
    private Object subResponseMessage;
}
