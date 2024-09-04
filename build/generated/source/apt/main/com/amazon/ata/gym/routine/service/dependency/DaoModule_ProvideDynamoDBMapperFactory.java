package com.amazon.ata.gym.routine.service.dependency;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaoModule_ProvideDynamoDBMapperFactory implements Factory<DynamoDBMapper> {
  private final DaoModule module;

  private final Provider<AmazonDynamoDB> amazonDynamoDBProvider;

  public DaoModule_ProvideDynamoDBMapperFactory(
      DaoModule module, Provider<AmazonDynamoDB> amazonDynamoDBProvider) {
    this.module = module;
    this.amazonDynamoDBProvider = amazonDynamoDBProvider;
  }

  @Override
  public DynamoDBMapper get() {
    return Preconditions.checkNotNull(
        module.provideDynamoDBMapper(amazonDynamoDBProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static DaoModule_ProvideDynamoDBMapperFactory create(
      DaoModule module, Provider<AmazonDynamoDB> amazonDynamoDBProvider) {
    return new DaoModule_ProvideDynamoDBMapperFactory(module, amazonDynamoDBProvider);
  }

  public static DynamoDBMapper proxyProvideDynamoDBMapper(
      DaoModule instance, AmazonDynamoDB amazonDynamoDB) {
    return Preconditions.checkNotNull(
        instance.provideDynamoDBMapper(amazonDynamoDB),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
