package org.apache.rocketmq.client.remoting;

import apache.rocketmq.v1.AckMessageRequest;
import apache.rocketmq.v1.AckMessageResponse;
import apache.rocketmq.v1.EndTransactionRequest;
import apache.rocketmq.v1.EndTransactionResponse;
import apache.rocketmq.v1.HealthCheckRequest;
import apache.rocketmq.v1.HealthCheckResponse;
import apache.rocketmq.v1.HeartbeatRequest;
import apache.rocketmq.v1.HeartbeatResponse;
import apache.rocketmq.v1.MultiplexingRequest;
import apache.rocketmq.v1.MultiplexingResponse;
import apache.rocketmq.v1.NackMessageRequest;
import apache.rocketmq.v1.NackMessageResponse;
import apache.rocketmq.v1.PullMessageRequest;
import apache.rocketmq.v1.PullMessageResponse;
import apache.rocketmq.v1.QueryAssignmentRequest;
import apache.rocketmq.v1.QueryAssignmentResponse;
import apache.rocketmq.v1.QueryOffsetRequest;
import apache.rocketmq.v1.QueryOffsetResponse;
import apache.rocketmq.v1.QueryRouteRequest;
import apache.rocketmq.v1.QueryRouteResponse;
import apache.rocketmq.v1.ReceiveMessageRequest;
import apache.rocketmq.v1.ReceiveMessageResponse;
import apache.rocketmq.v1.SendMessageRequest;
import apache.rocketmq.v1.SendMessageResponse;
import apache.rocketmq.v1.SendMessageToDeadLetterQueueRequest;
import apache.rocketmq.v1.SendMessageToDeadLetterQueueResponse;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.Metadata;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * Client for all explicit RPC in RocketMQ.
 */
public interface RpcClient {

    long idleSeconds();

    /**
     * Shutdown the client.
     */
    void shutdown();

    /**
     * Query topic route asynchronously.
     *
     * @param metadata gRPC request header metadata.
     * @param request  query route request.
     * @param executor gRPC asynchronous executor.
     * @param duration request max duration.
     * @param timeUnit duration time unit.
     * @return response future of topic route.
     */
    ListenableFuture<QueryRouteResponse> queryRoute(Metadata metadata, QueryRouteRequest request, Executor executor,
                                                    long duration, TimeUnit timeUnit);

    /**
     * Heart beat asynchronously.
     *
     * @param metadata gRPC request header metadata.
     * @param request  heart beat request.
     * @param executor gRPC asynchronous executor.
     * @param duration request max duration.
     * @param timeUnit duration time unit.
     * @return response future of heart beat.
     */
    ListenableFuture<HeartbeatResponse> heartbeat(Metadata metadata, HeartbeatRequest request, Executor executor,
                                                  long duration, TimeUnit timeUnit);

    /**
     * Asynchronous health check for producer.
     *
     * @param metadata gRPC request header metadata.
     * @param request  health check request.
     * @param executor gRPC asynchronous executor.
     * @param duration request max duration.
     * @param timeUnit duration time timeUnit
     * @return response future of health check response
     */
    ListenableFuture<HealthCheckResponse> healthCheck(Metadata metadata, HealthCheckRequest request,
                                                      Executor executor, long duration, TimeUnit timeUnit);

    /**
     * Send message asynchronously.
     *
     * @param metadata gRPC request header metadata.
     * @param request  send message request.
     * @param executor gRPC asynchronous executor.
     * @param duration request max duration.
     * @param timeUnit duration time unit.
     * @return response future of sending message.
     */
    ListenableFuture<SendMessageResponse> sendMessage(Metadata metadata, SendMessageRequest request,
                                                      Executor executor, long duration, TimeUnit timeUnit);

    /**
     * Query assignment asynchronously.
     *
     * @param metadata gRPC request header metadata.
     * @param request  query assignment request.
     * @param executor gRPC asynchronous executor.
     * @param duration request max duration.
     * @param timeUnit duration time unit.
     * @return response future of query assignment.
     */
    ListenableFuture<QueryAssignmentResponse> queryAssignment(Metadata metadata, QueryAssignmentRequest request,
                                                              Executor executor, long duration, TimeUnit timeUnit);

