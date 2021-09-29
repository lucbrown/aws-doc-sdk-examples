//snippet-sourcedescription:[CreateDataStream.java demonstrates how to create an Amazon Kinesis data stream.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-keyword:[Amazon Kinesis]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[09/28/2021]
//snippet-sourceauthor:[scmacdon - AWS]
/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.kinesis;

//snippet-start:[kinesis.java2.create.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kinesis.KinesisClient;
import software.amazon.awssdk.services.kinesis.model.CreateStreamRequest;
import software.amazon.awssdk.services.kinesis.model.KinesisException;
//snippet-end:[kinesis.java2.create.import]

/**
 * To run this Java V2 code example, ensure that you have setup your development environment, including your credentials.
 *
 * For information, see this documentation topic:
 *
 * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */
public class CreateDataStream {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage:\n" +
                "    <streamName>\n\n" +
                "Where:\n" +
                "    streamName - The Amazon Kinesis data stream (for example, StockTradeStream).\n\n" ;

        if (args.length != 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String streamName = args[0];
        Region region = Region.US_EAST_1;
        KinesisClient kinesisClient = KinesisClient.builder()
                .region(region)
                .build();

        createStream(kinesisClient, streamName);
        System.out.println("Done");
        kinesisClient.close();
    }

    // snippet-start:[kinesis.java2.create.main]
    public static void createStream(KinesisClient kinesisClient, String streamName) {

        try {
            CreateStreamRequest streamReq = CreateStreamRequest.builder()
                    .streamName(streamName)
                    .shardCount(1)
                    .build();

            kinesisClient.createStream(streamReq);
        } catch (KinesisException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
     }
    // snippet-end:[kinesis.java2.create.main]
}