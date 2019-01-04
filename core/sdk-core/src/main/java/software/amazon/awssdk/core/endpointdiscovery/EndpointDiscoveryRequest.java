/*
 * Copyright 2010-2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package software.amazon.awssdk.core.endpointdiscovery;

import java.util.Map;
import java.util.Optional;
import software.amazon.awssdk.annotations.SdkInternalApi;
import software.amazon.awssdk.utils.builder.CopyableBuilder;
import software.amazon.awssdk.utils.builder.ToCopyableBuilder;

@SdkInternalApi
public final class EndpointDiscoveryRequest
    implements ToCopyableBuilder<EndpointDiscoveryRequest.Builder, EndpointDiscoveryRequest> {

    private final Optional<String> operationName;
    private final Optional<Map<String, String>> identifiers;
    private final Optional<String> cacheKey;
    private final boolean required;

    private EndpointDiscoveryRequest(BuilderImpl builder) {
        this.operationName = builder.operationName;
        this.identifiers = builder.identifiers;
        this.cacheKey = builder.cacheKey;
        this.required = builder.required;
    }

    public Optional<String> operationName() {
        return operationName;
    }

    public Optional<Map<String, String>> identifiers() {
        return identifiers;
    }

    public Optional<String> cacheKey() {
        return cacheKey;
    }

    public boolean required() {
        return required;
    }

    public static Builder builder() {
        return new BuilderImpl();
    }

    @Override
    public Builder toBuilder() {
        return new BuilderImpl(this);
    }

    public interface Builder extends CopyableBuilder<Builder, EndpointDiscoveryRequest> {
        Builder operationName(String operationName);

        Builder identifiers(Map<String, String> identifiers);

        Builder cacheKey(String cacheKey);

        Builder required(boolean required);
    }

    static class BuilderImpl implements Builder {
        private Optional<String> operationName = Optional.empty();
        private Optional<Map<String, String>> identifiers = Optional.empty();
        private Optional<String> cacheKey = Optional.empty();
        private boolean required = false;

        private BuilderImpl() {

        }

        private BuilderImpl(EndpointDiscoveryRequest request) {
            this.operationName = request.operationName;
            this.identifiers = request.identifiers;
            this.cacheKey = request.cacheKey;
            this.required = request.required;
        }

        @Override
        public Builder operationName(String operationName) {
            this.operationName = Optional.ofNullable(operationName);
            return this;
        }

        @Override
        public Builder identifiers(Map<String, String> identifiers) {
            this.identifiers = Optional.ofNullable(identifiers);
            return this;
        }

        @Override
        public Builder cacheKey(String cacheKey) {
            this.cacheKey = Optional.ofNullable(cacheKey);
            return this;
        }

        @Override
        public Builder required(boolean required) {
            this.required = required;
            return this;
        }

        @Override
        public EndpointDiscoveryRequest build() {
            return new EndpointDiscoveryRequest(this);
        }
    }
}
