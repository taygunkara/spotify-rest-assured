
package com.spotify.oauth2.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

    @JsonProperty("error")
    private InnerRoot innerRoot;

    @JsonProperty("error")
    public InnerRoot getError() {
        return innerRoot;
    }

    @JsonProperty("error")
    public void setError(InnerRoot innerRoot) {
        this.innerRoot = innerRoot;
    }

}
