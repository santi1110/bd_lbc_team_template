package com.amazon.ata.music.playlist.service.dependency;
import com.amazon.ata.music.playlist.service.dynamodb.AlbumTrackDao;
import com.amazon.ata.music.playlist.service.dynamodb.PlaylistDao;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class DaoModule {
    @Provides
    @Singleton
    public AmazonDynamoDB provideAmazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.defaultClient();
    }

    @Provides
    @Singleton
    public DynamoDBMapper provideDynamoDBMapper(AmazonDynamoDB amazonDynamoDB) {
        return new DynamoDBMapper(amazonDynamoDB);
    }

    @Provides
    @Singleton
    public AlbumTrackDao provideAlbumTrackDao(DynamoDBMapper dynamoDbMapper) {
        return new AlbumTrackDao(dynamoDbMapper);
    }

    @Provides
    @Singleton
    public PlaylistDao providePlaylistDao(DynamoDBMapper dynamoDbMapper) {
        return new PlaylistDao(dynamoDbMapper);
    }
}
