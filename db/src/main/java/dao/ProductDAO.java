
package dao;

import entity.Product;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static generated.tables.Product.PRODUCT;


public final class ProductDAO extends DAO{

    public @NotNull Product get(int id) {
        var context = getContext();
        final var query = context.select(
                        PRODUCT.ID,
                        PRODUCT.NAME,
                        PRODUCT.ORGANIZATION,
                        PRODUCT.AMOUNT
                )
                .from(PRODUCT).where(PRODUCT.ID.eq(id));

        var rec = query.fetchOne();
        if (rec == null)
            throw new IllegalStateException("Product with id " + id + " not found");
        return new Product(rec.value1(), rec.value2(), rec.value3(), rec.value4());
    }

    public @NotNull List<Product> all() {
        final List<Product> result = new ArrayList<>();
        var context = getContext();
        final var query = context.select(
                        PRODUCT.ID,
                        PRODUCT.NAME,
                        PRODUCT.ORGANIZATION,
                        PRODUCT.AMOUNT
                )
                .from(PRODUCT);

        var records = query.fetch();
        for (var rec : records) {
            result.add(new Product(rec.value1(), rec.value2(), rec.value3(), rec.value4()));
        }
        return result;
    }

    public void save(@NotNull Product entity) {
        var context = getContext();
        context
                .insertInto(PRODUCT, PRODUCT.AMOUNT, PRODUCT.NAME, PRODUCT.ORGANIZATION)
                .values(entity.getAmount(), entity.getName(), entity.getOrgName())
                .execute();
    }

    public List<Product> getProductsFrom(@NotNull String organization) {
        final List<Product> result = new ArrayList<>();
        var context = getContext();
        var query = context
                .select(PRODUCT.AMOUNT, PRODUCT.NAME, PRODUCT.ORGANIZATION)
                .from(PRODUCT).where(PRODUCT.ORGANIZATION.eq(organization));

        var records = query.fetch();
        for (var rec : records) {
            result.add(new Product(rec.value2(), rec.value3(), rec.value1()));
        }
        return result;
    }

    public boolean deleteByName(@NotNull String name) {
        var context = getContext();
        final var query = context.select(
                        PRODUCT.ID
                )
                .from(PRODUCT).where(PRODUCT.NAME.eq(name));

        var records = query.fetch();
        if (records.size() == 0)
            return false;
        context
                .delete(PRODUCT)
                .where(PRODUCT.ID.in(records))
                .execute();
        return true;
    }
}

