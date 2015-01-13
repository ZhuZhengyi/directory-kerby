/**
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *  
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License. 
 *  
 */
package org.apache.kerberos.kerb.common;

import org.apache.haox.transport.tcp.DecodingCallback;
import org.apache.haox.transport.tcp.StreamingDecoder;

import java.nio.ByteBuffer;

public class KrbStreamingDecoder implements StreamingDecoder {

    @Override
    public void decode(ByteBuffer streamingBuffer, DecodingCallback callback) {
        if (streamingBuffer.remaining() >= 4) {
            int len = streamingBuffer.getInt();
            if (streamingBuffer.remaining() >= len) {
                callback.onMessageComplete(len + 4);
            } else {
                callback.onMoreDataNeeded(len + 4);
            }
        } else {
            callback.onMoreDataNeeded();
        }
    }
}
