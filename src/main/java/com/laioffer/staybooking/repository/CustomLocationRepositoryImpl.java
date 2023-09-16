package com.laioffer.staybooking.repository;


import com.laioffer.staybooking.model.Location;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;


import java.util.List;
import java.util.stream.Collectors;


public class CustomLocationRepositoryImpl implements CustomLocationRepository {


    private static final String DEFAULT_DISTANCE = "50";
    private final ElasticsearchOperations elasticsearchOperations;


    public CustomLocationRepositoryImpl(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }


    @Override
    public List<Long> searchByDistance(double lat, double lon, String distance) {
        if (distance == null || distance.isEmpty()) {
            distance = DEFAULT_DISTANCE;
        }
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withFilter(new GeoDistanceQueryBuilder("geoPoint").point(lat, lon).distance(distance, DistanceUnit.KILOMETERS));


        SearchHits<Location> searchResult = elasticsearchOperations.search(queryBuilder.build(), Location.class);
        return searchResult.getSearchHits().stream()
                .map(hit -> hit.getContent().getId())
                .collect(Collectors.toList());
    }
}
