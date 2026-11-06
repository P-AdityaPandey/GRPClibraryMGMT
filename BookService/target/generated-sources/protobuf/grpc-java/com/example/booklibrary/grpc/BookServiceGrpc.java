package com.example.booklibrary.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class BookServiceGrpc {

  private BookServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "booklibrary.BookService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.booklibrary.grpc.GetBookRequest,
      com.example.booklibrary.grpc.GetBookResponse> getGetBookMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBook",
      requestType = com.example.booklibrary.grpc.GetBookRequest.class,
      responseType = com.example.booklibrary.grpc.GetBookResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.booklibrary.grpc.GetBookRequest,
      com.example.booklibrary.grpc.GetBookResponse> getGetBookMethod() {
    io.grpc.MethodDescriptor<com.example.booklibrary.grpc.GetBookRequest, com.example.booklibrary.grpc.GetBookResponse> getGetBookMethod;
    if ((getGetBookMethod = BookServiceGrpc.getGetBookMethod) == null) {
      synchronized (BookServiceGrpc.class) {
        if ((getGetBookMethod = BookServiceGrpc.getGetBookMethod) == null) {
          BookServiceGrpc.getGetBookMethod = getGetBookMethod =
              io.grpc.MethodDescriptor.<com.example.booklibrary.grpc.GetBookRequest, com.example.booklibrary.grpc.GetBookResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBook"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.booklibrary.grpc.GetBookRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.booklibrary.grpc.GetBookResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BookServiceMethodDescriptorSupplier("GetBook"))
              .build();
        }
      }
    }
    return getGetBookMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.booklibrary.grpc.ListBooksRequest,
      com.example.booklibrary.grpc.ListBooksResponse> getListBooksMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListBooks",
      requestType = com.example.booklibrary.grpc.ListBooksRequest.class,
      responseType = com.example.booklibrary.grpc.ListBooksResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.booklibrary.grpc.ListBooksRequest,
      com.example.booklibrary.grpc.ListBooksResponse> getListBooksMethod() {
    io.grpc.MethodDescriptor<com.example.booklibrary.grpc.ListBooksRequest, com.example.booklibrary.grpc.ListBooksResponse> getListBooksMethod;
    if ((getListBooksMethod = BookServiceGrpc.getListBooksMethod) == null) {
      synchronized (BookServiceGrpc.class) {
        if ((getListBooksMethod = BookServiceGrpc.getListBooksMethod) == null) {
          BookServiceGrpc.getListBooksMethod = getListBooksMethod =
              io.grpc.MethodDescriptor.<com.example.booklibrary.grpc.ListBooksRequest, com.example.booklibrary.grpc.ListBooksResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListBooks"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.booklibrary.grpc.ListBooksRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.booklibrary.grpc.ListBooksResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BookServiceMethodDescriptorSupplier("ListBooks"))
              .build();
        }
      }
    }
    return getListBooksMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BookServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BookServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BookServiceStub>() {
        @java.lang.Override
        public BookServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BookServiceStub(channel, callOptions);
        }
      };
    return BookServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static BookServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BookServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BookServiceBlockingV2Stub>() {
        @java.lang.Override
        public BookServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BookServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return BookServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BookServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BookServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BookServiceBlockingStub>() {
        @java.lang.Override
        public BookServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BookServiceBlockingStub(channel, callOptions);
        }
      };
    return BookServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BookServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BookServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BookServiceFutureStub>() {
        @java.lang.Override
        public BookServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BookServiceFutureStub(channel, callOptions);
        }
      };
    return BookServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getBook(com.example.booklibrary.grpc.GetBookRequest request,
        io.grpc.stub.StreamObserver<com.example.booklibrary.grpc.GetBookResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetBookMethod(), responseObserver);
    }

    /**
     */
    default void listBooks(com.example.booklibrary.grpc.ListBooksRequest request,
        io.grpc.stub.StreamObserver<com.example.booklibrary.grpc.ListBooksResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListBooksMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service BookService.
   */
  public static abstract class BookServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return BookServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service BookService.
   */
  public static final class BookServiceStub
      extends io.grpc.stub.AbstractAsyncStub<BookServiceStub> {
    private BookServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BookServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BookServiceStub(channel, callOptions);
    }

    /**
     */
    public void getBook(com.example.booklibrary.grpc.GetBookRequest request,
        io.grpc.stub.StreamObserver<com.example.booklibrary.grpc.GetBookResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetBookMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listBooks(com.example.booklibrary.grpc.ListBooksRequest request,
        io.grpc.stub.StreamObserver<com.example.booklibrary.grpc.ListBooksResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListBooksMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service BookService.
   */
  public static final class BookServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<BookServiceBlockingV2Stub> {
    private BookServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BookServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BookServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public com.example.booklibrary.grpc.GetBookResponse getBook(com.example.booklibrary.grpc.GetBookRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getGetBookMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.booklibrary.grpc.ListBooksResponse listBooks(com.example.booklibrary.grpc.ListBooksRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getListBooksMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service BookService.
   */
  public static final class BookServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<BookServiceBlockingStub> {
    private BookServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BookServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BookServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.booklibrary.grpc.GetBookResponse getBook(com.example.booklibrary.grpc.GetBookRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetBookMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.booklibrary.grpc.ListBooksResponse listBooks(com.example.booklibrary.grpc.ListBooksRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListBooksMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service BookService.
   */
  public static final class BookServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<BookServiceFutureStub> {
    private BookServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BookServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BookServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.booklibrary.grpc.GetBookResponse> getBook(
        com.example.booklibrary.grpc.GetBookRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetBookMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.booklibrary.grpc.ListBooksResponse> listBooks(
        com.example.booklibrary.grpc.ListBooksRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListBooksMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_BOOK = 0;
  private static final int METHODID_LIST_BOOKS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_BOOK:
          serviceImpl.getBook((com.example.booklibrary.grpc.GetBookRequest) request,
              (io.grpc.stub.StreamObserver<com.example.booklibrary.grpc.GetBookResponse>) responseObserver);
          break;
        case METHODID_LIST_BOOKS:
          serviceImpl.listBooks((com.example.booklibrary.grpc.ListBooksRequest) request,
              (io.grpc.stub.StreamObserver<com.example.booklibrary.grpc.ListBooksResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetBookMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.booklibrary.grpc.GetBookRequest,
              com.example.booklibrary.grpc.GetBookResponse>(
                service, METHODID_GET_BOOK)))
        .addMethod(
          getListBooksMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.booklibrary.grpc.ListBooksRequest,
              com.example.booklibrary.grpc.ListBooksResponse>(
                service, METHODID_LIST_BOOKS)))
        .build();
  }

  private static abstract class BookServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    BookServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.booklibrary.grpc.BookLibraryProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("BookService");
    }
  }

  private static final class BookServiceFileDescriptorSupplier
      extends BookServiceBaseDescriptorSupplier {
    BookServiceFileDescriptorSupplier() {}
  }

  private static final class BookServiceMethodDescriptorSupplier
      extends BookServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    BookServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (BookServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BookServiceFileDescriptorSupplier())
              .addMethod(getGetBookMethod())
              .addMethod(getListBooksMethod())
              .build();
        }
      }
    }
    return result;
  }
}
