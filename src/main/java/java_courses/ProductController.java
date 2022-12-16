package java_courses;

import dao.OrganizationDAO;
import dao.ProductDAO;
import entity.Organization;
import entity.Product;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/products")
public class ProductController {

    private final ProductDAO productDao;
    private final OrganizationDAO organizationDao;

    public ProductController() {
        productDao = new ProductDAO();
        organizationDao = new OrganizationDAO();
    }

    //curl  -H "Content-Type: application/json" -X POST -d "{\"product_name\":\"yellow\",\"organization_name\":\"renault\",\"amount\":123}" http://localhost:3466/products/add
    @RolesAllowed("manager")
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Product p) {
        if (!organizationDao.dbHas(p.getOrgName()))
            organizationDao.save(new Organization(p.getOrgName()));
        productDao.save(p);

        return Response.status(Response.Status.OK).build();
    }

    //http://localhost:3466/products/all

    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAll() {
        return productDao.all();

    }

    //curl  -H "Content-Type: application/json" -X POST -d "yellow" http://localhost:3466/products/delete
    @RolesAllowed("manager")
    @Path("/delete")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProductByName(String name) {
        if (productDao.deleteByName(name))
            return Response.status(Response.Status.OK).build();

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    //http://localhost:3466/products/getByOrganization/org2
    @Path("/getByOrganization/{org}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProductsOf(@PathParam("org") String org) {
        return productDao.getProductsFrom(org);
    }
}
