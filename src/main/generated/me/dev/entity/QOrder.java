package me.dev.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = 277683250L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder order = new QOrder("order1");

    public final DateTimePath<java.sql.Timestamp> create_at = createDateTime("create_at", java.sql.Timestamp.class);

    public final StringPath customer = createString("customer");

    public final StringPath customerPhone = createString("customerPhone");

    public final StringPath delivery_address = createString("delivery_address");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<OrderMenu, QOrderMenu> orderMenus = this.<OrderMenu, QOrderMenu>createList("orderMenus", OrderMenu.class, QOrderMenu.class, PathInits.DIRECT2);

    public final EnumPath<me.dev.entity.enumerator.OrderStatus> orderStatus = createEnum("orderStatus", me.dev.entity.enumerator.OrderStatus.class);

    public final EnumPath<me.dev.entity.enumerator.OrderType> orderType = createEnum("orderType", me.dev.entity.enumerator.OrderType.class);

    public final StringPath payment_method = createString("payment_method");

    public final QStore store;

    public final NumberPath<Integer> totalPrice = createNumber("totalPrice", Integer.class);

    public final DateTimePath<java.sql.Timestamp> updated_at = createDateTime("updated_at", java.sql.Timestamp.class);

    public QOrder(String variable) {
        this(Order.class, forVariable(variable), INITS);
    }

    public QOrder(Path<? extends Order> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrder(PathMetadata metadata, PathInits inits) {
        this(Order.class, metadata, inits);
    }

    public QOrder(Class<? extends Order> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store"), inits.get("store")) : null;
    }

}

