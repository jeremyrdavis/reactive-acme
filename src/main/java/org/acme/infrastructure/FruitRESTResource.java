package org.acme.infrastructure;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import org.acme.domain.Fruit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/fruits")
@ApplicationScoped
public class FruitRESTResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(FruitRESTResource.class);

    @GET
    public Uni<List<Fruit>> getAll() {
        return Fruit.listAll(Sort.by("name"));
    }

    @GET
    @Path("/{id}")
    public Uni<Fruit> getById(final Long id){

        return Fruit.findById(id);
    }

    @POST
    public Uni<Response> post(Fruit fruit) {

        return Panache.<Fruit>withTransaction(fruit::persist)
                .onItem()
                .transform(inserted -> Response.created(URI.create("/fruits/" + inserted.id)).build());
    }
}
