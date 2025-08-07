package me.dev.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -545052057L;

    public static final QUser user = new QUser("user");

    public final StringPath area = createString("area");

    public final StringPath ceoName = createString("ceoName");

    public final DateTimePath<java.sql.Timestamp> created_at = createDateTime("created_at", java.sql.Timestamp.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath loginId = createString("loginId");

    public final StringPath nickname = createString("nickname");

    public final StringPath note = createString("note");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final StringPath refreshToken = createString("refreshToken");

    public final ListPath<Store, QStore> stores = this.<Store, QStore>createList("stores", Store.class, QStore.class, PathInits.DIRECT2);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

