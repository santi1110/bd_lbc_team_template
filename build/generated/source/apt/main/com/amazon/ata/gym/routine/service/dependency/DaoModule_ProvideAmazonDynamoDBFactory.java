package com.amazon.ata.gym.routine.service.dependency;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaoModule_ProvideAmazonDynamoDBFactory implements Factory<AmazonDynamoDB> {
  private final DaoModule module;

  public DaoModule_ProvideAmazonDynamoDBFactory(DaoModule module) {
    this.module = module;
  }

  @Override
  public AmazonDynamoDB get() {
    return Preconditions.checkNotNull(
        module.provideAmazonDynamoDB(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static DaoModule_ProvideAmazonDynamoDBFactory create(DaoModule module) {
    return new DaoModule_ProvideAmazonDynamoDBFactory(module);
  }

  public static AmazonDynamoDB proxyProvideAmazonDynamoDB(DaoModule instance) {
    return Preconditions.checkNotNull(
        instance.provideAmazonDynamoDB(),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
