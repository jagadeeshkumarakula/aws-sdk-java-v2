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

package software.amazon.awssdk.modulepath.tests.integtests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

public class S3ApiCall extends BaseApiCall {
    private static final Logger logger = LoggerFactory.getLogger(S3ApiCall.class);

    private S3Client s3Client = S3Client.builder()
                                        .region(Region.US_WEST_2)
                                        .httpClient(ApacheHttpClient.builder().build())
                                        .credentialsProvider(CREDENTIALS_PROVIDER_CHAIN)
                                        .build();

    private S3Client s3ClientWithHttpUrlConnection = S3Client.builder()
                                                             .region(Region.US_WEST_2)
                                                             .httpClient(UrlConnectionHttpClient.builder().build())
                                                             .credentialsProvider(CREDENTIALS_PROVIDER_CHAIN).build();

    public S3ApiCall() {
        super("s3");
    }

    @Override
    public Runnable apacheClientRunnable() {
        return () -> s3Client.listBuckets();
    }

    @Override
    public Runnable urlHttpConnectionClientRunnable() {
        return () -> s3ClientWithHttpUrlConnection.listBuckets();
    }

    //TODO: testing netty client once it's fixed
    @Override
    public Runnable nettyClientRunnable() {
        return () -> logger.info("Skipping testing s3 client with netty client");
    }
}