    /**
     * Receiving message asynchronously from server.
     *
     * @param metadata gRPC request header metadata.
     * @param request  receiving message request.
     * @param executor gRPC asynchronous executor.
     * @param duration request max duration.
     * @param timeUnit duration time unit.
     * @return response future of receiving message
     */
    ListenableFuture<ReceiveMessageResponse> receiveMessage(Metadata metadata, ReceiveMessageRequest request,
                                                            Executor executor, long duration, TimeUnit timeUnit);

    /**
     * Ack message asynchronously after consuming.
     *
     * @param metadata gRPC request header metadata.
     * @param request  ack message request.
     * @param executor gRPC asynchronous executor.
     * @param duration request max duration.
     * @param timeUnit duration time unit.
     * @return response future of ack message.
     */
    ListenableFuture<AckMessageResponse> ackMessage(Metadata metadata, AckMessageRequest request, Executor executor,
                                                    long duration, TimeUnit timeUnit);

    /**
     * Nack message asynchronously after consuming failure
     *
     * @param metadata gRPC request header metadata.
     * @param request  nack message request.
     * @param executor gRPC asynchronous executor.
     * @param duration request max duration.
     * @param timeUnit duration time unit.
     * @return response future of ack message.
     */
    ListenableFuture<NackMessageResponse> nackMessage(Metadata metadata, NackMessageRequest request, Executor executor,
                                                      long duration, TimeUnit timeUnit);

    /**
     * Send message to dead letter queue.
     *
     * @param metadata gRPC request header metadata.
     * @param request  request of sending message to DLQ.
     * @param executor gRPC asynchronous executor.
     * @param duration request max duration.
     * @param timeUnit duration time unit.
     * @return response future of sending message to DLQ.
     */
    ListenableFuture<SendMessageToDeadLetterQueueResponse> sendMessageToDeadLetterQueue(
            Metadata metadata, SendMessageToDeadLetterQueueRequest request, Executor executor, long duration,
            TimeUnit timeUnit);

    /**
     * Submit transaction resolution
     *
     * @param metadata gRPC request header metadata.
     * @param request  end transaction request.
     * @param executor gRPC asynchronous executor.
     * @param duration request max duration.
     * @param timeUnit duration time unit.
     * @return response future of submitting transaction resolution.
     */
    ListenableFuture<EndTransactionResponse> endTransaction(Metadata metadata, EndTransactionRequest request,
                                                            Executor executor, long duration, TimeUnit timeUnit);

    /**
     * Query offset for pull
     *
     * @param metadata gRPC request header metadata.
     * @param request  query offset request.
     * @param executor gRPC asynchronous executor.
     * @param duration request max duration.
     * @param timeUnit duration time unit.
     * @return response future of query offset.
     */
    ListenableFuture<QueryOffsetResponse> queryOffset(Metadata metadata, QueryOffsetRequest request, Executor executor,
                                                      long duration, TimeUnit timeUnit);

    /**
     * Pull message from remote
     *
     * @param metadata gRPC request header metadata.
     * @param request  pull message request.
     * @param executor gRPC asynchronous executor.
     * @param duration request max duration.
     * @param timeUnit duration time unit.
     * @return response future of pull message.
     */
    ListenableFuture<PullMessageResponse> pullMessage(Metadata metadata, PullMessageRequest request,
                                                      Executor executor, long duration, TimeUnit timeUnit);

    /**
     * Multiplexing call for composited request.
     *
     * @param metadata gRPC request header metadata.
     * @param request  multiplexing call request.
     * @param executor gRPC asynchronous executor.
     * @param duration request max duration.
     * @param timeUnit duration time unit.
     * @return response future of multiplexing call.
     */
    ListenableFuture<MultiplexingResponse> multiplexingCall(Metadata metadata, MultiplexingRequest request,
                                                            Executor executor, long duration, TimeUnit timeUnit);
}
