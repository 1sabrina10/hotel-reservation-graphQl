package org.example.hotel.graphql;

import graphql.language.IntValue;
import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphQLConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return builder -> builder
                .scalar(GraphQLScalarType.newScalar()
                        .name("Long")
                        .description("Java Long type")
                        .coercing(new Coercing<Long, Long>() {
                            @Override
                            public Long serialize(Object dataFetcherResult) {
                                return ((Number) dataFetcherResult).longValue();
                            }

                            @Override
                            public Long parseValue(Object input) {
                                return ((Number) input).longValue();
                            }

                            @Override
                            public Long parseLiteral(Object input) {
                                if (input instanceof IntValue) {
                                    return ((IntValue) input).getValue().longValue();
                                }
                                return null;
                            }
                        })
                        .build());
    }
}
